package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() { // проверка пополнения счета
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        boolean expected = true;
        boolean actual = account.add(4_500);

        Assertions.assertEquals(5_000 + 4_500, account.getBalance()); // проверка итогового баланса
        Assertions.assertEquals(expected, actual); // проверка статуса, который возвращает операция
    }
    @Test
    public void shouldAddAboveMaxLimit(){ // проверка пополнения счет на сумму выше максимального баланса
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.add(7_000);

        Assertions.assertEquals(5_000, account.getBalance());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldAddMaxLimit(){  // проверка пополнения счета до суммы максимального баланса
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.add(5_000);

        Assertions.assertEquals(5_000 + 5_000, account.getBalance());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldAddZero(){   // проверка пополнения счета на нулевую сумму
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.add(0);

        Assertions.assertEquals(5_000, account.getBalance());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldPay(){   //проверка покупки со счета
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.pay(2_500);

        Assertions.assertEquals(2_500, account.getBalance()); // проверка итогового баланса
        Assertions.assertEquals(expected, actual); // проверка статуса, который возвращает операция
    }
//    @Test
//    public void shouldPayBelowBalance(){  // проверка покупки со счета на сумму выше баланса
//        SavingAccount account = new SavingAccount(
//                5_000,
//                1_000,
//                10_000,
//                5
//        );
//        account.pay(6_000);
//        Assertions.assertEquals(5_000, account.getBalance());
//    }
    @Test
    public void shouldPayBelowMinLimit(){   // проверка покупки со счета до суммы меньше минимального баланса
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.pay(4_500);

        Assertions.assertEquals(5_000, account.getBalance()); // проверка итогового баланса
        Assertions.assertEquals(expected, actual); // проверка статуса, который возвращает операция
    }
    @Test
    public void shouldPayMinLimit(){   // проверка покупки со счета на сумму до минимального баланса
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.pay(4_000);

        Assertions.assertEquals(1_000, account.getBalance()); // проверка итогового баланса
        Assertions.assertEquals(expected, actual); // проверка статуса, который возвращает операция
    }
    @Test
    public void shouldZeroPay(){
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        boolean expected = true;
        boolean actual = account.pay(0);

        Assertions.assertEquals(5_000, account.getBalance()); // проверка итогового баланса
        Assertions.assertEquals(expected, actual); // проверка статуса, который возвращает операция
    }

    @Test
    public void shouldRateYear(){   // проверка начислении процентов на положительную сумму
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        Assertions.assertEquals(250, account.yearChange());
    }
    @Test
    public void shouldZeroRateYear(){   // проверка начисления процентов по 0 ставке
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                0
        );
        Assertions.assertEquals(0, account.yearChange());
    }
    @Test
    public void shouldNegativeRate(){   // проверка исключения при отрицательной ставке
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(5_000, 1_000, 10_000, -10));
    }
    @Test
    public void shouldAddRateZeroBalance(){  // проверка начисления процентов при 0 балансе
        SavingAccount account = new SavingAccount(
                0,
                1_000,
                10_000,
                5
        );
        Assertions.assertEquals(0, account.yearChange());
    }
    @Test
    public void shouldSavingAccountWithNegativeInitialBalance(){ // проверка исключения при отрицательном начальном балансе
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount( -200, 1_000,10_000,5));
    }

    @Test
    public void shouldSavingAccountWithNegativeMinBalance(){ // проверка исключения при отрицательном минимальном балансе
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(1_000, -2_000,10_000,5));
    }

    @Test
    public void shouldSavingAccountWithNegativeMaxBalance(){ // проверка исключения при отрицательном максимальном балансе
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(1_000, 2_000,-10_000,5));
    }

    @Test
    public void shouldSavingAccountIfMaxBalanceBelowMinBalance(){ // проверка исключения, если макс баланс меньше мин баланса
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(1_000, 2_000,1_000,5));
    }

    @Test
    public void shouldSavingAccountIfAddAmountBelowZero(){ // проверка операции пополнения на отрицательную сумму
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.add(-7_000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSavingAccountIfPayAmountBelowZero(){ // проверка операции покупки на отрицательную сумму
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        boolean expected = false;
        boolean actual = account.pay(-7_000);

        Assertions.assertEquals(expected, actual);
    }
}

