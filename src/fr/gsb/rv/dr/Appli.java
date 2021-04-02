/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.metier.Praticien;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.presentation.PanneauAccueil;
import fr.gsb.rv.dr.presentation.PanneauPraticiens;
import fr.gsb.rv.dr.presentation.PanneauRapports;
import fr.gsb.rv.dr.presentation.VueConnexion;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author developpeur
 */
public class Appli extends Application {
    
    PanneauAccueil panneauAccueil = new PanneauAccueil() ;
    PanneauRapports panneauRapports = new PanneauRapports() ;
    PanneauPraticiens panneauPraticiens = new PanneauPraticiens() ;
    
    @Override
    public void start(Stage primaryStage) throws ConnexionException {

        StackPane pile = new StackPane() ;
        pile.getChildren().addAll(panneauAccueil , panneauRapports , panneauPraticiens) ;
        panneauAccueil.toFront();
        panneauAccueil.setVisible(true) ;
        panneauRapports.setVisible(false) ;
        panneauPraticiens.setVisible(false) ;
        
        MenuBar barreMenus = new MenuBar() ;
        
        Menu menuFichier = new Menu( "Fichier" ) ;
        Menu menuRapports = new Menu( "Rapports" ) ;
        Menu menuPracticiens = new Menu( "Practiciens" ) ;
        
        MenuItem itemSeConnecter = new MenuItem( "Se connecter" ) ;
        MenuItem itemSeDeconnecter = new MenuItem( "Se déconnecter" ) ;
        MenuItem itemQuitter = new MenuItem( "Quitter" ) ; 
        MenuItem itemConsulter = new MenuItem( "Consulter" ) ;
        MenuItem itemHesitants = new MenuItem( "Hesitant" ) ;
        
        menuFichier.getItems().add( itemSeConnecter ) ;
        menuFichier.getItems().add( itemSeDeconnecter ) ;
        menuFichier.getItems().add( itemQuitter ) ;        
        menuRapports.getItems().add( itemConsulter ) ;   
        menuPracticiens.getItems().add( itemHesitants ) ;
        
        barreMenus.getMenus().add( menuFichier ) ;
        barreMenus.getMenus().add( menuRapports ) ;
        barreMenus.getMenus().add( menuPracticiens ) ;
        
        BorderPane root = new BorderPane() ;
        root.setTop( barreMenus ) ;
        //
        //root.getChildren().add(pile) ;
        root.setCenter(pile) ;
        //
        Scene scene = new Scene( root , 500 , 310 ) ;
        //Scene scene2 = new Scene( pile , 300 , 250 ) ;
        
        primaryStage.setTitle("Appli") ;
        primaryStage.setScene( scene ) ;
        //primaryStage.setScene( scene2 ) ;
        primaryStage.show() ;

          itemSeDeconnecter.setDisable(true) ;
          menuPracticiens.setDisable(true) ;
          menuRapports.setDisable(true) ;
        
        //
        //init
        //Session.fermer() ;
        /*Visiteur leVisiteur = new Visiteur();
        leVisiteur.setMatricule("OB001");
        leVisiteur.setPrenom("Oumayma");
        leVisiteur.setNom("BOUAICHI");*/
        Connection con = ConnexionBD.getConnexion() ;
        //
        //
          
        itemQuitter.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){
                        Alert alertQuitter = new Alert( Alert.AlertType.CONFIRMATION ) ;
                        alertQuitter.setTitle("Quitter");
                        alertQuitter.setHeaderText("Demande de confirmation");
                        alertQuitter.setContentText("Voulez-vous quitter l'application ?");
                          ButtonType btnOui = new ButtonType( "oui" ) ;
                          ButtonType btnNon = new ButtonType( "non" ) ;
                        alertQuitter.getButtonTypes().setAll( btnOui , btnNon ) ;
                        Optional<ButtonType> reponse = alertQuitter.showAndWait() ;
                        //System.out.println(reponse.get());
                        if( reponse.get() == btnOui ){
                            Platform.exit() ;                     
                        }
                    }
                }
        );  
        
        itemSeConnecter.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){ 
                        
                       /* itemSeDeconnecter.setDisable(false) ;
                        menuRapports.setDisable(false) ;
                        menuPracticiens.setDisable(false) ;
                        itemSeConnecter.setDisable(true) ; */
                        
                        System.out.println("Se connecter");
                        //System.out.println(con) ; 
                        /*Session.ouvrir(leVisiteur);
                        Session sess = Session.getSession() ;
                        if ( leVisiteur.getMatricule() == "OB001" ){
                            primaryStage.setTitle(leVisiteur.toString()) ;
                        }*/
                        
                        /*Visiteur vis = new Visiteur() ;
                        try {
                            vis = ModeleGsbRv.seConnecter("a17", "azerty");
                        } catch (ConnexionException ex) {
                            Logger.getLogger(Appli.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(vis.getNom());*/
                                               
                        VueConnexion vue = new VueConnexion() ;
                        Optional<Pair<String,String>> reponse = vue.showAndWait();                                       
                        if ( reponse.isPresent() ){

                              Visiteur vis = new Visiteur() ;
                              try {
                                vis = ModeleGsbRv.seConnecter(reponse.get().getKey(), reponse.get().getValue());
                              } catch (ConnexionException ex) {
                                Logger.getLogger(Appli.class.getName()).log(Level.SEVERE, null, ex);
                              }
                              if ( vis != null ){
                                 Session.ouvrir(vis);
                                 itemSeDeconnecter.setDisable(false) ;
                                 menuRapports.setDisable(false) ;
                                 menuPracticiens.setDisable(false) ;
                                 itemSeConnecter.setDisable(true) ;
                                 //panneauAccueil.toFront();
                                 panneauAccueil.setVisible(false) ;
                                 panneauRapports.setVisible(false) ;
                                 panneauPraticiens.setVisible(false) ;
                              } else { Session.fermer();
                                     Alert alertQuitter = new Alert( Alert.AlertType.ERROR ) ;
                                     alertQuitter.setTitle("Erreur"); 
                                     alertQuitter.setHeaderText("Erreur de connexion");
                                     ButtonType btnOk = new ButtonType( "OK" ) ;
                                     alertQuitter.getButtonTypes().setAll( btnOk ) ;
                                     Optional<ButtonType> reponse2 = alertQuitter.showAndWait() ;
                                     if( reponse2.get() == btnOk ){
                                           // Platform.exit() ;                     
                                     }
                              }
                              System.out.println(vis);
                        }   
                    }
                }
        );
        
        itemSeDeconnecter.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){ 
                        
                        itemSeDeconnecter.setDisable(true) ;
                        itemSeConnecter.setDisable(false) ;
                        menuPracticiens.setDisable(true) ;
                        menuRapports.setDisable(true) ;
                        
                        System.out.println("SeDeconnecter") ;
                        panneauAccueil.toFront();
                        panneauAccueil.setVisible(true) ;
                        panneauRapports.setVisible(false) ;
                        panneauPraticiens.setVisible(false) ;
                        /*Session.fermer();
                        primaryStage.setTitle("Appli") ;*/
                    }
                }
        );
        
        itemConsulter.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){ 
                        //System.out.println("[Rapports] "+leVisiteur.getPrenom()+" "+leVisiteur.getNom()) ;
                        panneauRapports.toFront();
                        panneauAccueil.setVisible(false) ;
                        panneauRapports.setVisible(true) ;
                        panneauPraticiens.setVisible(false) ;
                    }
                }
        );
        
        itemHesitants.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle( ActionEvent event ){ 
                        //System.out.println("[Praticiens] "+leVisiteur.getPrenom()+" "+leVisiteur.getNom()) ;
                        panneauPraticiens.toFront();
                        panneauAccueil.setVisible(false) ;
                        panneauRapports.setVisible(false) ;
                        panneauPraticiens.setVisible(true) ;
                        
                        /*try {
                            List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesitants();
                            for ( Praticien unPrat : praticiens ){
                                System.out.println( unPrat );
                            }
                        } catch (ConnexionException ex) {
                            Logger.getLogger(Appli.class.getName()).log(Level.SEVERE, null, ex);
                        } */  
                    }
                }
        );
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ConnexionException {
        launch(args);
        System.out.println("/\nListe des practiciens hésitants-------------------------------------------------------/");
        List<Praticien> prat = ModeleGsbRv.getPraticiensHesitants() ;
        for ( Praticien unPrat : prat ){
                                System.out.println( unPrat );
        } 
        System.out.println("/\nComparer par ordre croissant coef de confiance----------------------------------------/");
        Collections.sort( prat , new ComparateurCoefConfiance() ) ;
        for ( Praticien unPrat : prat ){
                                System.out.println( unPrat );
        }
        System.out.println("/\nComparer par ordre décroissant du coef de notoriété-----------------------------------/");
        Collections.sort( prat , new ComparateurCoefNotoriete() ) ;
        Collections.reverse( prat ) ;
        for ( Praticien unPrat : prat ){
                                System.out.println( unPrat );
        }
        System.out.println("/\nComparer par ordre chronologique inverse de la dernière visite------------------------/");
        Collections.sort( prat , new ComparateurDateVisite() ) ;
        Collections.reverse(prat) ;
        for ( Praticien unPrat : prat ){
                                System.out.println( unPrat );
        }
        System.out.println("/\n--------------------------------------------------------------------------------------/");
        
    }
    
}
