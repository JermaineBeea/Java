package za.co.wethinkcode.bank;

public class Money implements Comparable<Money>
{
    public static final Money ZERO = new Money( 0 );
    public long mny;
    Money amount;

    public Money(long i) {
        mny = i;
    }

    public Money add(Money money) {
//        mny += money;
        return money;
    }

    public Money subtract(Money money) {
        return money;
    }

    public String formattedAsRands() {
        return amount.toString();
    }

    @Override
    public int compareTo(Money o) {
        return 0;
    }
}
