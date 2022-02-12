import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ROAD12 extends Thread {
	private Semaphore sfeu1;
	private ArrayList<ArrayList<Semaphore>> semaphores;

	private int i, coordonnees[][];
	private String type;
	private String direction[] = { "ROAD11", "ROAD21", "RAOD22" };
	private String myDirection;

	public ROAD12(Semaphore sfeu1, ArrayList<ArrayList<Semaphore>> semaphores, int[][] coordonnees, String type) {
		this.sfeu1 = sfeu1;
		this.semaphores = semaphores;
		this.coordonnees = coordonnees;
		this.i = coordonnees[0].length - 1;
		this.type = type;
		this.myDirection = direction[new Random().nextInt(direction.length)];
	}

	public void run() {
		try {
			traversee1();
		} catch (InterruptedException e) {
		}
	}

	private void traversee1() throws InterruptedException {
		int x = coordonnees[0][i], y = 270, w = 50, h = 40;
		if (type.equals("bus"))
			w = 65;
	      Icon icon = new ImageIcon("src/TRUCK.PNG");
		JButton car = new JButton(icon);
		car.setBounds(x, y, w, h);
		Carrfour.frmCarrfour.getContentPane().add(car);
		sleep((new Random()).nextInt(60000));
		semaphores.get(2).get(i).acquire();
		while (coordonnees[0][i] != 570) {
			while (x > coordonnees[0][i]) {
				sleep(10);
				car.setBounds(x--, y, w, h);
			}
			if (x == 845)
				sfeu1.acquire();
			else {
				semaphores.get(2).get(i - 1).acquire();
				semaphores.get(2).get(i).release();
			}
			i--;
		}

		switch (myDirection) {
		case "ROAD11":
			semaphores.get(5).get(2).acquire();
			i++;
			semaphores.get(2).get(i).release();
			i--;
			while (x >= coordonnees[0][i]) {
				sleep(10);
				car.setBounds(x--, y, w, h);
			}
			semaphores.get(5).get(2).release();
			sfeu1.release();
			i--;
			switch (type) {
			case "bus":
				while (coordonnees[0][i] != 210) {
					while (x > coordonnees[0][i]) {
						sleep(10);
						car.setBounds(x--, y, w, h);
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
					car.setBounds(x--, y--, w, h);
				}
				semaphores.get(2).get(i).release();
				i--;
				sleep(2500);
				i--;
				semaphores.get(2).get(i + 1).acquire();
				semaphores.get(2).get(i).acquire();
				for (int j = 0; j < 65; j++) {
					sleep(10);
					car.setBounds(x--, y++, w, h);
				}
				semaphores.get(2).get(i + 1).release();
				semaphores.get(2).get(i).release();
				i--;
				semaphores.get(4).get(1).release();
				while (i != -1) {
					while (x > coordonnees[0][i]) {
						sleep(10);
						car.setBounds(x--, y, w, h);
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
						car.setBounds(x--, y, w, h);
					}
					if ((i - 1) != -1)
						semaphores.get(2).get(i - 1).acquire();
					semaphores.get(2).get(i).release();
					i--;
				}
				break;
			}
			break;
		case "ROAD21":
			semaphores.get(5).get(2).acquire();
			i++;
			semaphores.get(2).get(i).release();
			while (x >= 800) {
				sleep(10);
				car.setBounds(x--, y, w, h);
			}
			while (x > 785) {
				sleep(10);
				car.setBounds(x--, y--, w, h);
			}
			i = 3;
			semaphores.get(5).get(2).release();
			sfeu1.release();
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
			semaphores.get(5).get(2).acquire();
			i++;
			semaphores.get(2).get(i).release();
			while (x >= 685) {
				sleep(10);
				car.setBounds(x--, y, w, h);
			}
			while (x > 700) {
				sleep(10);
				car.setBounds(x--, y++, w, h);
			}
			i = 4;
			while (y < coordonnees[1][i]) {
				sleep(10);
				car.setBounds(x, y++, h, w);
			}
			i++;
			semaphores.get(5).get(0).release();
			semaphores.get(5).get(2).release();
			sfeu1.release();
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