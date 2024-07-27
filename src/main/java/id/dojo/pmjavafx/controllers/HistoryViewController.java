package id.dojo.pmjavafx.controllers;



import id.dojo.pmjavafx.MainApp;
import id.dojo.pmjavafx.models.Account;
import id.dojo.pmjavafx.models.History;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HistoryViewController {
    @FXML
    private TableView<History> table;
    @FXML
    private TableColumn<History, String> website;
    @FXML
    private Label namaakun;
    @FXML
    private Label username;
    @FXML
    private Label lastpassword;
    @FXML
    private Label index;
    @FXML
    private Label waktu;

    private MainApp mainAppp;

    public void setMainApp2(MainApp mainAppp) {
        this.mainAppp = mainAppp;
        table.setItems(mainAppp.getHistoryData());
    }


    @FXML
    private void initialize() {
        website.setCellValueFactory(cellData -> cellData.getValue().namaAkunProperty());
        showHistoryDetails(null);
        table.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showHistoryDetails(newValue));
    }

        private void showHistoryDetails(History history) {
        if (history != null) {
            namaakun.setText(history.getNamaAkun());
            username.setText(history.getUsername());
            lastpassword.setText(history.getPassSebelum());
            index.setText(history.getIndexGanti().toString());
            waktu.setText(history.getWaktu().toString());
        } else {
            namaakun.setText("");
            username.setText("");
            lastpassword.setText("");
            index.setText("");
            waktu.setText("");
        }
    }
}
