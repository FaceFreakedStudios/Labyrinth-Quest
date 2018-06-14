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

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author gavin17
 */
class Enemy
{
    protected long hp, xp_drop, pop;
    protected String name, name_id;
    protected String[] move_set;
    
    void setHP(long hp)
    {
        LQOS.outStat(getName(), getHP(), "HP");
        this.hp = hp;
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
    
    boolean isDead()
    {
        if(getHP() <= 0)
        {
            decrementPop();
            return true;
        }
        return false;
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
