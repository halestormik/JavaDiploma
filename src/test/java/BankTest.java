import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqadiplom.Account;
import ru.netology.javaqadiplom.Bank;

public class BankTest {

    @Test
    public void shouldTransferMoney() { // проверка статуса операции при успешном переводе
        Bank bank = new Bank();
        Account accountFrom = new Account();
        Account accountTo = new Account();

        accountFrom.add(500); // баланс счета списания

        boolean expected = true;
        boolean actual = bank.transfer(accountFrom, accountTo, 200);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    // первый баг-репорт. Операция возвращает true в любом случае, так быть не должно
    public void shouldNotTransferMoney() { // проверка статуса операции при неуспешном переводе (сумма перевода больше баланса счета списания)
        Bank bank = new Bank();
        Account accountFrom = new Account();
        Account accountTo = new Account();

        accountFrom.add(500); // баланс счета списания

        boolean expected = false;
        boolean actual = bank.transfer(accountFrom, accountTo, 1200);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    // второй баг-репорт. Операция add без @Override не меняет баланс счета списания и всегда возвращает false
    public void shouldSeenBalanceFromAfterTransfer() { // проверка баланса, с которого перевели деньги
        Bank bank = new Bank();
        Account accountFrom = new Account();
        Account accountTo = new Account();

        accountFrom.add(500); // баланс счета списания
        bank.transfer(accountFrom, accountTo, 200);

        int expectedBalanceFrom = 300;
        int actualBalanceFrom = accountFrom.getBalance();

        Assertions.assertEquals(expectedBalanceFrom, actualBalanceFrom);
    }

    @Test
    // Третий баг-репорт. Операция pay без @Override не меняет баланс счета и всегда возвращает false
    public void shouldSeenBalanceToAfterTransfer() { // проверка баланса, на который перевели деньги
        Bank bank = new Bank();
        Account accountFrom = new Account();
        Account accountTo = new Account();

        accountFrom.add(500); // баланс счета списания
        accountTo.add(150); // баланс счета пополения
        bank.transfer(accountFrom, accountTo, 200);

        int expectedBalanceTo = 350;
        int actualBalanceTo = accountTo.getBalance();

        Assertions.assertEquals(expectedBalanceTo, actualBalanceTo);
    }

    @Test
    public void shouldNotTransferIfAmountIsNegative() { // проверка статуса операции при отрицательной сумме перевода
        Bank bank = new Bank();
        Account accountFrom = new Account();
        Account accountTo = new Account();

        accountFrom.add(500); // баланс счета списания

        boolean expected = false;
        boolean actual = bank.transfer(accountFrom, accountTo, -200);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotTransferIfAmountIsZero() { // проверка статуса операции при нулевой сумме перевода
        Bank bank = new Bank();
        Account accountFrom = new Account();
        Account accountTo = new Account();

        accountFrom.add(500); // баланс счета списания

        boolean expected = false;
        boolean actual = bank.transfer(accountFrom, accountTo, 0);

        Assertions.assertEquals(expected, actual);
    }
}
