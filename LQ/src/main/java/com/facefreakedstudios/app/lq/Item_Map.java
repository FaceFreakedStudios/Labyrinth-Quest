/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facefreakedstudios.app.lq;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author gavin17
 */

public class Item_Map
{
    long pop;
    static final Map<String, Weighted_Object> CUR_ITEMS = new HashMap<>();
    
    public void addItem(int positx, int posity, String item_type) throws IOException
    {
        switch(item_type)
        {
            case "wooden helmet":
                for(pop = 0; CUR_ITEMS.containsKey("wooden helmet" + pop); ++pop);
                CUR_ITEMS.put("wooden helmet" + pop, new wooden_helm());
                CUR_ITEMS.get("wooden helmet" + pop).spawn(
                    CUR_ITEMS.get("wooden helmet" + pop).SYMBOL, 
                    positx, posity);
            case "potion of healing":
                for(pop = 0; CUR_ITEMS.containsKey("potion of healing" + pop); ++pop);
                CUR_ITEMS.put("potion of healing" + pop, new pot_of_healing());
                CUR_ITEMS.get("potion of healing" + pop).spawn(
                    CUR_ITEMS.get("potion of healing" + pop).SYMBOL, 
                    positx, posity);
            case "dull throwing knife":
                for(pop = 0; CUR_ITEMS.containsKey("dull throwing knife" + pop); ++pop);
                CUR_ITEMS.put("dull throwing knife" + pop, new dull_thrw_knife());
                CUR_ITEMS.get("dull throwing knife" + pop).spawn(
                    CUR_ITEMS.get("dull throwing knife" + pop).SYMBOL, 
                    positx, posity);
            case "bulk ring":
                for(pop = 0; CUR_ITEMS.containsKey("bulk ring" + pop); ++pop);
                CUR_ITEMS.put("bulk ring" + pop, new bulk_ring());
                CUR_ITEMS.get("bulk ring" + pop).spawn(
                    CUR_ITEMS.get("bulk ring" + pop).SYMBOL, 
                    positx, posity);
             case "mortifer":
                for(pop = 0; CUR_ITEMS.containsKey("mortifer" + pop); ++pop);
                CUR_ITEMS.put("mortifer" + pop, new mortifer());
                CUR_ITEMS.get("mortifer" + pop).spawn(
                    CUR_ITEMS.get("mortifer" + pop).SYMBOL, 
                    positx, posity);
        }
    }
    
    public static void derefItems(Lucas lucas) throws FileNotFoundException, IOException
    {
        for(Map.Entry<String, Weighted_Object> entry: CUR_ITEMS.entrySet())
        {
            if(entry.getValue().isPicked() || entry.getValue().isDiscarded())
            {
                CUR_ITEMS.remove(entry.getKey());
                ArrayList<String> file_copy = new ArrayList<>();
                File file = new File("/home/gavin18/Scripts/Java"
            + "/Labyrinth-Quest/LQ/src/main/resources/Map_Data/" + lucas.map  +
                    ".dat");
                Scanner read = new Scanner(file);
                while(read.hasNextLine())
                {
                    String current_line = read.nextLine();
                    if(current_line.contains(entry.getValue().getType()))
                    {
                        current_line = "-" + current_line;
                    } // Appends - to item in .dat file
                    file_copy.add(current_line);
                }
                file.delete();
                File mod_file = new File("/home/gavin18/Scripts/"
                    + "Java/Labyrinth-Quest/LQ/src/main/resources/"
                    + "Map_Data/" + lucas.map + ".dat"); // Replaces old dat file
                FileWriter writer = new FileWriter(mod_file);
                for(String lines: file_copy)
                {
                    writer.write(lines + "\n"); // Writes modified file
                }
                writer.flush(); writer.close();
            }
        }
    }
    
    static void forceDerefItems()
    {
        for(Map.Entry<String, Weighted_Object> entry: CUR_ITEMS.entrySet())
        {
            CUR_ITEMS.remove(entry.getKey());
        }
    }
}
