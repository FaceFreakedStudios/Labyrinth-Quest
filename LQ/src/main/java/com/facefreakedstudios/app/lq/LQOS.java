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

public class LQOS // Labyrinth Quest Output System (with sound)
{
    public static void outDMG(String name, String dmg_name, long dmg)
    {
        
        System.out.printf("\n\u001B[1m%s\u001B[0m: used %s, "
            + "\u001B[31m%d damage dealt\u001B[0m\n---", name, dmg_name, dmg);
    }
    
    public static void outDia(String name, String dialog)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: %s"
            + "\u001B[0m\n---", name, dialog);
    }
    
    public static void outStat(String name, long stat_num, String stat_name)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: now has "
            + "\033[0;34m%d %s\u001B[0m\n---", name, stat_num, stat_name);
    }
    public static void outDie(String name, long xp_drop)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: died, \033[0;34m%d XP"
            + "\u001B[0m dropped\n---", name, xp_drop);
    }
    public static void outError(String error)
    {
        System.out.printf("\n\u001B[31m!!! %s !!!\u001B[0m\n---", error);
    }
    public static void outAny(String anything)
    {
        System.out.printf("\n%s\n---", anything);
    }
    
    public static void outSound(String file_path, boolean flag) throws // only .wav
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
    
    public static String outAsk(String question)
    {
        Scanner usr_in = new Scanner(System.in);
        System.out.printf("\n\033[2;33m%s\n---", question);
        return usr_in.nextLine();
    }
    public static void outLucas(Lucas lucas) throws IOException
    {
       Scanner usr_in = new Scanner(System.in);
       switch(usr_in.nextLine())
       {
           case "read": lucas.read();
           case "attack":
               lucas.attack(usr_in.nextInt());
           case "enter": lucas.enter();
           case "upgrade": lucas.upgradeSkill(usr_in.nextLine(), usr_in.nextInt());
           case "move": lucas.move(usr_in.nextInt(), usr_in.nextInt());
           case "equip": 
               equipment_map equip_map = new equipment_map();
               String to_equip = usr_in.nextLine();
               lucas.equip(to_equip, equip_map.emap.get(to_equip));
//           case "bag":
       }
    }
}

