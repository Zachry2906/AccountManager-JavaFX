package id.dojo.pmjavafx.controllers;


import id.dojo.pmjavafx.MainApp;
import id.dojo.pmjavafx.models.Account;
import id.dojo.pmjavafx.models.AccountData;
import id.dojo.pmjavafx.models.History;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class FirstPageController {
    @FXML
    private TableView<Account> accountTable;
    @FXML
    private TableColumn<Account, String> websiteColumn;
    @FXML
    private Label namaAkun;
    @FXML
    private Label username;
    @FXML
    private Label website;
    @FXML
    private Label waktu;

    private MainApp mainApp;


    public FirstPageController() {
    }


    private void showAccountDetails(Account account) {
        if (account != null) {
            namaAkun.setText(account.getNamaAkun());
            username.setText(account.getUsername());
            website.setText(account.getWebsite());
            waktu.setText(account.getWaktu().toString());
        } else {
            namaAkun.setText("");
            username.setText("");
            website.setText("");
            waktu.setText("");
        }
    }

    @FXML
    private void initialize() {
        websiteColumn.setCellValueFactory(cellData -> cellData.getValue().websiteProperty());
        showAccountDetails(null);
        accountTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showAccountDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        accountTable.setItems(mainApp.getAccountData());
    }

    @FXML
    private void handleNewAccount() {
        Account tempAccount = new Account();
        History tempHistory = new History(" ", " ", " ", 1, " ");
        boolean okClicked = mainApp.showAccountDialog(tempAccount, tempHistory);
        if (okClicked) {
            mainApp.getAccountData().add(tempAccount);
            mainApp.getHistoryData().add(tempHistory);
        }
    }

    @FXML
    private void handleEditAccount() {
        Account selectedAccount = accountTable.getSelectionModel().getSelectedItem();
        History history = new History(" ", " ", " ", 1, " ");
        System.out.println(selectedAccount);
        if (selectedAccount != null) {
            mainApp.showInputKatasandi(selectedAccount, "edit", history);
            showAccountDetails(selectedAccount);
        } else {
            // Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Account Selected");
            alert.setContentText("Please select an account in the table.");
            alert.setHeight(400);
            alert.setWidth(600);
            alert.showAndWait();

        }
    }

    @FXML
    private void handleDeleteAccount() {
        int selectedIndex = accountTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            accountTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Account Selected");
            alert.setContentText("Please select an account in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleShowPassword() {
        Account selectedAccount = accountTable.getSelectionModel().getSelectedItem();
        History history = new History(" ", " ", " ", 1, " ");
        if (selectedAccount != null) {
            mainApp.showInputKatasandi(selectedAccount,  "show", history);
        } else {
            // Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Account Selected");
            alert.setContentText("Please select an account in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleHistory() {
        mainApp.ShowHistory();
    }


}
