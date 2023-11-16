package src.gui.View;

//Import de package
import javax.swing.*;
import java.awt.*;

//Import des autres dossiers
import src.gameobject.Joueur;
import src.gameobject.Plateau;
import src.gameobject.Tuile;
import src.gameobject.ObjCarcassonne.Lieu;
import src.gameobject.ObjCarcassonne.TuileCarcassonne;
import src.gameobject.ObjDominos.TuileDominos;

//Représente la vue du Plateau
public class PlateauView extends JPanel { // Une vue de plateau est un JPanel
    Plateau plateau; // Le plateau représenté
    // Ligne et colonne sont le nombre de lignes et de colonnes du plateau affiché
    // Les autres valeurs x,y sont les coordonnées qui délimitent l'espace affiché
    // En effet, nous n'affichons pas tout le plateau (cf. rapport)
    int xmin, ymin, xmax, ymax, ligne, colonne;

    /// Constructeurs
    public PlateauView(Plateau p) { // Constructeur d'un plateau
        this.plateau = p;
        // Dimensions de l'espace au départ
        this.xmin = plateau.getxCentrePlateau() - 1;
        this.ymin = plateau.getyCentrePlateau() - 1;
        this.xmax = plateau.getxCentrePlateau() + 1;
        this.ymax = plateau.getyCentrePlateau() + 1;
        this.ligne = xmax - xmin + 1;
        this.colonne = ymax - ymin + 1;

        this.setLayout(new GridLayout(ligne, colonne));
        Tuile[][] pla = plateau.getPlateauActuel();
        for (int x = xmin; x <= xmax; x++) {
            for (int y = ymin; y <= ymax; y++) {
                Tuile t = pla[x][y];
                if(t == null){
                    add(new CaseView()); // On ajoute des cases vides
                }
                if(t instanceof TuileDominos){
                    add(new TuileDominosView((TuileDominos) t));
                }
                if(t instanceof TuileCarcassonne){
                    add(new TuileCarcassonneView((TuileCarcassonne) t));
                }
            }
        }
    }

    /// Getter et Setter
    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public int getXmin() {
        return xmin;
    }

    public int getXmax() {
        return xmax;
    }

    public int getYmin() {
        return ymin;
    }

    public int getYmax() {
        return ymax;
    }

    public int getXView(int index) { // Renvoie la coordonnée x sur le plateauView en fonction de l'index
        return ((index - (index % colonne)) / colonne);
    }

    public int getYView(int index) { // Renvoie la coordonnée y sur le plateauView en fonction de l'index
        return (index % colonne);
    }
    
    /// Méthodes de la classe PlateauView
    public void update(int xpl, int ypl, Plateau pl) { // Met à jour la vue du plateau
        // Etape 1 : Acutalisation des variables
        if (xmin > xpl - 1)
            xmin = xpl - 1;
        if (ymin > ypl - 1)
            ymin = ypl - 1;
        if (xmax < xpl + 1)
            xmax = xpl + 1;
        if (ymax < ypl + 1)
            ymax = ypl + 1;

        this.plateau = pl;
        this.ligne = xmax - xmin + 1;
        this.colonne = ymax - ymin + 1;

        /// Etape 2 : Affichage actualisée
        this.removeAll(); // On supprime tout
        this.setLayout(new GridLayout(ligne, colonne)); // On crée une grille à partir des nouvelles variables

        for (int x = xmin; x <= xmax; x++) {
            for (int y = ymin; y <= ymax; y++) {
                Tuile[][] platAct = plateau.getPlateauActuel();
                if (platAct[x][y] == null) { // S'il n'y a pas de tuile, on ajoute une case vide
                    add(new CaseView());
                } else { // Sinon on ajoute la tuile
                    Tuile tpl = platAct[x][y];
                    if (tpl instanceof TuileDominos){
                        add(new TuileDominosView((TuileDominos) tpl));
                    }
                    else {
                        Lieu pin = plateau.getPionplacé()[x][y];
                        int jpin = plateau.getPionJoueur()[x][y];
                        // On crée une vue de Tuile Carcassonne
                        TuileCarcassonne tplc = (TuileCarcassonne)tpl;
                        JPanel j;
                        if(pin != null){
                            j = new TuileCarcassonneView(tplc, pin, Joueur.ListColor[jpin]);
                        }
                        else{
                            j = new TuileCarcassonneView(tplc);
                        }
                        add(j); // On ajoute l'image correspondante
                    }
                }
            }
        }
    }
}