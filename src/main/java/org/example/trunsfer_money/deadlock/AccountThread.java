package org.example.trunsfer_money.deadlock;

import org.example.trunsfer_money.Account;

public class AccountThread implements Runnable {
    private final Account accountFrom;
    private final Account accountTo;
    private final int money;

    public AccountThread(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4_000; i++) {
            synchronized (accountFrom) {
                synchronized (accountTo) {
                    if (accountFrom.takeOffMoney(money)) {
                        accountTo.addMoney(money);
                    }
                    if (accountTo.takeOffMoney(money)) {
                        accountFrom.addMoney(money);
                    }
                }
            }
        }
    }
}
