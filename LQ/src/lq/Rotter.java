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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Rotter
{
    
    private long hp;
    private final Map<String, Long> moveSetDMG = new HashMap<>();;
    private final String[] moveSet = {"Bite", "Gnaw"};
    
    Rotter()
    {
        moveSetDMG.put("Bite", 4L);
        moveSetDMG.put("Gnaw", ThreadLocalRandom.current().nextLong(4, 8 + 1));
    }
    
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
    
    public long attack(long protagonist)
    {
        return protagonist - moveSetDMG.get(moveSet[ThreadLocalRandom.current()
            .nextInt(0, moveSet.length + 1)]);
    }
}
