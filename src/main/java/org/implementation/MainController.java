package org.implementation;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private TextField champNomUtilisateur;

    @FXML
    private PasswordField champMotDePasse;

    @FXML
    private Button boutonConnexion;

    @FXML
    private Button boutonEnregistrement;

    @FXML
    private void handleLoginAction(ActionEvent event) {
        String nomUtilisateur = champNomUtilisateur.getText();
        String motDePasse = champMotDePasse.getText();
        if (SystemeAuthentification.verifierIdentifiants(nomUtilisateur, motDePasse)) {
            afficherAlerte("Approuvé", "Connexion réussie !", Alert.AlertType.INFORMATION);
            // Redirection vers la page de sélection de restaurant
            changerVue("SelectionRestaurant.fxml", event);
        } else {
            afficherAlerte("Erreur de connexion", "Nom d'utilisateur ou mot de passe incorrect.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleRegisterAction(ActionEvent event) {
        try {
            // Redirection vers la page d'enregistrement
            changerVue("enregistrementVue.fxml", event);
        } catch (Exception e) {
            afficherAlerte("Erreur de chargement", "Impossible de charger la vue d'enregistrement: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Méthode pour changer de vue
    private void changerVue(String nomFxml, ActionEvent event) {
        try {
            FXMLLoader chargeur = new FXMLLoader();
            chargeur.setLocation(getClass().getResource(nomFxml));
            Parent vueParent = chargeur.load();
            Scene scene = new Scene(vueParent);
            Stage scenePrincipale = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scenePrincipale.setScene(scene);
            scenePrincipale.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode utilitaire pour afficher des alertes
    private void afficherAlerte(String titre, String message, Alert.AlertType typeAlerte) {
        Alert alerte = new Alert(typeAlerte);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(message);
        alerte.showAndWait();
    }
}

// Classe SystemeAuthentification pour gérer la logique d'authentification
class SystemeAuthentification {
    public static boolean verifierIdentifiants(String nomUtilisateur, String motDePasse) {
        // Ici, on simule une vérification d'identifiants.
        return "admin".equals(nomUtilisateur) && "admin".equals(motDePasse);
    }
}