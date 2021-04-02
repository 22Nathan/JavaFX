/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.entites;

/**
 *
 * @author developpeur
 */
public class Visiteur {
    
    private String matricule ;
    private String nom ;
    private String prenom ;
    
    public Visiteur(){
    }
    
    public Visiteur(String matricule , String prenom , String nom){
        this.matricule = matricule ;
        this.nom = nom ;
        this.prenom = prenom ;
    }

    public String getMatricule() {
        return this.matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        //return super.toString(); //To change body of generated methods, choose Tools | Templates.
        return this.matricule+" "+this.prenom+" "+this.nom ;
    }
        
}
