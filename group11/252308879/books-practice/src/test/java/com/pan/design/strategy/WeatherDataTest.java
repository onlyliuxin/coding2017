package com.pan.design.strategy;

import com.pan.design.observer.CurrentConditionDisplay;
import com.pan.design.observer.CurrentConditionDisplay2;
import com.pan.design.observer.WeatherData;
import com.pan.design.observer.WeatherData2;
import org.junit.Test;

/**
 * Created by Pan on 2017/6/10.
 */
public class WeatherDataTest {

	@Test
	public void testWeatherData(){
		WeatherData weatherData = new WeatherData();
		CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);

		weatherData.setMeasurements(80f, 60f, 30.4f);
		weatherData.setMeasurements(82f, 70f, 29.2f);
		weatherData.setMeasurements(78f, 90f, 29.2f);
	}

	@Test
	public void testWeatherData2(){
		WeatherData2 weatherData2 = new WeatherData2();
		CurrentConditionDisplay2 currentConditionDisplay = new CurrentConditionDisplay2(weatherData2);

		weatherData2.setMeasurements(80f, 60f, 30.4f);
		weatherData2.setMeasurements(82f, 70f, 29.2f);
		weatherData2.setMeasurements(78f, 90f, 29.2f);
	}
}
