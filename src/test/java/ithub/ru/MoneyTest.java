package ithub.ru;

import org.junit.jupiter.api.Test;

import static ithub.ru.Money.dollar;
import static ithub.ru.Money.franc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
 * @created 29/08/2021 - 21:18
 * @project IntelliJ IDEA
 * @author Temnyakov Nikolay
 */
public class MoneyTest {

 @Test
 void testMultiplication() {
  Money five = dollar(5);
  assertEquals(dollar(10), five.times(2));
  assertEquals(dollar(15), five.times(3));

  Money fiveF = franc(5);
  assertEquals(franc(10), fiveF.times(2));
 }


 @Test
 void testEquality() {
  assertEquals(dollar(5), dollar(5));
  assertNotEquals(dollar(5), dollar(8));
  assertEquals(franc(5), franc(5));
  assertNotEquals(dollar(5), franc(5));
 }


 @Test
 void testCurrency() {
  assertEquals("USD", dollar(1).currency());
  assertEquals("CHF", franc(1).currency());
 }

 @Test
 void testSimpleAddition() {
   Money five = dollar(5);
   Expression sum = five.plus(five);
   Bank bank = new Bank();
   Money reduced = bank.reduce(sum, "USD");
   assertEquals(dollar(10), reduced);
 }
}
