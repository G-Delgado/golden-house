package ui;

import java.util.Observable;

public class Clock extends Observable implements Runnable{
	private int horas, minutos, segundos;
	
	public Clock(int h, int m, int s) {
		horas = h;
		minutos = m;
		segundos = s;
		
	}
	
	public void run() {
		String tiempo = "";
		while (true) {
			tiempo = "";
			if (horas < 10) {
				tiempo += "0" + horas;
			} else {
				tiempo += horas;
			}
			
			tiempo += ":";
			
			if (minutos < 10) {
				tiempo += "0" + minutos;
			} else {
				tiempo += minutos;
			}
			
			tiempo += ":";
			
			if (segundos < 10) {
				tiempo += "0" + segundos;
			} else {
				tiempo += segundos;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.setChanged();
			this.notifyObservers(tiempo);
			this.clearChanged();
			
			segundos++;
			
			if (segundos == 60) {
				minutos++;
				segundos = 0;
				if (minutos == 60) {
					minutos = 0;
					horas++;
					if (horas == 24) {
						horas = 0;
					}
				}
			}
		}
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
}
