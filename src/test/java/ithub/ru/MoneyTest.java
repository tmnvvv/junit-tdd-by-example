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
 void testMultiplicationDollar() {
  Money five = dollar(5);
  assertEquals(dollar(10), five.times(2));
  assertEquals(dollar(15), five.times(3));
 }


 @Test
 void testEqualityDollar() {
  assertEquals(dollar(5), dollar(5));
  assertNotEquals(dollar(5), dollar(8));
 }



 @Test
 void testMultiplicationFranc() {
  Money five = franc(5);
  assertEquals(franc(10), five.times(2));
  assertEquals(franc(15), five.times(3));
 }


 @Test
 void testEqualityFranc() {
  assertEquals(franc(5), franc(5));
  assertNotEquals(franc(5), franc(8));
 }

 @Test
 void testCurrency() {
  assertEquals("USD", dollar(1).currency());
  assertEquals("CHF", franc(1).currency());
 }
}
