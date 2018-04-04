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

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author gavin17
 */
public class Enemy
{
    long hp;
    long xp_drop;
    String[] move_set;
    
    public void setHP(long hp)
    {
        this.hp = hp;
    }
    public long getHP()
    {
        return this.hp;
    }
    public long getXPDrop()
    {
        return this.xp_drop;
    }
    public String[] getMoveSet()
    {
        return this.move_set;
    }
    
    boolean isDead()
    {
        if(getHP() <= 0)
        {
            return true;
        }
        return false;
    }
    String attack()
    {
        
        return getMoveSet()[ThreadLocalRandom.current().nextInt(0, getMoveSet()
            .length)];
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
    long xp_drop = 11; // default xp_drop
    String[] move_set = {"Bite_4", "Gnaw_" +
        Long.toString(ThreadLocalRandom.current().nextLong(4, 8 + 1))};
    
    Rotter(long hp)
    {
        this.hp = hp;
    }
    Rotter(long hp, long xp_drop)
    {
        this.hp = hp;
        this.xp_drop = xp_drop;
    }
    
    @Override
    public long getHP()
    {
        return this.hp;
    }
    
    @Override
    public long getXPDrop()
    {
        return this.xp_drop;
    }
    @Override
    public String[] getMoveSet()
    {
        return this.move_set;
    }
}
