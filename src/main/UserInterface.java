package main;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javafx.stage.FileChooser;

import javax.swing.*;

public class UserInterface extends JPanel{
	private JPanel center, west, topButtonPanel, listPanel, searchPanel;
	private JButton title, type, genre, actors, length, director, 
	rating, openFile, saveFile, searchButton, sort, shuffle, delete, addMovie;
	private JList<String> listOfMovies;
	private JTextField searchField;
	private Comparator<Movie> currentComp;
	private Controller controller;
	private boolean sortAsc;
	
	public UserInterface(){
		
		controller = new Controller();
		sortAsc = true;
		center = new JPanel();
		west = new JPanel();
		topButtonPanel = new JPanel();
		listPanel = new JPanel();
		searchPanel = new JPanel();
		
		title = new JButton("Titel");
		type = new JButton("Typ");
		genre = new JButton("Genre");
		actors = new JButton("Skådespelare");
		length = new JButton("Längd");
		director = new JButton("Regissör");
		rating = new JButton("Betyg");
		openFile = new JButton("Öppna fil");
		saveFile = new JButton("Spara fil");
		searchButton = new JButton("Sök:");
		sort = new JButton("Sortera");
		shuffle = new JButton("Blanda");
		delete = new JButton("Radera");
		addMovie = new JButton("Lägg till film");
		searchField = new JTextField();
		listOfMovies = new JList<String>();
		listOfMovies.setPreferredSize(new Dimension(1640, 600));
		listOfMovies.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		Dimension leftBtns = new Dimension(180, 40);
		openFile.setPreferredSize(leftBtns);
		saveFile.setPreferredSize(leftBtns);
		sort.setPreferredSize(leftBtns);
		shuffle.setPreferredSize(leftBtns);
		delete.setPreferredSize(leftBtns);
		addMovie.setPreferredSize(leftBtns);
		searchButton.setPreferredSize(new Dimension(80, 40));
		searchField.setPreferredSize(new Dimension(100, 40));
		ButtonListener btnListener = new ButtonListener();
		SortingListener srtListener = new SortingListener();
		openFile.addActionListener(btnListener);
		saveFile.addActionListener(btnListener);
		addMovie.addActionListener(btnListener);
		delete.addActionListener(btnListener);
		shuffle.addActionListener(btnListener);
		title.addActionListener(srtListener);
		type.addActionListener(srtListener);
		genre.addActionListener(srtListener);
		length.addActionListener(srtListener);
		director.addActionListener(srtListener);
		rating.addActionListener(srtListener);
		sort.addActionListener(btnListener);
		setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());
		west.setLayout(new FlowLayout());
		topButtonPanel.setLayout(new GridLayout(1,7));
		searchPanel.setLayout(new BorderLayout());
		
		setPreferredSize(new Dimension(1850, 600));
		west.setPreferredSize(new Dimension(200, 1000));
		
		searchPanel.add(searchButton, BorderLayout.WEST);
		searchPanel.add(searchField, BorderLayout.CENTER);
		topButtonPanel.add(title);
		topButtonPanel.add(type);
		topButtonPanel.add(genre);
		topButtonPanel.add(actors);
		topButtonPanel.add(length);
		topButtonPanel.add(director);
		topButtonPanel.add(rating);
		listPanel.add(listOfMovies);
		west.add(openFile);
		west.add(saveFile);
		west.add(Box.createRigidArea(new Dimension(180, 40)));
		west.add(searchPanel);
		west.add(Box.createRigidArea(new Dimension(180, 40)));
		west.add(sort);
		west.add(shuffle);
		west.add(Box.createRigidArea(new Dimension(180, 40)));
		west.add(addMovie);
		west.add(delete);
		center.add(topButtonPanel, BorderLayout.NORTH);
		center.add(listPanel, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);		
		add(center, BorderLayout.CENTER);
		
		currentComp = new TitleAsc();
		
//		Movie testMovie = new Movie("Test1", "Testttt", new String[] {"Jag", "Du"}, 3.4, "Direktor", 3.4, 1);
//		arrMovies.add(testMovie);
//		//listOfMovies = new JList(testMovie);
		
	}
	
	private void selectComp(String comp) {
		switch(comp) {
		case "title":
			if(sortAsc)
				currentComp = new TitleAsc();
			else
				currentComp = new TitleDsc();
			break;
			
		case "genre":
			if(sortAsc)
				currentComp = new GenreAsc();
			else
				currentComp = new GenreDsc();
			break;
			
		case "director":
			if(sortAsc)
				currentComp = new DirectorAsc();
			else
				currentComp = new DirectorDsc();
			break;
			
		case "type":
			if(sortAsc)
				currentComp = new TypeAsc();
			else
				currentComp = new TypeDsc();
			break;
			
		case "rating":
			if(sortAsc)
				currentComp = new RatingAsc();
			else
				currentComp = new RatingDsc();
			break;
		
		case "length":
			if(sortAsc)
				currentComp = new LengthAsc();
			else
				currentComp = new LengthDsc();
			break;
		}
	}
	
	private void update() {
		
		String[] content = new String[controller.getMovieList().size()];
		for(int i = 0; i < controller.getMovieList().size(); i++) {
			content[i] = controller.getMovieList().get(i).toString();
		}
		listOfMovies.setListData(content);
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == openFile) {
				JFileChooser fc = new JFileChooser();
				int result = fc.showOpenDialog(UserInterface.this);
				if(result == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						controller.openFile(file);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Fel när filen öppnades. Försök igen!");
					}
				}
				update();
				
			} else if(e.getSource() == saveFile) {
				JFileChooser fc = new JFileChooser();
				int result = fc.showSaveDialog(UserInterface.this);
				if(result == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						controller.saveFile(file);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Fel när filen sparades. Försök igen!");
					}
				}
			} else if(e.getSource() == addMovie) {
				controller.addMovie();
				controller.sortBubble(currentComp);
				update();
			} else if(e.getSource() == delete && listOfMovies.getSelectedIndex() >= 0) {
				controller.removeMovie(controller.getMovieList().get(listOfMovies.getSelectedIndex()));
				update();
			} else if(e.getSource() == shuffle) {
				controller.shuffleList();
				update();
			} else if(e.getSource() == sort) {
				controller.sortBubble(currentComp);
				update();
			}
		}
		
	}
	
	private class SortingListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == title) { 
				if(currentComp instanceof TitleAsc || currentComp instanceof TitleDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("title");
				controller.sortBubble(currentComp);
				update();
			} else if(e.getSource() == type) {
				if(currentComp instanceof TypeAsc || currentComp instanceof TypeDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("type");
				controller.sortBubble(currentComp);
				update();
			} else if(e.getSource() == genre) {
				if(currentComp instanceof GenreAsc || currentComp instanceof GenreDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("genre");
				controller.sortBubble(currentComp);
				update();
			} else if(e.getSource() == director) {
				if(currentComp instanceof DirectorAsc || currentComp instanceof DirectorDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("director");
				controller.sortBubble(currentComp);
				update();
			} else if(e.getSource() == length) {
				if(currentComp instanceof LengthAsc || currentComp instanceof LengthDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("length");
				controller.sortBubble(currentComp);
				update();
			} else if(e.getSource() == rating) {
				if(currentComp instanceof RatingAsc || currentComp instanceof RatingDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("rating");
				controller.sortBubble(currentComp);
				update();
			}
		}
		
	}
}
