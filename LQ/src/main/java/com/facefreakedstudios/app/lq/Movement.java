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
    protected int positx, posity, last_positx, last_posity;
    protected String current_blk, current_blk_dat, symbol;
    static protected String[][] orig_map, cur_map, map_data;
    
    void setPosit(int x, int y)
    {
        this.positx = x;
        this.posity = y;
    }
    void setLastPosit(int last_x, int last_y)
    {
        this.last_positx = last_x;
        this.last_posity = last_y;
    }
    void setCurrentBlk(String current_blk)
    {
        this.current_blk = current_blk;
    }
    void setCurrentBlkDat(String current_blk_dat)
    {
        this.current_blk_dat = current_blk_dat;
    }
    
    int[] getPosit()
    {
        int[] position = {this.positx, this.posity};
        return position;
    }
    int[] getLastPosit()
    {
        int[] last_position = {this.last_positx, this.last_posity};
        return last_position;
    }
    String getCurrentBlk()
    {
        return this.current_blk;
    }
    String getCurrentBlkDat()
    {
        return this.current_blk_dat;
    }
        
    protected void updateMapPosit()
    {
        this.setCurrentBlk(orig_map[this.getPosit()[1]][this.getPosit()[0]]);
        this.setCurrentBlkDat(map_data[this.getPosit()[1]][this.getPosit()[0]]);
    }
    
    protected boolean canMove(Enemy ene, int x, int y)
    {
        positx = this.getPosit()[0]; posity = this.getPosit()[1];
        if(this.positx  + x < 0 // to stay within the array
            || this.positx + x > 60 
            || this.posity + y < 0 
            || this.posity + y > 32)
        { 
            return false;
        }
        switch(orig_map[this.getPosit()[1] + y][this.getPosit()[0] + x])
        {
            case "#": return false;
            case "~": 
                if(ene.getMovement()[1] == false)
                {
                   return false;
                }
            case "-":
                if(ene.getMovement()[2] == false)
                {
                    return false;
                }
            default: break;
        }
        return true;
    }
    
    protected String spawn(String symbol, int x, int y) throws IOException
    {
        this.setPosit(x, y); this.setLastPosit(x, y);
        updateMapPosit();
        this.cur_map = LQCLI.updateMap(this.cur_map, this.orig_map, 
            this.getPosit()[0], this.getPosit()[1], this.getLastPosit()[0], 
            this.getLastPosit()[1], symbol);
        return LQCLI.stringMap(cur_map);
    }
    
    protected String move(Enemy ene, String symbol, int x, int y) throws IOException
    {
        if(canMove(ene, x, -y))
        {
            this.positx = this.getPosit()[0]; this.posity = this.getPosit()[1];
            this.setLastPosit(positx, posity);
            this.setPosit(this.getPosit()[0] + x, this.getPosit()[1] + -y);
        }
        updateMapPosit(); // Map position updates with every movement
        this.cur_map = LQCLI.updateMap(this.cur_map, this.orig_map, 
            this.getPosit()[0], this.getPosit()[1], this.getLastPosit()[0], 
            this.getLastPosit()[1], symbol); // updates the current map
        return LQCLI.stringMap(cur_map);
    }
}
