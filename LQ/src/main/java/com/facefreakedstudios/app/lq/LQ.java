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

import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author gavin17
 */


public class LQ
{
    
    public static String dmg_name;
    public static long dmg;
    public static long ap_drain;
    public static void convertEneDMG(String dmgWithName) // Seper. dmg and dmg name
    {
        String[] dmg_seper = dmgWithName.split("_");
        dmg_name = dmg_seper[0];
        dmg = Long.parseLong(dmg_seper[1]);
    }
    
    
    public static void main(String[] args) throws IOException
    {
        Lucas lucas = new Lucas(60,60);
        lucas.setMap(lucas, 
                "/home/gavin18/Scripts/Java/Labyrinth-Quest"
                    + "/LQ/src/main/resources/Maps/Town", null);
        int runs = 0;
        lucas.spawn(lucas.SYMBOL, 4, 4);
        while(!lucas.isDead())
        {
            ++runs;
            Turns.next(lucas);
            LQIS.inLucas(lucas);
            System.out.println(LQCLI.stringMap(lucas.cur_map));
//            break;
        }
    }
}
