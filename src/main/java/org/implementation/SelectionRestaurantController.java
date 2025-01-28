package org.implementation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SelectionRestaurantController {

    @FXML
    private ComboBox<String> comboRestaurants;

    @FXML
    private Button boutonSuivant;

    @FXML
    private Button boutonPrecedent;

    @FXML
    private void initialize() {
        comboRestaurants.getItems().addAll("Restaurant A", "Restaurant B", "Restaurant C");
    }

    @FXML
    private void handleSuivantAction(ActionEvent event) {
        if (comboRestaurants.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sélection requise");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir un restaurant avant de continuer.");
            alert.showAndWait();
        } else {
            // Redirection vers la page de menu du restaurant
            ouvrirMenuRestaurant(event);
        }
    }

    @FXML
    private void handlePrecedentAction(ActionEvent event) {
            try {
                Parent vuePrecedente = FXMLLoader.load(getClass().getResource("/org/implementation/authentificationVue.fxml"));
                Scene scene = new Scene(vuePrecedente);
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                //  affiche une alerte d'erreur si le chargement de la vue échoue
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Impossible de charger la vue précédente");
                alert.setContentText("Une erreur est survenue lors du retour à la page précédente.");
                alert.showAndWait();
            }
        }

    private void ouvrirMenuRestaurant(ActionEvent event) {
        try {
            Parent menuRestaurant = FXMLLoader.load(getClass().getResource("/org/implementation/menuVue.fxml")); // Assurez-vous que le chemin est correct
            Scene scene = new Scene(menuRestaurant);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'exception
        }
    }
}