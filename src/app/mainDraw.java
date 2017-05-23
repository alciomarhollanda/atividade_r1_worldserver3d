/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author ia941
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;
import ws3dproxy.model.Creature;

public class mainDraw extends JComponent {

    private  int x = 50;
    private  int y = 50;
    private Graphics g ;
   

    public void paintComponent(Graphics g) {
       
        this.g = g;
        super.paintComponent(g);
        
        g.drawRect(x, y, 50, 50);
        g.fillRect(x, y, 50, 50);
        g.setColor(Color.BLACK);
        
        OnTime();
        
    }

    public void moveRight() {
        x = x + 5;
        
        repaint();
        
    }

    public void moveLeft() {
        x = x - 5;
        
        repaint();
    }

    public void moveDown() {
        y = y + 5;
        
        repaint();
    }

    public void moveUp() {
        y = y - 5;        
        repaint();
    }
         
    
    public void OnTime() {
        ActionListener actListner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                x = 50;
                y = 50;
            }
        };
        Timer timer = new Timer(7000, actListner);
        timer.start();
    }
       
    
}
