package com.stk.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.stk.components.CollisionComponent;
import com.stk.components.ComponentType;
import com.stk.components.PlayerComponent;
 
public class CollisionSystem  extends IteratingSystem {
	 ComponentMapper<CollisionComponent> cm;
	 ComponentMapper<PlayerComponent> pm;
 
	@SuppressWarnings("unchecked")
	public CollisionSystem() {
		// only need to worry about player collisions
		super(Family.all(CollisionComponent.class,PlayerComponent.class).get());
		
		 cm = ComponentMapper.getFor(CollisionComponent.class);
		 pm = ComponentMapper.getFor(PlayerComponent.class);
	}
 
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// get player collision component
		CollisionComponent cc = cm.get(entity);
		
		Entity collidedEntity = cc.collisionEntity;
		if(collidedEntity != null){
			ComponentType type = collidedEntity.getComponent(ComponentType.class);
			if(type != null){
				switch(type.type){
				case ComponentType.ENEMY:
					//do player hit enemy thing
					System.out.println("player hit enemy");
					break;
				case ComponentType.SCENERY:
					//do player hit scenery thing
					System.out.println("player hit scenery");
					break;
				case ComponentType.OTHER:
					//do player hit other thing
					System.out.println("player hit other");
					break; //technically this isn't needed				
				}
				cc.collisionEntity = null; // collision handled reset component
			}
		}
		
	}
 
}