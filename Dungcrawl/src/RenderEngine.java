import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;
    private Hero hero;  // Ajout de la référence au héros

    public RenderEngine(JFrame jFrame, Hero hero) {  // Modifier le constructeur
        this.renderList = new ArrayList<>();
        this.hero = hero;  // Stocker la référence au héros
    }
    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Dessiner tous les objets du jeu
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }

        // Dessiner la barre de vie du héros
        if (hero != null) {
            int health = hero.getHealth();
            int maxHealth = 100;  // Vous pouvez ajouter une méthode getMaxHealth() dans Hero si vous voulez

            // Fond de la barre de vie
            g.setColor(Color.GRAY);
            g.fillRect(10, 10, maxHealth, 20);

            // Barre de vie actuelle
            g.setColor(Color.RED);
            g.fillRect(10, 10, health, 20);

            // Contour de la barre
            g.setColor(Color.BLACK);
            g.drawRect(10, 10, maxHealth, 20);

            // Afficher le nombre de points de vie
            g.setColor(Color.WHITE);
            g.drawString(health + "/" + maxHealth, 45, 25);
        }
    }

    @Override
    public void update(){
        this.repaint();
    }
}
