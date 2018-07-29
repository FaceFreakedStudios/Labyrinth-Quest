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
    protected final boolean[] movement = {}; //  land, water, lava
    protected final String SYMBOL = "!";
    protected long hp, xp_drop, pop;
    protected Integer move_speed; // 2 = Slow, 4 = Normal, 8 = Fast, Null = Teleportation
    protected String name, name_id;
    protected String[] move_set;
    
    void addHP(long hp)
    {
        LQOS.outStat(getName(), getHP(), "HP");
        this.hp += hp;
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
    Integer getMoveSpeed()
    {
        return this.move_speed;
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
    boolean[] getMovement()
    {
        return this.movement;
    }
    
    private void decrementPop()
    {
        --this.pop;
    }
    
    boolean nearLucas(Lucas lucas)
    {
        int[] l_posit = lucas.getPosit();
        long distance = Math.round(Math.sqrt(Math.pow(
            this.posit_x - l_posit[0], 2) + Math.pow(this.posit_y - l_posit[1], 2)));
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
        int[] l_posit = lucas.getPosit();
        
        if(this.posit_x > l_posit[0]) // Enemy is east of Lucas
        {
            this.posit_x +=  -1;
        }
        else if(this.posit_x < l_posit[0]) // Enemy is west of Lucas
        {
            this.posit_x += 1;
        }
        
        if(this.posit_y > l_posit[1]) // Enemy is north of Lucas
        {
            this.posit_y += -1;
        }
        else if(this.posit_y < l_posit[1]) // Enemy is south of Lucas
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
       lucas.addHP(-dmg);
    }
    
     // Overloads Movement.java
    String move(Lucas lucas) throws IOException
    {
        this.last_posit_x = posit_x;
        this.last_posit_y = posit_y;
        int x = 999, y = 999; // Values start off as false for canMove()
        
        while(canMove(this, x, y) == false)
        {
            Integer speed = getMoveSpeed();
            x = ThreadLocalRandom.current().nextInt(-speed,speed+1);
            y = ThreadLocalRandom.current().nextInt(-speed,speed+1);
        }
        if(nearLucas(lucas))
        {
            follow(lucas); // Follows Lucas if near
        }
        else
        {
            this.posit_x += x;
            this.posit_y += y;
        }
        updateMapPosit(); // Map position updates with every movement
        cur_map = LQCLI.updateMap(cur_map, orig_map, posit_x, posit_y, 
            last_posit_x, last_posit_y, SYMBOL); // updates the current map
        return LQCLI.stringMap(cur_map);
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
    private Integer move_speed = 2;
    private final String name = "Rotter", name_id;
    private final boolean[] movement = {true, false, false};
    private String[] move_set = {"Bite_4", "Gnaw_" +
        Long.toString(ThreadLocalRandom.current().nextLong(4, 8 + 1))};
    
    Rotter()
    {
        ++pop;
        this.name_id = name + pop;
    }
    
    @Override
    int[] getPosit()
    {
        return this.position;
    }
    @Override
    long getHP()
    {
        return this.hp;
    }
    @Override
    boolean[] getMovement()
    {
        return this.movement;
    }
    @Override
    long getXPDrop()
    {
        return this.xp_drop;
    }
    @Override
    Integer getMoveSpeed()
    {
        return this.move_speed;
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
