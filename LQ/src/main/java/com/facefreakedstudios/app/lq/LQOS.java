/*
 * Copyright 2018 gavin17.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.facefreakedstudios.app.lq;

/**
 *
 * @author gavin17
 */

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

class LQOS // Labyrinth Quest Output System (with sound)
{
    
    static Item_Map item_map = new Item_Map();
    
    static void outPrompt(String prompt)
    {
        System.out.printf("\u001B[32m%s: ", prompt);
    }
    
    static void outDMG(String name, String dmg_name, long dmg)
    {
        
        System.out.printf("\n\u001B[1m%s\u001B[0m: used %s, "
            + "\u001B[31m%d damage dealt\u001B[0m\n\u001B[32m---", name, dmg_name, dmg);
    }
    
    static void outDia(String name, String dialog)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: %s"
            + "\u001B[0m\n\u001B[32m---", name, dialog);
    }
    
    static void outStat(String name, long stat_num, String stat_name)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: now has "
            + "\033[0;34m%d %s\u001B[0m\n\u001B[32m---", name, stat_num, stat_name);
    }
    static void outDie(String name, long xp_drop)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: died, \033[0;34m%d XP"
            + "\u001B[0m dropped\n\u001B[32m---", name, xp_drop);
    }
    static void outError(String error)
    {
        System.out.printf("\n\u001B[31m!!! %s !!!\u001B[0m\n\u001B[32m---", error);
    }
    static void outAny(String anything)
    {
        System.out.printf("\n%s\n\u001B[32m---", anything);
    }
    
    static void outSound(String file_path, boolean flag) throws // only .wav
        UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        File file = new File(file_path);
        Clip clip;
        AudioInputStream stream = AudioSystem.getAudioInputStream(file);
        AudioFormat format = stream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
        if(flag) // on flag
        {
            clip.start();
        }
        else // off flag
        {
            clip.close();
        }
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
           case "read": lucas.read();
           case "attack":
               LQOS.outPrompt("Attack");
               lucas.attack(usr_in.nextInt());
           case "enter": lucas.enter();
           case "upgrade":
               LQOS.outPrompt("Skill");
               String skill = usr_in.nextLine();
               LQOS.outPrompt("Points");
               long points = usr_in.nextLong();
               lucas.upgradeSkill(skill, points);
           case "move":
               LQOS.outPrompt("X");
               int x = usr_in.nextInt();
               LQOS.outPrompt("Y");
               int y = usr_in.nextInt();
               lucas.move(x, y);
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
       }
    }
}

