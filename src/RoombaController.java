import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class RoombaController {

	public static void main(String[] args) throws Exception {
		Room room = new Room("Cliffs");
		Roomba roomba = new Roomba();
		
		JTextArea text = new JTextArea();
		JFrame frame = new JFrame();
		text.setFont(new Font("Consolas",Font.PLAIN,30));
		frame.add(text);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (room.isDirty() && room.iterations() < 10000) {
			//room.print();
			Thread.sleep(100);
			text.setText(room.toString());
			Tile[] tiles = room.getSurroundingTiles();
			int direction = roomba.move(tiles);
			room.moveRoomba(direction);
			frame.repaint();
		}
		if (!room.isDirty()) {
			text.setText(room.toString()+"\nRoom cleaned after "+room.iterations()+" moves.");
		} else {
			text.setText(room.toString()+"\nThe battery ran out (over 10K iterations).");
		}
	}
	
}
