package main;

import java.util.Comparator;

public class DirectorDsc implements Comparator<Movie>{
	
	public int compare(Movie movie1, Movie movie2) {
		return movie2.getDirector().compareTo(movie1.getDirector());
	}
	
}
