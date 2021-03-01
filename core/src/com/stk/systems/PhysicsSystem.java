package com.stk.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.stk.components.B2dBodyComponent;
import com.stk.components.FiringComponent;
import com.stk.components.TransformComponent;
 
 
public class PhysicsSystem extends IteratingSystem {
 
    private static final float MAX_STEP_TIME = 1/45f;
    private static float accumulator = 0f;
 
    private World world;
    private Array<Entity> bodiesQueue;
 
    private ComponentMapper<B2dBodyComponent> bm = ComponentMapper.getFor(B2dBodyComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<FiringComponent> fm = ComponentMapper.getFor(FiringComponent.class);
 
    @SuppressWarnings("unchecked")
	public PhysicsSystem(World world) {
        super(Family.all(B2dBodyComponent.class, 
                        TransformComponent.class, 
                        FiringComponent.class).get());
        this.world = world;
        this.bodiesQueue = new Array<Entity>();
    }
 
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        if(accumulator >= MAX_STEP_TIME) {
            world.step(MAX_STEP_TIME, 6, 2);
            accumulator -= MAX_STEP_TIME;
 
            //Entity Queue
            for (Entity entity : bodiesQueue) {
                TransformComponent tfm = tm.get(entity);
                B2dBodyComponent bodyComp = bm.get(entity);
                FiringComponent fc = fm.get(entity);
                Vector2 position = bodyComp.body.getPosition();
                tfm.position.x = position.x;
                tfm.position.y = position.y;
                tfm.rotation = bodyComp.body.getAngle() * MathUtils.radiansToDegrees;
                fc.pos.x += position.x * deltaTime;
                fc.pos.y += position.y * deltaTime;
            }
        }
        bodiesQueue.clear();
    }
 
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodiesQueue.add(entity);
    }
}