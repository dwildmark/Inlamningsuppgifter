package main;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Controller {
	private Library library;
	private FileManager fileMgr;
	private boolean sortQuick;
	private boolean searchBinary;
	
	public Controller() {
		library = new Library();
		fileMgr = new FileManager();
		sortQuick = true;
		searchBinary = true;
	}
	
	public void openFile(File file) throws IOException {
		library.setList(fileMgr.readFile(file));
	}
	
	public void saveFile(File file) throws IOException {
		fileMgr.writeFile(file, library.getList());
	}
	
	public void addMovie() {
		String[] messages = {"Titel:", "Genre:", "Skådespelare (separerade med ', '):", 
				"Längd:", "Regissör:", "Betyg:", "Dvd eller BlueRay (0 för Dvd, 1 för BlueRay):" };
		String[] res = new String[7];
		for(int i = 0; i < 7; i++) {
			res[i] = JOptionPane.showInputDialog(messages[i]);
		}
		String[] actors = res[2].split(", ");
		Movie movie = new Movie(res[0], res[1], actors, 
					Double.parseDouble(res[3]), res[4], 
					Double.parseDouble(res[5]), 
					Integer.parseInt(res[6]));
		
		addMovie(movie);
	}
	
	public void addMovie(Movie movie) {
		boolean add = true;
		for(int i = 0; i < library.getList().size(); i++) {
			if(movie.compareTo(library.getList().get(i)) == 0) {
				add = false;
			}
		}
		if(add) {
			library.add(movie);
		} else {
			JOptionPane.showMessageDialog(null, "Det finns redan en film med samma rubrik och av samma typ.");
		}
	}
	
	public void removeMovie(Movie movie) {
		library.remove(movie);
	}
	
	public void setSorter(boolean quick) {
		sortQuick = quick;
	}
	
	public void setSearchMethod(boolean binary) {
		searchBinary = binary;
	}
	
	public void sort(Comparator<Movie> comp) {
		if(sortQuick) {
			Sort.partitionSort(library.getList(), comp);
		} else {
			Sort.bubbleSort(library.getList(), comp);
		}
	}
	
	public ArrayList<Movie> getMovieList() {
		return library.getList();
	}
	
	public ArrayList<Movie> getSearchList() {
		return library.getSearchList();
	}
	
	public void search(String searchString) {
		Sort.partitionSort(library.getList(), new TitleAsc());
		if(searchBinary) {
			library.binarySearch(searchString);
		} else {
			library.linearSearch(searchString);
		}
	}
	
	public void shuffleList() {
		library.shuffle(0);
	}
	
	public void sort(Comparator<Movie> comp, boolean sortAsc) {
		
	}
}
