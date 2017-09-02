package redesTaller1;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

public class Logica {
	private int startGame;
	private PApplet app;
	private Conexion conexion;
	private ArrayList<Topos> topos;
	private ArrayList<Dust> dust;
	private int pantalla, puntaje, tiempo, tiempoMillis, cantTopos, golpes, ronda, contadorTopo;
	private PImage[] pantallas;
	private PImage[] regalos;
	private int posX;
	private int posY;
	private Minim minim;
	private AudioSample golpe;
	private AudioSample fail;
	private AudioPlayer musica;
	private int posRanX, posRanY;
	private PFont fuente;
	private int contador;

	public Logica(PApplet app) {
		this.app = app;
		minim = new Minim(app);
		startGame = 5;
		golpe = minim.loadSample("../data/golpe.mp3", 2048);
		fail = minim.loadSample("../data/fail.mp3", 1024);
		musica = minim.loadFile("../data/fondo.mp3", 1024);
		musica.setGain(-10);
		fail.setGain(40);
		ronda = 1;
		cantTopos = 60;
		golpes = 70;
		pantalla = 0;
		fuente = app.createFont("../data/CHUBBY.TTF", 32);
		conexion = new Conexion();
		conexion.start();
		topos = new ArrayList<Topos>();
		dust = new ArrayList<Dust>();
		pantallas = new PImage[10];
		regalos = new PImage[10];

		tiempo = 30;

		pantallas[3] = app.loadImage("../data/fondo3.png");
		pantallas[1] = app.loadImage("../data/instrucciones.png");
		pantallas[0] = app.loadImage("../data/inicio.png");
		regalos[0] = app.loadImage("../data/premioR1.png");
		regalos[1] = app.loadImage("../data/premioR2.png");
		regalos[2] = app.loadImage("../data/premioR3.png");
		regalos[3] = app.loadImage("../data/premioR4.png");
		regalos[4] = app.loadImage("../data/perdiste.png");


		for (int i = 0; i < 5; i++) {
			posRanX = (int) app.random(0, 4);
			posRanY = (int) app.random(0, 3);

			topos.add(new Topos(app, (214 * (posRanX)) + 141, (210 * posRanY) + 117));
		}

		// pantallas[0]= app.loadImage(filename);
		// TODO Auto-generated constructor stub
	}

	public void pintar() {
		switch (pantalla) {

		case 0:

			app.image(pantallas[0], 0, 0);
			app.textAlign(PConstants.CENTER);
	app.text(conexion.getIp(), app.width/2, app.height-80);
			break;

		case 1:
			app.image(pantallas[1], 0, 0);

			break;
		case 3:

			pantallajuego();
			app.noStroke();

			if (contador >= 60) {
				startGame -= 1;
				contador = 0;
			}

			if (startGame > 0) {
				app.fill(0, 0, 0, 170);
				

				app.rect(0, 0, app.width, app.height);
			app.textAlign(PConstants.CENTER);
				app.textFont(fuente,500);
				app.fill(255);
				app.text(startGame,app.width/2, app.height/2+app.height/4);
				contador++;
			}
			
			
			
			break;
		case 4:
			app.image(regalos[0], 0, 0);

	

			app.textAlign(PConstants.CENTER);
			app.textFont(fuente,24);
			app.fill(255);
			app.text(puntaje, 996,600);

			break;
		case 5:
			app.image(regalos[1], 0, 0);
	


			app.textAlign(PConstants.CENTER);
			app.textFont(fuente,24);
			app.fill(255);
			app.text(puntaje, 996,600);
			break;
		case 6:
			app.image(regalos[2], 0, 0);
	

	
			app.textAlign(PConstants.CENTER);
			app.textFont(fuente,24);
			app.fill(255);
			app.text(puntaje, 996,600);
			break;
		case 7:
			app.image(regalos[3], 0, 0);
	
			app.textAlign(PConstants.CENTER);
			app.textFont(fuente,60);
			app.fill(255);
			app.text(puntaje, 814,280);
			break;
		case 8:
			app.image(regalos[4], 0, 0);
			
			
			break;
		default:
			break;
		}

		partirPalabras();
		
	}

	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// PANTALLA DEL JUEGO
	
