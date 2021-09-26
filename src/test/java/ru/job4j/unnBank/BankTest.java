package ru.job4j.unnBank;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Test;

public class BankTest {

    @Test
    public void addUser() {
        User user = new User("3434", "Petr Arsentev");
        Bank bank = new Bank();
        bank.addUser(user);
        assertThat(bank.findByPassport("3434"), is(user));
    }

    @Test
    public void whenEnterInvalidPassport() {
        User user = new User("3434", "Petr Arsentev");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertNull(bank.findByRequisite("34", "5546"));
    }

    @Test
    public void addAccount() {
        User user = new User("3434", "Petr Arsentev");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertThat(bank.findByRequisite("3434", "5546").getBalance(), is(150D));
    }

    @Test
    public void transferMoney() {
        User user = new User("3434", "Petr Arsentev");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        bank.addAccount(user.getPassport(), new Account("113", 50D));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150D);
        assertThat(bank.findByRequisite(user.getPassport(), "113").getBalance(), is(200D));
    }

    @Test
    public void deleteAccountByRequisite() {
        User user = new User ("3434", "Petr Atsentev");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150D));
        assertThat(bank.deleteByRequisite("5546", "3434"),is(true));
    }

    @Test
    public void converter1() {
        assertThat(Bank.rubleToEuro(170), is (2));
    }

    @Test
    public void converter2() {
        assertThat(Bank.rubleToEuro(8500), is (100));
    }

    @Test
    public void converter3() {
        assertThat(Bank.rubleToDollar(730), is (10));
    }

    @Test
    public void converter4() {
        assertThat(Bank.rubleToDollar(73), is (1));
    }

    @Test
    public void balance() {
        User user = new User("3434", "Petr Arsentev");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546",50000));
        bank.addAccount(user.getPassport(), new Account("113", 50000));
        bank.checkBalance("5546","3434");
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 15000);
        bank.checkBalance("5546","3434");
        bank.checkBalance("113","3434");
    }


}