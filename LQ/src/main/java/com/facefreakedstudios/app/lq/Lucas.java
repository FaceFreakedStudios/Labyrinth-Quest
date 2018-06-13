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
class Lucas
{
    final static String NAME = "Lucas";
    private long hp, ap, xp;
    private long xp_point = 0; // used for upgrading skills
    private long lvl_cap = 10; // the amount of xp needed for an upgrade
    private long atk_pow = 0; // the dmg bonus, strength
    private long inv_weight = 20; // inventory weight, strength
    private long[] dmg_apdrain;
    private int positX, positY;
    private final Weapon weap = new mortifer(); // temp field
    private String[][] map, map_data;
    private String current_blk, current_blk_dat; // Current position on map
    private Enemy targ;
    final public Map<String, Weighted> inventory = new HashMap<>();
    final public Map<String, Weighted> equipped = new HashMap<>();
    final public Map<String, Long> skills = new HashMap<>(); // Skill tree
    
    Lucas(long hp, long ap) throws IOException
    { // Skills also have in game special effects (like lifting boulders)
        skills.put("Strength", 0L); // Attack power, Inventory weight
        skills.put("Persuasion", 0L); // Barter, Dialogue 
        skills.put("Ingenuity", 0L); // Repair, Crafting, BrewingNAME
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
        equipped.put("Sheild", null);
        equipped.put("QI0", null); // QI stands for Quick Item
        equipped.put("QI1", null); // QIs are mapped to the keyboard
        equipped.put("QI2", null);
        equipped.put("QI3", null);
        equipped.put("QI4", null);
        this.hp = hp;
        this.ap = ap;
    }
    
    private boolean canMove(int x, int y)
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
    boolean isDead()
    {
        if(hp <= 0)
        {
            return true;
        }
        return false;
    }
    boolean noAP()
    {
        if(ap <= 0)
        {
            ap = 0; // So you don't have negative AP
            return true;
        }
        return false;
    }
    private boolean canEnter()
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
    private boolean canRead()
    {
        if(current_blk.equals("="))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    void setHP(long hp)
    {
        LQOS.outStat(NAME, hp, "HP");
        this.hp = hp;
    }
    void setXP(long xp)
    {
        LQOS.outStat(NAME, xp, "XP");
        this.xp = xp;
        if(this.xp > lvl_cap)
        {
            ++xp_point; // gains one xp point
            this.xp -= lvl_cap; // removes xp from lvl_cap
            lvl_cap += 10; // revist
            LQOS.outStat(NAME, xp_point, "XP point");
        }
    }
    void setAP(long ap)
    {
        LQOS.outStat(NAME, ap, "AP");
        this.ap = ap;
    }
    void setTarg(Enemy ene)
    {
        this.targ = ene;
    }
    void addInventory(Weighted obj)
    {
        if(findInventoryWeight() < inv_weight)
        {
            inventory.put(obj.getName(),obj);
        }
        else
        {
            LQOS.outError("Reached max inventory weight");
        }
    }
  
    void equip(String type, Weighted equipment)
    {
        equipped.put(type, equipment);
    }

    void upgradeSkill(String skill, long xp_points)
    {
        if(xp_points < xp_point)
        {
            LQOS.outError("Invalid number of XP points");
        }
        long upgraded = skills.get(skill);
        skills.put(skill, upgraded);
        LQOS.outStat("Lucas", skills.get(skill), skill);
        updateSkills();
    }
    
    private void updateSkills()
    {
        
    }
    private void updateMapPosit()
    {
        current_blk = map[positX][positY];
        current_blk_dat = map_data[positX][positY];
    }
  
    void enter() throws IOException
    {
        if(canEnter())
        {
            map = LQCLI.fetchMap(current_blk_dat);
            map_data = LQCLI.fetchMapData(this, current_blk_dat);
        }
        else
        {
            LQOS.outError("Not an enterable block");
        }
    }
    
    void read()
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
    
    long getHP()
    {
        return hp;
    }
    long getAP()
    {
        return ap;
    }
    long getXP()
    {
        return xp;
    }
    
    private long findInventoryWeight()
    {
        long total_weight = 0;
        for(Map.Entry<String, Weighted> entry : inventory.entrySet()) 
        {
            Weighted obj = entry.getValue();
            total_weight += obj.getWeight();
        }
        return total_weight;
    }
    
    Enemy getTarg()
    {
        return targ;
    }
    
    void attack(int atk)
    {
        ap -= 8;
        if(noAP()) // return no damage if no AP
        {
            targ.setHP(targ.getHP() - 0);
        }
        switch(atk)
        {
            case 0:
                dmg_apdrain = weap.move0(this);
                setAP(ap - dmg_apdrain[1]); // 1 is the ap_drain
                targ.setHP(targ.getHP() - 
                    (dmg_apdrain[0] + atk_pow)); // 0 is the weap's damage
            case 1:
                dmg_apdrain = weap.move1(this); 
                setAP(ap - dmg_apdrain[1]);
                targ.setHP(targ.getHP() - (dmg_apdrain[0] + atk_pow));
            case 2:
                dmg_apdrain = weap.move2(this); 
                setAP(ap - dmg_apdrain[1]);
                targ.setHP(targ.getHP() - (dmg_apdrain[0] + atk_pow));
            case 3:
            default: targ.setHP(targ.getHP() - 0);
        }
    }
    
    String move(int x, int y)
    {
        if(canMove(x, y))
        {
            positX += x;
            positY += y;
        }
        updateMapPosit(); // Map position updates with every movement
        return LQCLI.stringMap(LQCLI.updateMap(map, positX, positY));
    }
}
