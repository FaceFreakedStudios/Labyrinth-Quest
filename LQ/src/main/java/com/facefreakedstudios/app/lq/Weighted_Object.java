/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facefreakedstudios.app.lq;

/**
 *
 * @author gavin17
 */
abstract class Weighted_Object extends Map_Object
{
    
    protected static final String SYMBOL = "i";
    protected boolean picked_up = false, discarded = false, equipped = false;
    
    protected String getType()
    {
        return null;
    }
    
    protected String getName()
    {
        return null;
    }
    
    protected Long getWeight()
    {
        return null;
    }
    
    protected Long getValue()
    {
        return null;
    }
    
    protected boolean isPicked()
    {
        return this.picked_up;
    }
    
    protected boolean isDiscarded()
    {
        return this.discarded;
    }
    
    protected boolean isEquipped()
    {
        return this.equipped;
    }
    
    protected void setPicked(boolean picked_up)
    {
        this.picked_up = picked_up;
    }
    
    protected void setDiscarded(boolean discarded)
    {
        this.discarded = discarded;
    }
    
    protected void setEquipped(boolean equipped)
    {
        this.equipped = equipped;
    }
}
