/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.utilitaires;

import fr.gsb.rv.dr.metier.Praticien;
import java.util.Comparator;

/**
 *
 * @author developpeur
 */
public class ComparateurCoefNotoriete implements Comparator<Praticien>{
    
    public ComparateurCoefNotoriete(){
        super();
    }

    @Override
    public int compare(Praticien o1, Praticien o2) {
        
        if( o1.getCoefNotoriete() == o2.getCoefNotoriete()){
            return 0 ;
        }
        else if( o1.getCoefNotoriete() > o2.getCoefNotoriete()){
            return 1 ;
        }
        else { return -1 ; }
        
    }
    
}
