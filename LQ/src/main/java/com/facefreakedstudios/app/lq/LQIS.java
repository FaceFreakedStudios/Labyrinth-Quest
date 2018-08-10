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
    
    static void outPrompt(String prompt)
    {
        System.out.printf("\u001B[32m%s: \n", prompt);
    }
    
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
               outPrompt("Attack");
               lucas.attack(usr_in.nextInt());
               break;
           case "enter": 
               lucas.enter();
               lucas.move(lucas.SYMBOL, 0, 0); // to fix repeated enter error
               break;
           case "upgrade":
               outPrompt("Skill");
               String skill = usr_in.nextLine();
               outPrompt("Points");
               long points = usr_in.nextLong();
               lucas.upgradeSkill(skill, points);
               break;
           case "move":
               outPrompt("X");
               int x = usr_in.nextInt();
               outPrompt("Y");
               int y = usr_in.nextInt();
               lucas.move(Lucas.SYMBOL, x, y);
               break;
           case "equip": 
               outPrompt("Equip");
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
           case "stat":
               System.out.println("--- General ---");
               System.out.println("HP: " + lucas.getHP());
               System.out.println("AP: " + lucas.getAP());
               System.out.println("XP: " + lucas.getXP());
               System.out.println("Cash: " + lucas.getCash());
               System.out.println("--- Location ---");
               System.out.printf("Position (x,y): (%d,%d)\n", 
                   lucas.getPosit()[0], lucas.getPosit()[1]);
               System.out.println("Current block: " + lucas.current_blk);
               System.out.println("--- Skills ---");
               System.out.println("Strength: " + lucas.skills.get("Strength"));
               System.out.println("Persuasion: " + lucas.skills.get("Persuasion"));
               System.out.println("Ingenuity: " + lucas.skills.get("Ingenuity"));
               System.out.println("Luck: " + lucas.skills.get("Luck"));
               System.out.println("Vitality: " + lucas.skills.get("Vitality"));
               System.out.println("Wisdom: " + lucas.skills.get("Wisdom"));
               System.out.println("Endurance: " + lucas.skills.get("Endurance"));
               System.out.println("Immunity: " + lucas.skills.get("Immunity"));
               System.out.println("Stamina: " + lucas.skills.get("Stamina"));
               System.out.println("--- Equipped ---");
               System.out.println("Ring: " + lucas.equipped.get("Ring"));
               System.out.println("Helm: " + lucas.equipped.get("Helm"));
               System.out.println("Legs: " + lucas.equipped.get("Legs"));
               System.out.println("Gaunts: " + lucas.equipped.get("Gaunts"));
               System.out.println("Chest: " + lucas.equipped.get("Chest"));
               System.out.println("Weapon: " + lucas.equipped.get("Weapon"));
               System.out.println("Sheild: " + lucas.equipped.get("Sheild"));
               System.out.println("Quick Item 0: " + lucas.equipped.get("QI0"));
               System.out.println("Quick Item 1: " + lucas.equipped.get("QI1"));
               System.out.println("Quick Item 2: " + lucas.equipped.get("QI2"));
               System.out.println("Quick Item 3: " + lucas.equipped.get("QI3"));
               System.out.println("Quick Item 4: " + lucas.equipped.get("QI4"));
       }
    }
}
