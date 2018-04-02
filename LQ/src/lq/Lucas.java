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
public class Lucas
{
    private long hp;
    private long ap;
    private long xp;
    private Sword weapon = new mortifer(); // temp field
    private long strength = 0; // temp field
    public long position;
    
    Lucas(long hp, long ap)
    {
        this.hp = hp;
        this.ap = ap;
    }
    
    public boolean isDead()
    {
        if(this.hp <= 0)
        {
            return true;
        }
        return false;
    }
    
    public boolean noAP()
    {
        if(this.ap <= 0)
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
    
    public long getAP()
    {
        return this.hp;
    }
    
    public long getXP()
    {
        return this.xp;
    }
    public long slash()
    {
        this.ap -= 8;
        if(noAP())
        {
            return 0;
        }
        return weapon.slash(this.strength);
    }
}