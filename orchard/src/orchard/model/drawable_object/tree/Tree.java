package orchard.model.drawable_object.tree;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import orchard.gui.GameGUI;
import orchard.model.drawable_object.DrawableObject;
import orchard.model.drawable_object.fruit.Apple;
import orchard.model.drawable_object.fruit.Cherry;
import orchard.model.drawable_object.fruit.Fruit;
import orchard.model.drawable_object.fruit.Pear;
import orchard.model.drawable_object.fruit.Plum;
import orchard.services.ImgService;
import orchard.util.Fruits;
import orchard.util.Position;

public abstract class Tree extends DrawableObject {
	
	private Fruit[] fruits = {null, null, null, null, null, null, null, null, null, null};
	private GameGUI gameDirector;
	private Position[] positions;
	private Position position;
	private Fruits fruit;
	
	protected Tree(Fruits fruit, Position position, int size, BufferedImage img, Position[] positions, GameGUI gameDirector){
		super();
		
		this.positions = positions;
		this.position = position;
		this.gameDirector = gameDirector;
		this.fruit = fruit;
		
		refill();
		
		setBounds(position.x() - size / 2, position.y() - size / 2, size, size);
		setIcon(ImgService.getImageIcon(img, size, size));
	}
	
	protected Tree(Fruits fruit, Position position, int size, Position[] positions, GameGUI jframe){
		this(fruit, position, size, ImgService.getImage("resources\\arbre.png"), positions, jframe);
	}
	
	protected Tree(Fruits fruit, Position[] positions, GameGUI jframe){
		this(fruit, new Position(0, 0), 400, positions, jframe);
	}
	
	public void refill() {
		for (int i = 0; i<10 ;i++) {
			
			if(fruit.equals(Fruits.APPLE))fruits[i] = new Apple(gameDirector, this, i);
			if(fruit.equals(Fruits.PEAR))fruits[i] = new Pear(gameDirector, this, i);
			if(fruit.equals(Fruits.CHERRY))fruits[i] = new Cherry(gameDirector, this, i);
			if(fruit.equals(Fruits.PLUM))fruits[i] = new Plum(gameDirector, this, i);
			
			if(positions[i] != null)fruits[i].setPosition(position.add(positions[i]));
			else System.out.println("Error, positions missing");
		}
	}
	
	public Fruits getFruit() {
		return fruit;
	}
	
	public Fruit[] getFruits() {
		return fruits;
	}

	public void setFruit(int index, Fruit fruit) {
		this.fruits[index] = fruit;
	}
	
	@Override
	public void showOnJFrame(JFrame jframe) {
		for (Fruit f : fruits) {
			f.showOnJFrame(jframe);
		}
		super.showOnJFrame(jframe);
	}
	
	public GameGUI getGameDirector() {
		return gameDirector;
	}
	public void updateFruitsPositions() {
		for(int i=0; i < fruits.length; i++) {
			if(fruits[i]!=null)fruits[i].setPosition(new Position(getX() + getWidth()/2, getY() + getHeight()/2).add(positions[i]));
		}
	}
	
	public Boolean isTreeEmpty() {
		for(Fruit f : fruits) {
			if(f!=null)return false;
		}
		return true;
	}
	
	@Override
	public void setPosition(Position position) {
		super.setPosition(position);
		updateFruitsPositions();
	}
	
	@Override
	public String toString() {
		return "Tree";
	}
	
}
