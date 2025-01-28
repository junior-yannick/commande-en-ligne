package org.implementation;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class supprimerCommandeController {
    @FXML
    private ListView<String> listViewProduits;

    public void initialize() {
        listViewProduits.setItems(supprimerModel.getInstance().getProduitsSelectionnes());
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

    @FXML
    private void handleSupprimerProduit() {
        // Vérification pour s'assurer qu'il y a des produits à supprimer
        if (!supprimerModel.getInstance().getProduitsSelectionnes().isEmpty()) {
            supprimerModel.getInstance().getProduitsSelectionnes().clear(); // Supprime tous les produits
            afficherConfirmationSuppression(); // Affiche une alerte de confirmation
        }
    }

    // Méthode utilitaire pour afficher des alertes de confirmation
    private void afficherConfirmationSuppression() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("La commande a été supprimé avec succès.");

        // Écouteur pour la réponse de l'utilisateur
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();  // Arrête l'application si l'utilisateur clique sur OK
            }
        });
    }
}
