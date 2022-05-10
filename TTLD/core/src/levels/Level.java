package levels;

import java.awt.*;
import java.util.ArrayList;

public abstract class Level {

	protected int hordeSize;
	protected ArrayList<Point> spawnpoints;

	protected Level() {
		spawnpoints = new ArrayList<>();
		loadArray();
	}

	private void loadArray() {
		//Top Left Corner
		spawnpoints.add(new Point(-640,360));
		spawnpoints.add(new Point(-640,324));
		spawnpoints.add(new Point(-640,288));
		spawnpoints.add(new Point(-576,360));
		spawnpoints.add(new Point(-512,360));
		//Bottom Left Corner
		spawnpoints.add(new Point(-640,-360));
		spawnpoints.add(new Point(-640,-324));
		spawnpoints.add(new Point(-640,-388));
		spawnpoints.add(new Point(-576,-360));
		spawnpoints.add(new Point(-612,-360));
		//Left Mid
		spawnpoints.add(new Point(-640,36));
		spawnpoints.add(new Point(-640,0));
		spawnpoints.add(new Point(-640,-36));
		//Top Mid
		spawnpoints.add(new Point(64,360));
		spawnpoints.add(new Point(0,360));
		spawnpoints.add(new Point(-64,360));
		//Bottom Mid
		spawnpoints.add(new Point(64,-360));
		spawnpoints.add(new Point(0,-360));
		spawnpoints.add(new Point(-64,-360));
		//Top Right Corner
		spawnpoints.add(new Point(640,360));
		spawnpoints.add(new Point(640,324));
		spawnpoints.add(new Point(640,288));
		spawnpoints.add(new Point(576,360));
		spawnpoints.add(new Point(512,360));
		//Bottom Right Corner
		spawnpoints.add(new Point(640,-360));
		spawnpoints.add(new Point(640,-324));
		spawnpoints.add(new Point(640,-288));
		spawnpoints.add(new Point(576,-360));
		spawnpoints.add(new Point(512,-360));
		//Right Mid
		spawnpoints.add(new Point(640,36));
		spawnpoints.add(new Point(640,0));
		spawnpoints.add(new Point(640,-36));
	}

	protected void setHordeSize(int n) {
		this.hordeSize = n;
	}

	protected void addSpawnPoint(Point p) {
		spawnpoints.add(p);
	}

	public Point getSpawnpoint(int i) {
		return spawnpoints.get(i);
	}

	public int getHordeSize() {
		return hordeSize;
	}

	public void decraseHordeSize() {
		hordeSize -= 1;
	}

	public int getSize() {
		return spawnpoints.size();
	}
	
}
