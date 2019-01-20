package main;

import main.database.BankException;
import main.database.BankRepo;
import main.database.Repos;
import main.model.Account;
import main.model.Action;
import main.model.Owner;

import java.util.List;

public class Test {
    public static void main(String[] args) throws BankException {
        new Test().run();
    }

    private void run() throws BankException {
        BankRepo bankRepo = Repos.getInstance().getBankRepo();
        List<Owner> owners = bankRepo.getOwners();

//        System.out.println(owners.get(0));

//        System.out.println(bankRepo.getAccounts(owners.get(0)));
//
//        owners.get(0).addAllAccounts(bankRepo.getAccounts(owners.get(0)));

        Account account = bankRepo.getAccounts(owners.get(0)).get(1);
        Account account1 = bankRepo.getAccounts(owners.get(0)).get(2);
        System.out.println(account);
        System.out.println(account1);

        Action action = new Action(Action.ActionType.WITHDRAW, account, 100);
        Action action1 = new Action(Action.ActionType.WITHDRAW, account1, 200);
        Action action2 = new Action(Action.ActionType.WITHDRAW, account, 300);
        action.execute();
        action1.execute();
        action2.execute();

        System.out.println(account);
        System.out.println(account1);
    }
}
