package ithub.ru;

/*
 * @created 30/08/2021 - 16:16
 * @project IntelliJ IDEA
 * @author Temnyakov Nikolay
 */
public class Franc extends Money {

    public Franc(int amount) {
        this.amount = amount;
    }

    public Money times(int multiplier) {
        return new Franc(this.amount * multiplier);
    }
}
