package org.implementation;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class panierController {

    @FXML
    private ListView<PanierItem> listeProduitsPanier;
    @FXML
    private Label labelPrixTotal;

    public void initialize() {
        listeProduitsPanier.setItems(panierModel.getInstance().getPanierItems());
        updatePrixTotal();

        // Utilise un ListChangeListener explicite pour éviter l'ambiguïté
        panierModel.getInstance().getPanierItems().addListener(new ListChangeListener<PanierItem>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends PanierItem> change) {
                updatePrixTotal();
            }
        });
    }

    private void updatePrixTotal() {
        // Mise à jour pour utiliser PanierModel
        labelPrixTotal.setText("Total : " + panierModel.getInstance().getTotal() + " $");
    }

    @FXML
    private void handlePrecedentAction(ActionEvent event) {
        try {
            Parent vuePrecedente = FXMLLoader.load(getClass().getResource("/org/implementation/monterCommandeVue.fxml"));
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
    private void handleSuivantAction(ActionEvent event) {
        try {
            Parent vuePrecedente = FXMLLoader.load(getClass().getResource("/org/implementation/finaliserVue.fxml"));
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
