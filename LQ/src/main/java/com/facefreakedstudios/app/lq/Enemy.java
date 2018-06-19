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

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author gavin17
 */
class Enemy extends Movement
{
    protected int[] position = {this.posit_x, this.posit_y};
    protected long hp, xp_drop, pop;
    protected String name, name_id;
    protected String[] move_set;
    
    void setHP(long hp)
    {
        LQOS.outStat(getName(), getHP(), "HP");
        this.hp = hp;
    }
    int[] getPosit()
    {
        return this.position;
    }
    long getHP()
    {
        return this.hp;
    }
    long getXPDrop()
    {
        return this.xp_drop;
    }
    String[] getMoveSet()
    {
        return this.move_set;
    }
    String getName()
    {
        return this.name;
    }
    String getNameID()
    {
        return this.name_id;
    }
    
    void decrementPop()
    {
        --this.pop;
    }
    
    boolean nearLucas(Lucas lucas)
    {
        int[] i_posit = this.getPosit();
        int[] l_posit = lucas.getPosit();
        long distance = Math.round(Math.sqrt(Math.pow(
            i_posit[0] - l_posit[0], 2) + Math.pow(i_posit[1] - l_posit[1], 2)));
        return distance <= 5;
    }
    
    boolean isDead()
    {
        if(getHP() <= 0)
        {
            decrementPop();
            return true;
        }
        return false;
    }
    
    void follow(Lucas lucas)
    {
        int[] i_posit = this.getPosit();
        int[] l_posit = lucas.getPosit();
        
        if(i_posit[0] > l_posit[0]) // Enemy is east of Lucas
        {
            this.posit_x +=  -1;
        }
        else if(i_posit[0] < l_posit[0]) // Enemy is west of Lucas
        {
            this.posit_x += -1;
        }
        
        if(i_posit[1] > l_posit[1]) // Enemy is north of Lucas
        {
            this.posit_y += -1;
        }
        else if(i_posit[1] < l_posit[1]) // Enemy is south of Lucas
        {
            this.posit_y += 1;
        }
    }
    
    void attack(Lucas lucas)
    {
       String dmgWithName = getMoveSet()[ThreadLocalRandom.current().nextInt(0, 
           getMoveSet().length)]; // Call a random move
       String[] dmg_seper = dmgWithName.split("_"); // Just seper. for output
       String dmg_name = dmg_seper[0];
       long dmg = Long.parseLong(dmg_seper[1]);
       LQOS.outDMG(getName(), dmg_name, dmg);
       lucas.setHP(lucas.getHP() - dmg);
    }
    
    long dropXP()
    {
        if(isDead())
        {
            return getXPDrop();
        }
    return 0;
    }
}

class Rotter extends Enemy
{
    static long pop; // The population of enemy type in current map
    private long xp_drop = 11, hp = 8;
    private final String name = "Rotter", name_id;
    private String[] move_set = {"Bite_4", "Gnaw_" +
        Long.toString(ThreadLocalRandom.current().nextLong(4, 8 + 1))};
    
    Rotter()
    {
        ++pop;
        this.name_id = name + pop;
    }
    
    // Overloads Movement.java
    protected String move(Lucas lucas, String symbol, int x, int y) throws IOException
    {
        if(canMove(x, y))
        {
            if(nearLucas(lucas))
            {
                follow(lucas); // Follows Lucas if near
            }
            else
            {
                this.posit_x += ThreadLocalRandom.current().nextInt(-1,1);
                this.posit_y += ThreadLocalRandom.current().nextInt(-1,1);
            }
        }
        updateMapPosit(); // Map position updates with every movement
        cur_map = LQCLI.updateMap(cur_map, orig_map, posit_x, posit_y, 
            last_posit_x, last_posit_y, symbol); // updates the current map
        return LQCLI.stringMap(cur_map);
    }
    
    @Override
    long getHP()
    {
        return this.hp;
    }
    
    @Override
    long getXPDrop()
    {
        return this.xp_drop;
    }
    @Override
    String[] getMoveSet()
    {
        return this.move_set;
    }
    @Override
    String getName()
    {
        return this.name;
    }
    @Override
    String getNameID()
    {
        return this.name_id;
    }
}
