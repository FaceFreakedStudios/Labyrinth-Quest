/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facefreakedstudios.app.lq;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author gavin18
 */
abstract class LQIS // Labyrinth Quest Input System
{
    
    static Item_Map item_map = new Item_Map();
    
    static String outAsk(String question)
    {
        Scanner usr_in = new Scanner(System.in);
        System.out.printf("\n\033[2;33m%s\n---", question);
        return usr_in.nextLine();
    }
    
    static void outLucas(Lucas lucas) throws IOException
    {
       Scanner usr_in = new Scanner(System.in);
       switch(usr_in.nextLine())
       {
           case "read": 
               lucas.read();
               break;
           case "attack":
               LQOS.outPrompt("Attack");
               lucas.attack(usr_in.nextInt());
               break;
           case "enter": 
               lucas.enter();
               break;
           case "upgrade":
               LQOS.outPrompt("Skill");
               String skill = usr_in.nextLine();
               LQOS.outPrompt("Points");
               long points = usr_in.nextLong();
               lucas.upgradeSkill(skill, points);
               break;
           case "move":
               LQOS.outPrompt("X");
               int x = usr_in.nextInt();
               LQOS.outPrompt("Y");
               int y = usr_in.nextInt();
               lucas.move(Lucas.SYMBOL, x, y);
               break;
           case "equip": 
               LQOS.outPrompt("Equip");
               String to_equip = usr_in.nextLine();
               Weighted to_equip_obj = item_map.imap.get(to_equip);
               if(lucas.inventory.containsValue(to_equip_obj))
               {
                   lucas.equip(to_equip_obj);
               }
               else
               {
                   LQOS.outError("Item not in inventory");
               }
               break;
       }
    }
}
