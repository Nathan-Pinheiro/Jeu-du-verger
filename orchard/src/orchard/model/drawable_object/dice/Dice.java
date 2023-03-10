package orchard.model.drawable_object.dice;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import orchard.gui.GameGUI;
import orchard.model.drawable_object.DrawableObject;
import orchard.services.ImgService;
import orchard.util.Fruits;
import orchard.util.Position;

public class Dice extends DrawableObject{

	private Boolean isDiceInteractable = true;
	
	Random random = new Random();
	
	GameGUI gameDirector;
	
	private final static int diceSize = 500;
	
	@Override
	public void setPosition(Position position) {
		setBounds(position.x() - getWidth() / 2, position.y() - getHeight() / 2, getWidth(), getHeight());
	}
	
	public Dice(int x, int y, GameGUI gameDirector){
		super(x, y, diceSize, diceSize, ImgService.getImage("resources\\dice1.png"));
		this.gameDirector = gameDirector;
        
        addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		long startTime = System.currentTimeMillis();
        		Thread rollThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                    	
                    	int face = 2;
                    	
                    	if(isDiceInteractable) {

                    		isDiceInteractable = false;
                    		
                            long endTime = System.currentTimeMillis();
                            try{
                                while((endTime - startTime)/1000F < 2){
                                	
                                    int randomNumber = random.nextInt(1, 6);

                                    if(randomNumber < face)face = randomNumber;
                                    else face = randomNumber + 1;
                                    
                                    setIcon("resources\\dice" + face + ".png");
                                    
                                    revalidate();
                                    repaint();

                                    Thread.sleep(100);

                                    endTime = System.currentTimeMillis();

                                }
                                Thread.sleep(1000);
                                
                                hidePanel();
                                
                                if(face == 1) {
                                	gameDirector.addJigsawPiece();
                                }
                                else if(face == 2){
                                	gameDirector.setSelectableFruit(Fruits.ALL);
                                	gameDirector.setFruitsAmount(2);
                                }
                                else if(face == 3){
                                	gameDirector.setSelectableFruit(Fruits.CHERRY);
                                	gameDirector.setFruitsAmount(1);
                                }
                                else if(face == 4){
                                	gameDirector.setSelectableFruit(Fruits.PEAR);
                                	gameDirector.setFruitsAmount(1);
                                }
                                else if(face == 5){
                                	gameDirector.setSelectableFruit(Fruits.APPLE);
                                	gameDirector.setFruitsAmount(1);
                                }
        						else {
        							gameDirector.setSelectableFruit(Fruits.PLUM);
        							gameDirector.setFruitsAmount(1);
        						}
                                
                                repaint();
                                revalidate();
                                
                            }catch(InterruptedException e){
                                System.out.println("Threading Error: " + e);
                            }
                        }
                    }

					
                });
        		rollThread.start();
        	}
        });
	}

	public Boolean getIsInteractable() {
		return isDiceInteractable;
	}

	public void setInteractable(Boolean isDiceInteractable) {
		this.isDiceInteractable = isDiceInteractable;
	}

	public void hidePanel() {
		isDiceInteractable = false;
		setVisible(false);
	}

	public void showPanel() {
		isDiceInteractable = true;
		setVisible(true);
	}
        
}
