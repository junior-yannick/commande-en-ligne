package org.implementation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class monterCommandeController {

    @FXML
    private ListView<modeleClass> listViewProduits;

    private panierController panierCtrl;

    // Liste observable des produits qui seront affichés dans la ListView
    private ObservableList<modeleClass> produits = FXCollections.observableArrayList();

    // Méthode appelée pour initialiser le contrôleur après le chargement du FXML
    public void initialize() {
        // Ajouter des produits  à la liste observable
        produits.addAll(
                new modeleClass("Big mag",9.63),
                new modeleClass("Frite",3.59),
                new modeleClass("CheeseBurgur",6.99),
                new modeleClass("Poulet",5.22),
                new modeleClass("Bouteille d'eau",4.79),
                new modeleClass("Ndolet",10.79),
                new modeleClass("Plantain",8.09),
                new modeleClass("Mac poulet",14.35),
                new modeleClass("Jus de pomme",6.24),
                new modeleClass("Jus d'orange",2.28),
                new modeleClass("Tomate",7.83),
                new modeleClass("Coca Cola",9.58)
    );
        listViewProduits.setItems(produits); // Associe la liste observable avec la ListView
    }

    // Méthode appelée lorsque l'utilisateur clique sur le bouton pour ajouter au panier
    @FXML
    private void handleAjouterAuPanier() {
        modeleClass produitSelectionne = listViewProduits.getSelectionModel().getSelectedItem();
        if (produitSelectionne != null) {
            supprimerModel.getInstance().ajouterProduit(produitSelectionne.getNom());
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Quantité du Produit");
            dialog.setHeaderText("Sélectionnez la quantité pour " + produitSelectionne.getNom());
            dialog.setContentText("Quantité:");

            dialog.showAndWait().ifPresent(quantite -> {
                try {
                    int qte = Integer.parseInt(quantite);
                    if (qte > 0) {
                        panierModel.getInstance().ajouterProduit(produitSelectionne, qte);
                    } else {
                        afficherAlerteErreur("Quantité Invalide", "La quantité doit être supérieure à zéro.");
                    }
                } catch (NumberFormatException e) {
                    afficherAlerteErreur("Quantité Invalide", "Veuillez entrer un nombre valide pour la quantité.");
                }
            });
        } else {
            afficherAlerteErreur("Aucun produit sélectionné", "Veuillez sélectionner un produit avant d'ajouter au panier.");
        }
    }


    @FXML
    private void handleAllerPanier(ActionEvent event) {
        chargerVue("/org/implementation/panierVue.fxml", event);
    }

    @FXML
    private void handlePrecedent(ActionEvent event) {
        chargerVue("/org/implementation/menuVue.fxml", event);
    }

    private void chargerVue(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            afficherAlerteErreur("Erreur de Chargement", "Une erreur s'est produite lors du chargement de la vue.");
        }
    }
    // Méthode appelée lorsque l'utilisateur clique sur le bouton pour annuler
    @FXML
    private void handleAnnuler() {
        // Simule la logique d'annulation
        listViewProduits.getSelectionModel().clearSelection();
    }

    // Méthode utilitaire pour afficher des alertes d'erreur
    private void afficherAlerteErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
