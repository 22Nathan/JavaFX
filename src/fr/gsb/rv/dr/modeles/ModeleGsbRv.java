package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.metier.Praticien;
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
}
