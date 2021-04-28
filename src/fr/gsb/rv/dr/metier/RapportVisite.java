/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.metier;

import fr.gsb.rv.dr.entites.Visiteur;
import java.time.LocalDate;

/**
 *
 * @author developpeur
 */
public class RapportVisite {
    
    private Praticien lePraticien ;
    private Visiteur leVisiteur ;        
            
    //
    private String matricule = null ;
    private int pra_num = 0 ;

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setPra_num(int pra_num) {
        this.pra_num = pra_num;
    }
    //
    
    private int numero ;
    private LocalDate dateVisite ;
    private LocalDate dateRedaction ;
    private String bilan ;
    private String motif ;
    private int coefConfiance ;
    private boolean lu ;

    public RapportVisite() {
    }

    public RapportVisite(int numero, LocalDate dateVisite, LocalDate dateRedaction, String bilan, String motif, int coefConfiance, boolean lu) {
        this.numero = numero;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.bilan = bilan;
        this.motif = motif;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
    }

    public RapportVisite(Praticien lePraticien, Visiteur leVisiteur, int numero, LocalDate dateVisite, LocalDate dateRedaction, String bilan, String motif, int coefConfiance, boolean lu) {
        this.lePraticien = lePraticien;
        this.leVisiteur = leVisiteur;
        this.numero = numero;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.bilan = bilan;
        this.motif = motif;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
    }

    public RapportVisite(Visiteur leVisiteur, int numero, LocalDate dateVisite, LocalDate dateRedaction, String bilan, String motif, int coefConfiance, boolean lu) {
        this.leVisiteur = leVisiteur;
        this.numero = numero;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.bilan = bilan;
        this.motif = motif;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
    }
    
        public RapportVisite(String matricule, int pra_num , int numero, LocalDate dateVisite, LocalDate dateRedaction, String bilan, String motif, int coefConfiance, boolean lu) {
        this.matricule = matricule;
        this.numero = numero;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.bilan = bilan;
        this.motif = motif;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
        this.pra_num = pra_num ;
    }

    public Praticien getLePraticien() {
        return lePraticien;
    }

    public Visiteur getLeVisiteur() {
        return leVisiteur;
    }
    
    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(LocalDate dateVisite) {
        this.dateVisite = dateVisite;
    }

    public LocalDate getDateRedaction() {
        return dateRedaction;
    }

    public void setDateRedaction(LocalDate dateRedaction) {
        this.dateRedaction = dateRedaction;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getCoefConfiance() {
        return coefConfiance;
    }

    public void setCoefConfiance(int coefConfiance) {
        this.coefConfiance = coefConfiance;
    }

    public boolean isLu() {
        return lu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    /*
    @Override
    public String toString() {
        return "RapportVisite{" + "numero=" + numero + ", dateVisite=" + dateVisite + ", dateRedaction=" + dateRedaction + ", bilan=" + bilan + ", motif=" + motif + ", coefConfiance=" + coefConfiance + ", lu=" + lu + '}';
    }*/

    @Override
    public String toString() {
        return "RapportVisite { " + "vis_matricule = " + matricule + " | rap_num = " + numero + " | dateVisite = " + dateVisite + " | dateRedaction = " + dateRedaction + " | bilan = " + bilan + " | motif = " + motif + " | coefConfiance = " + coefConfiance + " | pra_num = " + pra_num + " | lu = " + lu + " }";
    }
    
    
    
}
