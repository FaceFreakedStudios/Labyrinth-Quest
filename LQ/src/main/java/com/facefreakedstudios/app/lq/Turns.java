/*
 * Copyright 2018 gavin18.
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
 * @author gavin18
 */

import java.io.IOException;
import java.util.Map;

abstract class Turns // Keeps track of turn cycles and what they do
{
    static long turn_count = 0;
    
    static void next(Lucas lucas) throws IOException
    {
        ++turn_count;
        
        // Enemy Cycle //
        for(Map.Entry<String, Enemy> entry:
            Enemy_Map.CUR_ENES.entrySet())
        {
            entry.getValue().move(lucas); // Moves
            if(entry.getValue().onLucas(lucas))
            {
                entry.getValue().attack(lucas); // Attacks
            }
        }
        Enemy_Map.derefEnemies();
        
        // Lucas Cycle //
        lucas.regenHP(); // Regenrates HP
        if(!lucas.inCombat()) // Prints map if Lucas isn't in combat    
        {
            System.out.println(LQCLI.stringMap(lucas.cur_map));
        }
    }
}
