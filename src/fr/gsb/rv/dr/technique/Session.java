/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Visiteur;

/**
 *
 * @author developpeur
 */
public class Session {
    
    private static Session session = null ;
    private static Visiteur leVisiteur ; 
    
    
    private Session(Visiteur visiteur){
    }
    
    public static Session getSession(){
        return session ;
    }
    
    public static void ouvrir(Visiteur visiteur){
        session = new Session(visiteur) ;
    }
    
    public static void fermer(){
        session = null ;
    }
    
    public Visiteur getLeVisiteur(){        
        return this.leVisiteur ;
    }
    
    public static boolean estOuverte(){
        if ( session == null ){
            return false ;
        }
        else {
            return true ;
        }
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
