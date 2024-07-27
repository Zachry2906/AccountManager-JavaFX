package id.dojo.pmjavafx.controllers;

import id.dojo.pmjavafx.MainApp;
import id.dojo.pmjavafx.models.Account;
import id.dojo.pmjavafx.models.History;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class InputKatasandiController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;

    private String kelas;
    private MainApp mainApp;
    private Stage stage;
    private Account account;
    private History history;

    public InputKatasandiController() {
    }

    public void setMainApp(MainApp mainApp, String kelas, Account account, History history) {
        this.mainApp = mainApp;
        this.history = history;
        this.kelas = kelas;
        this.account = account;
        System.out.println(kelas);
    }

    public void setInputKatasandiController(Stage stage) {
        this.stage = stage;
    }


    @FXML
    private void handleSubmit() {
        if (passwordField.getText().equals("a")) {
            if (kelas.equals("show")) {
                mainApp.ShowPassword();
            } else if (kelas.equals("edit")) {
                mainApp.showAccountDialog(account, history);
            }
        }
    }
}
