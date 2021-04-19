package ui;

import java.time.LocalTime;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClockMain extends Thread{
	
	private int horas, minutos, segundos;
	
	@FXML
	private Label timeLabel;
	
	public ClockMain(String msg, Label timeLabel) {
		super(msg);
		horas = LocalTime.now().getHour();
		minutos = LocalTime.now().getMinute();
		segundos = LocalTime.now().getSecond();
		this.timeLabel = timeLabel;
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
			
			timeLabel.setText(tiempo);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
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

	public Label getTimeLabel() {
		return timeLabel;
	}

	public void setTimeLabel(Label timeLabel) {
		this.timeLabel = timeLabel;
	}

}
