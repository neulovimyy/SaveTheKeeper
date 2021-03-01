package com.stk.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

public class FiringComponent implements Component, Poolable{
    public Vector2 pos = new Vector2(0, 0);
    public Body body;
    @Override
    public void reset() {
        // TODO Auto-generated method stub
        
        
    }
    
}
