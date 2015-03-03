package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class MoviePane extends JPanel implements ActionListener{
	private Controller controller;
	private JPanel radioButtons;
	private JTextField inTitle, inGenre, inDirector, inActors, inLength, inRating;
	private JRadioButton rbDvd, rbBlueray;
	private ButtonGroup bg;
	private JButton submitBtn;
	private JFrame frame;
	private UserInterface ui;
	
	public MoviePane(Controller controller, JFrame frame, UserInterface ui) {
		this.controller = controller;
		this.frame = frame;
		this.ui = ui;
		initFrame();
	}
	private void initFrame() {
		setPreferredSize(new Dimension(200, 350));
		setLayout(new GridLayout(8,1));
		
		radioButtons = new JPanel(new GridLayout(1, 2));
		submitBtn = new JButton("Spara");
		inTitle = new JTextField("Titel");
		inGenre = new JTextField("Genre");
		inDirector = new JTextField("Regissör");
		inActors = new JTextField("Skådespelare");
		inLength = new JTextField("Längd");
		inRating = new JTextField("Betyg");
		rbDvd = new JRadioButton("DVD");
		rbBlueray = new JRadioButton("BlueRay");
		bg = new ButtonGroup();
		bg.add(rbDvd);
		bg.add(rbBlueray);
		submitBtn.addActionListener(this);
		
		add(inTitle);
		add(inGenre);
		add(inDirector);
		add(inActors);
		add(inLength);
		add(inRating);
		radioButtons.add(rbDvd);
		radioButtons.add(rbBlueray);
		add(radioButtons);
		add(submitBtn);
		
	}
	private void submitMovie() {
		String title, genre, director;
		String [] actors;
		Double length = null, rating = null;
		int dvdOrBlueray;
		boolean accept = true;
		title = inTitle.getText();
		genre = inGenre.getText();
		director = inDirector.getText();
		actors = inActors.getText().split(", ");
		try {
			length = Double.parseDouble(inLength.getText());
			rating = Double.parseDouble(inRating.getText());
		} catch(NumberFormatException e) {
			accept = false;
			JOptionPane.showMessageDialog(null, "Längd och betyg måste bestå av decimala tal.");
		}
		if(rbDvd.isSelected()) {
			dvdOrBlueray = 0;
		} else {
			dvdOrBlueray = 1;
		}
		if(accept) {
			Movie movie = new Movie(title, genre, actors, length, director, rating, dvdOrBlueray);
			controller.addMovie(movie);
			controller.sort(new TitleAsc());
			ui.update();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submitBtn) {
			submitMovie();
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
}
