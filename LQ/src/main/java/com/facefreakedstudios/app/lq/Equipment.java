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


abstract class Helmet extends Equipment
{
    String type = "Helmet";
}

abstract class Ring extends Equipment
{
    String type = "Ring";
}

class wooden_helm extends Helmet
{
    public long getCondition()
    {
        return this.condition;
    }
    public long getDefense()
    {
        return 5;
    }
}

class bulk_ring extends Equipment
{
    public long getCondition()
    {
        return this.condition;
    }
    public long getAbility() // Simple defense ring for testing
    {
        return 4;
    }
}