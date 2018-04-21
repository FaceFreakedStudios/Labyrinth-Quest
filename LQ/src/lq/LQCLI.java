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

package lq;

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
    char protag = '@';
    char enemy = '!';
    char npc = '?';
    // Static:
    char wall = '#';
    char hole = 'o';
    char ground = '.';
    char empty = ' ';
    char water = '~';
    char store = '$';
    char building = '^';
    char trap = ',';
    char lava = '-';
    char sign = '=';
    char labyrinth = 'L';
    char item = 'i';
}

public class LQCLI // Labyrinth Quest Command Line Interface
{
    static String[][] fetchMap(String map_name) throws IOException
    {
        File map_file = new File(map_name); // Map to load
        Scanner map_scan = new Scanner(map_file);
        String[][] map = new String[31][59]; // Map size limit
        String map_row_temp = ""; 
        int row_count = 0, column_count = 0;
        while(map_scan.hasNextLine())
        {
            String line = map_scan.nextLine();
            String[] map_row = line.split("!?^"); // Splits line by character
            for(String map_square: map_row) // Assigns map to char array
            {
                map[column_count][row_count] = map_square;
                ++row_count;
            }
            ++column_count;
            row_count = 0;
            map_row_temp = "";
        }
        return map;
    }
}
