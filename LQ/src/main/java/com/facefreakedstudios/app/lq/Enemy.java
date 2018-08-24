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
    protected long hp, xp_drop, pop, follow_dist;
    protected Integer move_speed; // 2 = Slow, 4 = Normal, 8 = Fast, Null = Teleportation
    protected String name, name_id;
    protected String[] move_set;
       
    void addHP(long hp)
    {
        LQOS.outStat(getName(), getHP(), "HP");
        this.hp += hp;
    }
    
    @Override
    int[] getPosit()
    {
        int[] position = {this.positx, this.posity};
        return position;
    }
    @Override
    int[] getLastPosit()
    {
        int[] last_position = {this.last_positx, this.last_posity};
        return last_position;
    }
    long getHP()
    {
        return this.hp;
    }
    long getXPDrop()
    {
        return this.xp_drop;
    }
    long getFollowDist()
    {
        return this.follow_dist;
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
        int[] i_posit = this.getPosit();
        long distance = Math.round(Math.sqrt(Math.pow(
            i_posit[0] - l_posit[0], 2) + Math.pow(i_posit[1] - l_posit[1], 2)));
        return distance <= this.getFollowDist();
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
        positx = this.getPosit()[0]; posity = this.getPosit()[1];
        if(this.positx > l_posit[0]) // Enemy is east of Lucas
        {
            this.setPosit(positx - 1, posity);
        }
        else if(this.positx < l_posit[0]) // Enemy is west of Lucas
        {
            this.setPosit(positx + 1, posity);
        }
        
        if(this.posity > l_posit[1]) // Enemy is north of Lucas
        {
            this.setPosit(getPosit()[0], posity - 1);
        }
        else if(this.posity < l_posit[1]) // Enemy is south of Lucas
        {
            this.setPosit(getPosit()[0], posity + 1);
        }
    }
    
    boolean onLucas(Lucas lucas)
    {
        int[] l_posit = lucas.getPosit(), i_posit = this.getPosit();
        return l_posit[0] == i_posit[0] && l_posit[1] == i_posit[1];
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
        this.setLastPosit(getPosit()[0], getPosit()[1]);
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
            this.setPosit(getPosit()[0] + x, getPosit()[1] + y);
        }
        updateMapPosit(); // Map position updates with every movement
        cur_map = LQCLI.updateMap(cur_map, orig_map, getPosit()[0], getPosit()[1], 
            getLastPosit()[0], getLastPosit()[1], SYMBOL); // updates the current map
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
    static long sub_pop; // The population of enemy type in current map
    private final long sub_xp_drop = 11, sub_hp = 8, sub_follow_dist = 3;
    private final Integer sub_move_speed = 2;
    private final String sub_name = "Rotter", sub_name_id;
    private final boolean[] sub_movement = {true, false, false};
    private final String[] sub_move_set = {"Bite_4", "Gnaw_" +
        Long.toString(ThreadLocalRandom.current().nextLong(4, 8 + 1))};
    
    Rotter()
    {
        ++sub_pop;
        this.sub_name_id = sub_name + sub_pop;
    }
    
    @Override
    int[] getPosit()
    {
        int[] position = {this.positx, this.posity};
        return position;
    }
    @Override
    int[] getLastPosit()
    {
        int[] last_position = {this.last_positx, this.last_posity};
        return last_position;
   }
    @Override
    long getHP()
    {
        return this.sub_hp;
    }
    @Override
    boolean[] getMovement()
    {
        return this.sub_movement;
    }
    @Override
    long getFollowDist()
    {
        return this.sub_follow_dist;
    }
    @Override
    long getXPDrop()
    {
        return this.sub_xp_drop;
    }
    @Override
    Integer getMoveSpeed()
    {
        return this.sub_move_speed;
    }
    @Override
    String[] getMoveSet()
    {
        return this.sub_move_set;
    }
    @Override
    String getName()
    {
        return this.sub_name;
    }
    @Override
    String getNameID()
    {
        return this.sub_name_id;
    }
}
