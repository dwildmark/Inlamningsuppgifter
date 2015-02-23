package main;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class MoviePane extends JPanel{
	private Controller controller;
	private JPanel main;
	private JTextField inTitle, inGenre, inDirector, inActors, inLength, inRating;
	private JRadioButton rbDvd, rbBlueray;
	
	public MoviePane(Controller controller) {
		this.controller = controller;
	}
	private void initFrame() {
		setPreferredSize(new Dimension(200, 800));
		setLayout(new GridLayout(8,1));
	}
}
