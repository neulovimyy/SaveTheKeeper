package com.stk.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.stk.MainClass;
import com.stk.components.B2dBodyComponent;
import com.stk.components.ComponentType;
import com.stk.components.FiringComponent;
import com.stk.components.PlayerComponent;
import com.stk.components.StateComponent;
import com.stk.components.TransformComponent;
import com.stk.misc.BodyMaker;
import com.stk.misc.KeyboardController;
import com.stk.screens.HomeScreen;

public class FiringSystem extends IteratingSystem {

    ComponentMapper <FiringComponent> fm;
    ComponentMapper <TransformComponent> tm;
    ComponentMapper <B2dBodyComponent> bm;
    ComponentMapper <StateComponent> sm;

    KeyboardController controller;
    Array<Entity> bullets;
    HomeScreen game;
    PooledEngine engine;
    int second,seconds;
    
    public FiringSystem(HomeScreen game) {
        
        super(Family.all(PlayerComponent.class).get());
        second = 0;
        seconds = 0;
        this.game = game;
        this.controller = game.getController();
        this.engine = game.getEngine();
        fm = ComponentMapper.getFor(FiringComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        bm = ComponentMapper.getFor(B2dBodyComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        bullets = new Array<>();
        
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // TODO Auto-generated method stub
        second += deltaTime;
        if(second>=1){
            seconds++;
            second = 0;
        }
        if(controller.fire) {
            Gdx.app.log("Firing bullets", "True");
            Entity fire = new Entity();

            B2dBodyComponent body = engine.createComponent(B2dBodyComponent.class);
            TransformComponent trans = engine.createComponent(TransformComponent.class);
            ComponentType type = engine.createComponent(ComponentType.class);
            StateComponent state = engine.createComponent(StateComponent.class);

            body.body = game.getBodyMaker().createBodySquare(game.getWorld(), new Vector2(5,5), 
                        BodyType.DynamicBody, new Vector2(10, 10));
            trans.position.x = body.body.getPosition().x;
            trans.position.y = body.body.getPosition().y;
            type.type = ComponentType.PLAYER;
            body.body.applyLinearImpulse(new Vector2(5,0), body.body.getPosition(), true);
            engine.addEntity(fire);
            if(seconds>=3){
                engine.removeEntity(fire);
                game.getWorld().destroyBody(body.body);
            }
        }

    }

}
    

