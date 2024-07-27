package id.dojo.pmjavafx.controllers;

import id.dojo.pmjavafx.models.Account;
import id.dojo.pmjavafx.models.History;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AccountDialogController {
    @FXML
    private TextField NamaAkun;
    @FXML
    private TextField Username;
    @FXML
    private TextField Website;
    @FXML
    private TextField password;

    private String namaAkun;
    private String username;
    private String passSebelum;
    private Integer indexGanti;
    private String waktu;

    private Account account;
    private Stage stage;
    private boolean okClicked = false;
    private History history;

    @FXML
    private void initialize() {
    }

    public void setHistory(History history){
        this.history = history;
    }

    public void setAccount(Account account) {
        this.account = account;

        NamaAkun.setText(account.getNamaAkun());
        Username.setText(account.getUsername());
        Website.setText(account.getWebsite());
        password.setText(account.getPassword());

        namaAkun = account.getNamaAkun();
        username = account.getUsername();
        passSebelum = account.getPassword();
        indexGanti = 0;
        waktu = LocalDate.now().toString();
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }   

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            if (history == null) {
                history = new History();
            } else {
                history.setNamaAkun(namaAkun);
                history.setUsername(username);
                history.setPassSebelum(passSebelum);
                history.setIndexGanti(indexGanti);
                history.setWaktu(waktu);
            }


            account.setNamaAkun(NamaAkun.getText());
            account.setUsername(Username.getText());
            account.setWebsite(Website.getText());
            account.setWaktu(LocalDate.now());
            account.setPassword(password.getText());

            if (history.getNamaAkun() == null) {
                history.setNamaAkun(account.getNamaAkun());
                history.setUsername(account.getUsername());
                history.setPassSebelum(account.getPassword());
                history.setIndexGanti(0);
                history.setWaktu(LocalDate.now().toString());
            }

            System.out.println("Nama Akun sebelumnya :  " + history.getNamaAkun());
            System.out.println("Username sebelumnya :  " + history.getUsername());
            System.out.println("Password sebelumnya :  " + history.getPassSebelum());
            System.out.println("Index ganti :  " + history.getIndexGanti());
            System.out.println("Waktu :  " + history.getWaktu());

            okClicked = true;
            stage.close();
        }
    }

    @FXML
    private void handleCancel() {
        stage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (NamaAkun.getText() == null || NamaAkun.getText().length() == 0) {
            errorMessage += "No valid account name!\n";
        }
        if (Username.getText() == null || Username.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        }
        if (Website.getText() == null || Website.getText().length() == 0) {
            errorMessage += "No valid website!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
}
