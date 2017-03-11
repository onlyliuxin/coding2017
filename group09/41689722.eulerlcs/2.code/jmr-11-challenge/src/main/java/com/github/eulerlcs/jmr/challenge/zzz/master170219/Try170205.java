package com.github.eulerlcs.jmr.challenge.zzz.master170219;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Try170205 {
	public static void main(String[] args) throws Exception {
		task94();
		task93();
		task84();
		task83();
		task65();
		task64();

		ArrayList<Integer> list = new ArrayList<Integer>();
		show(list);
	}

	public static void show(ArrayList<? extends Number> list) {
		// ....
	}

	public static void task64() {
		DataInputStream dis = null;
		double price = 0;
		int count = 0;
		double sum = 0;
		String disp = "";

		try {
			dis = new DataInputStream(new FileInputStream("data/shoppingcart.data"));
			while (dis.available() > 0) {
				price = dis.readDouble();
				count = dis.readInt();
				disp = dis.readUTF();
				System.out.println(disp);
				sum += price * count;
			}

			System.out.println("sum=" + sum);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void task65() {
		DataInputStream dis = null;
		byte[] magic = { (byte) 0xca, (byte) 0xfe, (byte) 0xba, (byte) 0xbe };
		boolean ret = true;

		try {
			dis = new DataInputStream(new FileInputStream("data/sc.class"));
			for (int i = 0; i < 4; i++) {
				if (magic[i] != dis.readByte()) {
					ret = false;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (ret) {
			System.out.println("it is cafebabe");
		} else {
			System.out.println("it is not cafebabe");
		}
	}

	public static void task83() throws Exception {
		Class<?> clazz = Class.forName("shoppingcart.Employee");
		Constructor<?> ct = clazz.getConstructor(String.class, int.class);
		Object obj = ct.newInstance("ref", 22);

		Method sayHello = clazz.getDeclaredMethod("sayHello");
		sayHello.invoke(obj);

		Method getID = clazz.getDeclaredMethod("getID");
		getID.setAccessible(true);
		String ids = (String) getID.invoke(obj);
		System.out.println("getID=" + ids);

		Field[] flds = clazz.getDeclaredFields();
		for (Field fld : flds) {
			System.out.println(fld);
		}
	}

	public static void task84() throws Exception {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(3232);

		Class<?> clazz = ArrayList.class;

		Field elementDataField = clazz.getDeclaredField("elementData");
		elementDataField.setAccessible(true);
		Object[] elementData = (Object[]) elementDataField.get(list);
		if (elementData.length > 1) {
			elementData[1] = "added by reflection";
		}
	}

	public static void task93() {
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		System.out.println(list1.getClass().equals(list2.getClass()));
	}

	public static void task94() {
		ArrayList<Number> numbers = new ArrayList<Number>();
		numbers.add(new Integer(10));
		numbers.add(new Double(10.0d));
	}
}
