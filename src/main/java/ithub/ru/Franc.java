package ithub.ru;

/*
 * @created 30/08/2021 - 16:16
 * @project IntelliJ IDEA
 * @author Temnyakov Nikolay
 */
public class Franc extends Money {

    public Franc(int amount, String currency) {
        super(amount, currency);
    }

    public Money times(int multiplier) {
        return franc(this.amount * multiplier);
    }
}
