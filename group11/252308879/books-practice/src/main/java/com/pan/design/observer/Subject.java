package com.pan.design.observer;

/**
 * Created by Pan on 2017/6/10.
 * 观察者模式中主题对象
 */
public interface Subject {

	/**
	 * 注册观察者对象
	 * @param observer
	 */
	void registerObserver(Observer observer);

	/**
	 * 注销观察者对象
	 * @param observer
	 */
	void removeObserver(Observer observer);

	/**
	 * 主题发生改变
	 * 通知所有观察者对象
	 */
	void notifyObserver();

}
