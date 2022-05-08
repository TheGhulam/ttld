package screens;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import gameObjects.Entity;
import gameObjects.NPC;
import gameObjects.Projectile;
import gameObjects.Tower;
public class WorldContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		if(fixA.getUserData() == "NpcBullet" && fixB.getUserData() =="Tower") {
			((Projectile) fixA.getBody().getUserData()).setShot();
			Projectile a = ((Projectile) fixA.getBody().getUserData());
			NPC b = (NPC)a.getStart();
			b.setLocked(false);
		}else if(fixA.getUserData() == "Tower" && fixB.getUserData() =="NpcBullet"){
			((Projectile) fixB.getBody().getUserData()).setShot();
			Projectile a = ((Projectile) fixB.getBody().getUserData());
			NPC b = (NPC)a.getStart();
			b.setLocked(false);
		
		}else if(fixA.getUserData() == "Bullet" && fixB.getUserData() =="Npc"){
			Entity t =((Projectile) fixA.getBody().getUserData()).getTarget();
			Entity hit = ((NPC) fixB.getBody().getUserData());
			if(t == hit) {
				((Projectile) fixA.getBody().getUserData()).setShot();
				Projectile a = ((Projectile) fixA.getBody().getUserData());
				Tower b = (Tower)a.getStart();
				b.setLocked(false);
			}
		}else if(fixB.getUserData() == "Bullet" && fixA.getUserData() =="Npc") {
			Entity t =((Projectile) fixB.getBody().getUserData()).getTarget();
			Entity hit = ((NPC) fixA.getBody().getUserData());
			if(t == hit) {
				((Projectile) fixB.getBody().getUserData()).setShot();
				
				Projectile a = ((Projectile) fixB.getBody().getUserData());
				Tower b = (Tower)a.getStart();
				b.setLocked(false);
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
