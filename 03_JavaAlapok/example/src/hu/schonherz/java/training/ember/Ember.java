package hu.schonherz.java.training.ember;


public class Ember {

  public enum SzemSzin {BARNA, KEK, ZOLD, SZURKE}
  
  private String nev;
  private SzemSzin szemeSzine=SzemSzin.BARNA;
  private static int szam=6;
  
  static {
    szam = 9;
  }
  
  {
    nev = "Zita";
  }
  
  public class Pc {
    String CPU;
  }
  
  public Ember() {
    super();
  }

  public Ember(String nev, SzemSzin szemeSzine) {
    super();
    this.nev = nev;
    this.szemeSzine = szemeSzine;
  }

  
  public final String getNev() {
    return nev;
  }

  
  public final void setNev(String nev) {
    this.nev = nev;
  }

  
  public final SzemSzin getSzemeSzine() {
    return szemeSzine;
  }

  
  public final void setSzemeSzine(SzemSzin szemeSzine) {
    this.szemeSzine = szemeSzine;
  }

  public static int getSzam() {
    return szam;
  }

  public static void setSzam(int szam) {
    Ember.szam = szam;
  }
  
  
  
}
