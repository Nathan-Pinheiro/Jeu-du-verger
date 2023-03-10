package orchard.model.drawable_object.tree;

import orchard.gui.GameGUI;
import orchard.util.Fruits;
import orchard.util.Position;
public class CherryTree extends Tree {

	public CherryTree(Position p, int size, GameGUI jframe) {
		super(Fruits.CHERRY, p, size, new Position[] {
				
				new Position(-80, -110),   
				new Position(-60, 15),   
				new Position(-85, -35), 
				new Position(-115, 10),  
				new Position(-30, -145), 
				new Position(35, -150),    
				new Position(100, -70),
				new Position(35, -72),
				new Position(75, -10),
				new Position(10, -15)
				
		}, jframe);
	}

}
