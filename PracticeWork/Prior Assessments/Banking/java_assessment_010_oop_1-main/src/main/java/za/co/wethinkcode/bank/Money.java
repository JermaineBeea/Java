package za.co.wethinkcode.bank;
import java.text.DecimalFormat;
import java.util.Objects;

public class Money implements Comparable<Money>
{
    public static final Money ZERO = new Money(0);
    private final long amountInCents;

    public Money(long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public Money subtract(Money other) {
        return new Money(this.amountInCents - other.amountInCents);

    }

    public Money add(Money other) {
        return new Money(this.amountInCents + other.amountInCents);
    }


    @Override
    public int compareTo(Money instance){
        return Long.compare(amountInCents, instance.amountInCents);
    }

    @Override
    public boolean equals(Object instance){
        if(instance == this) return true;
        if(instance == null || instance.getClass() != getClass()) return false;

        Money other = (Money) instance;
        return amountInCents == other.amountInCents;
    }

    @Override
    public int hashCode(){
        return Objects.hash(amountInCents);
    }

    public String formattedAsRands(){
        return new DecimalFormat("R0.00").format((double) amountInCents/100);
    }

    @Override
    public String toString(){
        return formattedAsRands();
    }

}
