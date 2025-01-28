package org.implementation;

// Classe DataManager utilisée pour gérer les données utilisateur de manière centralisée.
public class DataManager {
    // Instance statique pour implémenter le pattern Singleton.
    private static DataManager instance;
    // Attribut pour stocker les données utilisateur.
    private String userData;

    // Constructeur privé pour empêcher l'instanciation directe et garantir une unique instance de la classe.
    private DataManager() {}

    // Méthode publique statique pour obtenir l'instance de DataManager
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    // Méthode pour définir les données utilisateur.
    public void setUserData(String data) {
        this.userData = data;
    }

    // Méthode pour récupérer les données utilisateur.
    public String getUserData() {
        return userData;
    }

    // Méthode pour effacer les données utilisateur, remettant l'attribut à null.
    public void clearUserData() {
        userData = null;  // Efface les données utilisateur
    }
}