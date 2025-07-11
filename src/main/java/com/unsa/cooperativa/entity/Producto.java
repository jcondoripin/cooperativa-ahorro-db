package com.unsa.cooperativa.entity;

public class Producto {
  private int proCod;
  private String proIden;
  private String proDes;
  private int tipSocioCod;
  private double proMon;
  private String proActivo;

  public Producto(int proCod, String proIden, String proDes, int tipSocioCod, double proMon, String proActivo) {
    this.proCod = proCod;
    this.proIden = proIden;
    this.proDes = proDes;
    this.tipSocioCod = tipSocioCod;
    this.proMon = proMon;
    this.proActivo = proActivo;
  }

  // Getters and Setters
  public int getProCod() {
    return proCod;
  }

  public void setProCod(int proCod) {
    this.proCod = proCod;
  }

  public String getProIden() {
    return proIden;
  }

  public void setProIden(String proIden) {
    this.proIden = proIden;
  }

  public String getProDes() {
    return proDes;
  }

  public void setProDes(String proDes) {
    this.proDes = proDes;
  }

  public int getTipSocioCod() {
    return tipSocioCod;
  }

  public void setTipSocioCod(int tipSocioCod) {
    this.tipSocioCod = tipSocioCod;
  }

  public double getProMon() {
    return proMon;
  }

  public void setProMon(double proMon) {
    this.proMon = proMon;
  }

  public String getProActivo() {
    return proActivo;
  }

  public void setProActivo(String proActivo) {
    this.proActivo = proActivo;
  }
}