package com.unsa.cooperativa.entity;

import java.util.Date;

public class Tasa {
  private int tasCodigo;
  private String tasIden;
  private String tasDesc;
  private double tasTasa;
  private int tasPlazoDias;
  private Date tasFechaInicio;
  private Date tasFechaFin;
  private String tasActivo;

  public Tasa(int tasCodigo, String tasIden, String tasDesc, double tasTasa, int tasPlazoDias,
      Date tasFechaInicio, Date tasFechaFin, String tasActivo) {
    this.tasCodigo = tasCodigo;
    this.tasIden = tasIden;
    this.tasDesc = tasDesc;
    this.tasTasa = tasTasa;
    this.tasPlazoDias = tasPlazoDias;
    this.tasFechaInicio = tasFechaInicio;
    this.tasFechaFin = tasFechaFin;
    this.tasActivo = tasActivo;
  }

  // Getters and Setters
  public int getTasCodigo() {
    return tasCodigo;
  }

  public void setTasCodigo(int tasCodigo) {
    this.tasCodigo = tasCodigo;
  }

  public String getTasIden() {
    return tasIden;
  }

  public void setTasIden(String tasIden) {
    this.tasIden = tasIden;
  }

  public String getTasDesc() {
    return tasDesc;
  }

  public void setTasDesc(String tasDesc) {
    this.tasDesc = tasDesc;
  }

  public double getTasTasa() {
    return tasTasa;
  }

  public void setTasTasa(double tasTasa) {
    this.tasTasa = tasTasa;
  }

  public int getTasPlazoDias() {
    return tasPlazoDias;
  }

  public void setTasPlazoDias(int tasPlazoDias) {
    this.tasPlazoDias = tasPlazoDias;
  }

  public Date getTasFechaInicio() {
    return tasFechaInicio;
  }

  public void setTasFechaInicio(Date tasFechaInicio) {
    this.tasFechaInicio = tasFechaInicio;
  }

  public Date getTasFechaFin() {
    return tasFechaFin;
  }

  public void setTasFechaFin(Date tasFechaFin) {
    this.tasFechaFin = tasFechaFin;
  }

  public String getTasActivo() {
    return tasActivo;
  }

  public void setTasActivo(String tasActivo) {
    this.tasActivo = tasActivo;
  }
}