import java.awt.Point;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Room {

	private Tile[][] tiles;
	
	private Point roombaLocation=new Point();
	
	private int iterations=0;
	
	public Room(String filename) throws Exception {
		List<String> lines = new LinkedList<>();
		Scanner scan = new Scanner(new File("rooms/"+filename+".txt"));
		int width=-1;
		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			lines.add(s);
			if (s.length()!= width && width>0) {
				throw new Exception("Room must be a rectangle");
			}
			width=s.length();
		}
		tiles=new Tile[lines.size()][width];
		for (int i=0;i<lines.size();i++) {
			String line = lines.get(i);
			for (int j=0;j<line.length();j++) {
				tiles[i][j]=new Tile(line.charAt(j));
				if (line.charAt(j)=='R') {
					roombaLocation.x=j;
					roombaLocation.y=i;
				}
			}
		}
	}
	
	public Tile get(int x, int y) {
		return tiles[y][x];
	}
	
	public Tile[] getSurroundingTiles() {
		Tile[] result = new Tile[10];
		
		return result;
	}
	
	public void print() {
		for (int y=0;y<tiles.length;y++) {
			System.out.println();
			for (int x=0;x<tiles[y].length;x++) {
				if (roombaLocation.x==x && roombaLocation.y==y) {
					System.out.print('R');
				} else {
					System.out.print(tiles[y][x].getType());
				}
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y=0;y<tiles.length;y++) {
			sb.append("\n");
			for (int x=0;x<tiles[y].length;x++) {
				if (roombaLocation.x==x && roombaLocation.y==y) {
					sb.append('R');
				} else {
					sb.append(tiles[y][x].getType());
				}
			}
		}
		return sb.toString();
	}

	public int iterations() {
		return iterations;
	}
	
	public boolean isDirty() {
		for (int y=0;y<tiles.length;y++) {
			for (int x=0;x<tiles[y].length;x++) {
				if (tiles[y][x].getType()==Tile.DIRTY) {
					return true;
				}
			}
		}
		return false;
	}

	public void moveRoomba(int direction) {
		int x = roombaLocation.x;
		int y = roombaLocation.y;
		iterations++;
		switch (direction) {
			case 1:x--;y++;break;
			case 2:y++;break;
			case 3:y++;break;
			
			case 4:x--;break;
			case 6:x++;break;
			
			case 7:y--;x--;break;
			case 8:y--;break;
			case 9:y--;x++;break;
			default:;//do nothing
		}
		Tile destination = tiles[y][x];
		if (destination.getType()==Tile.BLOCKED) {
			//do nothing
			return;
		}
		else if (destination.getType()==Tile.HAZARD) {
			throw new RuntimeException("Your Roomba died in a hazard.");
		}
		roombaLocation.x=x;
		roombaLocation.y=y;
		tiles[y][x].clean();
	}
}
