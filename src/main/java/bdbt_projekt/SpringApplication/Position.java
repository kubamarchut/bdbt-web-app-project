package bdbt_projekt.SpringApplication;

public class Position {
    private int nr_stanowiska;
    private String nazwa_stanowiska;
    private String opis;

    public int getNr_stanowiska() {
        return nr_stanowiska;
    }

    public void setNr_stanowiska(int nr_stanowiska) {
        this.nr_stanowiska = nr_stanowiska;
    }

    public String getNazwa_stanowiska() {
        return nazwa_stanowiska;
    }

    public void setNazwa_stanowiska(String nazwa_stanowiska) {
        this.nazwa_stanowiska = nazwa_stanowiska;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Position(){

    }
    public Position(int nr_stanowiska, String nazwa_stanowiska, String opis) {
        this.nr_stanowiska = nr_stanowiska;
        this.nazwa_stanowiska = nazwa_stanowiska;
        this.opis = opis;
    }
}
