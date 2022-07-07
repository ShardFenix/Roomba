
public class Roomba {
	
	/**
	 * This will be called continuously until the Roomba dies, or the room
	 * has been cleaned.
     * 
	 * @param surroundingTiles An array of tiles that surround your Roomba
	 * @return The numpad direction to move (1 downleft, 2 down, 3 downright, etc).
	 */
	public int move(Tile[] surroundingTiles) {
		//move randomly for now
		int random = (int)(Math.random()*8)+1; //1-8
		if (random>=5) {
			random += 1; //1-4, 6-9
		}
		return random;
	}
}
