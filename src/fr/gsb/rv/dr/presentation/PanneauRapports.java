/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.presentation;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.Annee;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Mois;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author developpeur
 */
public class PanneauRapports extends Pane  {
    
    ComboBox<Visiteur> cbVisiteurs ;
    ComboBox<Mois> cbMois ;
    ComboBox<Annee> cbAnnee ;
    
    public PanneauRapports(){
    super() ;
        //Dialog<Pair<String, String>> dialog = new Dialog<>();
    
        LocalDate auj = LocalDate.now() ;
        int currentYear = auj.getYear() ;
    
        try {
            this.cbVisiteurs = new ComboBox( FXCollections.observableArrayList(ModeleGsbRv.getVisiteurs()) );
        } catch (ConnexionException ex) {
            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.cbMois.getItems().clear();
        //this.cbMois.getItems().setAll(Mois.values());
        this.cbMois = new ComboBox( FXCollections.observableArrayList( Mois.values() ) );
        this.cbAnnee = new ComboBox( FXCollections.observableArrayList( currentYear ,
                currentYear - 1 ,
                currentYear - 2 ,
                currentYear - 3 ,
                currentYear - 4 ,
                currentYear - 5 ) );
        
        VBox root = new VBox(new Label("Rapports")) ;
        root.setStyle("-fx-background-color: white");
        
        Button btn = new Button("Valider") ; 
        btn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(    cbVisiteurs.getSelectionModel().isEmpty() == false
                && cbMois.getSelectionModel().isEmpty() == false
                && cbAnnee.getSelectionModel().isEmpty() == false ){
                System.out.println("icii") ;
                Rafraichir();
            }
        }
        });
        
        this.getChildren().add(root) ;
    }
    
    public void Rafraichir(){
    }
    
}
