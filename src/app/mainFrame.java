/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JFrame;
import ws3dproxy.CommandExecException;
import ws3dproxy.WS3DProxy;
import ws3dproxy.model.Creature;
import ws3dproxy.model.Leaflet;
import ws3dproxy.model.Thing;
import ws3dproxy.model.World;
import ws3dproxy.model.WorldPoint;

/**
 *
 * @author ia941
 */
public class mainFrame extends JFrame implements KeyListener {

    private static World w;
    private static Creature creatureApp;
    List<String> HideThigs = new ArrayList<>();

    private mainDraw draw;

    public void keyPressed(KeyEvent e) {
        //System.out.println("keyPressed");
    }

    public void keyReleased(KeyEvent e) {
        try {        

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                draw.moveRight();
                creatureApp.rotate(1);

            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                creatureApp.rotate(-1);
                draw.moveLeft();

            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                creatureApp.move(-1.0, -1.0, 1.0);

                draw.moveDown();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                draw.moveUp();
                creatureApp.move(1.0, 1.0, 1.0);

            } else if (e.getKeyCode() == KeyEvent.VK_C) {

                List<Thing> thingsList = creatureApp.getThingsInVision();
                String nome = creatureApp.getName();
                String nomeThings = creatureApp.getThingsNames();

                for (Thing t : thingsList) {
                    System.out.println(t.getName());
                    System.out.println(t.getCategory());
                    double distance = creatureApp.calculateDistanceTo(t);

                    if (creatureApp.calculateDistanceTo(t) <= 50) {
                        creatureApp.eatIt(t.getName());

                    }
                }

            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                List<Thing> thingsList = creatureApp.getThingsInVision();

                for (Thing t : thingsList) {
                    double distance = creatureApp.calculateDistanceTo(t);
                    if (creatureApp.calculateDistanceTo(t) <= 50) {
                        creatureApp.putInSack(t.getName());
                        System.out.println("Put In Sack: " + t.getName());
                    }
                }
            } else if (e.getKeyCode() == KeyEvent.VK_H) {
                List<Thing> thingsList = creatureApp.getThingsInVision();

                for (Thing t : thingsList) {
                    double distance = creatureApp.calculateDistanceTo(t);
                    if (creatureApp.calculateDistanceTo(t) <= 100) {
                        creatureApp.hideIt(t.getName());

                        HideThigs.add(t.getName());
                        System.out.println("HideIt: " + t.getName());
                    }
                }

            } else if (e.getKeyCode() == KeyEvent.VK_U) {

                if (HideThigs != null) {
                    for (String t : HideThigs) {
                        creatureApp.unhideIt(t);
                    }
                }
            } else if (e.getKeyCode() == KeyEvent.VK_L) {
                List<Leaflet> leaflets = creatureApp.getLeaflets();

                for (Leaflet item : leaflets) {
                    System.out.println("getLeaflets: " + item.getID());
                    creatureApp.genLeaflet();
                    creatureApp.deliverLeaflet(item.getID().toString());
                }
            } else if (e.getKeyCode() == KeyEvent.VK_B) {
                creatureApp.updateBag();
            }

        } catch (Exception ex) {
            System.out.println("Erro:" + ex.getMessage());
        }
    }

    public void keyTyped(KeyEvent e) {
        //System.out.println("keyTyped");
    }

    public mainFrame() {
        this.draw = new mainDraw();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public static void main(String[] args) {

        //Create Object
        try {
            WorldApp.CreateAndRestartWorld();
            creatureApp = CreatureApp.CreatureCreate();

            WorldApp.TimeGameCreateFood();
            WorldApp.OnTime(creatureApp);

        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainFrame frame = new mainFrame();
                frame.setTitle("Square Move Practice");
                frame.setResizable(false);
                frame.setSize(170, 170);
                frame.setMinimumSize(new Dimension(170, 170));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(frame.draw);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}
