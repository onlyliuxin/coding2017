package com.pan.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2017/6/10.
 * 被观察的对象需要实现主题接口
 */
public class WeatherData implements Subject {

	/**
	 * 这个主题中注册的观察者集合
	 */
	private List<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;

	public WeatherData(){
		observers = new ArrayList<>();
	}

	@Override
	public void registerObserver(Observer observer) {
		if (observer != null){
			observers.add(observer);
		}
	}

	@Override
	public void removeObserver(Observer observer) {
		if (observer == null){
			return;
		}
		observers.removeIf(t -> t == observer);
	}

	@Override
	public void notifyObserver() {
		observers.forEach(observer -> observer.update(temperature, humidity, pressure));
	}

	/**
	 * 从气象局中得到的更新值：
	 * 		得到更新值之后，主动通知各个注册的观察者
	 */
	public void measurementsChanged(){
		notifyObserver();
	}

	/**
	 * 用来测试气象站的类
	 * @param temperature
	 * @param humidity
	 * @param pressure
	 */
	public void setMeasurements(float temperature, float humidity, float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}
}
