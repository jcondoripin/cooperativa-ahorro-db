package com.unsa.cooperativa.entity;

public class Departamento {
  private int depCod;
  private String depNom;
  private String depActivo;

  public Departamento(int depCod, String depNom, String depActivo) {
    this.depCod = depCod;
    this.depNom = depNom;
    this.depActivo = depActivo;
  }

  // Getters and Setters
  public int getDepCod() {
    return depCod;
  }

  public void setDepCod(int depCod) {
    this.depCod = depCod;
  }

  public String getDepNom() {
    return depNom;
  }

  public void setDepNom(String depNom) {
    this.depNom = depNom;
  }

  public String getDepActivo() {
    return depActivo;
  }

  public void setDepActivo(String depActivo) {
    this.depActivo = depActivo;
  }
}