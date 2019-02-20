package pobj.motx.tme1;

public class Grille {
	/**
	 * attributs de la classe Grille : 
	 * hauteur de la grille
	 * largeur de la grille
	 * une matrice de Case de taille hauteur x largeur
	 * par défaut on initialise toutes les cases pleines
	 */
	private int hauteur;
	private int largeur;
	private Case[][] grille;
	/**
	 * constructeur qui prend en paramètres la longueur et la largeur
	 * crée la grille, l'initialise et la remplit
	 * @param hauteur
	 * @param largeur
	 */
	public Grille(int hauteur, int largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		grille = new Case[hauteur][largeur];
		for(int i = 0; i < hauteur; i++) {
			for(int j = 0; j < largeur; j++) {
				grille[i][j] = new Case(i, j, ' ');
			}
		}
	}
	/**
	 * prend en paramètres une ligne et une colonne de la grille
	 * retourne la case contenue dans la grille à l'indice ligne x colonne
	 * @param lig
	 * @param col
	 * @return grille[i][j] (de type Case)
	 */
	public Case getCase(int lig, int col) {
		return this.grille[lig][col];
	}
	/**
	 * on affiche la grille
	 * fonction contenue dans GrilleLoadeur.java
	 */
	public String toString() {
		return GrilleLoader.serialize(this, false);
	}
	/**
	 * retourne le nombre de lignes de la grille
	 * @return hauteur
	 */
	public int nbLig() {
		return this.hauteur;
	}
	/**
	 * retourne le nombre de colonnes de la grille
	 * @return largeur
	 */
	public int nbCol() {
		return this.largeur;
	}
	/**
	 * crée une copie profonde de la grille
	 * on crée une grille vide qui sera retournée
	 * on l'initialise et on la remplit avec le contenu de la grille que l'on veut copier
	 * on prend soin de créer une nouvelle instance à chaque fois pour ne pas donner de référence
	 * pour ne pas générer d'erreurs sur les valeurs
	 * @return res (de type Grille, contient la copie de la grille)
	 */
	public Grille copy() {
		Grille res = new Grille(this.hauteur, this.largeur);
		for(int i = 0; i < this.hauteur; i++) {
			for(int j = 0; j < this.largeur; j++) {
				res.grille[i][j] = new Case(i, j, this.grille[i][j].getChar());
			}
		}
		return res;
	}

}
