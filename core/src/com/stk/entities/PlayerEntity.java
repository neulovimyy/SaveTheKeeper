package com.stk.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.stk.MainClass;
import com.stk.components.B2dBodyComponent;
import com.stk.components.CollisionComponent;
import com.stk.components.ComponentType;
import com.stk.components.FiringComponent;
import com.stk.components.PlayerComponent;
import com.stk.components.StateComponent;
import com.stk.components.TextureComponent;
import com.stk.components.TransformComponent;
import com.stk.misc.BodyMaker;
import com.stk.misc.KeyboardController;
import com.stk.screens.HomeScreen;

public class PlayerEntity {


    static KeyboardController controller;

    public static void createPlayer(HomeScreen screen, World world, BodyMaker bodyMaker, PooledEngine engine,
            Entity entity) {
        controller = screen.getController();
        B2dBodyComponent body = engine.createComponent(B2dBodyComponent.class);
        TransformComponent position = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        PlayerComponent player = engine.createComponent(PlayerComponent.class);
        CollisionComponent colComp = engine.createComponent(CollisionComponent.class);
        ComponentType type = engine.createComponent(ComponentType.class);
        StateComponent stateCom = engine.createComponent(StateComponent.class);
        Vector2 size = new Vector2(10, 10), pos = new Vector2(10, 10);
        FiringComponent fc = engine.createComponent(FiringComponent.class);

        body.body = bodyMaker.createBodySquare(world, size, BodyDef.BodyType.DynamicBody, pos);
        position.position.set(10, 10, 0);
        type.type = ComponentType.PLAYER;
        stateCom.set(StateComponent.STATE_NORMAL);
        body.body.setUserData(entity);
        fc.body = bodyMaker.createBodySquare(world, new Vector2(5,5), BodyType.DynamicBody, body.body.getPosition());

        entity.add(body);
        entity.add(position);
        entity.add(type);
        entity.add(colComp);
        entity.add(stateCom);
        entity.add(player);
        //body.body = bodyMaker.createBodySquare(world, new Vector2(5, 5), BodyType.DynamicBody, new Vector2(0,0));

        engine.addEntity(entity);
    }
}
