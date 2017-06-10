package com.pan.design.observer;

import java.util.Observable;

/**
 * Created by Pan on 2017/6/10.
 * 使用JDK自带的Observable类实现主题
 */
public class WeatherData2 extends Observable {

	private float temperature;
	private float humidity;
	private float pressure;

	public void measurementsChanged(){
		setChanged();
		notifyObservers();
	}

	public void setMeasurements(float temperature, float humidity, float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public float getPressure() {
		return pressure;
	}

	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
}
