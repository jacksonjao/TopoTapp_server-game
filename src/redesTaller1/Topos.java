package redesTaller1;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Topos {

	private PApplet app;
	private PImage[] topo;
	private int posX, posY;
	private int contadorTopo;

	private boolean salir;

	public Topos(PApplet app, int posX, int posY) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		contadorTopo=1;
		salir = true;
		topo = new PImage[12];
		app.loadImage("../data/topo.png");
		for (int i = 1; i < topo.length; i++) {
			topo[i] = app.loadImage("../data/topo" + i + ".png");
		}

		// TODO Auto-generated constructor stub

	}

	public boolean isSalir() {
		return salir;
	}

	public void setSalir(boolean salir) {
		this.salir = salir;
	}

	public void pintar() {
		app.imageMode(PConstants.CENTER);
		if (salir == true) {
			contadorTopo++;
			
				if (contadorTopo > 10) {
					contadorTopo = 11;
				}
				
			
		}

		if (salir == false) {

			contadorTopo--;

			if (contadorTopo <= 1) {
				contadorTopo = 1;
			}
		}

		app.image(topo[contadorTopo], posX, posY);
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

}
