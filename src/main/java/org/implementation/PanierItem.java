package org.implementation;


public class PanierItem {
    private modeleClass produit; // Le produit associé à cet item du panier
    private int quantite; // La quantité de ce produit dans le panier

    // Constructeur qui initialise l'item du panier avec un produit et une quantité spécifique.
    public PanierItem(modeleClass produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    // Retourne le produit associé à cet item du panier.
    public modeleClass getProduit() {
        return produit;
    }

    // Modifie le produit associé à cet item du panier.
    public void setProduit(modeleClass produit) {
        this.produit = produit;
    }

    // Retourne la quantité de ce produit dans le panier.
    public int getQuantite() {
        return quantite;
    }

    // Modifie la quantité de ce produit dans le panier.
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Calcule le prix total pour cet item du panier, basé sur la quantité et le prix unitaire du produit.
    public double getTotalPrix() {
        return quantite * produit.getPrix();
    }

    // Représentation sous forme de chaîne de cet item du panier pour l'affichage.
    @Override
    public String toString() {
        return String.format("%s x %d = %.1f $", produit.getNom(), quantite, getTotalPrix()); // Formate le total avec deux décimales
    }
}