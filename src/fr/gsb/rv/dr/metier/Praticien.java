/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.metier;

import java.time.LocalDate;

/**
 *
 * @author developpeur
 */
public class Praticien {
    
    private int numero ;
    private String nom ;
    private String prenom ;
    private double coefNotoriete ;
    private LocalDate dateDerniereVisite ;
    private int dernierCoefConfiance ;
    private String ville ;

    public Praticien() {
    }

    public Praticien(int numero, String nom, String prenom, double coefNotoriete, LocalDate dateDerniereVisite, int dernierCoefConfiance) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    public Praticien(int numero, String nom, String prenom, double coefNotoriete, LocalDate dateDerniereVisite, int dernierCoefConfiance, String ville) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
        this.ville = ville;
    }
    
    
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getCoefNotoriete() {
        return coefNotoriete;
    }

    public void setCoefNotoriete(double coefNotoriete) {
        this.coefNotoriete = coefNotoriete;
    }

    public LocalDate getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    public void setDateDerniereVisite(LocalDate dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    public int getDernierCoefConfiance() {
        return dernierCoefConfiance;
    }

    public void setDernierCoefConfiance(int dernierCoefConfiance) {
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    
    
    @Override
    public String toString() {
        return " [Praticien] numéro : "+this.numero+", nom : "+this.nom+", prenom : "+this.prenom+", ville : "+this.ville+", coef de notoriété : "+this.coefNotoriete+", coef de confiance : "+this.dernierCoefConfiance+", dernière date : "+this.dateDerniereVisite  ;
    }
    
    
    
    
    
}
