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
    
    protected boolean canMove(Lucas lucas, int x, int y)
    {
        int e_positx = 999, e_posity = 999; // unreachable values
        if(lucas.getTarg() != null)
        {
            e_positx = lucas.getTarg().getPosit()[0]; 
            e_posity = lucas.getTarg().getPosit()[1];
        }
        positx = getPosit()[0]; posity = getPosit()[1];
        if(positx == e_positx && posity == e_posity)
        {
            LQOS.outError("Cannot move during combat");
            return false;
        }
        if(this.positx  + x < 0 
            || this.positx + x > 32 
            || this.posity + y < 0 
            || this.posity + y > 60)
        {
            LQOS.outError("Cannot leave the map");
            return false;
        }
        if(x >= 0) // X moves first
        {
            for(int i = 0; i < x + 1; ++i)
            {
                switch(orig_map[this.getPosit()[1] + 0][this.getPosit()[0] + i])
                {
                    case "#":
                        LQOS.outError("Cannot walk on walls");
                        return false;
                    case "~": 
                        LQOS.outError("Cannot walk on water");
                        return false;
                    case "-": 
                        LQOS.outError("Cannot walk on lava");
                        return false;
                    default: break;
                }
            }
        }
        else if(x < 0)
        {
            for(int i = 0; i < Math.abs(x) + 1; ++i)
            {
                switch(orig_map[this.getPosit()[1] + 0][this.getPosit()[0] - i])
                {
                    case "#": 
                        LQOS.outError("Cannot walk on walls");
                        return false;
                    case "~": 
                        LQOS.outError("Cannot walk on water");
                        return false;
                    case "-": 
                        LQOS.outError("Cannot walk on lava");
                        return false;
                    default: break;
                }
            }
        }
        if(y >= 0)
        {
            for(int i = 0; i < y + 1; ++i)
            {
                switch(orig_map[this.getPosit()[1] + i][this.getPosit()[0] + x])
                {
                    case "#":
                        LQOS.outError("Cannot walk on walls");
                        return false;
                    case "~":
                        LQOS.outError("Cannot walk on water");
                        return false;
                    case "-":
                        LQOS.outError("Cannot walk on lava");
                        return false;
                    default: break;
                }
            }
        }
        else if(y < 0)
        {
            for(int i = 0; i < Math.abs(y) + 1; ++i)
            {
                switch(orig_map[this.getPosit()[1] - i][this.getPosit()[0] + x])
                {
                    case "#": 
                        LQOS.outError("Cannot walk on walls");
                        return false;
                    case "~": 
                        LQOS.outError("Cannot walk on water");
                        return false;
                    case "-":
                        LQOS.outError("Cannot walk on lava");
                        return false;
                    default: break;
                }
            }
        }
        if(Math.abs(y) + Math.abs(x) > lucas.skills.get("Stamina") + 5)
        {
            LQOS.outError("Too far of a distance");
            return false;
        }
        return true;
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
        if(x >= 0) // X moves first
        {
            for(int i = 0; i < x + 1; ++i)
            {
                switch(orig_map[this.getPosit()[1] + 0][this.getPosit()[0] + i])
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
            }
        }
        else if(x < 0)
        {
            for(int i = 0; i < Math.abs(x) + 1; ++i)
            {
                switch(orig_map[this.getPosit()[1] + 0][this.getPosit()[0] - i])
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
            }
        }
        if(y >= 0)
        {
            for(int i = 0; i < y + 1; ++i)
            {
                switch(orig_map[this.getPosit()[1] + i][this.getPosit()[0] + x])
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
            }
        }
        else if(y < 0)
        {
            for(int i = 0; i < Math.abs(y) + 1; ++i)
            {
                switch(orig_map[this.getPosit()[1] - i][this.getPosit()[0] + x])
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
            }
        }
        return true;
    }
    
    protected String spawn(String symbol, int x, int y) throws IOException
    {
        this.setPosit(x, y); this.setLastPosit(x, y);
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
