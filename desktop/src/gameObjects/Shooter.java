package gameObjects;

public interface Shooter {
	public void shoot(Entity e);
	public void setRange(float range);
	public void setShootingSpeed(float shootingSpeed);
	public void setDamage(int damage);
	
}
