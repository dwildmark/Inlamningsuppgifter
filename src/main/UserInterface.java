package main;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

import javax.swing.*;

public class UserInterface extends JPanel{
	private JPanel center, west, topButtonPanel, listPanel, searchPanel, actorsNLength;
	private JButton title, type, genre, actors, length, director, 
	rating, openFile, saveFile, searchButton, sort, shuffle, 
	delete, addMovie, goBack, editMovie;
	private JRadioButton rbQuick, rbBubble, rbLinear, rbBinary;
	private ButtonGroup bgSorting, bgSearchMethod;
	private JList<Movie> listOfMovies;
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
		actorsNLength = new JPanel();
		
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
		goBack = new JButton("Tillbaka");
		shuffle = new JButton("Blanda");
		delete = new JButton("Radera");
		addMovie = new JButton("Lägg till film");
		rbQuick = new JRadioButton("Snabb sortering");
		rbBubble = new JRadioButton("Bubble-sortering");
		rbLinear = new JRadioButton("Linjär sökning");
		rbBinary = new JRadioButton("Binär sökning");
		editMovie = new JButton("Redigera film");
		bgSearchMethod = new ButtonGroup();
		bgSorting = new ButtonGroup();
		searchField = new JTextField();
		listOfMovies = new JList<Movie>();
		listOfMovies.setPreferredSize(new Dimension(1190, 600));
		listOfMovies.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
		Dimension leftBtns = new Dimension(180, 40);
		openFile.setPreferredSize(leftBtns);
		saveFile.setPreferredSize(leftBtns);
		sort.setPreferredSize(leftBtns);
		shuffle.setPreferredSize(leftBtns);
		delete.setPreferredSize(leftBtns);
		addMovie.setPreferredSize(leftBtns);
		goBack.setPreferredSize(leftBtns);
		editMovie.setPreferredSize(leftBtns);
		searchButton.setPreferredSize(new Dimension(80, 40));
		searchField.setPreferredSize(new Dimension(100, 40));
		ButtonListener btnListener = new ButtonListener();
		SortingListener srtListener = new SortingListener();
		KeyboardListener kListener = new KeyboardListener();
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
		goBack.addActionListener(btnListener);
		searchButton.addActionListener(btnListener);
		editMovie.addActionListener(btnListener);
		searchField.addKeyListener(kListener);
		bgSorting.add(rbBubble);
		bgSorting.add(rbQuick);
		bgSearchMethod.add(rbBinary);
		bgSearchMethod.add(rbLinear);
		setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());
		west.setLayout(new FlowLayout());
		topButtonPanel.setLayout(new GridLayout(1,6));
		searchPanel.setLayout(new BorderLayout());
		actorsNLength.setLayout(new GridLayout());
		
		setPreferredSize(new Dimension(1400, 600));
		west.setPreferredSize(new Dimension(200, 1000));
		
		searchPanel.add(searchButton, BorderLayout.WEST);
		searchPanel.add(searchField, BorderLayout.CENTER);
		actorsNLength.add(rating);
		actorsNLength.add(length);
		topButtonPanel.add(title);
		topButtonPanel.add(type);
		topButtonPanel.add(genre);
		topButtonPanel.add(actors);
		topButtonPanel.add(director);
		topButtonPanel.add(actorsNLength);
		listPanel.add(listOfMovies);
		west.add(openFile);
		west.add(saveFile);
		west.add(Box.createRigidArea(new Dimension(180, 40)));
		west.add(searchPanel);
		west.add(rbBinary);
		west.add(rbLinear);
		west.add(goBack);
		west.add(sort);
		west.add(shuffle);
		//west.add(Box.createRigidArea(new Dimension(180, 40)));
		west.add(editMovie);
		west.add(addMovie);
		west.add(delete);
		west.add(rbQuick);
		west.add(rbBubble);
		center.add(topButtonPanel, BorderLayout.NORTH);
		center.add(listPanel, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);		
		add(center, BorderLayout.CENTER);
		
		currentComp = new TitleAsc();
		rbQuick.setSelected(true);
		rbLinear.setSelected(true);
		goBack.setEnabled(false);
		
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
	
	public void update() {
		listOfMovies.setListData(controller.getMovieList().toArray(new Movie[controller.getMovieList().size()]));
	}
	
	private void updateSearch() {
		listOfMovies.setListData(controller.getSearchList().toArray(new Movie[controller.getSearchList().size()]));
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			controller.setSorter(rbQuick.isSelected());
			if(e.getSource() == openFile) {
				JFileChooser fc = new JFileChooser();
				int result = fc.showOpenDialog(UserInterface.this);
				File file = fc.getSelectedFile();
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						controller.openFile(file);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Fel när filen öppnades. Försök igen!");
					}
				}
				Frame frame = (Frame)getTopLevelAncestor();
				frame.setTitle(fc.getName(file));
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
				JFrame frame = new JFrame();
				MoviePane moviePane = new MoviePane(controller, frame, UserInterface.this);
				frame.add(moviePane);
				frame.pack();
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

			} else if(e.getSource() == delete && listOfMovies.getSelectedIndex() >= 0) {
				controller.removeMovie(listOfMovies.getSelectedValue());

				update();
			} else if(e.getSource() == shuffle) {
				controller.shuffleList();
				update();
			} else if(e.getSource() == sort) {
				controller.sort(currentComp);
				update();
			} else if(e.getSource() == searchButton) {
				controller.setSearchMethod(rbBinary.isSelected());
				controller.search(searchField.getText());
				goBack.setEnabled(true);
				updateSearch();
			} else if(e.getSource() == goBack) {
				update();
				goBack.setEnabled(false);
			} else if(e.getSource() == editMovie) {
				if(listOfMovies.getSelectedIndex() > -1) {
					JFrame frame = new JFrame();
					EditMoviePane editMoviePane = new EditMoviePane(controller, frame, UserInterface.this, listOfMovies.getSelectedValue());
					frame.add(editMoviePane);
					frame.pack();
					//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Du har inte markerat någon film att redigera.");
				}

			}
		}
		
	}
	
	private class SortingListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			controller.setSorter(rbQuick.isSelected());
			if(e.getSource() == title) { 
				if(currentComp instanceof TitleAsc || currentComp instanceof TitleDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("title");
				controller.sort(currentComp);
				update();
			} else if(e.getSource() == type) {
				if(currentComp instanceof TypeAsc || currentComp instanceof TypeDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("type");
				controller.sort(currentComp);
				update();
			} else if(e.getSource() == genre) {
				if(currentComp instanceof GenreAsc || currentComp instanceof GenreDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("genre");
				controller.sort(currentComp);
				update();
			} else if(e.getSource() == director) {
				if(currentComp instanceof DirectorAsc || currentComp instanceof DirectorDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("director");
				controller.sort(currentComp);
				update();
			} else if(e.getSource() == length) {
				if(currentComp instanceof LengthAsc || currentComp instanceof LengthDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("length");
				controller.sort(currentComp);
				update();
			} else if(e.getSource() == rating) {
				if(currentComp instanceof RatingAsc || currentComp instanceof RatingDsc) {
					sortAsc = !sortAsc;
				} else {
					sortAsc = true;
				}
				selectComp("rating");
				controller.sort(currentComp);
				update();
			}
		}
		
	}
	
	private class KeyboardListener implements KeyListener {

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				controller.setSearchMethod(rbBinary.isSelected());
				controller.search(searchField.getText());
				goBack.setEnabled(true);
				updateSearch();
			}
		}
		
	}
}
