package org.implementation;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;


public class enregistrementController {

    @FXML
    private TextField champNom, champPrenom, champAdresse, champEmail, champNumCarte, champCodeSecurite, champDateExpiration;
    @FXML
    private Button boutonEnregistrer, boutonPrecedent;

    // Gère l'action du bouton 'Enregistrer'.
    @FXML
    private void handleEnregistrerAction(ActionEvent event) {
        if (validerDonnees()) {
            String userData = collecterDonnees();
            DataManager.getInstance().setUserData(userData);  // Stocker les données utilisateur collectées.

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation d'enregistrement");
            alert.setHeaderText(null);
            alert.setContentText("Vos informations ont été enregistrées avec succès.");
            alert.showAndWait();

            // Changement de vue après enregistrement réussi.
            changerVue(event, "/org/implementation/selectionRestaurant.fxml");
        }
    }

    // Gère l'action du bouton 'Précédent'.
    @FXML
    private void handlePrecedentAction(ActionEvent event) {
        changerVue(event, "/org/implementation/authentificationVue.fxml");
    }

    // Valide les données saisies par l'utilisateur.
    private boolean validerDonnees() {
        if (champNom.getText().isEmpty() || champPrenom.getText().isEmpty() ||
                champAdresse.getText().isEmpty() || champEmail.getText().isEmpty() ||
                !champEmail.getText().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$") ||
                !champNumCarte.getText().matches("\\d{16}") ||
                !champCodeSecurite.getText().matches("\\d{3}") ||
                !validerDateExpiration(champDateExpiration.getText())) {
            alertErreur("Veuillez vérifier vos informations et essayer à nouveau.");
            return false;
        }
        return true;
    }

    // Vérifie si la date d'expiration de la carte est valide.
    private boolean validerDateExpiration(String date) {
        return date.matches("(0[1-9]|1[0-2])/([0-9]{4})");
    }

    // Affiche une alerte en cas d'erreur de validation.
    private void alertErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de Validation");
        alert.setHeaderText("Erreur dans les informations fournies");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Collecte les données saisies par l'utilisateur.
    private String collecterDonnees() {
        return String.format("Nom:    %s\n\nPrénom:  %s\n\nAdresse:    %s\n\nEmail:   %s\n\nNuméro de carte:    %s\n\nCVV:   %s\n\nDate d'expiration:   %s",
                champNom.getText(), champPrenom.getText(), champAdresse.getText(), champEmail.getText(),
                champNumCarte.getText(), champCodeSecurite.getText(), champDateExpiration.getText());
    }

    // Change la vue en chargeant une nouvelle scène.
    private void changerVue(ActionEvent event, String vuePath) {
        try {
            Parent nouvelleVue = FXMLLoader.load(getClass().getResource(vuePath));
            Scene scene = new Scene(nouvelleVue);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            alertErreur("Impossible de charger la vue suivante.");
        }
    }
}