import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Enemy extends Character {
    private int damage;
    private double detectionRange;
    private Hero targetHero;
    private long lastAttackTime;
    private int attackCooldown; // en millisecondes

    public Enemy(double x, double y, Image image, double width, double height,
                 Hero hero, int damage, double detectionRange) {
        super(x, y, image, width, height, 50); // 50 points de vie par défaut
        this.targetHero = hero;
        this.damage = damage;
        this.detectionRange = detectionRange;
        this.attackCooldown = 1000; // 1 seconde entre chaque attaque
    }

    public void update() {
        if (!isAlive() || !targetHero.isAlive()) return;

        // Calculer la distance avec le héros
        double dx = targetHero.x - this.x;
        double dy = targetHero.y - this.y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Si le héros est dans la zone de détection
        if (distance <= detectionRange) {
            // Déplacer vers le héros
            if (dx > 0) setDirection(Direction.EAST);
            else if (dx < 0) setDirection(Direction.WEST);
            else if (dy > 0) setDirection(Direction.SOUTH);
            else if (dy < 0) setDirection(Direction.NORTH);

            // Attaquer si assez proche et si le cooldown est terminé
            if (distance < width && System.currentTimeMillis() - lastAttackTime > attackCooldown) {
                attackHero();
            }
        }
    }

    private void attackHero() {
        if (targetHero.isAlive()) {
            targetHero.takeDamage(damage);
            lastAttackTime = System.currentTimeMillis();
        }
    }
}