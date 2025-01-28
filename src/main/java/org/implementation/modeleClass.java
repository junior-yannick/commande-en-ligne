package org.implementation;


public class modeleClass {
    private String nom;  // Nom du produit
    private double prix; // Prix unitaire du produit
    private int quantite; // Quantité du produit en stock

    // Constructeur qui initialise le produit avec un nom et un prix. La quantité est initialisée à zéro par défaut.
    public modeleClass(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = 0; // Initialise la quantité à 0 par défaut
    }

    // Retourne le nom du produit.
    public String getNom() {
        return nom;
    }

    // Met à jour le nom du produit.
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Retourne le prix du produit.
    public double getPrix() {
        return prix;
    }

    // Met à jour le prix du produit.
    public void setPrix(double prix) {
        this.prix = prix;
    }

    // Retourne la quantité du produit en stock.
    public int getQuantite() {
        return quantite;
    }

    // Met à jour la quantité du produit en stock.
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Méthode pour afficher les informations du produit
    @Override
    public String toString() {
        // Détermine la longueur maximale pour le nom (par exemple, 20 caractères)
        int longueurMaxNom = 20;

        // Détermine la longueur maximale pour l'espacement entre le nom et le prix
        int espacementPrix = 130;

        // Crée une chaîne formatée avec un espace entre le nom et le prix
        String formatNomPrix = "%-" + longueurMaxNom + "s %" + espacementPrix + "s%.2f $";

        // Utilise la chaîne formatée pour générer la représentation sous forme de chaîne
        return String.format(formatNomPrix, nom, "", prix);
    }
}