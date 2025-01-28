package org.implementation;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class supprimerInformationController {

    @FXML
    private Label infoLabel;
    @FXML
    private Button deleteButton;

    @FXML
    private void initialize() {
        // Affiche les données de l'utilisateur à l'écran
        infoLabel.setText(DataManager.getInstance().getUserData());
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) {
        DataManager.getInstance().clearUserData();  // Supprime les données de l'utilisateur
        infoLabel.setText("Les informations ont été supprimées.");  // Met à jour l'affichage

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression réussie");
        alert.setHeaderText(null);
        alert.setContentText("Vos informations ont été supprimées avec succès.");
        alert.showAndWait();
    }

    @FXML
    private void handlePrecedentAction(ActionEvent event) {
        try {
            Parent vuePrecedente = FXMLLoader.load(getClass().getResource("/org/implementation/menuVue.fxml"));
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
}
