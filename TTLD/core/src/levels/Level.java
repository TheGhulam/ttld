package levels;

import java.awt.*;
import java.util.ArrayList;

public abstract class Level {

	protected int hordeSize;
	protected ArrayList<Point> spawnpoints;

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
	
}
