package redesTaller1;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Dust {

	private PApplet app;
	private PImage[] dust;
	private int posX, posY;
	private int contador;
	private int contadorDust;

	public Dust(PApplet app, int posX, int posY) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;

		dust = new PImage[5];

		dust[0] = app.loadImage("../data/Dust5.png");
		dust[1] = app.loadImage("../data/Dust4.png");
		dust[2] = app.loadImage("../data/Dust3.png");
		dust[3] = app.loadImage("../data/Dust2.png");
		dust[4] = app.loadImage("../data/Dust1.png");

		// TODO Auto-generated constructor stub

	}

	public void pintar() {
		app.imageMode(PConstants.CENTER);
		contador++;
		if (contador >= 4) {
			contadorDust += 1;
			contador = 0;
		}
		app.image(dust[contadorDust], posX, posY);

		app.imageMode(PConstants.CORNER);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setRanPosX(int posX) {
		this.posX = posX;
	}

	public void setRanPosY(int posY) {
		this.posY = posY;
	}

	public int getContadorDust() {
		return contadorDust;
	}

	public void setContadorDust(int contadorDust) {
		this.contadorDust = contadorDust;
	}

}
