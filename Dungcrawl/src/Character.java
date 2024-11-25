import java.awt.Image;

public abstract class Character extends DynamicSprite implements Damageable {
    protected int maxHealth;
    protected int currentHealth;
    protected boolean alive = true;

    public Character(double x, double y, Image image, double width, double height, int maxHealth) {
        super(x, y, image, width, height);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    @Override
    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth <= 0) {
            currentHealth = 0;
            alive = false;
        }
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public int getHealth() {
        return currentHealth;
    }
}