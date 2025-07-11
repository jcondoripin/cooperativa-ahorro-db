package com.unsa.cooperativa.entity;

public class Provincia {
  private int proCod;
  private String proNom;
  private int depCod;
  private String proActivo;

  public Provincia(int proCod, String proNom, int depCod, String proActivo) {
    this.proCod = proCod;
    this.proNom = proNom;
    this.depCod = depCod;
    this.proActivo = proActivo;
  }

  // Getters and Setters
  public int getProCod() {
    return proCod;
  }

  public void setProCod(int proCod) {
    this.proCod = proCod;
  }

  public String getProNom() {
    return proNom;
  }

  public void setProNom(String proNom) {
    this.proNom = proNom;
  }

  public int getDepCod() {
    return depCod;
  }

  public void setDepCod(int depCod) {
    this.depCod = depCod;
  }

  public String getProActivo() {
    return proActivo;
  }

  public void setProActivo(String proActivo) {
    this.proActivo = proActivo;
  }
}