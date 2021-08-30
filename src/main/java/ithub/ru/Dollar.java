package ithub.ru;

/*
 * @created 29/08/2021 - 21:24
 * @project IntelliJ IDEA
 * @author Temnyakov Nikolay
 */
public class Dollar extends Money {

    public Dollar(int amount) {
        this.amount = amount;
    }

    public Money times(int multiplier) {
        return new Dollar(this.amount * multiplier);
    }

}
