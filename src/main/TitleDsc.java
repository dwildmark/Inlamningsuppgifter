package main;

import java.util.Comparator;

public class TitleDsc implements Comparator<Movie>{
	
	public int compare(Movie movie1, Movie movie2) {
		return movie2.getTitle().compareTo(movie1.getTitle());
	}
	
}
