# Test Driven Development
## Введение в тестирование

### Задание №1
Предположим, что у нас есть отчет.
Например:

| Компания | Число акций | Цена | Всего |
| :---: | :---: | :---: | :---: |
| IBM       | 1000  | 25 USD          | 25 000 USD   |
| Novartis  | 400   | 150 CHF         | 60 000 CHF   |
|           |       | __Итого:__          |   65 000 USD |

Курсы валют:

| Из | В | Курс |
| :---: | :---: | :---: |
| CHF  | USD | 1,5 |

Описание задачи:

5$ + 10 CHF = 10$, если курс обмена 2:1</br>
__5$ * 2 = 10$__

1) Выполнять сложение величин в двух различных валютах и конвертировать 
    результат с учетом указанного курса обмена;
2) Выполнять умножение величин в валюте (стоимость одной акции) на число
   (количество акций), результатом этой операции должна быть величина в
    вылюте.
3) Сделать переменную amount закрытым членом класса
4) Побочные эффекты(side effect) в классе Dollar
5) Округление денежных величин

Проблемы:</br>
>нет класса Dollar;</br>
>нет конструктора;</br>
>нет метода times(int); ```times - умножить на```</br>
>нет поля amount;


Полный цикл TDD:</br>
1) Добавить небольшой тест</br>
2) Запустить все тесты, при этом обнаружить, что что-то не срабатывает.</br>
3) Внести небольшое изменение.</br>
4) Снова запустить тесты и убедиться, что все они успешно выполняются.</br>
5) Устранить доблирование с помощью рефакторинга</br>

Пример:</br>

void testSimpleAddition() {</br>
  Money five = dollar(5);</br>
  Expression sum = five.plus(five);</br>
  Bank bank = new Bank();</br>
  Money reduced = bank.reduce(sum, "USD");</br>
  assertEquals(dollar(10), reduced);</br>
}</br>

__Чистый код, который работает. (Ron Jeffries)__




Задача №1</br>
Необходимо написать метод, который должен вернуть ближайшее к 10 число из входного массива чисел.</br>
Если найдены несколько чисел  равно удалённые к 10 то вернуть максимальное. Например, среди чисел 9 и 11 вернуть 11.</br>
Например, среди чисел 8 и 11 ближайшее к десяти 11.</br>

Пример тестовых данных:</br>
[1, 2, 3, 4] -> 4</br>
[10, 2, 3, 4] -> 10</br>
[30, -10] -> 30</br>
[7, 13] -> 13</br>

@param array массив целых чисел</br>
@return ближайшее к десяти число</br>
public int nearToTen(int[] arrays) {}</br>

Задача №2</br>

Найти наибольший общий префикс.</br>

Пример тестовых данных:</br>
["flower","flow","flight"] -> "fl"</br>
["dog","racecar","car"] -> ""</br>

Задача №3</br>
Поиск повторяющихся элементов в массиве</br>

Пример тестовых данных:</br>
[1,2,3,1] -> true</br>
[1,2,3,4] -> false</br>
[1,1,1,3,3,4,3,2,4,2] -> true</br>

Задача №4</br>

Input : 1, 2, 5, 4, 4, 5, 2, 3, 6, 5</br>
Output:</br>
Map<String, Integer></br>
<"1-hello": 1>,</br>
<"2-hello": 2>,</br>
<"3-hello": 1>,</br>
<"5-hello": 3></br>

Notes:</br>
1. В результирующей выборке нет 4, 6</br>
2. Ключ - преобразованный элемент стрима, значение - количество вхождений данного элемента в стриме</br>
3. Написать все одним чейном(без доп филдов, стейта)</br>


public static Map<String, Long> transform(final Integer... nums) {</br>
return</br>
}</br>


Задание Spring:
Требуется разработать приложение с использованием Spring Boot, которое должно хранить вопросы
и ответы в виде текстового файла (~ 8 вопросов).
Ответы могут быть как выбором из нескольких вариантов или пользователь может сам ввести ответ.
Приложение выводит заданный список вопросов и пользователь после ответов получает отчет
на сколько процентов его ответы верны.
Желательно для этой задачи написать Unit-тесты.	
	