package main;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.stage.FileChooser;

import javax.swing.*;

public class UserInterface extends JPanel{
	private JPanel center, west, topButtonPanel, listPanel, searchPanel;
	private JButton title, type, genre, actors, length, director, 
	rating, openFile, saveFile, searchButton, sort, shuffle, delete, addMovie;
	private JList<String> listOfMovies;
	private JTextField searchField;
//	private ArrayList<Movie> arrMovies = new ArrayList<Movie>();
	private Controller controller;
	
	public UserInterface(){
		
		controller = new Controller();
		
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
		openFile.addActionListener(btnListener);
		saveFile.addActionListener(btnListener);
		addMovie.addActionListener(btnListener);
		delete.addActionListener(btnListener);
		
		setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());
		west.setLayout(new FlowLayout());
		topButtonPanel.setLayout(new GridLayout(1,7));
//		listPanel.setLayout(new GridLayout(1, 1));
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
		
//		Movie testMovie = new Movie("Test1", "Testttt", new String[] {"Jag", "Du"}, 3.4, "Direktor", 3.4, 1);
//		arrMovies.add(testMovie);
//		//listOfMovies = new JList(testMovie);
		
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
						JOptionPane.showMessageDialog(null, "Fel n�r filen �ppnades. F�rs�k igen!");
					}
				}
				update();
				
			}
			if(e.getSource() == saveFile) {
				JFileChooser fc = new JFileChooser();
				int result = fc.showSaveDialog(UserInterface.this);
				if(result == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						controller.saveFile(file);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Fel n�r filen sparades. F�rs�k igen!");
					}
				}
			}
			if(e.getSource() == addMovie) {
				controller.addMovie();
				update();
			}
			if(e.getSource() == delete) {
				controller.removeMovie(listOfMovies.getSelectedIndex());
				update();
			}
		}
		
	}
}
