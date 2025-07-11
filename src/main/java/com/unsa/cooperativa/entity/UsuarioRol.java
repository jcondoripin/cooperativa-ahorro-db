package com.unsa.cooperativa.entity;

public class UsuarioRol {
  private int usuCod;
  private int rolCod;

  public UsuarioRol(int usuCod, int rolCod) {
    this.usuCod = usuCod;
    this.rolCod = rolCod;
  }

  // Getters and Setters
  public int getUsuCod() {
    return usuCod;
  }

  public void setUsuCod(int usuCod) {
    this.usuCod = usuCod;
  }

  public int getRolCod() {
    return rolCod;
  }

  public void setRolCod(int rolCod) {
    this.rolCod = rolCod;
  }
}