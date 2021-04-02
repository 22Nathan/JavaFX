/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.presentation;

import javafx.scene.control.Dialog;
import javafx.util.Pair;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 *
 * @author developpeur
 */
public class VueConnexion extends Dialog<Pair<String,String>>{   
    
    public VueConnexion(){
        super() ;
        //Dialog<Pair<String, String>> dialog = new Dialog<>();
        super.setTitle("Authentification");
        super.setHeaderText("Demande d'authentification");   
        
               //ButtonType OK = new ButtonType( "confirmer" , ButtonData.OK_DONE ) ;
               //ButtonType CANCEL = new ButtonType( "annuler" , ButtonData.CANCEL_CLOSE ) ;
                 ButtonType ButtonTypeOkDone = new ButtonType("Login", ButtonData.OK_DONE) ; 
                 ButtonType ButtonTypeCancelClose = new ButtonType("Fermer" , ButtonData.CANCEL_CLOSE) ;
               //dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
                 super.getDialogPane().getButtonTypes().addAll(ButtonTypeOkDone, ButtonTypeCancelClose) ;
                 
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField username = new TextField();
                username.setPromptText("");
                PasswordField password = new PasswordField();
                password.setPromptText("");

                grid.add(new Label("Identifiant :"), 0, 0);
                grid.add(username, 1, 0);
                grid.add(new Label("Mot de passe :"), 0, 1);
                grid.add(password, 1, 1);
                
                super.getDialogPane().setContent(grid);
  
                super.setResultConverter(
                new Callback<ButtonType, Pair<String,String>>() {
                    @Override
                    public Pair<String, String> call(ButtonType typeBouton) {      
                        if ( typeBouton == ButtonTypeOkDone ) {
                        //if ( "OK".equals(reponse.get()) ) {
                            return new Pair<>(username.getText(), password.getText());
                        } else {
                        return new Pair<>("non" , "non") ; }
                        }
                    }
                ) ;      
    }
}