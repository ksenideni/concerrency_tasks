package org.example.trunsfer_money.safe_transfer;

import org.example.trunsfer_money.Account;

public class AccountThread implements Runnable {
    private final Account accountFrom;
    private final Account accountTo;
    private final int money;
    private static final Object lock = new Object();

    public AccountThread(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4_000; i++) {
            synchronized (lock) {
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
