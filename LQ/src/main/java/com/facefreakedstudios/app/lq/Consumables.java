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
public class Consumables extends Weighted_Object
{
    protected static long uses;
    
    boolean hasUses()
    {
        return uses > 0;
    }
    
    void use(Lucas lucas)
    {
        --this.uses;
    }
    
    void incrementUses()
    {
        ++this.uses;
    }
}

/// Potions ///
class pot_of_healing extends Consumables
{
    private final String name = "potion of healing";
    private final long weight = 1, value = 5;
    private static long sub_uses = 0;
    
    @Override
    protected boolean isPicked()
    {
        return this.picked_up;
    }
    
    @Override
    protected boolean isDiscarded()
    {
        return this.discarded;
    }
    
    @Override
    protected boolean isEquipped()
    {
        return this.equipped;
    }
    
    @Override
    protected String getName()
    {
        return this.name;
    }
    @Override
    protected Long getWeight()
    {
        return this.weight;
    }
    @Override
    protected Long getValue()
    {
        return this.value;
    }
    
    @Override
    protected void use(Lucas lucas)
    {
        --this.sub_uses;
        lucas.addHP(5);
    }
}

/// Throwables ///
class dull_thrw_knife extends Consumables
{
    private final String name = "dull throwing knife";
    private final long weight = 1, value = 2;
    private static long sub_uses = 0;

    @Override
    protected boolean isPicked()
    {
        return this.picked_up;
    }
    
    @Override
    protected boolean isDiscarded()
    {
        return this.discarded;
    }
    
    @Override
    protected boolean isEquipped()
    {
        return this.equipped;
    }
    
    @Override
    protected String getName()
    {
        return this.name;
    }
    @Override
    protected Long getWeight()
    {
        return this.weight;
    }
    @Override
    protected Long getValue()
    {
        return this.value;
    }
    
    @Override
    void use(Lucas lucas)
    {
        --this.sub_uses;
        lucas.getTarg().addHP(-3);
    }
}
