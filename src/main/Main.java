package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		UserInterface ui = new UserInterface();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(ui);
		frame.pack();
		frame.setVisible(true);
	}
}
