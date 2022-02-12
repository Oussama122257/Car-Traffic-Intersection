import java.util.ArrayList;

import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ROAD11 extends Thread {
	private Semaphore slight1;
	private ArrayList<ArrayList<Semaphore>> semaphores;

	private int i, coordonnees[][];
	private String type;
	private String direction[] = { "ROAD12", "ROAD21", "RAOD22" };
	private String myDirection;

	public ROAD11(Semaphore slight1, ArrayList<ArrayList<Semaphore>> semaphores, int[][] coordonnees, String type) {
		this.slight1 = slight1;
		this.semaphores = semaphores;
		this.coordonnees = coordonnees;
		this.type = type;
		this.i = 0;
		this.myDirection = direction[new Random().nextInt(direction.length)];
	}

	public void run() {
		try {
			traversee1();
		} catch (InterruptedException e) {
		}
	}

	private void traversee1() throws InterruptedException {
		int x = coordonnees[0][i], y = 380, w = 50, h = 40;
	      Icon icon = new ImageIcon("src/PNG.PNG");
		JButton car = new JButton(icon);
		Carrfour.frmCarrfour.getContentPane().add(car);
		sleep((new Random()).nextInt(60000));
		semaphores.get(0).get(i).acquire();
		switch (type) {
		case "bus":
			w = 65;
			car.setBounds(x, y, w, h);
			while (coordonnees[0][i] != 210) {
				while (x < coordonnees[0][i]) {
					sleep(10);
					car.setBounds(x++, y, w, h);
				}
				if (x == 120)
					semaphores.get(4).get(0).acquire();
				semaphores.get(0).get(i + 1).acquire();
				semaphores.get(0).get(i).release();
				i++;
			}
			for (int j = 0; j < 35; j++) {
				sleep(10);
				car.setBounds(x++, y, w, h);
			}
			semaphores.get(0).get(i).release();
			for (int j = 0; j < 65; j++) {
				sleep(10);
				car.setBounds(x++, y++, w, h);
			}
			for (int j = 0; j < 10; j++) {
				sleep(10);
				car.setBounds(x++, y, w, h);
			}
			sleep(2500);
			i++;
			semaphores.get(0).get(i).acquire();
			for (int j = 0; j < 65; j++) {
				sleep(10);
				car.setBounds(x++, y--, w, h);
			}
			semaphores.get(4).get(0).release();
			while (coordonnees[0][i] != 845) {
				while (x < coordonnees[0][i]) {
					sleep(10);
					car.setBounds(x++, y, w, h);
				}
				if (x == 570)
					slight1.acquire();
				else {
					semaphores.get(0).get(i + 1).acquire();
					semaphores.get(0).get(i).release();
				}
				i++;
			}
			break;
		case "car":
			car.setBounds(x, y, w, h);
			while (coordonnees[0][i] != 845) {
				while (x < coordonnees[0][i]) {
					sleep(10);
					car.setBounds(x++, y, w, h);
				}
				if (x == 570)
					slight1.acquire();
				else {
					semaphores.get(0).get(i + 1).acquire();
					semaphores.get(0).get(i).release();
				}
				i++;
			}
			break;
		}

		switch (myDirection) {
		case "ROAD12":
			semaphores.get(5).get(0).acquire();
			i--;
			semaphores.get(0).get(i).release();
			i++;
			while (x <= coordonnees[0][i]) {
				sleep(10);
				car.setBounds(x++, y, w, h);
			}
			i++;
			semaphores.get(5).get(0).release();
			slight1.release();
			while (i != coordonnees[0].length) {
				while (x < coordonnees[0][i]) {
					sleep(10);
					car.setBounds(x++, y, w, h);
				}
				if ((i + 1) != coordonnees[0].length)
					semaphores.get(0).get(i + 1).acquire();
				semaphores.get(0).get(i).release();
				i++;
			}
			break;
		case "ROAD21":
			semaphores.get(5).get(0).acquire();
			semaphores.get(5).get(2).acquire();
			i--;
			semaphores.get(0).get(i).release();
			while (x <= 770) {
				sleep(10);
				car.setBounds(x++, y, w, h);
			}
			while (x <= 785) {
				sleep(10);
				car.setBounds(x++, y--, w, h);
			}
			i = 3;
			while (y > coordonnees[1][i]) {
				sleep(10);
				car.setBounds(x, y--, h, w);
			}
			i--;
			semaphores.get(5).get(0).release();
			semaphores.get(5).get(2).release();
			slight1.release();
			while (i != -1) {
				while (y > coordonnees[1][i]) {
					sleep(10);
					car.setBounds(x, y--, h, w);
				}
				if ((i - 1) != -1)
					semaphores.get(3).get(i - 1).acquire();
				semaphores.get(3).get(i).release();
				i--;
			}
			break;
		case "RAOD22":
			semaphores.get(5).get(0).acquire();
			i--;
			semaphores.get(0).get(i).release();
			while (x <= 665) {
				sleep(10);
				car.setBounds(x++, y, w, h);
			}
			while (x <= 680) {
				sleep(10);
				car.setBounds(x++, y++, w, h);
			}
			i = 4;
			semaphores.get(5).get(0).release();
			slight1.release();
			while (i != coordonnees[1].length) {
				while (y < coordonnees[1][i]) {
					sleep(10);
					car.setBounds(x, y++, h, w);
				}
				if ((i + 1) != coordonnees[1].length)
					semaphores.get(1).get(i + 1).acquire();
				semaphores.get(1).get(i).release();
				i++;
			}
			break;
		}

	}
}