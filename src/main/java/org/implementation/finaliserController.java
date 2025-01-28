package org.implementation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class finaliserController {

    @FXML
    private ComboBox<String> comboLivraison;  // ComboBox pour choisir le mode de livraison

    @FXML
    private ComboBox<String> comboPaiement;  // ComboBox pour choisir le mode de paiement

    @FXML
    private Button boutonPrecedent;  // Bouton pour retourner à la vue précédente

    @FXML
    private Button boutonSuivant;  // Bouton pour avancer à la vue de paiement

    // Méthode appelée automatiquement à la création de la vue pour initialiser les composants.
    @FXML
    private void initialize() {
        // Ajout des options disponibles pour la livraison et le paiement.
        comboLivraison.getItems().addAll("Livraison à domicile", "Collecte en magasin");
        comboPaiement.getItems().addAll("Paiement en ligne", "Paiement à la livraison");
    }

    // Gère l'action du bouton 'Précédent'.
    @FXML
    private void handlePrecedentAction(ActionEvent event) {
        changerVue(event, "/org/implementation/panierVue.fxml");
    }

    // Gère l'action du bouton 'Suivant'.
    @FXML
    private void handleSuivantAction(ActionEvent event) {
        if (comboLivraison.getValue() == null || comboPaiement.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sélection requise");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir le mode de livraison et l'option de paiement avant de continuer.");
            alert.showAndWait();
        } else {
            changerVue(event, "/org/implementation/paiementVue.fxml");
        }
    }

    // Méthode pour changer de vue.
    private void changerVue(ActionEvent event, String vuePath) {
        try {
            Parent nouvelleVue = FXMLLoader.load(getClass().getResource(vuePath));
            Scene scene = new Scene(nouvelleVue);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de charger la vue");
            alert.setContentText("Une erreur est survenue lors du chargement de la page demandée.");
            alert.showAndWait();
        }
    }
}