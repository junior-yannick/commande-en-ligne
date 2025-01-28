package org.implementation;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class panierModel {

    // L'instance unique de PanierModel (Singleton)
    private static panierModel instance = new panierModel();

    // La liste observable qui contiendra les éléments du panier
    private ObservableList<PanierItem> panierItems = FXCollections.observableArrayList();

    // Le total du panier
    private double total;

    // Constructeur privé pour empêcher l'instanciation externe
    private panierModel() {
        // Attacher un listener à la liste pour mettre à jour le total à chaque modification
        panierItems.addListener((ListChangeListener.Change<? extends PanierItem> change) -> {
            updatePrixTotal();
        });
    }

    // Méthode pour obtenir l'instance unique
    public static panierModel getInstance() {
        return instance;
    }

    // Méthode pour obtenir les éléments du panier
    public ObservableList<PanierItem> getPanierItems() {
        return panierItems;
    }

    // Méthode pour ajouter un produit au panier
    public void ajouterProduit(modeleClass produit, int quantite) {
        // Rechercher si le produit existe déjà dans le panier
        for (PanierItem item : panierItems) {
            if (item.getProduit().equals(produit)) {
                item.setQuantite(item.getQuantite() + quantite); // Augmenter la quantité si déjà présent
                return; // Sortie  après la mise à jour
            }
        }
        // Si le produit n'est pas déjà dans le panier, l'ajouter
        panierItems.add(new PanierItem(produit, quantite));
    }

    // Méthode privée pour mettre à jour le total du panier
    private void updatePrixTotal() {
        total = 0;
        for (PanierItem item : panierItems) {
            total += item.getTotalPrix();
        }
    }

    // Méthode pour obtenir le total du panier
    public double getTotal() {
        return total;
    }
}
