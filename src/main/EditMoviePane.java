package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class EditMoviePane extends JPanel implements ActionListener{
	private Controller controller;
	private JPanel radioButtons;
	private JTextField inTitle, inGenre, inDirector, inActors, inLength, inRating;
	private JRadioButton rbDvd, rbBlueray;
	private ButtonGroup bg;
	private JButton submitBtn;
	private JFrame frame;
	private Movie currentMovie;
	private UserInterface ui;
	
	public EditMoviePane(Controller controller, JFrame frame, UserInterface ui, Movie movie) {
		this.controller = controller;
		this.frame = frame;
		this.ui = ui;
		currentMovie = movie;
		initFrame();
	}
	private void initFrame() {
		setPreferredSize(new Dimension(200, 350));
		setLayout(new GridLayout(8,1));
		
		radioButtons = new JPanel(new GridLayout(1, 2));
		submitBtn = new JButton("Spara");
		inTitle = new JTextField(currentMovie.getTitle());
		inGenre = new JTextField(currentMovie.getGenre());
		inDirector = new JTextField(currentMovie.getDirector());
		inActors = new JTextField(currentMovie.getActorsString());
		inLength = new JTextField(currentMovie.getLength() + "");
		inRating = new JTextField(currentMovie.getRating() + "");
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
		if(currentMovie.getType() == 0) {
			rbDvd.setSelected(true);
		} else {
			rbBlueray.setSelected(true);
		}
		
	}
	private void submitMovie() {
		String title, genre, director;
		String [] actors;
		Double length, rating;
		int dvdOrBlueray;
		title = inTitle.getText();
		genre = inGenre.getText();
		director = inDirector.getText();
		actors = inActors.getText().split(", ");
		length = Double.parseDouble(inLength.getText());
		rating = Double.parseDouble(inRating.getText());
		if(rbDvd.isSelected()) {
			dvdOrBlueray = 0;
		} else {
			dvdOrBlueray = 1;
		}
		currentMovie.setTitle(title);
		currentMovie.setGenre(genre);
		currentMovie.setDirector(director);
		currentMovie.setActors(actors);
		currentMovie.setLength(length);
		currentMovie.setRating(rating);
		currentMovie.setType(dvdOrBlueray);
		controller.sort(new TitleAsc());
		ui.update();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submitBtn) {
			submitMovie();
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
}
