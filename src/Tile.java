
public class Tile {

	public final static char BLOCKED = '#';
	public final static char HAZARD = 'X';
	public final static char CLEAN = ' ';
	public final static char DIRTY = '.';

	private char type;

	public Tile() {
	}

	public Tile(char c) {
		if (c=='R') {
			type = CLEAN;
		} else {
			type = c;
		}
	}

	public char getType() {
		return type;
	}

	public void clean() {
		if (type == DIRTY) {
			type = CLEAN;
		}
	}
	
	public String toString() {
		return ""+type;
	}
}
