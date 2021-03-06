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

import com.facefreakedstudios.app.lq_engine.LQOS;

/**
 *
 * @author gavin17
 */
class Equipment extends Weighted_Object
{
    protected long condition = 200; // Starting condition       
    
    void setCondition(long condition)
    {
        if(isBroken())
        {
            this.condition = 0;
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
        
    Long getCondition()
    {
        return null;
    }
    
    protected boolean isBroken()
    {
        return condition <= 0;
    }
}

abstract class Weapon extends Equipment
{
   protected final static String TYPE = "weapon";
    
   @Override
   protected String getType()
   {
       return TYPE;
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
    protected final static String TYPE = "helmet";

    @Override
    protected String getType()
    {
        return TYPE;
    }
   
    Long getDefense()
    {
        return null;
    }
}
abstract class Ring extends Equipment
{
   protected final static String TYPE = "ring";
    
   @Override
   protected String getType()
   {
       return TYPE;
   }
    
    Long Ability()
    {
        return null;
    }
}
abstract class Leggings extends Equipment
{
   protected final static String TYPE = "leggings";
   
   @Override
   protected String getType()
   {
       return TYPE;
   }
    
    Long getDefense()
    {
        return null;
    }
}
abstract class Gauntlets extends Equipment
{
    protected final static String TYPE = "gauntlets";
   
    @Override
    protected String getType()
    {
        return TYPE;
    }
    
    Long getDefense()
    {
        return null;
    }
}
abstract class Chestplate extends Equipment
{
    protected final static String TYPE = "chestplate";
    
    @Override
    protected String getType()
    {
        return TYPE;
    }
    
    Long getDefense()
    {
        return null;
    }
}

class wooden_helm extends Helmet
{
    private final String name = "wooden helmet";
    private final long weight = 4, value = 3, defense = 5;
    
    @Override
    protected String getName()
    {
        return this.name;
    }
    
    @Override
    protected Long getWeight()
    {
        return this.weight;
    }
    @Override
    Long getCondition()
    {
        return this.condition;
    }
    @Override
    Long getDefense()
    {
        return this.defense;
    }
    @Override
    protected Long getValue()
    {
        return this.value;
    }
}

class bulk_ring extends Ring
{
    private final String name = "bulk ring";
    private final long weight = 1, value = 5;
    
    @Override
    protected boolean isPicked()
    {
        return this.picked_up;
    }
    
    @Override
    protected boolean isDiscarded()
    {
        return this.discarded;
    }
    
    @Override
    protected boolean isEquipped()
    {
        return this.equipped;
    }
    
    @Override
    protected String getName()
    {
        return this.name;
    }
    
    @Override
    protected Long getWeight()
    {
        return this.weight;
    }
    @Override
    Long getCondition()
    {
        return this.condition;
    }
    @Override
    Long Ability() // Simple defense ring for testing
    {
        return 4L;
    }
    @Override
    protected Long getValue()
    {
        return this.value;
    }
}

class mortifer extends Weapon
{
   private final String[] move_names = {"Slash", "Thrust", "Special"};
   private final String name = "mortifer";
   private final long weight = 8, value = 9;
   
    @Override
    protected boolean isPicked()
    {
        return this.picked_up;
    }
    
    @Override
    protected boolean isDiscarded()
    {
        return this.discarded;
    }
    
    @Override
    protected boolean isEquipped()
    {
        return this.equipped;
    }
   
    @Override
    protected String getName()
    {
        return this.name;
    }
    
    @Override
    protected Long getWeight()
    {
        return this.weight;
    }
    @Override
    Long getCondition()
    {
        return this.condition;
    }
    @Override
    protected Long getValue()
    {
        return this.value;
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
       LQOS.outDMG(lucas.NAME, move_names[0], dmg);
       long[] dmg_apdrain = {dmg ,8L};
       return dmg_apdrain;
   }
   
   @Override
   long[] move1(Lucas lucas)
   {
       long dmg =  lucas.skills.get("Strength") + 5;
       LQOS.outDMG(lucas.NAME, move_names[1], dmg);
       long[] dmg_apdrain = {dmg, 8L};
       return dmg_apdrain;
   }
   
   @Override
   long[] move2(Lucas lucas)
   {
       long dmg = lucas.skills.get("Strength") + 0L;
       LQOS.outDMG(lucas.NAME, move_names[2], dmg);
       long[] dmg_apdrain = {dmg, 10};
       return dmg_apdrain;
   }
}