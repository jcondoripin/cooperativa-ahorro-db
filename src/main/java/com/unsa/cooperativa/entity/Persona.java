package com.unsa.cooperativa.entity;

import java.util.Date;

public class Persona {
  private int perCod;
  private String perIden;
  private String perApePat;
  private String perApeMat;
  private String perNom;
  private Date perFechaNacimiento;
  private String perCor;
  private String perFot;
  private int perCoo;
  private String perActivo;

  public Persona(int perCod, String perIden, String perApePat, String perApeMat, String perNom,
      Date perFechaNacimiento, String perCor, String perFot, int perCoo, String perActivo) {
    this.perCod = perCod;
    this.perIden = perIden;
    this.perApePat = perApePat;
    this.perApeMat = perApeMat;
    this.perNom = perNom;
    this.perFechaNacimiento = perFechaNacimiento;
    this.perCor = perCor;
    this.perFot = perFot;
    this.perCoo = perCoo;
    this.perActivo = perActivo;
  }

  // Getters and Setters
  public int getPerCod() {
    return perCod;
  }

  public void setPerCod(int perCod) {
    this.perCod = perCod;
  }

  public String getPerIden() {
    return perIden;
  }

  public void setPerIden(String perIden) {
    this.perIden = perIden;
  }

  public String getPerApePat() {
    return perApePat;
  }

  public void setPerApePat(String perApePat) {
    this.perApePat = perApePat;
  }

  public String getPerApeMat() {
    return perApeMat;
  }

  public void setPerApeMat(String perApeMat) {
    this.perApeMat = perApeMat;
  }

  public String getPerNom() {
    return perNom;
  }

  public void setPerNom(String perNom) {
    this.perNom = perNom;
  }

  public Date getPerFechaNacimiento() {
    return perFechaNacimiento;
  }

  public void setPerFechaNacimiento(Date perFechaNacimiento) {
    this.perFechaNacimiento = perFechaNacimiento;
  }

  public String getPerCor() {
    return perCor;
  }

  public void setPerCor(String perCor) {
    this.perCor = perCor;
  }

  public String getPerFot() {
    return perFot;
  }

  public void setPerFot(String perFot) {
    this.perFot = perFot;
  }

  public int getPerCoo() {
    return perCoo;
  }

  public void setPerCoo(int perCoo) {
    this.perCoo = perCoo;
  }

  public String getPerActivo() {
    return perActivo;
  }

  public void setPerActivo(String perActivo) {
    this.perActivo = perActivo;
  }
}