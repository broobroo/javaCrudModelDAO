package com.example.model;


public class Couleur {
    private String nom;
    private String hexadecimal_rvb;



    public Couleur(String nom, String hexadecimal_rvb) {
        this.nom = nom;
        this.hexadecimal_rvb = hexadecimal_rvb;
    }
    
    // Getters et setters
    public String getNom() {
        return nom;
    }
    public void setNom(String nomCouleur) {
        this.nom = nomCouleur;
    }

    public String getHexadecimal_rvb() {
        return hexadecimal_rvb;
    }

    public void setHexadecimal_rvb(String hexadecimal_rvb) {
        this.hexadecimal_rvb = hexadecimal_rvb;
    }

}
