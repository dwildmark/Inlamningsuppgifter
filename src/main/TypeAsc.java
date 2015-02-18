package main;

import java.util.Comparator;

public class TypeAsc implements Comparator<Movie> {

	public int compare(Movie movie1, Movie movie2) {
		if(movie1.getType() < movie2.getType()) {
			return -1;
		} else if(movie1.getType() > movie2.getType()) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
