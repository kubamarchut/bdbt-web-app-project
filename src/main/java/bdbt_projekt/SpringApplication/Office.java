package bdbt_projekt.SpringApplication;

import java.util.Date;

public class Office {
    private int nr_biura;
    private String nazwa;
    private Date data_zalozenia;
    private String nr_telefonu;
    private String aderes_email;
    private int procent_z_prowizji;
    private int nr_adresu;

    public int getNr_biura() {
        return nr_biura;
    }

    public void setNr_biura(int nr_biura) {
        this.nr_biura = nr_biura;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Date getData_zalozenia() {
        return data_zalozenia;
    }

    public void setData_zalozenia(Date data_zalozenia) {
        this.data_zalozenia = data_zalozenia;
    }

    public String getNr_telefonu() {
        return nr_telefonu;
    }

    public void setNr_telefonu(String nr_telefonu) {
        this.nr_telefonu = nr_telefonu;
    }

    public String getAderes_email() {
        return aderes_email;
    }

    public void setAderes_email(String aderes_email) {
        this.aderes_email = aderes_email;
    }

    public int getProcent_z_prowizji() {
        return procent_z_prowizji;
    }

    public void setProcent_z_prowizji(int procent_z_prowizji) {
        this.procent_z_prowizji = procent_z_prowizji;
    }

    public int getNr_adresu() {
        return nr_adresu;
    }

    public void setNr_adresu(int nr_adresu) {
        this.nr_adresu = nr_adresu;
    }
    public Office(){

    }

    public Office(int nr_biura, String nazwa, Date data_zalozenia, String nr_telefonu, String aderes_email, int procent_z_prowizji, int nr_adresu) {
        this.nr_biura = nr_biura;
        this.nazwa = nazwa;
        this.data_zalozenia = data_zalozenia;
        this.nr_telefonu = nr_telefonu;
        this.aderes_email = aderes_email;
        this.procent_z_prowizji = procent_z_prowizji;
        this.nr_adresu = nr_adresu;
    }
}
