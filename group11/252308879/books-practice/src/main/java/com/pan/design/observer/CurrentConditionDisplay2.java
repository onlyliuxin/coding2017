package com.pan.design.observer;

import java.util.*;

/**
 * Created by Pan on 2017/6/10.
 */
public class CurrentConditionDisplay2 implements java.util.Observer, DisplayElement {

	private float temperature;
	private float humidity;
	private Observable observable;

	public CurrentConditionDisplay2(Observable observable){
		this.observable = observable;
		observable.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Current Conditions: " + temperature + "F degrees and " + humidity + "% humidity");
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof WeatherData2){
			WeatherData2 weatherData2 = (WeatherData2) o;
			this.temperature = weatherData2.getTemperature();
			this.humidity = weatherData2.getHumidity();
			display();
		}
	}
}
