package main;

import java.util.Comparator;

public class GenreDsc implements Comparator<Movie>{

	public int compare(Movie movie1, Movie movie2) {
		return movie2.getGenre().compareTo(movie1.getGenre());
	}

}
