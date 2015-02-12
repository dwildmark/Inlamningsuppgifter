package main;

import java.awt.*;

import javax.swing.*;

public class UserInterface extends JPanel{
	private JPanel center, west, topButtonPanel, listPanel, searchPanel;
	private JButton title, type, genre, actors, length, director, 
	rating, openFile, saveFile, searchButton, sort, shuffle, delete;
	private JList<Movie> listOfMovies;
	private JTextField searchField;
	
	public UserInterface(){
		
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
		searchField = new JTextField();
		
		setLayout(new GridLayout(1, 2));
		center.setLayout(new BorderLayout());
		west.setLayout(new GridLayout(6,1));
		topButtonPanel.setLayout(new GridLayout(1,7));
		listPanel.setLayout(new GridLayout(10, 1));
		searchPanel.setLayout(new GridLayout(1, 2));
		
		searchPanel.add(searchButton, searchField);
		topButtonPanel.add(title);
		topButtonPanel.add(type);
		topButtonPanel.add(genre);
		topButtonPanel.add(actors);
		topButtonPanel.add(length);
		topButtonPanel.add(director);
		topButtonPanel.add(rating);
		west.add(openFile);
		west.add(saveFile);
		west.add(searchPanel);
		west.add(sort);
		west.add(shuffle);
		west.add(delete);
		center.add(topButtonPanel, BorderLayout.NORTH);
		center.add(listPanel, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);		
		add(center, BorderLayout.CENTER);

		
	}
}
