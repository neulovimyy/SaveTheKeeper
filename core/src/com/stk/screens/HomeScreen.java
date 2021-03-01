package com.stk.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.stk.MainClass;
import com.stk.components.B2dBodyComponent;
import com.stk.components.CollisionComponent;
import com.stk.components.ComponentType;
import com.stk.components.PlayerComponent;
import com.stk.components.StateComponent;
import com.stk.components.TextureComponent;
import com.stk.components.TransformComponent;
import com.stk.entities.PlayerEntity;
import com.stk.misc.BodyMaker;
import com.stk.misc.KeyboardController;
import com.stk.systems.AnimationSystem;
import com.stk.systems.CollisionSystem;
import com.stk.systems.FiringSystem;
import com.stk.systems.PhysicsDebugSystem;
import com.stk.systems.PhysicsSystem;
import com.stk.systems.PlayerControlSystem;
import com.stk.systems.RenderingSystem;

public class HomeScreen extends ScreenAdapter implements Poolable {
    
    SpriteBatch sb;
    MainClass game; 
    OrthographicCamera camera;
    PooledEngine engine;
    World world;
    KeyboardController controller;
    BodyMaker bodyMaker = new BodyMaker();
    public HomeScreen(MainClass game){
        // Gdx.input.setCursorCatched(true);
        this.game = game;
        world = new World(new Vector2(0, -9.8f), true);
        sb = new SpriteBatch();
        
        controller = new KeyboardController();
        RenderingSystem rs = new RenderingSystem(sb);
        camera = rs.getCamera();
        sb.setProjectionMatrix(camera.combined);
        
        //initialize engine
        engine = new PooledEngine();

        engine.addSystem(new AnimationSystem());
        engine.addSystem(rs);
        engine.addSystem(new PhysicsSystem(world));
        engine.addSystem(new PhysicsDebugSystem(world, rs.getCamera()));
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new PlayerControlSystem(controller));
        engine.addSystem(new FiringSystem(this));
        addEntities();
        Gdx.input.setInputProcessor(controller);
    }

    void addEntities(){
        Entity player = engine.createEntity();
        PlayerEntity.createPlayer(this, world,bodyMaker, engine, player);
        Entity floor = engine.createEntity();
        platformComponentList(floor);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        engine.update(delta);
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
    }

    void platformComponentList(Entity entity){
        B2dBodyComponent body = engine.createComponent(B2dBodyComponent.class);
        ComponentType type = engine.createComponent(ComponentType.class);
        Vector2 size = new Vector2(500, 10);
        Vector2 pos = new Vector2(1, 1);
        body.body = bodyMaker.createFloor(world, size, BodyType.StaticBody, pos);
        body.body.setUserData(entity);
        type.type = ComponentType.SCENERY;

        entity.add(body);
        entity.add(type);
        
        engine.addEntity(entity);
    }

    public KeyboardController getController(){
        return controller;
    }

	public PooledEngine getEngine() {
		return engine;
	}

    public BodyMaker getBodyMaker(){
        return bodyMaker;
    }
    
    public World getWorld(){
        return world;
    }

}