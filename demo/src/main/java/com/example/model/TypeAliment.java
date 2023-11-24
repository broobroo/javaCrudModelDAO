package com.example.model;


public class TypeAliment {
    private int id;
    private String nom;

    //Contructeurs
    
    public TypeAliment(){
    }
    public TypeAliment(String nom){
        this.nom = nom;
    }
    public TypeAliment(int id, String nom){
        this.id = id;
        this.nom = nom;
    }
    




    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
