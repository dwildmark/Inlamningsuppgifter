package main;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Controller {
	private Library library;
	private FileManager fileMgr;
	
	public Controller() {
		library = new Library();
		fileMgr = new FileManager();
	}
	
	public void openFile(File file) throws IOException {
		library.setList(fileMgr.readFile(file));
	}
	
	public void saveFile(File file) throws IOException {
		fileMgr.writeFile(file, library.getList());
	}
	
	public void addMovie() {
		String[] messages = {"Titel:", "Genre:", "Skådespelare (separerade med ','):", 
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
		library.add(movie);
	}
	
	public void removeMovie(int index) {
		library.remove(index);
	}
	
	public ArrayList<Movie> getMovieList() {
		return library.getList();
	}
}
