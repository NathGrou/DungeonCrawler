import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Hero hero = new Hero(200, 300, ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);
        Enemy enemy = new Enemy(100, 100, ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50, hero, 10, 150);


        renderEngine = new RenderEngine(displayZoneFrame, hero);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);
        gameEngine.addEnemy(enemy);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground("./data/level1.txt");
        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0);
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        renderEngine.addToRenderList(enemy);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.addToMovingSpriteList(enemy);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {
        // write your code here
        Main main = new Main();
    }
}
