package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() { // проверка пополнения счета
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }
    @Test
    public void shouldAddAboveMaxLimit(){ // проверка пополнения счет на сумму выше максимального баланса
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        account.add(7_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }
    @Test
    public void shouldAddMaxLimit(){  // проверка пополнения счета до суммы максимального баланса
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        account.add(5_000);
        Assertions.assertEquals(5_000 + 5_000, account.getBalance());
    }
    @Test
    public void shouldAddZero(){   // проверка пополнения счета на нулевую сумму
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        account.add(0);
        Assertions.assertEquals(5_000, account.getBalance());
    }
    @Test
    public void shouldPay(){   //проверка покупки со счета
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        account.pay(2_500);
        Assertions.assertEquals(2_500, account.getBalance());
    }
    @Test
    public void shouldPayBelowBalance(){  // проверка покупки со счета на сумму выше баланса
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        account.pay(6_000);
        Assertions.assertEquals(5_000, account.getBalance());
    }
    @Test
    public void shouldPayBelowMinLimit(){   // проверка покупки со счета до суммы меньше минимального баланса
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        account.pay(4_500);
        Assertions.assertEquals(5_000, account.getMinBalance());
    }
    @Test
    public void shouldPayMinLimit(){   // проверка покупки со счета на сумму до минимального баланса
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        account.pay(4_000);
        Assertions.assertEquals(1_000,account.getMinBalance());
    }
    @Test
    public void shouldZeroPay(){
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );
        account.pay(0);
        Assertions.assertEquals(5_000,account.getBalance());
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
    public void shouldSavingAccountWithNegativeInitialBalance(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount( -200, 1_000,10_000,5));
    }

    @Test
    public void shouldSavingAccountWithNegativeMinBalance(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(1_000, -2_000,10_000,5));
    }
}

