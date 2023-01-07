package bdbt_projekt.SpringApplication;

public class Language {
    private int nr_jezyki;
    private String kod_jezyka;
    private String nazwa_jezyka;

    public int getNr_jezyki() {
        return nr_jezyki;
    }

    public void setNr_jezyki(int nr_jezyki) {
        this.nr_jezyki = nr_jezyki;
    }

    public String getKod_jezyka() {
        return kod_jezyka;
    }

    public void setKod_jezyka(String kod_jezyka) {
        this.kod_jezyka = kod_jezyka;
    }

    public String getNazwa_jezyka() {
        return nazwa_jezyka;
    }

    public void setNazwa_jezyka(String nazwa_jezyka) {
        this.nazwa_jezyka = nazwa_jezyka;
    }

    public Language(){
    }
    public Language(int nr_jezyki, String kod_jezyka, String nazwa_jezyka) {
        this.nr_jezyki = nr_jezyki;
        this.kod_jezyka = kod_jezyka;
        this.nazwa_jezyka = nazwa_jezyka;
    }

    @Override
    public String toString() {
        return "Language{" +
                "nr_jezyki=" + nr_jezyki +
                ", kod_jezyka='" + kod_jezyka + '\'' +
                ", nazwa_jezyka='" + nazwa_jezyka + '\'' +
                '}';
    }
}
