package za.co.wethinkcode.bank;

public class Money implements Comparable<Money>
{
    public static final Float ZERO = null;
    // public static final Money ZERO = new Money( 0 );
    private final long amountInCents;

    public Money(long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public Money subtract(Money other) {
        return new Money(this.amountInCents);
    }

    public Money add(Money other) {
        return new Money(this.amountInCents);
    }

    public String formattedAsRands() {
        long rands = amountInCents / 100;
        long cents = Math.abs(amountInCents % 100);
        return String.format("R%d.%02d", rands, cents);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return amountInCents == money.amountInCents;
    }

    public int hashCode() {
        return Long.hashCode(amountInCents);
    }

    @Override
    public int compareTo(Money arg0) {
        return Long.compare(this.amountInCents, arg0.amountInCents);
    }
}
