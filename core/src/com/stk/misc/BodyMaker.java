package com.stk.misc;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.stk.MainClass;

public class BodyMaker {
    World world;

    public Body createBodySquare(World world, Vector2 size, BodyDef.BodyType type, Vector2 position ){
        this.world = world;
        Body body = null;
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bdef.type = type;
        bdef.position.set(position.x, position.y);
        body = world.createBody(bdef);
        shape.setAsBox(size.x/MainClass.PPM, size.y/MainClass.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
        shape.dispose();
        return body;
    }

    public Body createFloor(World world, Vector2 size, BodyDef.BodyType type, Vector2 position){
        this.world = world;
        Body body = null;
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        bdef.type = type;
        bdef.position.set(position.x, position.y);
        body = world.createBody(bdef);
        shape.setAsBox(size.x, size.y);
        fdef.shape = shape;
        body.createFixture(fdef);
        shape.dispose();
        return body;
    }
}
