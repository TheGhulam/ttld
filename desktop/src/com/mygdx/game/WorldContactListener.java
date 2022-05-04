package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import entity.Entity;
import entity.Projectile;
import entity.Npc;
public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		if(fixA.getUserData() == "Bullet" && fixB.getUserData() =="Tower") {
			((Projectile) fixA.getBody().getUserData()).setShot();
		}else if(fixA.getUserData() == "Tower" && fixB.getUserData() =="Bullet"){
			((Projectile) fixB.getBody().getUserData()).setShot();
		}else if(fixA.getUserData() == "Bullet" && fixB.getUserData() =="Npc"){
			Entity t =((Projectile) fixA.getBody().getUserData()).getTarget();
			Entity hit = ((Npc) fixB.getBody().getUserData());
			if(t == hit) {
				((Projectile) fixA.getBody().getUserData()).setShot();
			}
		}else if(fixB.getUserData() == "Bullet" && fixA.getUserData() =="Npc") {
			Entity t =((Projectile) fixB.getBody().getUserData()).getTarget();
			Entity hit = ((Npc) fixA.getBody().getUserData());
			if(t == hit) {
				((Projectile) fixB.getBody().getUserData()).setShot();
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
