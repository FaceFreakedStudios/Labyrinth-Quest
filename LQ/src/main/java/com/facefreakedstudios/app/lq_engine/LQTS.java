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
package com.facefreakedstudios.app.lq_engine;

/**
 *
 * @author gavin18
 */

import com.facefreakedstudios.app.lq.Enemy;
import com.facefreakedstudios.app.lq.Enemy_Map;
import com.facefreakedstudios.app.lq.Item_Map;
import com.facefreakedstudios.app.lq.Lucas;
import com.facefreakedstudios.app.lq_engine.LQCLI;
import com.facefreakedstudios.app.lq_engine.LQIS;
import java.io.IOException;
import java.util.Map;

public abstract class LQTS // Keeps track of turn cycles and what they do
{
    public static long turn_count = 0;
    
    public static void next(Lucas lucas) throws IOException
    {
        ++turn_count;
        
        // Enemy Cycle //
        for(Map.Entry<String, Enemy> entry:
            Enemy_Map.CUR_ENES.entrySet())
        {
            entry.getValue().move(lucas); // Moves
            if(entry.getValue().onLucas(lucas))
            {
                entry.getValue().move(entry.getValue(), entry.getValue().
                    BATTLE_SYMBOL, 0, 0);
                System.out.println("\n" + LQCLI.stringMap(lucas.cur_map));
            }
            while(entry.getValue().onLucas(lucas) && !lucas.isDead())
            {
                entry.getValue().attack(lucas); // Attacks
                lucas.regenHP(); // Regenrates HP
                lucas.setTarg(entry.getValue());
                LQIS.inLucas(lucas);
            }
        }
        Enemy_Map.derefEnemies();
        Item_Map.derefItems(lucas);
        // Lucas Cycle //
       if(!lucas.isDead())
       {
           LQIS.inLucas(lucas);
           System.out.println("\n" + LQCLI.stringMap(lucas.cur_map));
       }
    }
}
