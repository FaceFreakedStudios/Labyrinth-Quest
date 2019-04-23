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

public class Enemy_Map
{ 
    long pop;
    public static final Map<String, Enemy> CUR_ENES = 
        new HashMap<>(); // Contains all the enemies that are currently spawned
    
    public void addEnemy(int positx, int posity, String ene_type) throws IOException
    {
        switch(ene_type)
        {
            case "rotter":
                for(pop = 0; CUR_ENES.containsKey("rotter" + pop); ++pop);
                CUR_ENES.put("rotter" + pop, new Rotter());
                CUR_ENES.get("rotter" + pop).spawn(CUR_ENES.get(
                    "rotter" + pop).SYMBOL, positx, posity);
        }
    }
    
    public static void derefEnemies()
    {
        for(Map.Entry<String, Enemy> entry: 
            CUR_ENES.entrySet()) // Searches for spawned enemies who are dead
        {
            if(entry.getValue().isDead())
            {
                CUR_ENES.remove(entry.getKey());
            }
        }
    }
    
    static void forceDerefEnemies() // Just dereferences all enemies
    {
        for(Map.Entry<String, Enemy> entry: 
            CUR_ENES.entrySet())
        {
                CUR_ENES.remove(entry.getKey());
        }
    }
    
    boolean allDead()
    {
        return CUR_ENES.isEmpty();
    }
}