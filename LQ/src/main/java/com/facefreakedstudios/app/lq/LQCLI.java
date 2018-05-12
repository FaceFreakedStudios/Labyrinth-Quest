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

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

class map_objects
{
    // Dynamic:
    String protag = "@";
    String enemy = "!";
    String npc = "?";
    // Static:
    String hole = "o";
    String ground = ".";
    String empty = " ";
    String store = "$";
    String building = "^";
    String trap = ",";
    String sign = "=";
    String labyrinth = "L";
    String item = "i";
    // Static Immovable:
    String wall = "#";
    String water = "~";
    String lava = "-";
    
    
    
}

public class LQCLI // Labyrinth Quest Command Line Interface
{
    public static void clearTerm() // Clears the terminal
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    public static String[][] fetchMapData(String map_name) throws IOException
    {
        File data_file = new File(map_name);
        Scanner data_scan = new Scanner(data_file);
        String[][] map_data = new String[32][60];
        String[] data;
        while(data_scan.hasNextLine())
        {
            data = data_scan.nextLine().trim().split(":");
            map_data[Integer.parseInt(data[0])][Integer.parseInt(data[1])] = data[2];
        }
        return map_data;
    }
    
    // VVV fetchs a .map file and loads it as a 2d string array
    public static String[][] fetchMap(String map_name) throws IOException
    {
        File map_file = new File(map_name); // Map to load
        Scanner map_scan = new Scanner(map_file);
        String[][] map = new String[32][60]; // Map size limit
        int row_count = 0, column_count = 0;
        while(map_scan.hasNextLine())
        {
            String[] map_row = map_scan.nextLine().trim().split("(?!^)");
            for(String map_square: map_row) // Assigns map to String array
            {
                map[column_count][row_count] = map_square;
                ++row_count;
            }
            ++column_count;
            row_count = 0;
        }
        return map;
    }
    
    // VVV updates the map for Stringacter movement
    public static String[][] updateMap(String[][] map, int positX, int positY)
    {
        String[][] temp_map = map;
        temp_map[positX][positY] = "@";
        return temp_map;
    }
    
    // VVV converts 2d string array of map into a string representation
    public static String stringMap(String[][] map)
    {
        LQCLI.clearTerm(); // Clears previous map
        String map_str = "";
        for(String[] map_row: map)
        {
            for(String map_sqaure: map_row)
            {
                map_str += map_sqaure;
            }
            map_str += "\n";
        }
        return map_str;
    }

}
