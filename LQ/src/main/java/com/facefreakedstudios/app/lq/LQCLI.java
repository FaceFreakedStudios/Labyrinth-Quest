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

abstract class LQCLI // Labyrinth Quest Command Line Interface
{
    
    static void clearTerm() // Clears the terminal
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    // VVV fetchs a map's .dat file and maps its data to its .map file
    static String[][] fetchMapData(Lucas lucas, String map_name, String location) 
        throws IOException
    {
        Enemy_Map ene_map = new Enemy_Map(); Item_Map it_map = new Item_Map();
        File data_file = new File(map_name);
        Scanner data_scan = new Scanner(data_file);
        String[][] map_data = new String[32][60];
        String[] data;
        while(data_scan.hasNextLine())
        {
            data = data_scan.nextLine().trim().split(":");
            map_data[Integer.parseInt(data[0])]
                [Integer.parseInt(data[1])] = data[2]; // Maps data to 2D array
            if(data[2].equals("*") && location == null) // Searchs for spawn point if one
            {
                lucas.spawn(lucas.SYMBOL, Integer.parseInt(data[0]),
                    Integer.parseInt(data[1])); // sets spawn point
            }
            if(data[2].contains("!"))
            {
                ene_map.addEnemy(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                    data[2].substring(1));
            }
            if(data[2].contains("+"))
            {
                it_map.addItem(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                    data[2].substring(1));
            }
        }
        if(location != null) // sets spawn point if a location is specified in .dat
        {
            String[] cords = location.split(";");
            lucas.spawn(lucas.SYMBOL, Integer.parseInt(cords[0]), 
                Integer.parseInt(cords[1]));
        }
        return map_data;
    }
    
    // VVV fetchs a .map file and loads it as a 2d string array
    static String[][] fetchMap(String map_name) throws IOException
    {
        File map_file = new File(map_name); // Map to load
        Scanner map_scan = new Scanner(map_file);
        String[][] map = new String[32][60]; // Map size limit
        int row_count = 0, column_count = 0;
        while(map_scan.hasNextLine())
        {
            String[] map_row = map_scan.nextLine().trim().split("(?!^)");
            for(String map_sq: map_row) // Assigns map to String array
            {
                map[column_count][row_count] = map_sq;
                ++row_count;
            }
            ++column_count;
            row_count = 0;
        }
        return map;
    }
    
    // VVV updates the map from Lucas movement
    static String[][] updateMap(String[][] cur_map, String[][] orig_map ,int x, int y,
        int last_x, int last_y, String symbol) throws IOException
    {
        cur_map[last_y][last_x] =
            orig_map[last_y][last_x]; // Replaces character symbol with map
        cur_map[y][x] = "\033[0;32m" + symbol + "\033[0m";
        return cur_map;
    }
    
    // VVV converts 2d string array of map into a string representation
    static String stringMap(String[][] map)
    {
        String map_str = "";
        for(String[] map_row: map)
        {
            for(String map_sq: map_row)
            {
                switch(map_sq) // adds color to map
                {
                    case "#": 
                        map_sq = "\033[0;31m" + map_sq + "\033[0m";
                        break;
                    case "~":
                        map_sq = "\033[0;34m" + map_sq + "\033[0m";
                        break;
                    case "$":
                        map_sq = "\033[0;33m" + map_sq + "\033[0m";
                        break;
                    case "^":
                        map_sq = "\033[0;33m" + map_sq + "\033[0m";
                        break;
                    case "L":
                        map_sq = "\033[0;33m" + map_sq + "\033[0m";
                        break;
                    case "o":
                        map_sq = "\033[0;33m" + map_sq + "\033[0m";
                        break;
                    case "i":
                        map_sq = "\033[0;36m" + map_sq + "\033[0m";
                        break;
                    case "-":
                        map_sq = "\033[0;31m" + map_sq + "\033[0m";
                        break;
                }
                map_str += map_sq;
            }
            map_str += "\n";
        }
        return map_str;
    }

}
