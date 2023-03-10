package orchard.model.drawable_object.fruit;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import orchard.gui.GameGUI;
import orchard.model.drawable_object.DrawableObject;
import orchard.model.drawable_object.tree.Tree;
import orchard.util.Fruits;

public class Fruit extends DrawableObject {

	Tree tree;
	int treeIndex;
	Fruits fruitType;
	
	public Fruit(BufferedImage image, GameGUI gameDirector, Tree tree, int treeIndex, Fruits fruitType) {
		super(50, 50, image);
		
		this.tree = tree;
		this.treeIndex = treeIndex;
		this.fruitType = fruitType;
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(gameDirector.getSelectableFruit() != null && gameDirector.getFruitsAmount() > 0 && (gameDirector.getSelectableFruit() == fruitType || gameDirector.getSelectableFruit() == Fruits.ALL)) {
					gameDirector.collectFruit(gameDirector.getPlayers()[gameDirector.getTurn()], tree, treeIndex);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {			
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
			
		});
	}

	@Override
	public String toString() {
		return "Fruit";
	}
	
}
