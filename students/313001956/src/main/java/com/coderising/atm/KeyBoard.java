package com.coderising.atm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JFrame;

public class KeyBoard implements KeyListener {

	int charA = 0;

	public String getUserInput() {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void keyPressed(KeyEvent e) {		 
		SuperKeypad sk = (SuperKeypad) e.getSource();
		charA = e.getKeyCode();
		sk.display.displayPassword('*');	
		if (charA != 10) {
			sk.appendStr((char) (int) charA);
		} else {
			sk.display.displayPassword((char) (int) charA);
			sk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			sk.removeKeyListener(this);
			sk.reGetPassword();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
