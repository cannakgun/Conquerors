import java.awt.image.BufferedImage;

public class Bonus extends GameObject{

	//properties
	private BufferedImage sprite;
	private int posX;
	private int posY;
	private int widht;
	private int height;
	private boolean powerup;
	private boolean badSurprise;

	//methods
	
	public boolean isPowerup() {
		return powerup;
	}

	public void setPowerup(boolean powerup) {
		this.powerup = powerup;
	}

	public boolean isBadSurprise() {
		return badSurprise;
	}

	public void setBadSurprise(boolean badSurprise) {
		this.badSurprise = badSurprise;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}

	public int getWidht() {
		return widht;
	}

	public void setWidht(int widht) {
		this.widht = widht;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}
