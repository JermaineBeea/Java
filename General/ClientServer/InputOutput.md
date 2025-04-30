You're right that `.println()` is primarily for sending string data. For sending other data types in a Java client-server model, you'll need different methods. Here are the appropriate techniques:

## For Primitive Data Types

Use `DataOutputStream` and `DataInputStream`, which have specialized methods for each primitive type:

```java
// On the server side (sending)
DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
dataOut.writeInt(123);       // send an integer
dataOut.writeDouble(45.67);  // send a double
dataOut.writeBoolean(true);  // send a boolean
dataOut.flush();

// On the client side (receiving)
DataInputStream dataIn = new DataInputStream(socket.getInputStream());
int number = dataIn.readInt();
double decimal = dataIn.readDouble();
boolean flag = dataIn.readBoolean();
```

## For Objects and Complex Data

1. **Using Object Serialization**:
```java
// Sending objects
ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
MyClass myObject = new MyClass(); // Must implement Serializable
objectOut.writeObject(myObject);
objectOut.flush();

// Receiving objects
ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
MyClass receivedObject = (MyClass) objectIn.readObject();
```

2. **Raw Bytes for Binary Data** (like files or images):
```java
// Sending binary data
OutputStream out = socket.getOutputStream();
byte[] data = getYourBinaryData(); // Your byte array
out.write(data, 0, data.length);
out.flush();

// Receiving binary data
InputStream in = socket.getInputStream();
byte[] buffer = new byte[8192]; // Buffer size
int bytesRead;
while ((bytesRead = in.read(buffer)) != -1) {
    // Process bytes in buffer
}
```

The method you choose depends on exactly what type of data you need to transfer and your specific requirements for performance, compatibility, and ease of implementation.