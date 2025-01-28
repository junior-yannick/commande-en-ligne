package org.implementation;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;

public class paiementController {

    @FXML
    private TextField numeroCarteTextField;

    @FXML
    private TextField cvvTextField;

    @FXML
    private DatePicker datePickerExpiration;

    @FXML
    private Button payerButton;



    @FXML
    void handlePayerAction(ActionEvent event) {
        if (validerDonneesCarte()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Paiement effectué");
            alert.setHeaderText(null);
            alert.setContentText("Le paiement a été effectué avec succès.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Platform.exit();  // Arrête l'application lorsque l'utilisateur clique sur OK
                }
            });
        }
    }


    private boolean validerDonneesCarte() {
        String numeroCarte = numeroCarteTextField.getText();
        String cvv = cvvTextField.getText();
        LocalDate dateExpiration = datePickerExpiration.getValue();

        // Validation du numéro de la carte (exemple simplifié, normalement une regex plus complexe est nécessaire)
        if (numeroCarte == null || !numeroCarte.matches("\\d{16}")) {
            afficherAlerte("Numéro de carte invalide", "Veuillez entrer un numéro de carte valide à 16 chiffres.");
            return false;
        }

        // Validation du CVV
        if (cvv == null || !cvv.matches("\\d{3}")) {
            afficherAlerte("CVV invalide", "Veuillez entrer un code CVV valide à 3 chiffres.");
            return false;
        }

        // Validation de la date d'expiration
        if (dateExpiration == null || dateExpiration.isBefore(LocalDate.now())) {
            afficherAlerte("Date d'expiration invalide", "La date d'expiration doit être dans le futur.");
            return false;
        }

        return true;
    }

    @FXML
    private void handleSupprimerAction(ActionEvent event) {
        try {
            Parent vuePrecedente = FXMLLoader.load(getClass().getResource("/org/implementation/supprimerCommandeVue.fxml"));
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

    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
