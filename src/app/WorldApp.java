/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import ws3dproxy.CommandExecException;
import ws3dproxy.WS3DProxy;
import ws3dproxy.model.*;

/**
 *
 * @author alciomar.hollanda
 */
public class WorldApp {

    static void CreateAndRestartWorld() {
        WS3DProxy proxy = new WS3DProxy();
        try {
            World w = World.getInstance();

            w.reset();
            
            

        } catch (CommandExecException e) {
            System.out.println("Erro capturado");
        }
    }
    static int cnt;   
    static int countJewel = 0;

    static void TimeGameCreateFood() {

        ActionListener actListner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                try {
                    Random gerador = new Random();

                    for (int i = 0; i < 2; i++) {
                        int x = gerador.nextInt(600);
                        int y = gerador.nextInt(600);
                        int type = gerador.nextInt(2);

                       
                        World.createFood(type, x, y);
                        //World.createBrick(type, x+7, y+7, x+10, y+10);

                        countJewel++;
                        

                        if (countJewel == 2) {
                            
                            int xj = gerador.nextInt(600);
                            int yj = gerador.nextInt(600);
                            int typej = gerador.nextInt(6);

                            World.createJewel(typej, xj, yj);
                            
                            
                            countJewel = 0;
                            System.out.println("NewJewel");
                        }

                    }

                } catch (CommandExecException ex) {
                    Logger.getLogger(WorldApp.class.getName()).log(Level.SEVERE, null, ex);
                }

                cnt += 1;
                
            }
        };
        Timer timer = new Timer(10000, actListner);
        timer.start();
    }

    static void OnTime(Creature creatureApp) {
        ActionListener actListner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                creatureApp.updateState();
            }
        };
        Timer timer = new Timer(100, actListner);
        timer.start();
    }

}
