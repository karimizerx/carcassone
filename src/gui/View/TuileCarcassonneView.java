package src.gui.View;

//Import de packages
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

//Import des autres dossiers
import src.gameobject.ObjCarcassonne.*;

//Représente la vue d'une tuile de Carcassonne
public class TuileCarcassonneView extends JPanel { // Une vue de Tuile Carcassonne est un JPanel
    BufferedImage bi;
    TuileCarcassonne tuile; // La tuile représentée
    String id;
    static String chemin = (new File("src/gui/photos/")).getAbsolutePath();
    Lieu pion; 
    Color jpion;

    /// Constructeurs
    public TuileCarcassonneView() { // Constructeur d'une tuile vide
        id = "88888";
        try {
            bi = ImageIO.read(new File(chemin + "/" + id + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TuileCarcassonneView(TuileCarcassonne tpl) { // Constructeur d'une tuile
    tuile = tpl;
    id = tuile.getId();
    try {
        bi = ImageIO.read(new File(chemin + "/" + id + ".png"));
    } catch (IOException e) {
        //Impossible de lire le fichier
        e.printStackTrace();
    }
}

    public TuileCarcassonneView(TuileCarcassonne tpl, Lieu p, Color jp) { // Constructeur d'une tuile
        tuile = tpl;
        id = tuile.getId();
        pion = p;
        jpion = jp;
        try {
            bi = ImageIO.read(new File(chemin + "/" + id + ".png"));
        } catch (IOException e) {
            //Impossible de lire le fichier
            e.printStackTrace();
        }
    }

    /// Getter et Setter
    public TuileCarcassonne getTuile() {
        return tuile;
    }

    /// Méthodes de la classe Tuile Carcassonne View
    public void paintComponent(Graphics g) {
            g.drawImage(bi, 0, 0, this.getWidth(), this.getHeight(), null);
            if(pion!=null){ // S'il y a un pion a dessiner
                g.setColor(jpion);
                g.fillOval(pionPosY(),pionPosX() , 10, 10);
            }
    }
    
    private int pionPosX(){
        int cnt = 0;
        if(pion == tuile.getHaut()){
            cnt = (this.getHeight()*10)/100;
        }

        if(pion == tuile.getDroite()){
            cnt =  this.getHeight()/2;
        }

        if(pion == tuile.getBas()){
            cnt =  (this.getHeight()*90)/100;
        }

        if(pion == tuile.getGauche()){
            cnt =  this.getHeight()/2;
        }

        if(pion == tuile.getCentre()){
            cnt =  this.getHeight()/2;
        }
        return cnt;
    }

    private int pionPosY(){
        int cnt = 0;
        if(pion == tuile.getHaut()){
            cnt = this.getWidth()/2;
        }

        if(pion == tuile.getDroite()){
            cnt =  (this.getHeight()*90)/100;
        }

        if(pion == tuile.getBas()){
            cnt =  this.getWidth()/2;
        }

        if(pion == tuile.getGauche()){
            cnt =  (this.getHeight()*10)/100;
        }

        if(pion == tuile.getCentre()){
            cnt =  this.getWidth()/2;
        }
        return cnt;
    }

}