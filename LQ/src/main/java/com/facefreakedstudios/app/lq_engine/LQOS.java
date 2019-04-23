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

package com.facefreakedstudios.app.lq_engine;

/**
 *
 * @author gavin17
 */

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.File;

public abstract class LQOS // Labyrinth Quest Output System (with sound)
{
    
    public static void outDMG(String name, String dmg_name, long dmg)
    {
        
        System.out.printf("\n\u001B[1m%s\u001B[0m: used %s, "
            + "\u001B[31m%d damage dealt\u001B[0m\n\u001B[32m---", name, dmg_name, dmg);
    }
    
    public static void outDia(String name, String dialog)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: %s"
            + "\u001B[0m\n\u001B[32m---", name, dialog);
    }
    
    public static void outStat(String name, long stat_num, String stat_name)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: now has "
            + "\033[0;34m%d %s\u001B[0m\n\u001B[32m---", name, stat_num, stat_name);
    }
    public static void outDie(String name, long xp_drop)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: died, \033[0;34m%d XP"
            + "\u001B[0m dropped\n\u001B[32m---", name, xp_drop);
    }
    public static void outError(String error)
    {
        System.out.printf("\n\u001B[31m!!! %s !!!\u001B[0m\n\u001B[32m---", error);
    }
    public static void outAny(String anything)
    {
        System.out.printf("\n%s\n\u001B[32m---", anything);
    }
    public static void outSign(String sign)
    {
        String sign_symbols = "";
        long char_count = sign.length(); // sign scales with message
        for(long count = 0; count < (char_count + 6); ++count)
        {
            sign_symbols += "=";
        }
        System.out.printf("\n%s\n=  %s  =\n%1$s\n", sign_symbols, sign);
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
}