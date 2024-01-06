import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqadiplom.Account;
import ru.netology.javaqadiplom.CreditAccount;

public class CreditAccountTest {

   /* @Test
    public void shouldCreateCreditAccountWithNegativeRate() { // проверка выпадения исключения при отрицательной ставке

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CreditAccount(500, 1200, -20));
    }

    @Test
    // первый баг-репорт. условие в операции покупки реализовано некорректно
    public void shouldPaySuccessfull() { // проверка оплаты без выхода за пределы лимита
        Account myCreditAccount = new CreditAccount(500, 300, 15);

        myCreditAccount.pay(700);

        int expectedBalance = -200; // ожидаемый баланс: 500 - 700 = -200
        int actualBalance = myCreditAccount.getBalance(); // Фактический баланс -700

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    // второй баг-репорт. условие в операции покупки реализовано некорректно
    public void shouldPayUnSuccessfull() { // проверка оплаты с выходом за пределы лимита
        Account myCreditAccount = new CreditAccount(500, 300, 15);

        myCreditAccount.pay(1700);

        int expectedBalance = 500; // ожидаемый баланс: 500, так как после покупки выйдем за пределы лимита
        int actualBalance = myCreditAccount.getBalance(); // Фактический баланс -1200

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    // девятый баг-репорт. При итоговом балансе после покупки равном кредитному лимиту операция возвращает false
    public void shouldPaySuccessfullAndExpectedBalanceEqualsLimit() { // итоговый баланс равен лимиту
        Account myCreditAccount = new CreditAccount(500, 300, 15);

        //myCreditAccount.pay(800);
        boolean expected = true; // ожидаем, что операция завершится true
        boolean actual = myCreditAccount.pay(800); // операция завершается false

        int expectedBalance = -300; // ожидаемый баланс: 500 - 80  = -300
        int actualBalance = myCreditAccount.getBalance(); // Фактический баланс -300

        Assertions.assertEquals(expectedBalance, actualBalance);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPaySuccessfullIfZeroAmount() { // проверка нулевой оплаты
        Account myCreditAccount = new CreditAccount(500, 300, 15);

        myCreditAccount.pay(0);

        int expectedBalance = 500; // ожидаемый баланс: 500, так как сумма покупки нулевая
        int actualBalance = myCreditAccount.getBalance(); // Фактический баланс 500

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void shouldPaySuccessfullIfNegativeAmount() { // проверка отрицательной оплаты
        Account myCreditAccount = new CreditAccount(500, 300, 15);

        myCreditAccount.pay(-100);

        int expectedBalance = 500; // ожидаемый баланс: 500, так как сумма покупки отрицательная
        int actualBalance = myCreditAccount.getBalance(); // Фактический баланс 500

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    // третий баг-репорт. операция пополнения реализована некорректно
    public void shouldAddSuccessful() { // операция успешного пополнения счета
        Account myCreditAccount = new CreditAccount(500, 300, 15);

        myCreditAccount.add(1000);

        int expectedBalance = 1500; // ожидаемый баланс: 1500
        int actualBalance = myCreditAccount.getBalance(); // Фактический баланс 1000

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    // четвертый баг-репорт. Сумма пополнения может быть нулевой, операция должна возвращать true
    public void shouldAddSuccessfulIfZeroAmount() { // операция успешного пополнения счета на нулевую сумму
        Account myCreditAccount = new CreditAccount(500, 300, 15);

        boolean expected = true;
        boolean actual = myCreditAccount.add(0);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddSuccessfulIfNegativeAmount() { // операция неуспешного пополнения счета на отрицательную сумму
        Account myCreditAccount = new CreditAccount(500, 300, 15);

        myCreditAccount.add(-200);

        int expectedBalance = 500; // ожидаемый баланс: 500
        int actualBalance = myCreditAccount.getBalance(); // Фактический баланс 500

        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    // пятый баг-репорт. При положительном балансе проценты не должны начисляться
    public void shouldNotAddPercentsIfBalanceIsPositive() { // баланс положительный, проценты не должны начисляться
        Account myCreditAccount = new CreditAccount(200, 300, 15);

        int expectedPercent = 0; // ожидаемый процент нулевой
        int actualPercent = myCreditAccount.yearChange(); // рассчитанный процент 30

        Assertions.assertEquals(expectedPercent, actualPercent);
    }

    @Test
    public void shouldNotAddPercentsIfBalanceIsZero() { // баланс нулевой, проценты не должны начисляться
        Account myCreditAccount = new CreditAccount(0, 300, 15);

        int expectedPercent = 0; // ожидаемый процент нулевой
        int actualPercent = myCreditAccount.yearChange(); // рассчитанный процент 0

        Assertions.assertEquals(expectedPercent, actualPercent);
    }

    @Test
    public void shouldNotAddPercentsIfBalanceIsNegative() { // баланс отрицательный, проценты начисляются по ставке
        Account myCreditAccount = new CreditAccount(-200, 300, 15);

        int expectedPercent = -30; // ожидаемый процент нулевой
        int actualPercent = myCreditAccount.yearChange(); // рассчитанный процент -30

        Assertions.assertEquals(expectedPercent, actualPercent);
    }

    @Test
    // шестой баг-репорт. выпадение исключения при нулевой ставке (по условию ставка должна быть неотрицательной)
    public void shouldCreateCreditAccountWithZeroRate() { // проверка выпадения исключения при нулевой ставке
        Account myCreditAccount = new CreditAccount(500, 300, 0);
    }

    @Test
    // седьмой баг-репорт. выпадение исключения при отрицательном начальном балансе (по условию начальный баланс должен быть неотрицательным)
    public void shouldCreateCreditAccountWithZeroInitialBalance() { // проверка выпадения исключения при отрицательном начальном балансе
       Assertions.assertThrows(IllegalArgumentException.class,
               () -> new CreditAccount(-500, 300, 15));
    }

    @Test
    // восьмой баг-репорт. выпадение исключения при отрицательном кредитном лимите (по условию кредитный лимит должен быть неотрицательным)
    public void shouldCreateCreditAccountWithZeroCreditLimit() { // проверка выпадения исключения при отрицательном кредитном лимите
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new CreditAccount(500, -300, 15));
    }*/
}
