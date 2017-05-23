/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import ws3dproxy.CommandExecException;
import ws3dproxy.WS3DProxy;
import ws3dproxy.model.Creature;
import ws3dproxy.model.CreatureState;
import ws3dproxy.model.World;
import ws3dproxy.model.WorldPoint;

/**
 *
 * @author alciomar.hollanda
 */
public class CreatureApp {
    private static Creature creature;
    
    public static Creature CreatureCreate() {
        WS3DProxy proxy = new WS3DProxy();
        try {
            
            creature = proxy.createCreature(100, 450, 10);
            
            creature.start();
            
            WorldPoint position = creature.getPosition();
            double pitch = creature.getPitch();
            double fuel = creature.getFuel();
            
            creature.genLeaflet();
           
            
            
            
        } catch (CommandExecException e) {
            System.out.println("Erro capturado");
        }
        return creature;
    }
    
    
    public static Creature getInstance() {
        
        return creature;
    }
}
