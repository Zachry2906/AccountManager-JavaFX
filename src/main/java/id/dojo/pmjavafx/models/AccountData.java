package id.dojo.pmjavafx.models;

import java.io.Serializable;
import java.time.LocalDate;

public class AccountData implements Serializable {
    private String namaAkun;
    private String username;
    private String website;
    private String password;
    private LocalDate waktu;

    public AccountData(String namaAkun, String username, String website, String password) {
        this.namaAkun = namaAkun;
        this.username = username;
        this.website = website;
        this.password = password;
        this.waktu = LocalDate.now();
    }

    public AccountData(){
        
    }

    public String getNamaAkun() {
        return namaAkun;
    }

    public void setNamaAkun(String namaAkun) {
        this.namaAkun = namaAkun;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getWaktu() {
        return waktu;
    }

    public void setWaktu(LocalDate waktu) {
        this.waktu = waktu;
    }
}