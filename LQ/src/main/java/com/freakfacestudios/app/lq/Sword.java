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
package com.freakfacestudios.app.lq;

/**
 *
 * @author gavin17
 */



public class Sword
{   
   String[] move_names;
   String sword_name;
   
   String getMoveName(int move_num)
   {
    
       return move_names[move_num];
   }
   
   String move0(long strength)
   {
       String dmg_apdrain = strength + "_0";
       return dmg_apdrain;
   }
   String move1(long strength)
   {
       String dmg_apdrain = strength + "_0";
       return dmg_apdrain;
   }
   String move2(long strength, long sw_special)
   {
       String dmg_apdrain = (strength + sw_special) + "_0";
       return dmg_apdrain;
   }
}

class mortifer extends Sword
{
   String[] move_names = {"Slash", "Thrust", "Special"};
   String sword_name = "Mortifer";
   
   @Override
   String getMoveName(int move_num)
   {
       return move_names[move_num];
   }
   @Override
   String move0(long strength)
   {
       long dmg = strength + 4;
       LQOS.outDMG("Lucas", move_names[0], dmg);
       String dmg_apdrain = dmg + "_8";
       return dmg_apdrain;
   }
   
   @Override
   String move1(long strength)
   {
       long dmg = strength + 5;
       LQOS.outDMG("Lucas", move_names[1], dmg);
       String dmg_apdrain = dmg + "_8";
       return dmg_apdrain;
   }
   
   @Override
   String move2(long strength, long sw_special)
   {
       long dmg = strength + sw_special;
       LQOS.outDMG("Lucas", move_names[2], dmg);
       String dmg_apdrain = dmg + "_10";
       return dmg_apdrain;
   }
}