package redesTaller1;

import processing.core.PApplet;

public class Main extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logica logica;

	

	public void setup() {
		
		size(1200, 700);
		 
		logica = new Logica(this);
	}

	public void draw() {
		background(255);
		logica.pintar();
	
	}
	
	public void mousePressed(){
		//logica.press(mouseX,mouseY);
		
	}
	
	@Override
	public void keyPressed() {
		// TODO Auto-generated method stub
	//logica.teclado();
	}

	 public static void main(String args[]) { PApplet.main(new String[] {
	  "--present","--bgcolor=#0000", "redesTaller1.Main" });
	 
	 }
	 
}
