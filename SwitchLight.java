import java.awt.Color;
import java.util.concurrent.Semaphore;

public class SwitchLight extends Thread {
	private Semaphore slight1r , slight2r, slight1l , slight2l;
	private final int Duree_du_light = 4500;
	public SwitchLight(Semaphore slight1r, Semaphore slight2r, Semaphore slight1l, Semaphore slight2l ) {
		this.slight1r = slight1r;
		this.slight2r = slight2r;
		this.slight1l = slight1l;
		this.slight2l = slight2l;
	}
	public void run() {
		try {
			SwitchingLights();
		} catch (InterruptedException e) {}
	}
	
	private void SwitchingLights() throws InterruptedException {
		int Feu = 1;
		while(true) {
			sleep(Duree_du_light);
			if(Feu == 1) {
				slight1r.acquire();
				Carrfour.lightV1_1.setBackground(new Color(255,69,0));
				Carrfour.lightV1_2.setBackground(new Color(255,69,0));
				Carrfour.lightV2_1.setBackground(new Color(255,69,0));
				Carrfour.lightV2_2.setBackground(new Color(255,69,0));
				slight1l.acquire();
				Carrfour.lightV1_1.setBackground(new Color(220, 20, 60));
				Carrfour.lightV1_2.setBackground(new Color(220, 20, 60));
				Carrfour.lightV2_1.setBackground(new Color(0,255,0));
				Carrfour.lightV2_2.setBackground(new Color(0,255,0));
				Feu = 2;
				slight2r.release();
				slight2l.release();
			}else {
				slight2r.acquire();
				Carrfour.lightV1_1.setBackground(new Color(255,69,0));
				Carrfour.lightV1_2.setBackground(new Color(255,69,0));
				Carrfour.lightV2_1.setBackground(new Color(255,69,0));
				Carrfour.lightV2_2.setBackground(new Color(255,69,0));
				slight2l.acquire();
				Carrfour.lightV1_1.setBackground(new Color(0,255,0));
				Carrfour.lightV1_2.setBackground(new Color(0,255,0));
				Carrfour.lightV2_1.setBackground(new Color(220, 20, 60));
				Carrfour.lightV2_2.setBackground(new Color(220, 20, 60));
				Feu = 1;
				slight1r.release();
				slight1l.release();
			}
		}
	}

}