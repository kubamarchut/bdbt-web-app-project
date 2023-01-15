package bdbt_projekt.SpringApplication;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.OneToOne;
import java.util.Date;

public class Employee {
    private int nr_pracownika;
    private String imie;
    private String nazwisko;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_urodzenia;
    private String plec;
    private String pesel;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_zatrudnienia;
    private String nr_konta;
    private String nr_telefonu;
    private String adres_email;
    private int nr_biura;
    private int nr_adresu;
    private Position stanowisko;
    private Office biuro;
    private int nr_stanowiska;

    public int getNr_pracownika() {
        return nr_pracownika;
    }

    public void setNr_pracownika(int nr_pracownika) {
        this.nr_pracownika = nr_pracownika;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(Date data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getData_zatrudnienia() {
        return data_zatrudnienia;
    }

    public void setData_zatrudnienia(Date data_zatrudnienia) {
        this.data_zatrudnienia = data_zatrudnienia;
    }

    public String getNr_konta() {
        return nr_konta;
    }

    public void setNr_konta(String nr_konta) {
        this.nr_konta = nr_konta;
    }

    public String getNr_telefonu() {
        return nr_telefonu;
    }

    public void setNr_telefonu(String nr_telefonu) {
        this.nr_telefonu = nr_telefonu;
    }

    public String getAdres_email() {
        return adres_email;
    }

    public void setAdres_email(String adres_email) {
        this.adres_email = adres_email;
    }

    public int getNr_adresu() {
        return nr_adresu;
    }

    public void setNr_adresu(int nr_adresu) {
        this.nr_adresu = nr_adresu;
    }
    public void setStanowisko(Position stanowisko) {
        this.stanowisko = stanowisko;
        this.nr_stanowiska = stanowisko.getNr_stanowiska();
    }
    public Position getStanowisko() {
        return stanowisko;
    }
    public int getNr_stanowiska() {
        return stanowisko.getNr_stanowiska();
    }
    public void setBiuro(Office biuro) {
        this.biuro = biuro;
        this.nr_biura = biuro.getNr_biura();
    }
    public Office getBiuro() {
        return this.biuro;
    }
    public int getNr_biura() {
        return this.biuro.getNr_biura();
    }




    public Employee(){
    }

    public Employee(int nr_pracownika, String imie, String nazwisko, Date data_urodzenia, String plec, String pesel, Date data_zatrudnienia, String nr_konta, String nr_telefonu, String adres_email, Office biuro, int nr_adresu, Position stanowisko) {
        this.nr_pracownika = nr_pracownika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_urodzenia = data_urodzenia;
        this.plec = plec;
        this.pesel = pesel;
        this.data_zatrudnienia = data_zatrudnienia;
        this.nr_konta = nr_konta;
        this.nr_telefonu = nr_telefonu;
        this.adres_email = adres_email;
        this.biuro = biuro;
        this.nr_biura = biuro.getNr_biura();
        this.nr_adresu = nr_adresu;
        this.stanowisko = stanowisko;
        this.nr_stanowiska = stanowisko.getNr_stanowiska();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "nr_pracownika=" + nr_pracownika +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", data_urodzenia=" + data_urodzenia +
                ", plec='" + plec + '\'' +
                ", pesel='" + pesel + '\'' +
                ", data_zatrudnienia=" + data_zatrudnienia +
                ", nr_konta='" + nr_konta + '\'' +
                ", nr_telefonu='" + nr_telefonu + '\'' +
                ", adres_email='" + adres_email + '\'' +
                ", nr_biura=" + nr_biura +
                ", nr_adresu=" + nr_adresu +
                ", stanowisko=" + stanowisko +
                '}';
    }
}
