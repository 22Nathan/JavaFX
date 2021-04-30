package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.metier.Praticien;
import fr.gsb.rv.dr.metier.RapportVisite;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {
    
    public static Visiteur seConnecter( String matricule , String mdp ) throws ConnexionException{
        
        // Code de test à compléter
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select v.vis_nom , v.vis_prenom , v.vis_matricule , v.vis_mdp , t.jjmmaa , t.tra_role "
                + "from Visiteur as v inner join Travailler as t "
                + "on v.vis_matricule = t.vis_matricule "
                + "where jjmmaa in ( select max(t.jjmmaa) from Travailler as t where t.vis_matricule = v.vis_matricule ) "
                + "and v.vis_matricule = ? and v.vis_mdp = ?";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            requetePreparee.setString( 2 , mdp );
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( resultat.getString( "v.vis_matricule" ) );
                visiteur.setNom( resultat.getString( "v.vis_nom" ) ) ;
                visiteur.setPrenom( resultat.getString( "v.vis_prenom" )) ;
                
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( Exception e ){
            return null ;
        } 
    }
    
    //+-------------------------------------------------------------------------------------------+//
    //+-------------------------------------------------------------------------------------------+//
    
    public static List<Praticien> getPraticiensHesitants() throws ConnexionException{
        List<Praticien> list = new ArrayList<Praticien>() ;
        Connection connexion = ConnexionBD.getConnexion() ;
        String requete = "select r.pra_num , r.rap_coeff_confiance , p.pra_num , p.pra_nom , p.pra_prenom , p.pra_adresse , p.pra_cp , p.pra_ville ,  p.pra_coefnotoriete , typ_code , r.rap_date_visite " 
                + " from RapportVisite as r inner join Praticien as p " 
                + " on r.pra_num = p.pra_num "
                + " where r.rap_coeff_confiance < 5 " ;
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            ResultSet resultat = requetePreparee.executeQuery() ;
            while( resultat.next() ){
                Praticien prat = new Praticien() ;
                prat.setNumero( resultat.getInt( "p.pra_num" ) );
                prat.setNom( resultat.getString( "p.pra_nom" ) );
                prat.setPrenom( resultat.getString( "p.pra_prenom" ) );
                prat.setVille( resultat.getString( "p.pra_ville" ) ) ;
                prat.setCoefNotoriete( resultat.getDouble( "p.pra_coefnotoriete" ) );
                prat.setDernierCoefConfiance( resultat.getInt( "r.rap_coeff_confiance" ) );
                Date date = resultat.getDate("r.rap_date_visite" );
                prat.setDateDerniereVisite( date.toLocalDate() );
                list.add(prat) ;
                
            }
            requetePreparee.close() ;
            return list ;
        }
        catch( Exception e ){
            return null ;
        }
    }
    
    //+-------------------------------------------------------------------------------------------+//
    //+-------------------------------------------------------------------------------------------+//
    
    public static List<Visiteur> getVisiteurs() throws ConnexionException {
    
        List<Visiteur> listVisiteurs = new ArrayList<Visiteur>() ;
        Connection connexion = ConnexionBD.getConnexion() ;
        String requete = "select vis_matricule , vis_nom , vis_prenom from Visiteur " ;
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            ResultSet resultat = requetePreparee.executeQuery() ;
            while( resultat.next() ){
                Visiteur vis = new Visiteur() ;
                vis.setMatricule( resultat.getString( "vis_matricule" ) );
                vis.setPrenom( resultat.getString( "vis_prenom" ) );
                vis.setNom( resultat.getString( "vis_nom" ) );
                listVisiteurs.add(vis) ;
                
            }
            requetePreparee.close() ;
            return listVisiteurs ;
        }
        catch( Exception e ){
            return null ;
        }
        
    }
    
    //+-------------------------------------------------------------------------------------------+//
    //+-------------------------------------------------------------------------------------------+//
    
    /*
    public static List<RapportVisite> getRapportVisite( String matricule , int mois , int annee ) throws ConnexionException{
    
        List<RapportVisite> listRV = new ArrayList<RapportVisite>() ;
        Connection connexion = ConnexionBD.getConnexion() ;
        String requete = "select * from RapportVisite where vis_matricule = ? and month(rap_date_visite) = ? and year(rap_date_visite) = ? " ;    
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            requetePreparee.setInt( 2 , mois );
            requetePreparee.setInt( 3 , annee );
            ResultSet resultat = requetePreparee.executeQuery() ;
            while( resultat.next() ){
                RapportVisite rapv = new RapportVisite() ;
                
                //rapv.getLeVisiteur().setMatricule(matricule);
                rapv.setMatricule( matricule );
                //rapv.set
                //----------------------
                rapv.setNumero( resultat.getInt( "rap_num" ) );
                Date date = resultat.getDate( "rap_date_visite" ) ;
                rapv.setDateVisite( date.toLocalDate() );
                rapv.setBilan( resultat.getString( "rap_bilan" ) );
                rapv.setCoefConfiance( resultat.getInt( "rap_coeff_confiance" ) );
                rapv.setMotif( resultat.getString( "rap_motif_visite" ) );
                Date date2 = resultat.getDate( "rap_date_saisie" );
                rapv.setDateRedaction( date2.toLocalDate() );
                //rapv.getLePraticien().setNumero( resultat.getInt( "pra_num" ) );
                rapv.setPra_num( resultat.getInt( "pra_num" ) );
                //rapv.setLu( resultat.getBoolean( "rap_lu" ) );
                rapv.setLu(false);
                listRV.add( rapv );

            }
            requetePreparee.close() ;
            return listRV ;    
        }
        catch( Exception e ){
            return null ;
        }
        
    }
    */
    
    
    //+-------------------------------------------------------------------------------------------+//
    //+-------------------------------------------------------------------------------------------+//
    
    
    public static List<RapportVisite> getRapportVisite( String matricule , int mois , int annee ) throws ConnexionException{
    
        List<RapportVisite> listRV = new ArrayList<RapportVisite>() ;
        Connection connexion = ConnexionBD.getConnexion() ;
        Connection connexionV = ConnexionBD.getConnexion() ;
        Connection connexionP = ConnexionBD.getConnexion() ;
        String requete = " select vis_matricule, rap_num, rap_date_visite, rap_bilan, rap_date_saisie, rap_coeff_confiance, "
                + " pra_num, rap_lu, rap_motif_visite "
                + " from RapportVisite "
                + " where vis_matricule = ? "
                + " and MONTH(rap_date_visite) = ? "
                + " and YEAR(rap_date_visite) = ? " ;
        String requeteV = " select vis_matricule, vis_nom, vis_prenom "
                + " from Visiteur "
                + " where vis_matricule = ? " ;
        String requeteP = " select pra_nom, p.pra_num, pra_ville, pra_coefnotoriete, rv.rap_date_visite, "
                + " rv.rap_coeff_confiance, pra_adresse, pra_prenom, pra_cp "
                + " from Praticien as p "
                + " inner join RapportVisite as rv "
                + " on p.pra_num = rv.pra_num "
                + " where p.pra_num = ? " ;
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString(1, matricule);
            requetePreparee.setInt(2, mois);
            requetePreparee.setInt(3, annee);
            ResultSet resultat = requetePreparee.executeQuery() ;
            while(resultat.next()){
                RapportVisite rapv = new RapportVisite();
                Visiteur unVisiteur = new Visiteur();
                Praticien unPraticien = new Praticien();
                
                rapv.setNumero( resultat.getInt( "rap_num" ) );
                rapv.setDateVisite( resultat.getDate( "rap_date_visite" ).toLocalDate() );
                rapv.setBilan( resultat.getString( "rap_bilan" ) );
                rapv.setCoefConfiance( resultat.getInt( "rap_coeff_confiance" ) );
                rapv.setMotif( resultat.getString( "rap_motif_visite" ) );
                rapv.setDateRedaction( resultat.getDate( "rap_date_saisie" ).toLocalDate() );
                rapv.setLu( resultat.getBoolean( "rap_lu" ) );
                
                //-----------------------
                PreparedStatement requetePrepareeV = (PreparedStatement) connexionV.prepareStatement( requeteV ) ;
                requetePrepareeV.setString(1, resultat.getString( "vis_matricule" ));
                ResultSet resultatV = requetePrepareeV.executeQuery() ;
                while(resultatV.next()){
                    unVisiteur.setMatricule( resultatV.getString( "vis_matricule" ) );
                    unVisiteur.setPrenom( resultatV.getString( "vis_prenom" ) );
                    unVisiteur.setNom( resultatV.getString( "vis_nom" ) );
                }               
                rapv.setLeVisiteur(unVisiteur);
                
                //----------------------
                PreparedStatement requetePrepareeP = (PreparedStatement) connexionP.prepareStatement( requeteP ) ;
                requetePrepareeP.setInt(1, resultat.getInt( "pra_num" ));
                ResultSet resultatP = requetePrepareeP.executeQuery() ;
                while(resultatP.next()){
                    unPraticien.setNom( resultatP.getString( "pra_nom" ) );
                    unPraticien.setPrenom( resultatP.getString( "pra_prenom" ) );
                    unPraticien.setNumero( resultatP.getInt( "pra_num" ) );
                    unPraticien.setVille( resultatP.getString( "pra_ville" ) );
                    unPraticien.setCoefNotoriete( resultatP.getDouble( "pra_coefnotoriete" ) );
                    unPraticien.setDateDerniereVisite( resultatP.getDate( "rap_date_visite" ).toLocalDate() );
                    unPraticien.setDernierCoefConfiance( resultatP.getInt( "rap_coeff_confiance" ) );
                    unPraticien.setAdresse( resultatP.getString( "pra_adresse" ) );
                    unPraticien.setCodePostal( resultatP.getString( "pra_cp" ) );
                }
                rapv.setLePraticien(unPraticien);
                
                listRV.add(rapv);
            }
            
        } catch( Exception e ){
            return null ;
        }
        return listRV ;
        
    }    
        
    //+-------------------------------------------------------------------------------------------+//
    //+-------------------------------------------------------------------------------------------+//
    
    
    
    public static void setRapportVisiteLu( String matricule , int numRapport ) throws ConnexionException{
        
        Connection connexion = ConnexionBD.getConnexion() ;
        String requete = "update RapportVisite set rap_lu = true where vis_matricule = ? and rap_num = ? " ;
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            requetePreparee.setInt( 2 , numRapport );
            requetePreparee.executeUpdate();
 
        }
        catch( Exception e ){

        }
        
    }
    
}
