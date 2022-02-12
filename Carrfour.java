import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import javax.swing.JPanel;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Carrfour {

	static JFrame frmCarrfour;
	private ImageIcon image1;
	static JPanel lightV1_1;
	static JPanel lightV1_2;
	static JPanel lightV2_1;
	static JPanel lightV2_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Carrfour();
					Carrfour.frmCarrfour.setVisible(true);

					Semaphore slight1r = new Semaphore(1, true), slight2r = new Semaphore(0, true),
							slight1l = new Semaphore(1, true), slight2l = new Semaphore(0, true),
							busStationL = new Semaphore(1, true), busStationR = new Semaphore(1, true);

					int coordonneesVoie1[] = { -150, -60, 30, 120, 210, 300, 390, 480, 570, 845, 935, 1025, 1115, 1205,
							1295, 1385 }, coordonneesVoie2[] = { -110, -20, 70, 160, 440, 530, 620, 710 };

					int[][] coordonnees = { coordonneesVoie1, coordonneesVoie2 };

					String type[] = { "car", "car", "car", "bus", "bus" };

					ArrayList<Semaphore> svoie1L = new ArrayList<Semaphore>();
					ArrayList<Semaphore> svoie2L = new ArrayList<Semaphore>();
					ArrayList<Semaphore> svoie1R = new ArrayList<Semaphore>();
					ArrayList<Semaphore> svoie2R = new ArrayList<Semaphore>();
					ArrayList<Semaphore> crossroads = new ArrayList<Semaphore>();
					ArrayList<Semaphore> busStation = new ArrayList<Semaphore>();
					busStation.add(busStationL);
					busStation.add(busStationR);

					ArrayList<ArrayList<Semaphore>> semaphores = new ArrayList<ArrayList<Semaphore>>();
					semaphores.add(svoie1L); 
					semaphores.add(svoie2L); 
					semaphores.add(svoie1R); 
					semaphores.add(svoie2R); 
					semaphores.add(busStation); 
					semaphores.add(crossroads); 

					for (int i = 0; i < 4; i++) {
						crossroads.add(new Semaphore(1, true));
					}
					for (int i = 0; i < coordonneesVoie1.length; i++) {
						svoie1L.add(new Semaphore(1, true));
						svoie1R.add(new Semaphore(1, true));
					}
					for (int i = 0; i < coordonneesVoie2.length; i++) {
						svoie2L.add(new Semaphore(1, true));
						svoie2R.add(new Semaphore(1, true));
					}

					SwitchLight ch = new SwitchLight(slight1r, slight2r, slight1l, slight2l);
					ch.start();

					int nb_v1r = (new Random()).nextInt(20);
					for (int i = 0; i < nb_v1r; i++) {
						ROAD12 v1r = new ROAD12(slight1r, semaphores, coordonnees, type[(new Random()).nextInt(5)]);
						v1r.start();
					}

					int nb_v1l = (new Random()).nextInt(20);
					for (int i = 0; i < nb_v1l; i++) {
						ROAD11 v1l = new ROAD11(slight1l, semaphores, coordonnees, type[(new Random()).nextInt(5)]);
						v1l.start();
					}

					int nb_v2l = (new Random()).nextInt(20);
					for (int i = 0; i < nb_v2l; i++) {
						ROAD21 v2l = new ROAD21(slight2l, semaphores, coordonnees, type[(new Random()).nextInt(5)]);
						v2l.start();
					}

					int nb_v2r = (new Random()).nextInt(20);
					for (int i = 0; i < nb_v2r; i++) {
						ROAD22 v2r = new ROAD22(slight2r, semaphores, coordonnees, type[(new Random()).nextInt(5)]);
						v2r.start();
					}

				} catch (Exception e) {
				}
			}
		});
	}

	public Carrfour() {
		initialize();
	}

	private JPanel initialize() {
		frmCarrfour = new JFrame();
		 Icon icon = new ImageIcon("src/MAP.png");
		 try {
		 	frmCarrfour.setContentPane(new JLabel(icon));
		   } catch (Exception e) {
     }
	    frmCarrfour.getContentPane().setBackground(Color.GRAY);
		frmCarrfour.setTitle("Traffic Car Intersection");
		frmCarrfour.setBounds(0, 0, 1360, 730);
		frmCarrfour.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCarrfour.getContentPane().setLayout(null);


		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(650, 235, 100, 20);
		frmCarrfour.getContentPane().add(panel);
		panel.setLayout(null);

		
		lightV2_1 = new JPanel();
		lightV2_1.setBounds(0, 0, 100, 20);
		panel.add(lightV2_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(640, 342, 20, 100);
		frmCarrfour.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lightV1_1 = new JPanel();
		lightV1_1.setBounds(0, 0, 20, 100);
		panel_1.add(lightV1_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(845, 245, 20, 100);
		frmCarrfour.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		lightV1_2 = new JPanel();
		lightV1_2.setBounds(0, 0, 20, 100);
		panel_2.add(lightV1_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(760, 436, 100,20);
		frmCarrfour.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		lightV2_2 = new JPanel();
		lightV2_2.setBounds(0, 0, 100, 20);
		panel_3.add(lightV2_2);

		lightV1_1.setBackground(new Color(0, 255, 0));
		lightV1_2.setBackground(new Color(0, 255, 0));
		lightV2_1.setBackground(new Color(220, 20, 60));
		lightV2_2.setBackground(new Color(220, 20, 60));
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 0, 0,0);
		frmCarrfour.getContentPane().add(panel_4);
		panel_4.setBackground(Color.WHITE);
		return panel_4;


	}
}