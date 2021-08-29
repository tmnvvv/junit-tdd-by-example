package ithub.ru;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * @created 29/08/2021 - 21:18
 * @project IntelliJ IDEA
 * @author Temnyakov Nikolay
 */
public class MoneyTest {

 @Test
 void testMultiplication() {
  Dollar five = new Dollar(5);
  five.times(2);
  assertEquals(10, five.amount);
 }

}
