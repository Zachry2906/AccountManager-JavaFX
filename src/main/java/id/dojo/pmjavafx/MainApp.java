package id.dojo.pmjavafx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import id.dojo.pmjavafx.controllers.*;
import id.dojo.pmjavafx.helper.VigeneCipher;
import id.dojo.pmjavafx.models.Account;
import id.dojo.pmjavafx.models.History;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import id.dojo.pmjavafx.controllers.AccountDialogController;
import id.dojo.pmjavafx.controllers.InputKatasandiController;
import id.dojo.pmjavafx.controllers.RootLayoutController;
import id.dojo.pmjavafx.controllers.ShowPasswordController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Account> accountData = FXCollections.observableArrayList();
    private ObservableList<History> historyData = FXCollections.observableArrayList();
    private Account account;
    private History history;




	/**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
    public ObservableList<Account> getAccountData() {
        return accountData;
    }

    public ObservableList<History> getHistoryData() {
        return historyData;
    }



    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Password Manager");
        initRootLayout();

        showFirstPage();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/root-layout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showFirstPage() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/first-page.fxml"));
            AnchorPane FirstPage = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(FirstPage);
        
            // Give the controller access to the main app.
            FirstPageController controller = loader.getController();
            System.out.println(controller);
            controller.setMainApp(this);
        } catch (IOException e) {
            System.err.println("Error loading first-page.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean showAccountDialog(Account account, History history) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/account-dialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Account");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AccountDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAccount(account);
            System.out.println("shiw account dialog: " + history);
            controller.setHistory(history);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showInputKatasandi(Account account, String kelas, History history) {
        try {
            this.account = account;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/input-katasandi.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Input Kata Sandi");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            InputKatasandiController controller = loader.getController();
            controller.setMainApp(this, kelas, account, history);
            controller.setInputKatasandiController(dialogStage);


            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ShowPassword() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/show-password.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Show Password");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ShowPasswordController controller = loader.getController();
            controller.setDialogPassword(dialogStage);
            controller.setAccount(account);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ShowHistory() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/history-view.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();

            dialogStage.setTitle("Show History");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            HistoryViewController controller = loader.getController();
            controller.setMainApp2(this);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


public File getAccountFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    String filePath = prefs.get("accountFilePath", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

public File getHistoryFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    String filePath = prefs.get("historyFilePath", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setAccountFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("accountFilePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("accountFilePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }

    public void setHistoryFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("historyFilePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("historyFilePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }

public void handleLoad(File file) {
    try {
        List<Account> loadedAccounts = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            // Asumsikan setiap baris berisi data satu akun
            // Format: username,password,email,dll (sesuaikan dengan struktur Account Anda)
            String[] data = line.split(",");
            Account account = new Account(data[0], data[1], data[2], data[3], VigeneCipher.decrypt(data[4],"ZACHRY")) ; // Sesuaikan dengan konstruktor Account
            loadedAccounts.add(account);
        }
        reader.close();

        accountData.clear();
        accountData.addAll(loadedAccounts);

        // Simpan path file ke registry
        setAccountFilePath(file);

    } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());

        alert.showAndWait();
    }
}

    public void handleLoadHistory(File file) {
        try {
            List<History> loadedHistory = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                // Asumsikan setiap baris berisi data satu akun
                // Format: username,password,email,dll (sesuaikan dengan struktur Account Anda)
                String[] data = line.split(",");
                History history = new History(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4]); // Sesuaikan dengan konstruktor history
                loadedHistory.add(history);
            }
            reader.close();

            historyData.clear();
            historyData.addAll(loadedHistory);

            // Simpan path file ke registry
            setHistoryFilePath(file);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    
    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     */
    public void handleSave(File file, File file2) {
        try {
            System.out.println("file: " + file);
            System.out.println("file2: " + file2);
            System.out.println("accountData: " + accountData);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Account account : accountData) {
                // Tulis setiap account ke file
                // Format: username,password,email,dll
                writer.write(String.join(",", account.getNamaAkun(), account.getUsername(), account.getWebsite(), account.getWaktu().toString(), VigeneCipher.encrypt(account.getPassword(), "ZACHRY")));
                writer.newLine();
            }
            writer.close();


            BufferedWriter writer2 = new BufferedWriter(new FileWriter(file2));
            System.out.println("historyData: " + historyData);
            for (History history : historyData) {
                // Tulis setiap account ke file
                // Format: username,password,email,dll
                writer2.write(String.join(",", history.getNamaAkun(), history.getUsername(), history.getPassSebelum(), history.getIndexGanti().toString(), history.getWaktu().toString()));
                writer2.newLine();
            }
            writer2.close();
    
            // Simpan path file ke registry
            setAccountFilePath(file);
            setHistoryFilePath(file2);
    
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
    
            alert.showAndWait();
        }
    }

	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}