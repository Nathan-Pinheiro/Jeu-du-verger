package orchard.model.drawable_object.jigsaw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import orchard.interfaces.Showable;
import orchard.model.drawable_object.DrawableObject;
import orchard.services.ImgService;
import orchard.util.Dimension;
import orchard.util.Position;

public class RavenJigsaw extends DrawableObject {
	
	DrawableObject[] jigsawPieces = new DrawableObject[9];
	ArrayList<Integer> piecesHided = new ArrayList<Integer>();
	
	Position[] positions = new Position[9];
	
	public RavenJigsaw(Position position, Dimension dimension) {
		super(position, dimension,ImgService.getImage("resources\\jigsawBackground.png"), 0.3f);
		
		setOpaque(false);
		
		Dimension imageDimension = new Dimension(dimension.getWidth()/3, dimension.getHeight()/3);
		
		updatePiecesPositions(getWidth(), getHeight());
		
		for(int i = 0; i < jigsawPieces.length; i++) {
			piecesHided.add(i);
			jigsawPieces[i] = new DrawableObject(position.add(positions[i]), imageDimension,ImgService.getImage("resources\\jigsawPiece" + (i + 1) + ".png"));
			jigsawPieces[i].setVisible(false);
		}
	}
	
	private void updatePiecesPositions(int width, int height){
		positions = new Position[] {
				new Position(0, - height/3),
				new Position(- width/3/2, - height/3/2),
				new Position(width/3/2, - height/3/2),
				new Position(- width/3, 0),
				new Position(0, 0),
				new Position(width/3, 0),
				new Position(- width/3/2, height/3/2),
				new Position(width/3/2, height/3/2),
				new Position(0, height/3)
		};
	}
	
	@Override
	public void setPosition(Position position){
		super.setPosition(position);
		
		updatePiecesPositions(getWidth(), getHeight());
		
		for(int i = 0; i < jigsawPieces.length; i++) {
			jigsawPieces[i].setPosition(position.add(positions[i]));
		}
	}
	
	public RavenJigsaw(int x, int y, int width, int height) {
		this(new Position(x,y), new Dimension(width, height));
	}
	
	public Boolean isJigsawFinished(){
		Boolean isJigsawFinished;
		if(piecesHided.isEmpty())isJigsawFinished = true;
		else isJigsawFinished = false;
		return isJigsawFinished;
	}
	
	public void addAPiece(){
		if(!piecesHided.isEmpty()) {
			
			Random random = new Random();
			int index = random.nextInt(0, piecesHided.size());
			
			jigsawPieces[piecesHided.get(index)].setVisible(true);
			piecesHided.remove(piecesHided.get(index));
		}
	}
	
	@Override
	public void showOnJFrame(JFrame jframe) {
		
		for(int i=0; i<jigsawPieces.length; i++) {
			if(!piecesHided.contains(i)) {
				jigsawPieces[i].setVisible(false);
			}
			jigsawPieces[i].showOnJFrame(jframe);
		}
		
		super.showOnJFrame(jframe);
	}
	
	
}
