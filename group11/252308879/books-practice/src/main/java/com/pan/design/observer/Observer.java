package com.pan.design.observer;

/**
 * Created by Pan on 2017/6/10.
 * 观察者对象的抽象方法：
 * 		所有观察者对象必须实现下面的方法
 */
public interface Observer {

	void update(float temp, float humidity, float pressure);
}
