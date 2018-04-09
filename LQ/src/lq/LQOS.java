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
public class LQOS // Labyrinth Quest Output System
{
    
    public static void outDMG(String name, String dmg_name, long dmg)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: used %s, "
            + "\u001B[31m%d damage dealt\u001B[0m\n---", name, dmg_name, dmg);
    }
    
    public static void outDia(String name, String dialog)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: %s"
            + "\u001B[0m\n---", name, dialog);
    }
    
    public static void outStat(String name, long stat_num, String stat_name)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: now has "
            + "\033[0;34m%d %s\u001B[0m\n---", name, stat_num, stat_name);
    }
    
    public static void outGain(String name,long stat_num, String stat_name)
    {
        System.out.printf("\n\u001B[1m%s\u001B[0m: gained"
            + " \033[0;34m%d %s\u001B[0m\n---", name, stat_num, stat_name);
    }
}
