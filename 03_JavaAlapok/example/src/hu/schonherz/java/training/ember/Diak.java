package hu.schonherz.java.training.ember;

import hu.schonherz.java.training.interfaces.AdoFizeto;
import hu.schonherz.java.training.interfaces.TarsadalomBiztositott;


public class Diak extends Ember implements AdoFizeto, TarsadalomBiztositott {
  
  private String neptunKod;
  private int tbSzam;
  private int adoSzam;  

  public Diak(String nev, SzemSzin szemeSzine, String neptunKod, int tbSzam, int adoSzam) {
    super(nev, szemeSzine);
    this.neptunKod = neptunKod;
    this.tbSzam = tbSzam;
    this.adoSzam = adoSzam;
  }

  @Override
  public int getTBSzam() {
    return tbSzam;
  }

  @Override
  public void setTBSzam(int tbSzam) {
    this.tbSzam = tbSzam;
  }

  @Override
  public int getAdoSzam() {
    return adoSzam;
  }

  @Override
  public void setAdoSzam(int adoSzam) {
    this.adoSzam = adoSzam;
  }

}
