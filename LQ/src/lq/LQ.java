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

import java.util.Scanner;
import java.io.IOException;

public class LQ
{

    /**
     * @param args the command line arguments
     */
    
    public static String dmg_name;
    public static long dmg;
    public static long ap_drain;
    public static void convertEneDMG(String dmgWithName) // Seper. dmg and dmg name
    {
        String[] dmg_seper = dmgWithName.split("_");
        dmg_name = dmg_seper[0];
        dmg = Long.parseLong(dmg_seper[1]);
    }
    
    public static void main(String[] args) throws IOException
    {
        Lucas lucas = new Lucas(50, 60); // testing
        Rotter rotter1 = new Rotter(15); // testing
        Scanner user_input = new Scanner(System.in);
        while(!lucas.isDead())
        {
            System.out.println("");
            int input = user_input.nextInt();
            rotter1.setHP(lucas.weapon(input));
            convertEneDMG(rotter1.attack());
            lucas.setHP(lucas.getHP() - dmg);
        }
    }
    
}
