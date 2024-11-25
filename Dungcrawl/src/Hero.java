import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


public class Hero extends Character {
    private int attackDamage;
    private double attackRange;
    private long lastAttackTime;
    private int attackCooldown;
    private boolean isAttacking;

    public Hero(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height, 100);
        this.attackDamage = 25;
        this.attackRange = 60;  // Distance d'attaque
        this.attackCooldown = 500; // 500ms entre chaque attaque
        this.isAttacking = false;
    }

    public void attack(ArrayList<Enemy> enemies) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAttackTime < attackCooldown) {
            return;  // Encore en cooldown
        }

        isAttacking = true;
        lastAttackTime = currentTime;

        // Calculer la zone d'attaque en fonction de la direction
        Rectangle2D.Double attackArea = getAttackArea();

        // Vérifier les ennemis touchés
        for (Enemy enemy : enemies) {
            if (enemy.isAlive() && enemy.getHitBox().intersects(attackArea)) {
                enemy.takeDamage(attackDamage);
            }
        }
    }

    private Rectangle2D.Double getAttackArea() {
        double attackX = x;
        double attackY = y;

        switch (getDirection()) {
            case NORTH -> attackY -= attackRange;
            case SOUTH -> attackY += height;
            case EAST -> attackX += width;
            case WEST -> attackX -= attackRange;
        }

        return switch (getDirection()) {
            case NORTH, SOUTH -> new Rectangle2D.Double(x, attackY, width, attackRange);
            case EAST, WEST -> new Rectangle2D.Double(attackX, y, attackRange, height);
        };
    }

    public boolean isAttacking() {
        return isAttacking && System.currentTimeMillis() - lastAttackTime < 200; // Animation d'attaque dure 200ms
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        // Optionnel : Afficher la zone d'attaque quand le héros attaque
        if (isAttacking()) {
            Rectangle2D.Double attackArea = getAttackArea();
            g.setColor(new Color(255, 0, 0, 100));
            g.fillRect((int)attackArea.x, (int)attackArea.y,
                    (int)attackArea.width, (int)attackArea.height);
        }
    }
}