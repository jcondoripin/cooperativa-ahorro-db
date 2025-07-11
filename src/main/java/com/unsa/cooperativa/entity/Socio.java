package com.unsa.cooperativa.entity;

import java.util.Date;

public class Socio {
  private int socCod;
  private String socIden;
  private String socApePat;
  private String socApeMat;
  private String socNom;
  private Date socFechaNacimiento;
  private String socCor;
  private String socTipPro;
  private String socCta;
  private int socCoo;
  private int disCod;
  private String socActivo;

  public Socio(int socCod, String socIden, String socApePat, String socApeMat, String socNom,
      Date socFechaNacimiento, String socCor, String socTipPro, String socCta,
      int socCoo, int disCod, String socActivo) {
    this.socCod = socCod;
    this.socIden = socIden;
    this.socApePat = socApePat;
    this.socApeMat = socApeMat;
    this.socNom = socNom;
    this.socFechaNacimiento = socFechaNacimiento;
    this.socCor = socCor;
    this.socTipPro = socTipPro;
    this.socCta = socCta;
    this.socCoo = socCoo;
    this.disCod = disCod;
    this.socActivo = socActivo;
  }

  // Getters and Setters
  public int getSocCod() {
    return socCod;
  }

  public void setSocCod(int socCod) {
    this.socCod = socCod;
  }

  public String getSocIden() {
    return socIden;
  }

  public void setSocIden(String socIden) {
    this.socIden = socIden;
  }

  public String getSocApePat() {
    return socApePat;
  }

  public void setSocApePat(String socApePat) {
    this.socApePat = socApePat;
  }

  public String getSocApeMat() {
    return socApeMat;
  }

  public void setSocApeMat(String socApeMat) {
    this.socApeMat = socApeMat;
  }

  public String getSocNom() {
    return socNom;
  }

  public void setSocNom(String socNom) {
    this.socNom = socNom;
  }

  public Date getSocFechaNacimiento() {
    return socFechaNacimiento;
  }

  public void setSocFechaNacimiento(Date socFechaNacimiento) {
    this.socFechaNacimiento = socFechaNacimiento;
  }

  public String getSocCor() {
    return socCor;
  }

  public void setSocCor(String socCor) {
    this.socCor = socCor;
  }

  public String getSocTipPro() {
    return socTipPro;
  }

  public void setSocTipPro(String socTipPro) {
    this.socTipPro = socTipPro;
  }

  public String getSocCta() {
    return socCta;
  }

  public void setSocCta(String socCta) {
    this.socCta = socCta;
  }

  public int getSocCoo() {
    return socCoo;
  }

  public void setSocCoo(int socCoo) {
    this.socCoo = socCoo;
  }

  public int getDisCod() {
    return disCod;
  }

  public void setDisCod(int disCod) {
    this.disCod = disCod;
  }

  public String getSocActivo() {
    return socActivo;
  }

  public void setSocActivo(String socActivo) {
    this.socActivo = socActivo;
  }
}