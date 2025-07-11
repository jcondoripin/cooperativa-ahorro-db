package com.unsa.cooperativa.entity;

public class Rol {
  private int rolCod;
  private String rolRol;
  private String rolNom;
  private String rolActivo;

  public Rol(int rolCod, String rolRol, String rolNom, String rolActivo) {
    this.rolCod = rolCod;
    this.rolRol = rolRol;
    this.rolNom = rolNom;
    this.rolActivo = rolActivo;
  }

  // Getters and Setters
  public int getRolCod() {
    return rolCod;
  }

  public void setRolCod(int rolCod) {
    this.rolCod = rolCod;
  }

  public String getRolRol() {
    return rolRol;
  }

  public void setRolRol(String rolRol) {
    this.rolRol = rolRol;
  }

  public String getRolNom() {
    return rolNom;
  }

  public void setRolNom(String rolNom) {
    this.rolNom = rolNom;
  }

  public String getRolActivo() {
    return rolActivo;
  }

  public void setRolActivo(String rolActivo) {
    this.rolActivo = rolActivo;
  }
}