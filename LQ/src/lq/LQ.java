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
public class LQ
{

    /**
     * @param args the command line arguments
     */
    public static String dmg_name;
    public static long dmg;
    public static void convert(String dmgWithName)
    {
        String[] dmg_seper = dmgWithName.split("_");
        dmg_name = dmg_seper[0];
        dmg = Long.parseLong(dmg_seper[1]);
    }
    
    public static void main(String[] args)
    {
        Lucas lucas = new Lucas(50, 60); // testing
        Rotter rotter1 = new Rotter(0, 15); // testing
        convert(rotter1.attack());
        lucas.setHP(lucas.getHP() - dmg);
        LQOS.outDia("Old Man Jenkins", "Would you like to buy some gold?");
//        System.out.println(lucas.getHP());
//        System.out.print(rotter1.getHP());
        
    }
    
}
