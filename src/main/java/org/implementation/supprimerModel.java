package org.implementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class supprimerModel {
    private static supprimerModel instance;
    private ObservableList<String> produitsSelectionnes = FXCollections.observableArrayList();

    private supprimerModel() {}

    public static supprimerModel getInstance() {
        if (instance == null) {
            instance = new supprimerModel();
        }
        return instance;
    }

    public ObservableList<String> getProduitsSelectionnes() {
        return produitsSelectionnes;
    }

    public void ajouterProduit(String nom) {
        if (!produitsSelectionnes.contains(nom)) {
            produitsSelectionnes.add(nom);
        }
    }

    public void retirerProduit(String nom) {
        produitsSelectionnes.remove(nom);
    }
}
