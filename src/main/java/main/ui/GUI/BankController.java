package main.ui.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import main.model.Account;
import main.database.BankException;
import main.model.Action;
import main.model.Owner;
import main.database.BankRepo;
import main.database.Repos;

import java.util.LinkedList;
import java.util.List;

public class BankController {
    private ObservableList<Owner> ownerObservableList;
    private ObservableList<Account> accountObservableList;
    private BankRepo repo = Repos.getInstance().getBankRepo();
    private Owner activeSourceOwner = null;
    private Owner activeTargetOwner = null;
    private Account activeSourceAccount = null;
    private Account activeTargetAccount = null;

    private List<Action> actions;

    @FXML
    public void initialize() throws BankException {
        List<Owner> owners = repo.getOwners();
        ownerObservableList = FXCollections.observableArrayList(owners);
        withdrawPerson.setItems(ownerObservableList);
        transferTargetPerson.setItems(ownerObservableList);
        transferSourcePerson.setItems(ownerObservableList);
        depositPerson.setItems(ownerObservableList);

        actions = new LinkedList<>();
    }

    @FXML
    private Tab withdraw;

    @FXML
    private Button withdrawConfirm;

    @FXML
    private ComboBox<Owner> withdrawPerson;

    @FXML
    private ComboBox<Account> withdrawAccount;

    @FXML
    private TextField withdrawAmount;

    @FXML
    private Tab transfer;

    @FXML
    private AnchorPane tranferLabel;

    @FXML
    private Tab deposit;

    @FXML
    private Button persistData;

    @FXML
    private Button transferConfirm;

    @FXML
    private ComboBox<Owner> transferSourcePerson;

    @FXML
    private ComboBox<Account> transferSourceAccount;

    @FXML
    private ComboBox<Owner> transferTargetPerson;

    @FXML
    private ComboBox<Account> transferTargetAccount;

    @FXML
    private TextField tranferAmount;


    @FXML
    private TextField depositAmount;

    @FXML
    private Button depositConfirm;

    @FXML
    private ComboBox<Owner> depositPerson;

    @FXML
    private ComboBox<Account> depositAccount;


    @FXML
    void withdrawPersonAction(ActionEvent event) throws BankException {
        activeSourceOwner = withdrawPerson.getSelectionModel().getSelectedItem();
        accountObservableList = FXCollections.observableArrayList(repo.getAccounts(activeSourceOwner));
        withdrawAccount.setItems(accountObservableList);
    }

    @FXML
    void withdrawAccountAction(ActionEvent event) {
        activeSourceAccount = withdrawAccount.getSelectionModel().getSelectedItem();
    }

    @FXML
    void withdrawConfirmAction(ActionEvent event) {
        double amount = Double.parseDouble(withdrawAmount.getText());

        actions.add(new Action(Action.ActionType.WITHDRAW, activeSourceAccount, amount));

        // reset ?
        activeSourceOwner = null;
        activeSourceAccount = null;
    }


    // transfer
    // Source
    @FXML
    void transferSourcePersonAction(ActionEvent event) throws BankException {
        activeSourceOwner = transferSourcePerson.getSelectionModel().getSelectedItem();
        accountObservableList = FXCollections.observableArrayList(repo.getAccounts(activeSourceOwner));
        transferSourceAccount.setItems(accountObservableList);
    }

    @FXML
    void transferSourceAccountAction(ActionEvent event) {
        activeSourceAccount = transferSourceAccount.getSelectionModel().getSelectedItem();
    }

    // target
    @FXML
    void transferTargetPersonAction(ActionEvent event) throws BankException {
        activeTargetOwner = transferTargetPerson.getSelectionModel().getSelectedItem();
        accountObservableList = FXCollections.observableArrayList(repo.getAccounts(activeTargetOwner));
        transferTargetAccount.setItems(accountObservableList);
    }

    @FXML
    void transferTargetAccountAction(ActionEvent event) {
        activeTargetAccount = transferTargetAccount.getSelectionModel().getSelectedItem();

    }

    @FXML
    void transferConfirmAction(ActionEvent event) {
        double amount = Double.parseDouble(tranferAmount.getText());
        actions.add(new Action(Action.ActionType.WITHDRAW, activeSourceAccount, amount));
        actions.add(new Action(Action.ActionType.DEPOSIT, activeTargetAccount, amount));

        // reset ?
        activeTargetOwner = null;
        activeTargetAccount = null;
        activeSourceOwner = null;
        activeSourceAccount = null;
    }


    //deposit
    @FXML
    void depositPersonAction(ActionEvent event) throws BankException {
        activeTargetOwner = depositPerson.getSelectionModel().getSelectedItem();
        accountObservableList = FXCollections.observableArrayList(repo.getAccounts(activeTargetOwner));
        depositAccount.setItems(accountObservableList);
    }

    @FXML
    void depositAccountAction(ActionEvent event) {
        activeTargetAccount = depositAccount.getSelectionModel().getSelectedItem();
    }

    @FXML
    void depositConfirmAction(ActionEvent event) {
        double amount = Double.parseDouble(depositAmount.getText());
        actions.add(new Action(Action.ActionType.DEPOSIT, activeTargetAccount, amount));

        // reset ?
        activeTargetOwner = null;
        activeTargetAccount = null;

    }



    // databank updaten!
    @FXML
    void persistDataAction(ActionEvent event) {
        // TODO: 20/01/2019 rekeningen gaan onder 0

        System.out.println(actions);
        actions.forEach(Action::execute);
        actions = new LinkedList<>();
    }

}
