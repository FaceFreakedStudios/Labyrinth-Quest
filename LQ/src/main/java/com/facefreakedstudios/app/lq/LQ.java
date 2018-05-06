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
        Lucas lucas = new Lucas(50,50);
        String[][] map = LQCLI.fetchMap("/home/gavin17/Scripts/Java/Labyrinth-Quest/LQ/src/main/resources/Maps/Town.map");
        String updated_map;
        while(true)
        {
            Scanner input = new Scanner(System.in);
            String move = input.next();
            switch(move)
            {
                case "w":
                    updated_map = lucas.move(map, 0, +1);
                    break;
                case "s":
                    updated_map = lucas.move(map, 0, -1);
                    break;
                case "d":
                    updated_map = lucas.move(map, +1, 0);
                    break;
                case "a":
                    updated_map = lucas.move(map, -1, 0);
                    break;
                default: break;
            }
            System.out.print(updated_map);
        }
    }
}
