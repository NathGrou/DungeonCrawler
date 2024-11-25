import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GameEngine implements Engine, KeyListener {
    private Hero hero;
    private ArrayList<Enemy> enemies;
    private boolean isMoving;

    public GameEngine(Hero hero) {
        this.hero = hero;
        this.enemies = new ArrayList<>();
        this.isMoving = false;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_SPACE:
                // Attaque du héros
                hero.attack(enemies);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Arrêter le mouvement quand les touches de direction sont relâchées
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT:
                isMoving = false;
                break;
        }

    }
}
