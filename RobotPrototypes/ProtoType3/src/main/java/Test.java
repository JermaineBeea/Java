import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class Test {
    
    public static void main(String[] args) {
        Client client1 = new Client(1, "John");
        Client client2 = new Client(2, "John");

        Clients.addClient(1, client1);
        Clients.addClient(2, client2);

        Client client3 = Clients.getClient(1);

        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client3);

        System.out.println(client1.equals(client3));

    }

    static class Client{

        private int ID;
        private String name;
        
        public Client(int ID, String name){
            this.ID = ID;
            this.name = name;
        }

        @Override
        public boolean equals(Object object){
            if(object == this) return true;
            if(object == null || object.getClass() != getClass()) return false;

            Client otherInstance = (Client) object;
            return otherInstance.ID == ID && Objects.equals(otherInstance.name, name);
        }

        @Override
        public int hashCode(){
            return Objects.hash(ID, name);
        }

        @Override
        public String toString() {
            return "Client " + ID + ": name[" + name + "]";
        }
    }

    static class Clients{

        private static Map<Integer, Client> setClients = Collections.synchronizedMap(new HashMap<>());

        public  static void addClient(int Id, Client instance){
            setClients.put(Id, instance);
        }

        public static Client getClient(int ID){
            return setClients.getOrDefault(ID, null);
        }
    }

}
