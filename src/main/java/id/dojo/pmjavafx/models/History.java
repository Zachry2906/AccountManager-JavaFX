package id.dojo.pmjavafx.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class History {
    private StringProperty namaAkun;
    private StringProperty username;
    private StringProperty passSebelum;
    private IntegerProperty indexGanti;
    private StringProperty waktu;

    public History(String namaAkun, String username, String passSebelum, Integer indexGanti, String waktu) {
        this.namaAkun = new SimpleStringProperty(namaAkun);
        this.username = new SimpleStringProperty(username);
        this.passSebelum = new SimpleStringProperty(passSebelum);
        this.indexGanti = new SimpleIntegerProperty(indexGanti);
        this.waktu = new SimpleStringProperty(waktu);
    }

    public History() {
        this(null, null, null, 0, null);
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

    public String getPassSebelum() {
        return passSebelum.get();
    }

    public StringProperty passSebelumProperty() {
        return passSebelum;
    }

    public void setPassSebelum(String passSebelum) {
        this.passSebelum.set(passSebelum);
    }

    public Integer getIndexGanti() {
        return indexGanti.get();
    }

    public IntegerProperty indexGantiProperty() {
        return indexGanti;
    }

    public void setIndexGanti(Integer indexGanti) {
        this.indexGanti.set(indexGanti);
    }

    public String getWaktu() {
        return waktu.get();
    }

    public StringProperty waktuProperty() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu.set(waktu);
    }

}
