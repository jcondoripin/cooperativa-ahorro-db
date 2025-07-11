package com.unsa.cooperativa.entity;

public class Cooperativa {
  private int cooCod;
  private String cooIden;
  private String cooNom;
  private String cooSig;
  private String cooDir;
  private String cooTel;
  private String cooCor;
  private String cooSlo;
  private String cooLog;
  private int cooUsu;
  private String cooActivo;

  public Cooperativa(int cooCod, String cooIden, String cooNom, String cooSig, String cooDir,
      String cooTel, String cooCor, String cooSlo, String cooLog, int cooUsu, String cooActivo) {
    this.cooCod = cooCod;
    this.cooIden = cooIden;
    this.cooNom = cooNom;
    this.cooSig = cooSig;
    this.cooDir = cooDir;
    this.cooTel = cooTel;
    this.cooCor = cooCor;
    this.cooSlo = cooSlo;
    this.cooLog = cooLog;
    this.cooUsu = cooUsu;
    this.cooActivo = cooActivo;
  }

  // Getters and Setters
  public int getCooCod() {
    return cooCod;
  }

  public void setCooCod(int cooCod) {
    this.cooCod = cooCod;
  }

  public String getCooIden() {
    return cooIden;
  }

  public void setCooIden(String cooIden) {
    this.cooIden = cooIden;
  }

  public String getCooNom() {
    return cooNom;
  }

  public void setCooNom(String cooNom) {
    this.cooNom = cooNom;
  }

  public String getCooSig() {
    return cooSig;
  }

  public void setCooSig(String cooSig) {
    this.cooSig = cooSig;
  }

  public String getCooDir() {
    return cooDir;
  }

  public void setCooDir(String cooDir) {
    this.cooDir = cooDir;
  }

  public String getCooTel() {
    return cooTel;
  }

  public void setCooTel(String cooTel) {
    this.cooTel = cooTel;
  }

  public String getCooCor() {
    return cooCor;
  }

  public void setCooCor(String cooCor) {
    this.cooCor = cooCor;
  }

  public String getCooSlo() {
    return cooSlo;
  }

  public void setCooSlo(String cooSlo) {
    this.cooSlo = cooSlo;
  }

  public String getCooLog() {
    return cooLog;
  }

  public void setCooLog(String cooLog) {
    this.cooLog = cooLog;
  }

  public int getCooUsu() {
    return cooUsu;
  }

  public void setCooUsu(int cooUsu) {
    this.cooUsu = cooUsu;
  }

  public String getCooActivo() {
    return cooActivo;
  }

  public void setCooActivo(String cooActivo) {
    this.cooActivo = cooActivo;
  }
}
