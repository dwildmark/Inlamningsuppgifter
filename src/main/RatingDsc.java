package main;

import java.util.Comparator;

public class RatingDsc implements Comparator<Movie>{
	
	public int compare(Movie movie1, Movie movie2) {
		if(movie1.getRating() < movie2.getRating()) {
			return 1;
		} else if(movie1.getRating() > movie2.getRating()) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
