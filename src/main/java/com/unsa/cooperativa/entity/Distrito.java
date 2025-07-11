package com.unsa.cooperativa.entity;

public class Distrito {
  private int disCod;
  private String disNom;
  private int proCod;
  private String disActivo;

  public Distrito(int disCod, String disNom, int proCod, String disActivo) {
    this.disCod = disCod;
    this.disNom = disNom;
    this.proCod = proCod;
    this.disActivo = disActivo;
  }

  // Getters and Setters
  public int getDisCod() {
    return disCod;
  }

  public void setDisCod(int disCod) {
    this.disCod = disCod;
  }

  public String getDisNom() {
    return disNom;
  }

  public void setDisNom(String disNom) {
    this.disNom = disNom;
  }

  public int getProCod() {
    return proCod;
  }

  public void setProCod(int proCod) {
    this.proCod = proCod;
  }

  public String getDisActivo() {
    return disActivo;
  }

  public void setDisActivo(String disActivo) {
    this.disActivo = disActivo;
  }
}