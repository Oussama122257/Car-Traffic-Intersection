import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ROAD21 extends Thread {
	private Semaphore slight2;
	private ArrayList<ArrayList<Semaphore>> semaphores;

	private String type;
	private int i, coordonnees[][];
	private String direction[] = { "RAOD22", "ROAD11", "ROAD12" };
	private String myDirection;

	public ROAD21(Semaphore slight2, ArrayList<ArrayList<Semaphore>> semaphores, int[][] coordonnees, String type) {
		this.slight2 = slight2;
		this.semaphores = semaphores;
		this.coordonnees = coordonnees;
		this.i = 0;
		this.type = type;
		this.myDirection = direction[new Random().nextInt(direction.length)];
	}

	public void run() {
		try {
			traversee2();
		} catch (InterruptedException e) {
		}
	}

	private void traversee2() throws InterruptedException {
		int x = 680, y = coordonnees[1][i], w = 40, h = 50;
		switch (type) {
		case "car":
			h = 50;
			break;
		case "bus":
			h = 65;
			break;
		}

	      Icon icon = new ImageIcon("src/taxi.PNG");
		JButton car = new JButton(icon);
		car.setBounds(x, y, w, h);
		Carrfour.frmCarrfour.getContentPane().add(car);
		sleep((new Random()).nextInt(60000));
		semaphores.get(1).get(i).acquire();
		while (coordonnees[1][i] != 440) {
			while (y < coordonnees[1][i]) {
				sleep(10);
				car.setBounds(x, y++, w, h);
			}
			if (y == 160)
				slight2.acquire();
			else {
				semaphores.get(1).get(i + 1).acquire();
				semaphores.get(1).get(i).release();
			}
			i++;
		}

		switch (myDirection) {
		case "RAOD22":
			semaphores.get(5).get(1).acquire();
			i--;
			semaphores.get(1).get(i).release();
			i++;
			while (y <= coordonnees[1][i]) {
				sleep(10);
				car.setBounds(x, y++, w, h);
			}
			i++;
			semaphores.get(5).get(1).release();
			slight2.release();
			while (i != coordonnees[1].length) {
				while (y < coordonnees[1][i]) {
					sleep(10);
					car.setBounds(x, y++, w, h);
				}
				if ((i + 1) != coordonnees[1].length)
					semaphores.get(1).get(i + 1).acquire();
				semaphores.get(1).get(i).release();
				i++;
			}
			break;
		case "ROAD11":
			semaphores.get(5).get(1).acquire();
			i--;
			semaphores.get(1).get(i).release();
			while (y <= 250) {
				sleep(10);
				car.setBounds(x, y++, w, h);
			}
			while (y <= 270) {
				sleep(10);
				car.setBounds(x--, y++, w, h);
			}
			i = 8;
			while (x > coordonnees[0][i]) {
				sleep(10);
				car.setBounds(x--, y, h, w);
			}
			i--;
			semaphores.get(5).get(1).release();
			slight2.release();
			switch (type) {
			case "bus":
				while (coordonnees[0][i] != 210) {
					while (x > coordonnees[0][i]) {
						sleep(10);
						car.setBounds(x--, y, h, w);
					}
					if (x == 300) {
						semaphores.get(4).get(1).acquire();
						break;
					}
					semaphores.get(2).get(i - 1).acquire();
					semaphores.get(2).get(i).release();
					i--;
				}
				for (int j = 0; j < 65; j++) {
					sleep(10);
					car.setBounds(x--, y--, h, w);
				}
				semaphores.get(2).get(i).release();
				i--;
				sleep(2500);
				i--;
				semaphores.get(2).get(i + 1).acquire();
				semaphores.get(2).get(i).acquire();
				for (int j = 0; j < 65; j++) {
					sleep(10);
					car.setBounds(x--, y++, h, w);
				}
				semaphores.get(2).get(i + 1).release();
				semaphores.get(2).get(i).release();
				i--;
				semaphores.get(4).get(1).release();
				while (i != -1) {
					while (x > coordonnees[0][i]) {
						sleep(10);
						car.setBounds(x--, y, h, w);
					}
					if ((i - 1) != -1)
						semaphores.get(2).get(i - 1).acquire();
					semaphores.get(2).get(i).release();
					i--;
				}
				break;
			case "car":
				while (i != -1) {
					while (x > coordonnees[0][i]) {
						sleep(10);
						car.setBounds(x--, y, h, w);
					}
					if ((i - 1) != -1)
						semaphores.get(2).get(i - 1).acquire();
					semaphores.get(2).get(i).release();
					i--;
				}
				break;
			}
			break;
		case "ROAD12":
			semaphores.get(5).get(1).acquire();
			semaphores.get(5).get(3).acquire();
			i--;
			semaphores.get(1).get(i).release();
			while (y <= 365) {
				sleep(10);
				car.setBounds(x, y++, w, h);
			}
			while (y < 380) {
				sleep(10);
				car.setBounds(x++, y++, w, h);
			}
			i = 9;
			while (x < coordonnees[0][i]) {
				sleep(10);
				car.setBounds(x++, y, h, w);
			}
			i++;
			semaphores.get(5).get(1).release();
			semaphores.get(5).get(3).release();
			slight2.release();
			while (i != coordonnees[0].length) {
				while (x < coordonnees[0][i]) {
					sleep(10);
					car.setBounds(x++, y, h, w);
				}
				if ((i + 1) != coordonnees[0].length)
					semaphores.get(0).get(i + 1).acquire();
				semaphores.get(0).get(i).release();
				i++;

			}
			break;
		}

	}
}