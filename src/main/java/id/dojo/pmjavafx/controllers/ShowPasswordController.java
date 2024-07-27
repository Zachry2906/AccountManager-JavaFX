package id.dojo.pmjavafx.controllers;

import id.dojo.pmjavafx.models.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ShowPasswordController {
    @FXML
    private Label passwordLabel;

    private Account account;
    private Stage stage;

    public ShowPasswordController() {
    }

    public void setDialogPassword(Stage stage) {
        this.stage = stage;
    }

    public  void setAccount(Account account) {
        this.account = account;
        passwordLabel.setText(account.getPassword());
    }

    
}
