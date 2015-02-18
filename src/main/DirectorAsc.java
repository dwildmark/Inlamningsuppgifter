package main;

import java.util.Comparator;

public class DirectorAsc implements Comparator<Movie>{

	public int compare(Movie movie1, Movie movie2) {
		return movie1.getDirector().compareTo(movie2.getDirector());
	}

}
