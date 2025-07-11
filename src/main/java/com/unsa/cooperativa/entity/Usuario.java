package com.unsa.cooperativa.entity;

public class Usuario {
    private int usuCod;
    private String usuIde;
    private String usuUsu;
    private String usuPas;
    private String usuEmp;
    private String usuActivo;

    public Usuario(int usuCod, String usuIde, String usuUsu, String usuPas, String usuEmp, String usuActivo) {
        this.usuCod = usuCod;
        this.usuIde = usuIde;
        this.usuUsu = usuUsu;
        this.usuPas = usuPas;
        this.usuEmp = usuEmp;
        this.usuActivo = usuActivo;
    }

    // Getters and Setters
    public int getUsuCod() {
        return usuCod;
    }

    public void setUsuCod(int usuCod) {
        this.usuCod = usuCod;
    }

    public String getUsuIde() {
        return usuIde;
    }

    public void setUsuIde(String usuIde) {
        this.usuIde = usuIde;
    }

    public String getUsuUsu() {
        return usuUsu;
    }

    public void setUsuUsu(String usuUsu) {
        this.usuUsu = usuUsu;
    }

    public String getUsuPas() {
        return usuPas;
    }

    public void setUsuPas(String usuPas) {
        this.usuPas = usuPas;
    }

    public String getUsuEmp() {
        return usuEmp;
    }

    public void setUsuEmp(String usuEmp) {
        this.usuEmp = usuEmp;
    }

    public String getUsuActivo() {
        return usuActivo;
    }

    public void setUsuActivo(String usuActivo) {
        this.usuActivo = usuActivo;
    }
}