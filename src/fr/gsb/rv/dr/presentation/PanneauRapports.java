/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.presentation;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.metier.RapportVisite;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.Annee;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Mois;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author developpeur
 */
public class PanneauRapports extends Pane  {
    
    private ComboBox<Visiteur> cbVisiteurs = new ComboBox<Visiteur>();
    private ComboBox<Mois> cbMois = new ComboBox<Mois>();
    private ComboBox<Integer> cbAnnee = new ComboBox<Integer>();
    
    private TableView<RapportVisite> tvRV = new TableView<RapportVisite>();
    
    
    public PanneauRapports(){
    super() ;
        
        VBox root = new VBox() ;
        root.setSpacing(5);
        root.setPadding(new Insets(10,20, 10,10));
        GridPane gp = new GridPane() ;

        Button btn = new Button("Valider") ; 
        btn.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(    cbVisiteurs.getSelectionModel().isEmpty() == false
                        && cbMois.getSelectionModel().isEmpty() == false
                        && cbAnnee.getSelectionModel().isEmpty() == false ){
                        System.out.println("panneauRapports trigger rafaichir") ;
                        rafraichir();
                    }
                    else {
                        System.out.println("non");
                        Alert alertQuitter = new Alert( Alert.AlertType.ERROR ) ;
                        alertQuitter.setTitle("Erreur"); 
                        alertQuitter.setHeaderText("Saisie imcomplète \nVeuillez sélectionner les 3 critères");
                        ButtonType btnOk = new ButtonType( "OK" ) ;
                        alertQuitter.getButtonTypes().setAll( btnOk ) ;
                        Optional<ButtonType> reponse2 = alertQuitter.showAndWait() ;
                    }
                }
            }
        );
        
        //------------------------------
        try {
            cbVisiteurs.getItems().setAll( ModeleGsbRv.getVisiteurs() );
            cbVisiteurs.setPromptText("Visiteurs");
        } catch (ConnexionException ex) {
            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //------------------------------
        cbMois.getItems().setAll( Mois.values() );
        cbMois.setPromptText("Mois");
        
        LocalDate auj = LocalDate.now() ;
        int anneeCourante = auj.getYear() ;
        int anneeMoins5 = anneeCourante - 5 ;
        while( anneeCourante != anneeMoins5 ){
            cbAnnee.getItems().add(anneeCourante);
            anneeCourante -= 1 ;
        }
        cbAnnee.setPromptText("Année");
        
        gp.setHgap(10);
        gp.setVgap(10);
        
        gp.add(cbVisiteurs,0,0);
        gp.add(cbMois,1,0);
        gp.add(cbAnnee,2,0);
        
        
        
        //------------------------------
        TableColumn<RapportVisite, Integer> colNumero = new TableColumn<RapportVisite, Integer>("Numéro");
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        //------------------------------
        
        
        
        //------------------------------
        TableColumn<RapportVisite, String> colPraticien = new TableColumn<RapportVisite, String>("Praticien");
        colPraticien.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<RapportVisite, String>, ObservableValue<String>>(){
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<RapportVisite, String> param) {
                        String nom = param.getValue().getLePraticien().getNom();
                        return new SimpleStringProperty(nom);
                    }
                }
        );
        //------------------------------
        
        
        
        //------------------------------
        TableColumn<RapportVisite, String> colVille = new TableColumn<RapportVisite, String>("Ville");
        colVille.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<RapportVisite, String>, ObservableValue<String>>(){
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<RapportVisite, String> param) {
                        String ville = param.getValue().getLePraticien().getVille();
                        return new SimpleStringProperty(ville);
                    }
                }
        );
        //------------------------------
        
        
                
        //------------------------------
        TableColumn<RapportVisite, LocalDate> colDateVisite = new TableColumn<RapportVisite, LocalDate>("Date de visite");
        colDateVisite.setCellValueFactory(new PropertyValueFactory<>("dateVisite"));
        colDateVisite.setCellFactory(
                colonne -> {
                    return new TableCell<RapportVisite, LocalDate>(){
                        @Override
                        protected void updateItem(LocalDate item, boolean empty){
                            super.updateItem(item, empty);
                            if(empty){
                                setText("");
                            }
                            else{
                                DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                setText(item.format(formateur));
                            }
                        }
                    };
                }
        );
        //------------------------------
        
        
                
        //------------------------------
        TableColumn<RapportVisite, LocalDate> colDateSaisie = new TableColumn<RapportVisite, LocalDate>("Date de saisie");
        colDateSaisie.setCellValueFactory(new PropertyValueFactory<>("dateRedaction"));
        colDateSaisie.setCellFactory(
                colonne -> {
                    return new TableCell<RapportVisite, LocalDate>(){
                        @Override
                        protected void updateItem(LocalDate item, boolean empty){
                            super.updateItem(item, empty);
                            if(empty){
                                setText("");
                            }
                            else{
                                DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                setText(item.format(formateur));
                            }
                        }
                    };
                }
        );
        //------------------------------
        
        
        
        tvRV.setRowFactory(
                ligne -> {
                    return new TableRow<RapportVisite>(){
                        @Override
                        protected void updateItem(RapportVisite item, boolean empty){
                            super.updateItem(item, empty);
                            if(item != null){
                                if(item.isLu()){
                                    setStyle("-fx-background-color: gold");
                                }
                                else {
                                    setStyle("-fx-background-color: cyan");
                                }
                            }
                        }
                                                       
                    };
                }
        );
        
        
        tvRV.setOnMouseClicked(
                (MouseEvent event) -> {
                    if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                        int numero = tvRV.getSelectionModel().getSelectedItem().getNumero();
                        VueRapport vRapport = new VueRapport(tvRV.getSelectionModel().getSelectedItem());
                        Optional<ButtonType> reponse = vRapport.showAndWait();
                        if(reponse.get().getButtonData() == ButtonBar.ButtonData.OK_DONE){
                            vRapport.close();
                        }
                        String matricule = tvRV.getSelectionModel().getSelectedItem().getLeVisiteur().getMatricule();
                        try {
                            ModeleGsbRv.setRapportVisiteLu(matricule, numero);
                        } catch (ConnexionException ex) {
                            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        rafraichir();
                    }
                }
        );
        
        tvRV.getColumns().addAll( colNumero , colPraticien , colVille , colDateVisite  , colDateSaisie );
        tvRV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        root.getChildren().add(gp);
        root.getChildren().add(btn);
        root.getChildren().add(tvRV);
        
        this.getChildren().add(root);
        this.setStyle("-fx-background-color: white");
        
    }
    
    public void rafraichir(){
        Visiteur leVisiteur = cbVisiteurs.getSelectionModel().getSelectedItem();
        Mois leMois = cbMois.getSelectionModel().getSelectedItem();
        int lAnnee = cbAnnee.getSelectionModel().getSelectedItem();
                
        try {
            
            List<RapportVisite> listeRV = ModeleGsbRv.getRapportVisite(leVisiteur.getMatricule(), leMois.ordinal() + 1, lAnnee);
            ObservableList<RapportVisite> liste = FXCollections.observableArrayList(listeRV);
            tvRV.setItems(liste);
        } catch (ConnexionException ex) {
            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
