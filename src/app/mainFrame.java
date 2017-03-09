/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JFrame;
import ws3dproxy.CommandExecException;
import ws3dproxy.WS3DProxy;
import ws3dproxy.model.Creature;
import ws3dproxy.model.Thing;
import ws3dproxy.model.World;
import ws3dproxy.model.WorldPoint;

/**
 *
 * @author ia941
 */
public class mainFrame extends JFrame implements KeyListener {

    private static Creature creature;

    private static void CreatureCreate() {
        WS3DProxy proxy = new WS3DProxy();
        try {
            World w = World.getInstance();
            w.reset();
            World.createFood(0, 350, 75);
            World.createFood(0, 100, 220);
            World.createFood(0, 250, 210);
            creature = proxy.createCreature(100, 450, 0);
            creature.start();
            WorldPoint position = creature.getPosition();
            double pitch = creature.getPitch();
            double fuel = creature.getFuel();
            
        } catch (CommandExecException e) {
            System.out.println("Erro capturado");
        }
    }

    private mainDraw draw;

    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
    }

    public void keyReleased(KeyEvent e) {
        try {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                draw.moveRight();
                creature.rotate(1);
                creature.updateState();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                creature.rotate(-1);
                draw.moveLeft();
                creature.updateState();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                creature.move(-1.0, -1.0, 1.0);
                creature.updateState();
                draw.moveDown();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                draw.moveUp();
                creature.move(1.0, 1.0, 1.0);
                creature.updateState();
            } else if (e.getKeyCode() == KeyEvent.VK_C) {
                
               List<Thing> thingsList =  creature.getThingsInVision();
                
                for (Thing t : thingsList) {
                    System.out.println(t.getName());
                    System.out.println(t.getCategory());
                   double distance =  creature.calculateDistanceTo(t);
                }
               
                
                creature.updateState();
            }

        } catch (Exception ex) {
            System.out.println("Erro:" + ex.getMessage());
        }
    }

    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }

    public mainFrame() {
        this.draw = new mainDraw();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public static void main(String[] args) {

        CreatureCreate();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame frame = new mainFrame();
                frame.setTitle("Square Move Practice");
                frame.setResizable(false);
                frame.setSize(300, 300);
                frame.setMinimumSize(new Dimension(600, 600));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(frame.draw);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}
