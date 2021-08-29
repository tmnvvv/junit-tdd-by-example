package ithub.ru;

/*
 * @created 29/08/2021 - 21:24
 * @project IntelliJ IDEA
 * @author Temnyakov Nikolay
 */
public class Dollar {
    int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    public void times(int multiplier) {
        this.amount *= multiplier;
    }
}
