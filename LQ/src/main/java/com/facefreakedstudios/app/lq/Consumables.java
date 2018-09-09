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
class Consumables extends Weighted
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
    String getName()
    {
        return this.name;
    }
    @Override
    Long getWeight()
    {
        return this.weight;
    }
    @Override
    Long getValue()
    {
        return this.value;
    }
    
    @Override
    void use(Lucas lucas)
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
    String getName()
    {
        return this.name;
    }
    @Override
    Long getWeight()
    {
        return this.weight;
    }
    @Override
    Long getValue()
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
