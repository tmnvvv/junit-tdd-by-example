package ithub.ru;

/*
 * @created 30/08/2021 - 16:24
 * @project IntelliJ IDEA
 * @author Temnyakov Nikolay
 */
public abstract class Money {
    protected int amount;

    public abstract Money times(int multiplier);

    public static Dollar dollar(int amount) {
        return new Dollar(amount);
    }

    public static Franc franc(int amount) {
        return new Franc(amount);
    }

    public boolean equals(Object object) {
        Money money = Money.class.cast(object);
        return this.amount == money.amount && getClass().equals(object.getClass());
    }
}
