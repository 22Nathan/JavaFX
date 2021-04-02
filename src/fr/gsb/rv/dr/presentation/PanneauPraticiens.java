/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.presentation;

import fr.gsb.rv.dr.metier.Praticien;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author developpeur
 */
public class PanneauPraticiens extends Pane {
    
    private VBox root = new VBox() ;
    private GridPane grid = new GridPane();
    private TableView table = new TableView();
    private ToggleGroup groupe = new ToggleGroup() ;
    private ObservableList<Praticien> olPraticien = FXCollections.observableArrayList() ;
    private List<Praticien> listPraticien ;
    private RadioButton rd1 = new RadioButton("Confiance") ;
    private RadioButton rd2 = new RadioButton("Notoriété") ;
    private RadioButton rd3 = new RadioButton("Date Visite") ;
    private String critSelect ;
    
    public PanneauPraticiens(){
    super() ;
        //this.ccc = ModeleGsbRv.getPraticiensHesitants();
        //
        try {
            List<Praticien> listPraticien = ModeleGsbRv.getPraticiensHesitants();
            for ( Praticien unPrat : listPraticien ){
                olPraticien.add(unPrat);
            }
        } catch (ConnexionException ex) {
            Logger.getLogger(PanneauPraticiens.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        root.getChildren().add(new Label("Sélectionner un critère de tri : ")) ;
        root.setStyle("-fx-background-color: white");
        root.setStyle("-fx-font-weight: bold");
            this.getChildren().add(root) ;
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10)) ;
        
        
        //+--------------------------------------------------------------------------+//
        //+--------------------------------------------------------------------------+//
        RadioButton rd1 = new RadioButton("Confiance") ;
        //
            rd1.setOnAction( ( ActionEvent event ) -> {
                critSelect = rd1.getText() ;
                rafraichir();
                //Collections.sort( olPraticien , new ComparateurCoefConfiance() ) ;              
            }) ;
        //    
        rd1.setToggleGroup(groupe);
        rd1.setSelected(true);
        //+--------------------------------------------------------------------------+//
        //+--------------------------------------------------------------------------+//
        
        
        RadioButton rd2 = new RadioButton("Notoriété") ;
        //
            rd2.setOnAction( ( ActionEvent event ) -> {
                critSelect = rd2.getText() ;
                rafraichir();
                /*Collections.sort( olPraticien , new ComparateurCoefNotoriete() ) ;
                Collections.reverse( olPraticien ) ;*/
            }) ;
        //        
        rd2.setToggleGroup(groupe);
        //+--------------------------------------------------------------------------+//
        //+--------------------------------------------------------------------------+//
        
        
        RadioButton rd3 = new RadioButton("Date Visite") ;
        //
            rd3.setOnAction( ( ActionEvent event ) -> {
                critSelect = rd3.getText() ;
                rafraichir();
                /*Collections.sort( olPraticien , new ComparateurDateVisite() ) ;
                Collections.reverse( olPraticien ) ;*/
            }) ;
        //        
        rd3.setToggleGroup(groupe);
        //+--------------------------------------------------------------------------+//
        //+--------------------------------------------------------------------------+//
        
        
            //grid.getChildren().addAll(rd1,rd2,rd3) ;
            //root.getChildren().add(grid) ;
            grid.add(rd1,0,0);
            grid.add(rd2,1,0);
            grid.add(rd3,2,0);
            root.getChildren().add(grid) ;
        table.setEditable(false);
        
        TableColumn num = new TableColumn("Numéro");
        num.setMinWidth(100);
        num.setCellValueFactory(
                new PropertyValueFactory<Praticien, String>("numero"));
        
        TableColumn nom = new TableColumn("Nom");
        nom.setMinWidth(100);
        nom.setCellValueFactory(
                new PropertyValueFactory<Praticien, String>("nom"));
        
        TableColumn ville = new TableColumn("Ville");
        ville.setMinWidth(100);
        ville.setCellValueFactory(
                new PropertyValueFactory<Praticien, String>("ville"));

        rafraichir();
        
        table.setItems(olPraticien);
        //table.setItems(listPraticien);
        
        table.getColumns().addAll(num, nom, ville);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            root.getChildren().add(table) ;
    }
    
    public void rafraichir(){
        try {
            ObservableList<Praticien> olPraticien = FXCollections.observableArrayList() ;
            List<Praticien> listPraticien = ModeleGsbRv.getPraticiensHesitants();
            for ( Praticien unPrat : listPraticien ){
                olPraticien.add(unPrat);
            }
        } catch (ConnexionException ex) {
            Logger.getLogger(PanneauPraticiens.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        rd1.setOnAction( ( ActionEvent event ) -> {
                Collections.sort( olPraticien , new ComparateurDateVisite() ) ;
            }) ;
        rd2.setOnAction( ( ActionEvent event ) -> {
                Collections.sort( olPraticien , new ComparateurCoefNotoriete() ) ;
                Collections.reverse( olPraticien ) ;
            }) ;
        rd3.setOnAction( ( ActionEvent event ) -> {
                Collections.sort( olPraticien , new ComparateurDateVisite() ) ;
                Collections.reverse( olPraticien ) ;
            }) ;*/
        
        if( critSelect == rd1.getText() ){
            Collections.sort( olPraticien , new ComparateurCoefConfiance() ) ;
        }
        else if ( critSelect == rd2.getText() ){
            Collections.sort( olPraticien , new ComparateurCoefNotoriete() ) ;
            Collections.reverse( olPraticien ) ;
        }
        else if ( critSelect == rd3.getText() ){
            Collections.sort( olPraticien , new ComparateurDateVisite() ) ;
            Collections.reverse( olPraticien ) ;
        }
        else if ( critSelect == null ){
            Collections.sort( olPraticien , new ComparateurCoefConfiance() ) ;
        }

        
        table.setItems(olPraticien);
        
    }
}
