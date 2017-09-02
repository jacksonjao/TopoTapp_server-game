package redesTaller1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Conexion extends Thread {
	// creo el socket para el intercambio de datos
	private DatagramSocket socket;
	// creo mi paquete, el me traerá la info
	private DatagramPacket paquete;
	// creo el puerto al por el que se comunicarán
	private final int puerto = 5000;
	private String comando;
private String ip;
	public Conexion() {
		// TODO Auto-generated constructor stub

		try {

			socket = new DatagramSocket(puerto);
		System.out.println(InetAddress.getLocalHost());
		ip="ip del servidor: "+InetAddress.getLocalHost().getHostAddress();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {

		while (!Thread.currentThread().isInterrupted()) {

			try {
				recibir();
				// este sleep no lo estoy usando, fue por si las moscas :)
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();

			}

		}

	}

	public void recibir() {
		try {
			// creo mi bufer que almacenará los datos
			byte[] buffer = new byte[1024];

			// mi paquete va a tener las características de este bufer
			paquete = new DatagramPacket(buffer, buffer.length);
			// recibo lo que me enviaron
			socket.receive(paquete);

			// mi paquete trae Strings contenidos en byte y los extraigo.
			comando = new String(paquete.getData(), 0, paquete.getLength());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getComando() {
		return comando;

	}

	public void setComando(String comando) {
		this.comando = comando;
	}

	public String getIp() {
		
		return ip;
	}

	
	
}
