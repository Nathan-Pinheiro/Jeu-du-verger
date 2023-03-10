package orchard.model;

public class Player {
	
	private String name;
	private int appleAmount;
	private int pearAmount;
	private int cherryAmount;
	private int plumAmount;
	
	public Player(String name, int appleAmount, int pearAmount, int cherryAmount, int plumAmount) {
		this.name = name;
		this.appleAmount = appleAmount;
		this.pearAmount = pearAmount;
		this.cherryAmount = cherryAmount;
		this.plumAmount = plumAmount;
	}
	
	public Player(String name) {
		this(name, 0, 0, 0, 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getFruits() {
		return appleAmount + pearAmount + cherryAmount + plumAmount;
	}
	
	public void addPear() {
		pearAmount++;
	}
	
	public void addApple() {
		appleAmount++;
	}
	
	public void addCherry() {
		cherryAmount++;
	}
	
	public void addPlum() {
		plumAmount++;
	}
}
