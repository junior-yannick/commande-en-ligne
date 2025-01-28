package org.implementation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class menuController {

    @FXML
    private void handleMonterCommande(ActionEvent event) {
        chargerVue("/org/implementation/monterCommandeVue.fxml", event);
    }



    @FXML
    private void handleSupprimerCompte(ActionEvent event) {
        chargerVue("/org/implementation/supprimerInformationVue.fxml", event);
    }

    private void chargerVue(String fxmlPath, ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
