package za.co.wethinkcode.bank;

public class Money implements Comparable<Money>
{

    // TODO
    
    public int amountInCents2;
    
    public Money(int rands, int cents) {
    this.amountInCents2 = (rands * 100) + cents;
    }
    public Money(String amountInCents2) {
        String[] parts = amountInCents2.split("\\.");
        int rands = Integer.parseInt(parts[0]);
        int cents = (parts.length > 1) ? Integer.parseInt(parts[1]) : 0;
        this.amountInCents2 = rands * 100 + (rands < 0 ? -Math.abs(cents) : Math.abs(cents));
    }

    public Money(int amountInCents2) {
        this.amountInCents2 = amountInCents2;
    }


    public Money add(Money creditAmount) {
        return new Money(this.amountInCents2 + creditAmount.amountInCents2);
    }
    

    public Money subtract(Money debitAmount) {
        int results = this.amountInCents2 - debitAmount.amountInCents2;
        return new Money(Money.max(results, 0));
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return amountInCents2 == money.amountInCents2;
    }
    
        public String formattedAsRands() {
            int rands = amountInCents2 / 100;
            int cents = Math.abs(amountInCents2 % 100);
            return String.format("R%d.%02d", rands, cents);
        }

    public int hashCode() {
        return Integer.hashCode(amountInCents2);
    }

    @Override
    public int compareTo(Money other) {
        return Integer.compare(this.amountInCents2, other.amountInCents2);
    }
}
