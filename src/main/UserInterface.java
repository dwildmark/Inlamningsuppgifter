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
	rating, openFile, saveFile, searchButton, sort, shuffle, delete;
	private JList<Movie> listOfMovies;
	private JTextField searchField;
	private ArrayList<Movie> arrMovies = new ArrayList<Movie>();
	
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
		
		Dimension leftBtns = new Dimension(180, 40);
		openFile.setPreferredSize(leftBtns);
		saveFile.setPreferredSize(leftBtns);
		sort.setPreferredSize(leftBtns);
		shuffle.setPreferredSize(leftBtns);
		delete.setPreferredSize(leftBtns);
		searchButton.setPreferredSize(new Dimension(80, 40));
		searchField.setPreferredSize(new Dimension(100, 40));
		ButtonListener btnListener = new ButtonListener(); 
		openFile.addActionListener(btnListener);
		
		setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());
		west.setLayout(new FlowLayout());
		topButtonPanel.setLayout(new GridLayout(1,7));
		listPanel.setLayout(new GridLayout(10, 1));
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
		west.add(openFile);
		west.add(saveFile);
		west.add(Box.createRigidArea(new Dimension(180, 40)));
		west.add(searchPanel);
		west.add(Box.createRigidArea(new Dimension(180, 40)));
		west.add(sort);
		west.add(shuffle);
		west.add(Box.createRigidArea(new Dimension(180, 40)));
		west.add(delete);
		center.add(topButtonPanel, BorderLayout.NORTH);
		center.add(listPanel, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);		
		add(center, BorderLayout.CENTER);
		
		Movie testMovie = new Movie("Test1", "Testttt", new String[] {"Jag", "Du"}, 3.4, "Direktor", 3.4, 1);
		arrMovies.add(testMovie);
		//listOfMovies = new JList(testMovie);
		
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == openFile) {
				JFileChooser fc = new JFileChooser();
				FileManager fm = new FileManager();
				int retval = fc.showOpenDialog(UserInterface.this);
				if(retval == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						System.out.println(fm.readFile(file).get(0).toString());
					} catch (IOException e1) {}
				}
				
			}
		}
		
	}
}
