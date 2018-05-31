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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gavin17
 */
public class Equipment
{
    long condition = 200; // Starting condition       

    public void setCondition(long condition)
    {
        if(broken())
        {
            this.condition = -999;
        }
        else
        {
            if(condition > 200)
            {
                this.condition = 200; // equip condition doesn't exceed 200
            }
            else
            {
                this.condition = condition;
            }
        }
    }

    public boolean broken()
    {
        if(this.condition <= 0)
        {
            return true;
        }
        return false;
    }
}

abstract class Weapon extends Equipment
{
    String type = "Weapon";
    
    
    Long getCondition()
    {
        return null;
    }
    String getName()
    {
        return null;
    }
    
    String getMoveName(int move_num)
   {
       return null;
   }
 
   long[] move0(Lucas lucas)
   {
       return null;
   }
   
   long[] move1(Lucas lucas)
   {
       return null;
   }
   
   long[] move2(Lucas lucas)
   {
       return null;
   }
}
abstract class Helmet extends Equipment
{
    String type = "Helmet";
    
    public long getCondition()
    {
        return this.condition;
    }
    public Long getDefense()
    {
        return null;
    }
}
abstract class Ring extends Equipment
{
    String type = "Ring";
    
    public long getCondition()
    {
        return this.condition;
    }
    public long getAbility()
    {
        return 5;
    }
}
abstract class Leggings extends Equipment
{
    String type = "Leggings";
    
    public long getCondition()
    {
        return this.condition;
    }
    public long getDefense()
    {
        return 5;
    }
}
abstract class Gauntlets extends Equipment
{
    String type = "Gauntlets";
    
    public long getCondition()
    {
        return this.condition;
    }
    public long getDefense()
    {
        return 5;
    }
}
abstract class Chestplate extends Equipment
{
    String type = "Chestplate";
    
    public long getCondition()
    {
        return this.condition;
    }
    
    public long getDefense()
    {
        return 5;
    }
}

class wooden_helm extends Helmet
{
    String name = "Wooden Helmet";
    
    @Override
    public long getCondition()
    {
        return this.condition;
    }
    @Override
    public Long getDefense()
    {
        return 5L;
    }
}

class bulk_ring extends Ring
{
    String name = "Bulk Ring";
    
    @Override
    public long getCondition()
    {
        return this.condition;
    }
    @Override
    public long getAbility() // Simple defense ring for testing
    {
        return 4;
    }
}

class mortifer extends Weapon
{
   String[] move_names = {"Slash", "Thrust", "Special"};
   String name = "Mortifer";
   
   @Override
    Long getCondition()
    {
        return this.condition;
    }
   @Override
   String getName()
   {
       return this.name;
   }
   
   @Override
   String getMoveName(int move_num)
   {
       return this.move_names[move_num];
   }
 
   @Override
   long[] move0(Lucas lucas)
   {
       long dmg = lucas.skills.get("Strength") + 4;
       LQOS.outDMG("Lucas", move_names[0], dmg);
       long[] dmg_apdrain = {dmg ,8L};
       return dmg_apdrain;
   }
   
   @Override
   long[] move1(Lucas lucas)
   {
       long dmg =  lucas.skills.get("Strength") + 5;
       LQOS.outDMG("Lucas", move_names[1], dmg);
       long[] dmg_apdrain = {dmg, 8L};
       return dmg_apdrain;
   }
   
   @Override
   long[] move2(Lucas lucas)
   {
       long dmg = lucas.skills.get("Strength") + 0L;
       LQOS.outDMG("Lucas", move_names[2], dmg);
       long[] dmg_apdrain = {dmg, 10};
       return dmg_apdrain;
   }
}

class equipment_map
{
    // Initializing equipment
    wooden_helm helm0 = new wooden_helm();
    bulk_ring ring0 = new bulk_ring();
    mortifer weap0 = new mortifer();
    
    final Map<String, Equipment> emap = new HashMap<>();
    equipment_map()
    {
        emap.put(helm0.name, helm0);
        emap.put(ring0.name, ring0);
        emap.put(weap0.name, weap0);
    }
}