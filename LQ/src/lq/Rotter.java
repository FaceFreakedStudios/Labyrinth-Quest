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

/**
 *
 * @author gavin17
 */

import java.util.concurrent.ThreadLocalRandom;

public class Rotter
{
    
    private long hp;
    private final String[] moveSet = {"Bite_4", "Gnaw_" + // Move_moveDamage
        Long.toString(ThreadLocalRandom.current().nextLong(4, 8 + 1))};
    
    Rotter(long hp)
    {
        this.hp = hp;
    }
    
    public boolean ifDead()
    {
        if(this.hp <= 0)
        {
           return true; 
        }
        return false;
    }
    
    public void setHP(long hp)
    {
        this.hp = hp;
    }
    public long getHP()
    {
        return this.hp;
    }
    
    public String attack()
    {
        return moveSet[ThreadLocalRandom.current().nextInt(0, moveSet.length)];
    }
}
