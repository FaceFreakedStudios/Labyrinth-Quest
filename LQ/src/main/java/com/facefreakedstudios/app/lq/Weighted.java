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
abstract class Weighted extends Movement
{
    
    protected static final String SYMBOL = "i";
    protected boolean picked_up = false; // Starting status
    
    String getType()
    {
        return null;
    }
    
    String getName()
    {
        return null;
    }
    
    Long getWeight()
    {
        return null;
    }
    
    Long getValue()
    {
        return null;
    }
    
    protected boolean isPicked()
    {
        return picked_up;
    }
    
    void setPicked(boolean picked_up)
    {
        this.picked_up = picked_up;
    }
}
