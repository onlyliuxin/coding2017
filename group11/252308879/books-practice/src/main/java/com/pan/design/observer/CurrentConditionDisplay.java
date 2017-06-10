package com.pan.design.observer;

/**
 * Created by Pan on 2017/6/10.
 * 具体的观察者，需要继承观察者接口
 */
public class CurrentConditionDisplay implements Observer, DisplayElement {

	private float temperature;
	private float humidity;
	private Subject weatherData;

	/**
	 * 表示需要针对哪一个主题进行观察:
	 * 并且注册到对应的主题中去
	 *
	 * @param weatherData
	 */
	public CurrentConditionDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Current Conditions: " + temperature + "F degrees and " + humidity + "% humidity");
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		this.temperature = temp;
		this.humidity = humidity;
		display();
	}
}
