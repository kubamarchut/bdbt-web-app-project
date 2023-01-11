package bdbt_projekt.SpringApplication;

public class Property {
   private int nr_nieruchomosci;
   private int metraz;
    private int liczba_pokoi;
    private int nr_ksiegi_wieczystej;
    private int liczba_pieter;
    private String stan_wykonczenia;
    private String miejsce_parkingowe;
    private String rynek;
    private int cena;
    private int nr_adresu;
    private int nr_biura;

 public Property(int nr_nieruchomosci, int metraz, int liczba_pokoi, int nr_ksiegi_wieczystej, int liczba_pieter, String stan_wykonczenia, String miejsce_parkingowe, String rynek, int cena, int nr_adresu, int nr_biura) {
  this.nr_nieruchomosci = nr_nieruchomosci;
  this.metraz = metraz;
  this.liczba_pokoi = liczba_pokoi;
  this.nr_ksiegi_wieczystej = nr_ksiegi_wieczystej;
  this.liczba_pieter = liczba_pieter;
  this.stan_wykonczenia = stan_wykonczenia;
  this.miejsce_parkingowe = miejsce_parkingowe;
  this.rynek = rynek;
  this.cena = cena;
  this.nr_adresu = nr_adresu;
  this.nr_biura = nr_biura;
 }

 public Property() {
 }

 public int getNr_nieruchomosci() {
  return nr_nieruchomosci;
 }

 public void setNr_nieruchomosci(int nr_nieruchomosci) {
  this.nr_nieruchomosci = nr_nieruchomosci;
 }

 public int getMetraz() {
  return metraz;
 }

 public void setMetraz(int metraz) {
  this.metraz = metraz;
 }

 public int getLiczba_pokoi() {
  return liczba_pokoi;
 }

 public void setLiczba_pokoi(int liczba_pokoi) {
  this.liczba_pokoi = liczba_pokoi;
 }

 public int getNr_ksiegi_wieczystej() {
  return nr_ksiegi_wieczystej;
 }

 public void setNr_ksiegi_wieczystej(int nr_ksiegi_wieczystej) {
  this.nr_ksiegi_wieczystej = nr_ksiegi_wieczystej;
 }

 public int getLiczba_pieter() {
  return liczba_pieter;
 }

 public void setLiczba_pieter(int liczba_pieter) {
  this.liczba_pieter = liczba_pieter;
 }

 public String getStan_wykonczenia() {
  return stan_wykonczenia;
 }

 public void setStan_wykonczenia(String stan_wykonczenia) {
  this.stan_wykonczenia = stan_wykonczenia;
 }

 public String getMiejsce_parkingowe() {
  return miejsce_parkingowe;
 }

 public void setMiejsce_parkingowe(String miejsce_parkingowe) {
  this.miejsce_parkingowe = miejsce_parkingowe;
 }

 public String getRynek() {
  return rynek;
 }

 public void setRynek(String rynek) {
  this.rynek = rynek;
 }

 public int getCena() {
  return cena;
 }

 public void setCena(int cena) {
  this.cena = cena;
 }

 public int getNr_adresu() {
  return nr_adresu;
 }

 public void setNr_adresu(int nr_adresu) {
  this.nr_adresu = nr_adresu;
 }

 public int getNr_biura() {
  return nr_biura;
 }

 public void setNr_biura(int nr_biura) {
  this.nr_biura = nr_biura;
 }

 @Override
 public String toString() {
  return "Property{" +
          "nr_nieruchomosci=" + nr_nieruchomosci +
          ", metraz=" + metraz +
          ", liczba_pokoi=" + liczba_pokoi +
          ", nr_ksiegi_wieczystej=" + nr_ksiegi_wieczystej +
          ", liczba_pieter=" + liczba_pieter +
          ", stan_wykonczenia='" + stan_wykonczenia + '\'' +
          ", miejsce_parkingowe='" + miejsce_parkingowe + '\'' +
          ", rynek='" + rynek + '\'' +
          ", cena=" + cena +
          ", nr_adresu=" + nr_adresu +
          ", nr_biura=" + nr_biura +
          '}';
 }
}
