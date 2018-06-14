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

/**
 *
 * @author gavin17
 */
class Equipment extends Weighted
{
    protected long condition = 200; // Starting condition       

    void setCondition(long condition)
    {
        if(broken())
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
    
    boolean broken()
    {
        if(condition <= 0)
        {
            return true;
        }
        return false;
    }
}

abstract class Weapon extends Equipment
{
   final static String TYPE = "Weapon";
    
   @Override
   String getType()
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
    final static String TYPE = "Helmet";

    @Override
    String getType()
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
   final static String TYPE = "Ring";
    
   @Override
   String getType()
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
   final static String TYPE = "Leggings";
   
   @Override
   String getType()
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

    final static String TYPE = "Gauntlets";
   
    @Override
    String getType()
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
    final static String TYPE = "Chestplate";
    
    @Override
    String getType()
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
    String name = "Wooden Helmet";
    long weight = 4;
    
    @Override
    String getName()
    {
        return this.name;
    }
    
    @Override
    Long getWeight()
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
        return 5L;
    }
}

class bulk_ring extends Ring
{
    String name = "Bulk Ring";
    long weight = 1;
    
    @Override
    String getName()
    {
        return this.name;
    }
    
    @Override
    Long getWeight()
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
}

class mortifer extends Weapon
{
   String[] move_names = {"Slash", "Thrust", "Special"};
   String name = "Mortifer";
   long weight = 8;
   
   @Override
    String getName()
    {
        return this.name;
    }
    
   @Override
    Long getWeight()
    {
        return this.weight;
    }
    @Override
    Long getCondition()
    {
        return this.condition;
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