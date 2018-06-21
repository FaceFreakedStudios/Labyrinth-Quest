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
    protected String current_blk, current_blk_dat;
    protected String[][] orig_map, cur_map, map_data;
    protected int posit_x, posit_y, last_posit_x, last_posit_y;
    
    private void setMap(String map) throws IOException
    {
        this.orig_map = LQCLI.fetchMap(map);
        this.cur_map = this.orig_map;
    }
    
    protected void updateMapPosit()
    {
        this.current_blk = orig_map[posit_x][posit_y];
        this.current_blk_dat = map_data[posit_x][posit_y];
    }
    
    protected boolean canMove(int x, int y)
    {
        switch(orig_map[posit_x + x][posit_y + y])
        {
            case "#": LQOS.outError("Cannot walk on walls"); return false;
            case "~": LQOS.outError("Cannot walk on water"); return false;
            case "-": LQOS.outError("Cannot walk on lava"); return false;
            default: break;
        }
        return true;
    }
    
    protected String move(String symbol, int x, int y) throws IOException
    {
        if(canMove(x, y))
        {
            this.posit_x += x;
            this.posit_y += y;
        }
        updateMapPosit(); // Map position updates with every movement
        cur_map = LQCLI.updateMap(cur_map, orig_map, posit_x, posit_y, 
            last_posit_x, last_posit_y, symbol); // updates the current map
        return LQCLI.stringMap(cur_map);
    }
}
