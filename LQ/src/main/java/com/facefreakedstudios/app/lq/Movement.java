/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.facefreakedstudios.app.lq;

import java.io.IOException;

/**
 *
 * @author gavin17
 */
abstract class Movement
{
    protected String current_blk, current_blk_dat, symbol;
    static protected String[][] orig_map, cur_map, map_data;
    protected int posit_x = 0, posit_y = 0, last_posit_x = 0, last_posit_y = 0;
    
    protected void setMap(Lucas lucas, String map) throws IOException // without extensions
    {
        orig_map = LQCLI.fetchMap(map + ".map");
        cur_map = LQCLI.fetchMap(map + ".map");
        map_data = LQCLI.fetchMapData(lucas, map + ".dat");
    }
        
    protected void updateMapPosit()
    {
        this.current_blk = orig_map[posit_y][posit_x];
        this.current_blk_dat = map_data[posit_y][posit_x];
    }
    
    protected boolean canMove(int x, int y)
    {
        if(this.posit_x  + x < 0 // to stay within the array
            || this.posit_x + x > 60 
            || this.posit_y + y < 0 
            || this.posit_y + y > 32)
        { 
            return false;
        }
        switch(orig_map[this.posit_y + y][this.posit_x + x])
        {
            case "#": return false;
            case "~": return false;
            case "-": return false;
            default: break;
        }
        return true;
    }
    
    protected String spawn(String symbol, int x, int y) throws IOException
    {
        this.posit_x = x; this.last_posit_x = x;
        this.posit_y = y; this.last_posit_y = y;
        updateMapPosit();
        this.cur_map = LQCLI.updateMap(this.cur_map, this.orig_map, 
            this.posit_x, this.posit_y, this.last_posit_x, 
            this.last_posit_y, symbol);
        return LQCLI.stringMap(cur_map);
    }
    
    protected String move(String symbol, int x, int y) throws IOException
    {
        if(this.canMove(x, -y))
        {
            this.last_posit_x = posit_x;
            this.last_posit_y = posit_y;
            this.posit_x += x;
            this.posit_y += -y;
        }
        updateMapPosit(); // Map position updates with every movement
        this.cur_map = LQCLI.updateMap(this.cur_map, this.orig_map, 
            this.posit_x, this.posit_y, this.last_posit_x, 
            this.last_posit_y, symbol); // updates the current map
        return LQCLI.stringMap(cur_map);
    }
}
