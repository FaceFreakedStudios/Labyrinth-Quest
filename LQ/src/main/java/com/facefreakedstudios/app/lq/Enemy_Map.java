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
import java.util.HashMap;
import java.util.Map;

class Enemy_Map
{ 
    long pop;
    final Map<String, Enemy> cur_enes = 
        new HashMap<>(); // Contains all the enemies that are currently spawned
    
    void addEnemy(int positx, int posity, String ene_type) throws IOException
    {
        switch(ene_type)
        {
            case "rotter":
                for(pop = 0; cur_enes.containsKey("rotter" + pop); ++pop);
                cur_enes.put("rotter" + pop, new Rotter());
                cur_enes.get("rotter" + pop).spawn(cur_enes.get(
                    "rotter" + pop).SYMBOL, positx, posity);
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