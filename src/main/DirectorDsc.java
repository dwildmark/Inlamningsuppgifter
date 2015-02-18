package main;

import java.util.Comparator;

public class DirectorDsc implements Comparator<Movie>{
	
	public int compare(Movie movie1, Movie movie2) {
		return movie1.getDirector().compareTo(movie2.getDirector());
	}
	
}
