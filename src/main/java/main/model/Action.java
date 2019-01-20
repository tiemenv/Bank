package main.model;

import main.database.BankException;
import main.database.Repos;

public class Action {

    private ActionType actionType;
    private Account account;
    private double amount;

    public Action(ActionType actionType, Account account, double amount) {
        this.actionType = actionType;
        this.account = account;
        this.amount = amount;
    }


    public void execute() {
        System.out.printf("executing %s from  %s amount: %f\n", actionType, account.getAccountNr(), amount);
        try {
            switch (actionType) {
                case DEPOSIT:
                    Repos.getInstance().getBankRepo().updateAccount(account, amount);
                    break;
                case WITHDRAW:
                    Repos.getInstance().getBankRepo().updateAccount(account, -amount);
                    break;
            }
        } catch (BankException e) {
            System.err.println("Something went wrong with");
        }

    }

    public enum ActionType {
        WITHDRAW,
        DEPOSIT
    }

    @Override
    public String toString() {
        return "Action{" +
                "actionType=" + actionType +
                ", account=" + account +
                ", amount=" + amount +
                '}';
    }
}