	public void pantallajuego() {
		app.image(pantallas[3], 0, 0);
		if (startGame == 0) {
			contadorTopo++;
			tiempoMillis++;
			if (tiempoMillis >= 60) {
				tiempo -= 1;
				tiempoMillis = 0;
			}

			app.fill(255);
			app.textFont(fuente);
			app.textAlign(PConstants.CENTER);
			app.text(ronda, 968 + ((1177 - 968) / 2), 130);
			app.text(tiempo, 968 + ((1177 - 968) / 2), 130 * 2);

			if (golpes < cantTopos) {
				app.fill(255, 0, 0);
			}

			app.text(cantTopos, 968 + ((1177 - 968) / 2), 128 * 3);

			app.text(golpes, 968 + ((1177 - 968) / 2), 128 * 4);
			app.fill(255);
			app.text(puntaje, 968 + ((1177 - 968) / 2), 128 * 5);

			// TODO Auto-generated method stub
			for (int i = 0; i < topos.size(); i++) {
				topos.get(i).pintar();
				if (contadorTopo >= 70) {
					topos.get(i).setSalir(false);
				}

				if (contadorTopo >= 80) {
					topos.get(i).setSalir(true);

					posRanX = (int) app.random(0, 4);
					posRanY = (int) app.random(0, 3);
					topos.get(i).setRanPosX((214 * (posRanX)) + 141);
					topos.get(i).setRanPosY((210 * posRanY) + 117);
				}

			}

			

			if (ronda == 1) {
				if (contadorTopo >= 80) {
					contadorTopo = 0;
				}
			}

			if (ronda == 2) {
				if (contadorTopo >= 80) {
					contadorTopo = 15;
				}
			}

			if (ronda == 3) {
				if (contadorTopo >= 80) {
					contadorTopo = 30;
				}
			}

			if (ronda == 4) {
				if (contadorTopo >= 80) {
					contadorTopo = 45;
				}
			}

			for (int i = 0; i < topos.size(); i++) {
				for (int j = 0; j < topos.size(); j++) {
					Topos top1 = topos.get(i);
					Topos top2 = topos.get(j);

					if (i != j) {
						if (PApplet.dist(top1.getPosX(), top1.getPosY(), top2.getPosX(), top2.getPosY()) < 100 / 2) {

							posRanX = (int) app.random(0, 4);
							posRanY = (int) app.random(0, 3);
							top2.setRanPosX((214 * (posRanX)) + 141);
							top2.setRanPosY((210 * posRanY) + 117);

						}
					}

				}

			}

			if (golpes < cantTopos || tiempo <= 0 && cantTopos > 0) {

				pantalla = 8;

			}

			if (cantTopos <= 0 && golpes > cantTopos && tiempo > 0 && ronda == 1) {
				topos.removeAll(topos);
				pantalla = 4;

			}

			if (cantTopos <= 0 && golpes > cantTopos && tiempo > 0 && ronda == 2) {
				topos.removeAll(topos);
				pantalla = 5;

			}

			if (cantTopos <= 0 && golpes > cantTopos && tiempo > 0 && ronda == 3) {
				topos.removeAll(topos);
				pantalla = 6;

			}

			if (cantTopos <= 0 && golpes > cantTopos && tiempo > 0 && ronda == 4) {
				topos.removeAll(topos);
				pantalla = 7;

			}

			for (int i = 0; i < dust.size(); i++) {
				dust.get(i).pintar();
				if (dust.get(i).getContadorDust() == 4) {
					dust.remove(i);
				}
			}
		}

	}
	// FINALIZA PANTALLA DEL JUEGO
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// SE LE DA LA ORDEN A LOS STRINGS QUE RECIBEN
	public void partirPalabras() {
		if (conexion.getComando() != null) {
			
			if(pantalla==0||pantalla==1){
				if(conexion.getComando().contains("jugar")){
					posX=600;
					posY=500;
				
				}
				if(conexion.getComando().contains("instrucciones")){
					posX=600;
					posY=600;
				
				}
				press(posX,posY);
				conexion.setComando(null);
			}
			
			if(pantalla==4||pantalla==5||pantalla==6||pantalla==8){
		
					
				if (conexion.getComando().contains("b2")) {
					posX = 400;
					posY = 570;
				}

				if (conexion.getComando().contains("c2")) {
					posX = 700;
					posY = 570;
				}
				press(posX,posY);
				conexion.setComando(null);
			}
		
			if(pantalla==7){
				if (conexion.getComando().contains("b2")) {
					posX = 400;
					posY = 550;
				}
				press(posX,posY);
				conexion.setComando(null);
			}
			if(pantalla==3){
		if (startGame == 0) {
			

				if (conexion.getComando().contains("a1")) {
					posX = 140;
					posY = 145;
				}

				if (conexion.getComando().contains("b1")) {
					posX = 356;
					posY = 145;
				}

				if (conexion.getComando().contains("c1")) {
					posX = 567;
					posY = 145;
				}

				if (conexion.getComando().contains("d1")) {
					posX = 785;
					posY = 145;
				}

				if (conexion.getComando().contains("a2")) {
					posX = 140;
					posY = 355;
				}

				if (conexion.getComando().contains("b2")) {
					posX = 356;
					posY = 355;
				}

				if (conexion.getComando().contains("c2")) {
					posX = 567;
					posY = 355;
				}

				if (conexion.getComando().contains("d2")) {
					posX = 785;
					posY = 355;
				}

				if (conexion.getComando().contains("a3")) {
					posX = 140;
					posY = 566;
				}

				if (conexion.getComando().contains("b3")) {
					posX = 356;
					posY = 566;
				}

				if (conexion.getComando().contains("c3")) {
					posX = 567;
					posY = 566;
				}

				if (conexion.getComando().contains("d3")) {
					posX = 785;
					posY = 566;

				}

				golpear(posX, posY);
				conexion.setComando(null);

			}}
		}
	}
	// FINALIZA SE LE DA LA ORDEN A LOS STRINGS QUE RECIBEN
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// SE CREA  LA DIFICULTAD DE LOS NIVELES Y SE LE DA LA ORDEN DE GOLPEAR
	public void golpear(int x, int y) {

		if (conexion.getComando() != null) {
			switch (pantalla) {
			case 3:
				golpes--;
				fail.trigger();
				// TODO Auto-generated method stub
				for (int i = 0; i < topos.size(); i++) {

					// fail.loop();

					if (PApplet.dist(x, y, topos.get(i).getPosX(), topos.get(i).getPosY()) < 150 / 2) {

						golpe.trigger();
						dust.add(new Dust(app, x, y));
						topos.remove(i);

						cantTopos--;
						puntaje += 113;

					}

				}

				posRanX = (int) app.random(0, 4);
				posRanY = (int) app.random(0, 3);
				if (topos.size() < 1) {
					if (ronda == 1) {
						for (int i = 0; i < 5; i++) {
							topos.add(new Topos(app, (214 * (posRanX)) + 141, (210 * posRanY) + 117));

						}
					}

					if (ronda == 2) {
						for (int i = 0; i < 4; i++) {
							topos.add(new Topos(app, (214 * (posRanX)) + 141, (210 * posRanY) + 117));
						}
					}
					if (ronda == 3) {
						for (int i = 0; i < 3; i++) {
							topos.add(new Topos(app, (214 * (posRanX)) + 141, (210 * posRanY) + 117));
						}
					}
					if (ronda == 4) {
						for (int i = 0; i < 2; i++) {
							topos.add(new Topos(app, (214 * (posRanX)) + 141, (210 * posRanY) + 117));
						}
					}
				}
				break;

			}
		}

	}
	// FINALIZA SE CREA  LA DIFICULTAD DE LOS NIVELES Y SE LE DA LA ORDEN DE GOLPEAR
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// ----------/----------/----------/----------/----------/----------/----------/-
	// ---------/----------/----------/----------/----------/----------/----------/----------/-
	// ACCIONES DEL MOUSE
	public void press(int x, int y) {
	
		System.out.println(x + "y" + y);
		switch (pantalla) {
		case 0:

			if (posX >= 541 && posX <= 661 && posY >= 499 && posY <= 544) {

				musica.loop();
				startGame = 5;
				pantalla = 3;
			}
			if (x >= 461 && x <= 738 && y >= 580 && y <= 628) {
				pantalla = 1;
			}

			break;

		case 1:

			if (x >= 541 && x <= 661 && y >= 499 && y <= 544) {

				musica.loop();
				startGame = 5;
				pantalla = 3;
			}

			if (x >= 461 && x <= 738 && y >= 580 && y <= 628) {
				pantalla = 0;
			}
			break;

		case 3:

			break;
		case 4:

			if (x > 642 && x <= 828 && y >= 554 && y <= 599) {
				tiempo = 30;
				golpes = 70;
				puntaje *= 2;
				ronda++;
				cantTopos = 60;
				startGame = 5;
				for (int i = 0; i < 4; i++) {
					topos.add(new Topos(app, (214 * (posRanX)) + 141, (210 * posRanY) + 117));
				}
				pantalla = 3;
			}

			if (x > 384 && x <= 568 && y >= 562 && y <= 598) {
				app.exit();
			}
			break;

		case 5:
			if (x > 642 && x <= 828 && y >= 554 && y <= 599) {
				tiempo = 30;
				golpes = 70;
				puntaje *= 2;
				ronda++;
				cantTopos = 60;
				startGame = 5;
				for (int i = 0; i < 3; i++) {
					topos.add(new Topos(app, (214 * (posRanX)) + 141, (210 * posRanY) + 117));
				}

				pantalla = 3;
			}

			if (x > 384 && x <= 568 && y >= 562 && y <= 598) {
				app.exit();
			}
			break;

		case 6:
			if (x > 642 && x <= 828 && y >= 554 && y <= 599) {
				tiempo = 30;
				golpes = 70;
				puntaje *= 2;
				ronda++;
				cantTopos = 60;
				startGame = 5;
				for (int i = 0; i < 2; i++) {
					topos.add(new Topos(app, (214 * (posRanX)) + 141, (210 * posRanY) + 117));
				}
				pantalla = 3;
			}

			if (x > 384 && x <= 568 && y >= 562 && y <= 598) {
				app.exit();
			}
			break;

		case 7:

			if (x >= 375 && x <= 551 && y >= 522 && y < 560) {
				app.exit();
			}
			break;

		case 8:

			if (x >= 375 && x <= 551 && y >= 522 && y < 560) {

				app.exit();

			}

			if (x >= 620 && x <= 834 && y >= 522 && y < 560) {
				tiempo = 30;
				golpes = 70;
				puntaje = 0;
				ronda = 1;
				cantTopos = 60;
				startGame = 5;
				pantalla = 3;

			}

			break;

		default:
			break;
		}

	}
	
	
	public void teclado(){
		pantalla++;
	}
	// FINALIZA ACCIONES DEL MOUSE
}
