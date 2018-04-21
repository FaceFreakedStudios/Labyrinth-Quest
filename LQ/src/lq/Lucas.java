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

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author gavin17
 */
public class Lucas
{
    
    private long dmg, ap_drain, dmg_inflict;
    
    public void convertInfo(String infoWithName) // Seperates array info
    {
        String[] info_seper = infoWithName.split("_");
        dmg = Long.parseLong(info_seper[0]);
        ap_drain = Long.parseLong(info_seper[1]);
    }
    
    private long hp, ap, xp;
    private int positX, positY;
    private long lvl_cap = 10;
    private long xp_point = 0;
    private Sword weapon = new mortifer(); // temp field
    public String name = "Lucas";
    final private Map<String, Long> skills = new HashMap<>(); // Skill tree
    
    Lucas(long hp, long ap)
    {
        skills.put("Strength", 0L);
        skills.put("Persuasion", 0L);
        skills.put("Ingenuity", 0L);
        skills.put("Luck", 0L);
        skills.put("Vitality", 0L);
        skills.put("Wisdom", 0L);
        skills.put("Stamina", 0L);
        skills.put("Immunity", 0L);
        this.hp = hp;
        this.ap = ap;
    }
    
    public boolean canMove(String[][] map, int x, int y)
    {
        switch(map[positX + x][positY + y])
        {
            case "#": return false;
            case "~": return false;
            case "-": return false;
            default: return true;
        }
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
            this.ap = 0; // So you don't have negative AP
            return true;
        }
        return false;
    }
    
    public void move(String[][] map, int x, int y)
    {
        if(canMove(map, x, y))
        {
            this.positX += x;
            this.positY += y;
        }
    }
    public void setHP(long hp)
    {
        LQOS.outStat(name, hp, "HP");
        this.hp = hp;
    }
    public void setXP(long xp)
    {
        LQOS.outStat(name, xp, "XP");
        this.xp = xp;
        if(this.xp < lvl_cap)
        {
            ++xp_point;
            lvl_cap += 10;
            LQOS.outStat("Lucas", xp_point, "XP point");
        }
    }
    public void setAP(long ap)
    {
        LQOS.outStat(name, ap, "AP");
        this.ap = ap;
    }
    
    public void upgradeSkill(String skill, long xp_points)
    {
        if(xp_points < xp_point)
        {
            LQOS.outError("Invalid number of XP points");
        }
        long upgraded = this.skills.get(skill);
        this.skills.put(skill, upgraded);
        LQOS.outStat("Lucas", this.skills.get(skill), skill);
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
    public long weapon(int attack)
    {
        this.ap -= 8;
        if(noAP()) // return no damage if no AP
        {
            return 0;
        }
        switch(attack)
        {
            case 0:
                convertInfo(weapon.move0(skills.get("Strength"))); 
                setAP(this.ap - ap_drain);
                return dmg;
            case 1:
                convertInfo(weapon.move1(skills.get("Strength"))); 
                setAP(this.ap - ap_drain);
                return dmg;
            case 2:
                convertInfo(weapon.move2(skills.get("Strength"), 0)); 
                setAP(this.ap - ap_drain);
                return dmg;
            case 3:
            default: return 0;
        }
    }
}