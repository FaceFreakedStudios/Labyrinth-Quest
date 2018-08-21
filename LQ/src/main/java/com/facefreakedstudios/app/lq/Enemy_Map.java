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

import java.util.HashMap;
import java.util.Map;

class Enemy_Map
{
    Rotter rotter0 = new Rotter("Rotter0");
    Rotter rotter1 = new Rotter("Rotter1");
    
    long pop;
    final Map<String, Enemy> ene_map = 
        new HashMap<>(); // Contains all the enemies that can be spawn in the game
    final Map<String, Enemy> cur_enes = 
        new HashMap<>(); // Contains all the enemies that are currently spawned
    
    Enemy_Map()
    {
        ene_map.put("rotter0", rotter0);
        ene_map.put("rotter1", rotter1);
    }
    
    void addEnemy(int positx, int posity, String ene_type)
    {
        switch(ene_type)
        {
            case "rotter":
                for(pop = 0; cur_enes.containsKey("rotter" + pop); ++pop);
                cur_enes.put("rotter" + pop, ene_map.get("rotter" + pop));
        }
    }
    
    void derefEnemies()
    {
        for(Map.Entry<String, Enemy> entry: 
            cur_enes.entrySet()) // Searches for spawned enemies who are dead
        {
            if(entry.getValue().isDead())
            {
                cur_enes.remove(entry.getKey());
            }
        }
    }
    
    boolean allDead()
    {
        return cur_enes.isEmpty();
    }
}