package id.dojo.pmjavafx.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Account {
    private final StringProperty namaAkun;
    private final StringProperty username;
    private final StringProperty website;
    private final StringProperty waktu;
    private final StringProperty password;


    public Account(String namaAkun, String username, String website, String waktu, String password) {
        this.namaAkun = new SimpleStringProperty(namaAkun);
        this.username = new SimpleStringProperty(username);
        this.website = new SimpleStringProperty(website);
        this.waktu = new SimpleStringProperty(waktu);
        this.password = new SimpleStringProperty(password);
    }

    public Account() {
        this(null, null, null, null, null);
    }

    public String getNamaAkun() {
        return namaAkun.get();
    }

    public StringProperty namaAkunProperty() {
        return namaAkun;
    }

    public void setNamaAkun(String namaAkun) {
        this.namaAkun.set(namaAkun);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getWebsite() {
        return website.get();
    }

    public StringProperty websiteProperty() {
        return website;
    }

    public void setWebsite(String website) {
        this.website.set(website);
    }

    public String getWaktu() {
        return waktu.get();
    }

    public StringProperty waktuProperty() {
        return waktu;
    }

    public void setWaktu(LocalDate waktu) {
        this.waktu.set(waktu.toString());
    }


    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }



}
