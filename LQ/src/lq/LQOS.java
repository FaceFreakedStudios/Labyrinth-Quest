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
        System.out.printf("\n%s: used %s, %d damage dealt", name, dmg_name, dmg);
    }
    public static void outDia(String name, String dialog)
    {
        System.out.printf("\n%s: %s", name, dialog);
    }
}
