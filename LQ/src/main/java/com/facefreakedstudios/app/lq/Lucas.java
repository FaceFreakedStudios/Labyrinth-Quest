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
import java.util.Map;
import java.util.HashMap;

/*
 *
 * @author gavin17
 */
public class Lucas
{
    private long[] dmg_apdrain;
    private long hp, ap, xp;
    private int positX, positY;
    private long lvl_cap = 10;
    private long xp_point = 0;
    private Sword weap = new mortifer(); // temp field
    private String[][] map, map_data;
    private String current_blk, current_blk_dat; // Current position on map
    public String name = "Lucas";
    final protected Map<String, Object> equipped = new HashMap<>();
    final protected Map<String, Long> skills = new HashMap<>(); // Skill tree
    
    Lucas(long hp, long ap) throws IOException
    { // Skills also have in game special effects (like lifting boulders)
        skills.put("Strength", 0L); // Attack power, Inventory weight
        skills.put("Persuasion", 0L); // Barter, Dialogue 
        skills.put("Ingenuity", 0L); // Repair, Crafting, Brewingname
        skills.put("Luck", 0L); // XP drops, Item pickups, Gambling
        skills.put("Vitality", 0L); // HP, HP regen
        skills.put("Wisdom", 0L); // Magic use, Magic strength, Potion effect
        skills.put("Endurance", 0L); // AP, Walk dist, Run dist
        skills.put("Immunity", 0L); // Poi, Fre, Drk, Frst, Phy and Bld damage
        equipped.put("Ring0", null);
        equipped.put("Ring1", null);
        equipped.put("Helm", null);
        equipped.put("Legs", null);
        equipped.put("Gaunts", null);
        equipped.put("Chest", null);
        equipped.put("Weapon", null);
        equipped.put("Weapon", null);
        equipped.put("Sheild", null);
        equipped.put("QI0", null); // QI stands for Quick Item
        equipped.put("QI1", null);
        equipped.put("QI2", null);
        equipped.put("QI3", null);
        equipped.put("QI4", null);
        this.map_data = LQCLI.fetchMapData("/home/gavin17/Scripts/Java/"
            + "Labyrinth-Quest/LQ/src/main/resources/Maps/Empty Grave.dat");
        this.map = LQCLI.fetchMap("/home/gavin17/Scripts/Java/Labyrinth-Quest/"
            + "LQ/src/main/resources/Maps/Empty Grave.map"); // starting map
        this.hp = hp;
        this.ap = ap;
    }
    
    public boolean canMove(String[][] map, int x, int y)
    {
        switch(map[positX + x][positY + y])
        {
            case "#": LQOS.outError("Cannot walk on walls"); return false;
            case "~": LQOS.outError("Cannot walk on water"); return false;
            case "-": LQOS.outError("Cannot walk on lava"); return false;
            default: break;
        }
        if(y > skills.get("Stamina") + 1 || x > skills.get("Stamina") + 1)
        {
            LQOS.outError("Too far of a distance");
            return false;
        }
        return true;
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
    public boolean canEnter()
    {
        switch(current_blk)
        {
            case "o": return true;
            case "L": return true;
            case "^": return true;
            case "$": return true;
            default: return false;
        }
    }
    public boolean canRead()
    {
        if(current_blk == "=")
        {
            return true;
        }
        else
        {
            return false;
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
  
    public void equip(String type, Object equipment)
    {
        equipped.put(type, equipment);
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
    
    public void updateMapPosit()
    {
        current_blk = map[positX][positY];
        current_blk_dat = map_data[positX][positY];
    }
  
    public void enter() throws IOException
    {
        if(canEnter())
        {
            map = LQCLI.fetchMap(current_blk_dat);
            map_data = LQCLI.fetchMapData(current_blk_dat);
        }
        else
        {
            LQOS.outError("Not an enterable block");
        }
    }
    
    public void read()
    {
        if(canRead())
        {
            LQOS.outAny(current_blk_dat);
        }
        else
        {
            LQOS.outError("Not a readable block");
        }
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
    
    public long weap(int attack)
    {
        this.ap -= 8;
        if(noAP()) // return no damage if no AP
        {
            return 0;
        }
        switch(attack)
        {
            case 0:
                dmg_apdrain = weap.move0(this); 
                setAP(this.ap - dmg_apdrain[1]); // 1 is the ap_drain
                return dmg_apdrain[0]; // 0 is the weap's damage
            case 1:
                dmg_apdrain = weap.move1(this); 
                setAP(this.ap - dmg_apdrain[1]);
                return dmg_apdrain[0];
            case 2:
                dmg_apdrain = weap.move2(this); 
                setAP(this.ap - dmg_apdrain[1]);
                return dmg_apdrain[0];
            case 3:
            default: return 0;
        }
    }
    
    public String move(String[][] map,int x, int y)
    {
        if(canMove(map, x, y))
        {
            this.positX += x;
            this.positY += y;
        }
        updateMapPosit(); // Map position updates with every movement
        return LQCLI.stringMap(LQCLI.updateMap(map, this.positX, this.positY));
    }
}
