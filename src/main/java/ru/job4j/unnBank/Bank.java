package ru.job4j.unnBank;

import java.util.*;

public class Bank {
    private Map<User, List<Account>> users = new HashMap<>();


    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account ac : accounts) {
                if (ac.getRequisite().equals(requisite)) {
                    return ac;
                }
            }

        }
        return null;
    }


    public boolean deleteByRequisite(String requisite, String passport) {
        boolean rsl = false;
        List<Account> accounts = users.get(findByPassport(passport));
        if (accounts != null) {
            for (Account ac : accounts) {
                if (ac.getRequisite().equals(requisite)) {
                    users.remove(ac);
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
