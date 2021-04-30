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
        //private TableView table = new TableView();
        TableView<Praticien> tablePrat = new TableView();
        private ToggleGroup groupe = new ToggleGroup() ;
        private ObservableList<Praticien> olPraticien = FXCollections.observableArrayList() ;
        private List<Praticien> listPraticien ;
        private RadioButton rd1 = new RadioButton("Confiance") ;
        private RadioButton rd2 = new RadioButton("Notoriété") ;
        private RadioButton rd3 = new RadioButton("Date Visite") ;
        private String critSelect ;

        public PanneauPraticiens(){
        super() ;

            /*
            try {
                List<Praticien> listPraticien = ModeleGsbRv.getPraticiensHesitants();
                for ( Praticien unPrat : listPraticien ){
                    olPraticien.add(unPrat);
                }
            } catch (ConnexionException ex) {
                Logger.getLogger(PanneauPraticiens.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
            //TableView<Praticien> tablePrat = new TableView();

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
                rd1.setOnAction( ( ActionEvent event ) -> {
                    this.critSelect = rd1.getText() ;
                    //System.out.println(rd1.getText());
                    rafraichir();              
                }) ;   
            rd1.setToggleGroup(groupe);
            rd1.setSelected(true);
            //+--------------------------------------------------------------------------+//
            //+--------------------------------------------------------------------------+//
            RadioButton rd2 = new RadioButton("Notoriété") ;
                rd2.setOnAction( ( ActionEvent event ) -> {
                    this.critSelect = rd2.getText() ;
                    //System.out.println(rd2.getText());
                    rafraichir();
                }) ;    
            rd2.setToggleGroup(groupe);
            //+--------------------------------------------------------------------------+//
            //+--------------------------------------------------------------------------+//
            RadioButton rd3 = new RadioButton("Date Visite") ;
                rd3.setOnAction( ( ActionEvent event ) -> {
                    this.critSelect = rd3.getText() ;
                    //System.out.println(rd3.getText());
                    rafraichir();
                }) ;   
            rd3.setToggleGroup(groupe);
            //+--------------------------------------------------------------------------+//
            //+--------------------------------------------------------------------------+//


                //grid.getChildren().addAll(rd1,rd2,rd3) ;
                //root.getChildren().add(grid) ;
            grid.add(rd1,0,0);
            grid.add(rd2,1,0);
            grid.add(rd3,2,0);
            root.getChildren().add(grid) ;

            //table.setEditable(false);
            tablePrat.setEditable( false );

            //+-------------------------------------------------------------------------
            //+-------------------------------------------------------------------------
            /*TableColumn num = new TableColumn("Numéro");
            num.setMinWidth(100);
            num.setCellValueFactory(
                    new PropertyValueFactory<Praticien, String>("numero"));*/

            TableColumn<Praticien, Integer> colNumero = new TableColumn<Praticien,Integer>( "Numéro" );
            colNumero.setMinWidth(100);
                    colNumero.setCellValueFactory(
                    new PropertyValueFactory<>("numero"));
            //+-------------------------------------------------------------------------
            //+-------------------------------------------------------------------------      
            /*TableColumn nom = new TableColumn("Nom");
            nom.setMinWidth(100);
            nom.setCellValueFactory(
                    new PropertyValueFactory<Praticien, String>("nom"));*/

            TableColumn<Praticien, String> colNom = new TableColumn<Praticien,String>( "Nom" );
            colNom.setMinWidth(100);
                    colNom.setCellValueFactory(
                    new PropertyValueFactory<>("nom"));
            //+-------------------------------------------------------------------------
            //+-------------------------------------------------------------------------
            /*TableColumn ville = new TableColumn("Ville");
            ville.setMinWidth(100);
            ville.setCellValueFactory(
                    new PropertyValueFactory<Praticien, String>("ville"));*/

            TableColumn<Praticien, String> colVille = new TableColumn<Praticien,String>( "Ville" );
            colVille.setMinWidth(100);
                    colVille.setCellValueFactory(
                    new PropertyValueFactory<>("ville"));
            //+-------------------------------------------------------------------------
            //+-------------------------------------------------------------------------

            //rafraichir();
            //System.out.println(olPraticien.toString());

            //table.setItems(olPraticien);
            //table.setItems(listPraticien);

            //table.getColumns().addAll(num, nom, ville);
            tablePrat.getColumns().add(colNumero);
            tablePrat.getColumns().add(colNom);
            tablePrat.getColumns().add(colVille);

            //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tablePrat.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            rafraichir();

            /*for( int i = 0 ; i < olPraticien.size() ; i++ ){
                System.out.println(this.olPraticien.get(i));
            } */   
                //root.getChildren().add(table) ;
                root.getChildren().add(tablePrat);

                //rafraichir();
        }

        public void rafraichir(){

            //+----------------------------------------------------------------------------
            /*try {

                this.listPraticien = ModeleGsbRv.getPraticiensHesitants();

                if( this.olPraticien.toString() == "[]" ){
                    for ( Praticien unPrat : this.listPraticien ){
                        this.olPraticien.add(unPrat);
                    }
                }  

            } catch (ConnexionException ex) {
                Logger.getLogger(PanneauPraticiens.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            //+----------------------------------------------------------------------------

            if( getCritereTri() == rd1.getText() ){
                try {
                    List<Praticien> lesPraticiens = ModeleGsbRv.getPraticiensHesitants();
                    ObservableList<Praticien> list = FXCollections.observableArrayList(lesPraticiens);
                    Collections.sort( list , new ComparateurCoefConfiance() ) ;
                    tablePrat.setItems(list);
                } catch (ConnexionException ex) {
                    Logger.getLogger(PanneauPraticiens.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if ( getCritereTri() == rd2.getText() ){
                try {
                    List<Praticien> lesPraticiens = ModeleGsbRv.getPraticiensHesitants();
                    ObservableList<Praticien> list = FXCollections.observableArrayList(lesPraticiens);
                    Collections.sort( list, new ComparateurCoefNotoriete() ) ;
                    Collections.reverse( list ) ;
                    tablePrat.setItems(list);
                } catch (ConnexionException ex) {
                    Logger.getLogger(PanneauPraticiens.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if ( getCritereTri() == rd3.getText() ){
                try {
                    List<Praticien> lesPraticiens = ModeleGsbRv.getPraticiensHesitants();
                    ObservableList<Praticien> list = FXCollections.observableArrayList(lesPraticiens);
                    Collections.sort( list , new ComparateurDateVisite() ) ;
                    Collections.reverse( list ) ;
                    tablePrat.setItems(list);
                } catch (ConnexionException ex) {
                    Logger.getLogger(PanneauPraticiens.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                try {
                    List<Praticien> lesPraticiens = ModeleGsbRv.getPraticiensHesitants();
                    ObservableList<Praticien> list = FXCollections.observableArrayList(lesPraticiens);
                    Collections.sort( list , new ComparateurCoefConfiance() ) ;
                    tablePrat.setItems(list);
                } catch (ConnexionException ex) {
                    Logger.getLogger(PanneauPraticiens.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        //+-----------------------------------------------------------------------------------------
        //+-----------------------------------------------------------------------------------------

        public String getCritereTri(){
            return this.critSelect ;
        }
    }


    //select vis_matricule , vis_nom , vis_prenom from Visiteur ;
    //select * from RapportVisite where vis_matricule = 'a17' and month(rap_date_visite) = 10 and year(rap_date_visite) = 2020  ;
    //update RapportVisite set rap_lu = 1 where rap_num = 4 and vis_matricule = 't60' ;