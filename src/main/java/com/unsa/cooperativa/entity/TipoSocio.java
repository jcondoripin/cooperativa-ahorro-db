package com.unsa.cooperativa.entity;

public class TipoSocio {
  private int tipSocioCod;
  private String tipSocioNom;
  private String tipActivo;

  public TipoSocio(int tipSocioCod, String tipSocioNom, String tipActivo) {
    this.tipSocioCod = tipSocioCod;
    this.tipSocioNom = tipSocioNom;
    this.tipActivo = tipActivo;
  }

  // Getters and Setters
  public int getTipSocioCod() {
    return tipSocioCod;
  }

  public void setTipSocioCod(int tipSocioCod) {
    this.tipSocioCod = tipSocioCod;
  }

  public String getTipSocioNom() {
    return tipSocioNom;
  }

  public void setTipSocioNom(String tipSocioNom) {
    this.tipSocioNom = tipSocioNom;
  }

  public String getTipActivo() {
    return tipActivo;
  }

  public void setTipActivo(String tipActivo) {
    this.tipActivo = tipActivo;
  }
}