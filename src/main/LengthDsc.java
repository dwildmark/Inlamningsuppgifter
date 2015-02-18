package main;

import java.util.Comparator;

public class LengthDsc implements Comparator<Movie>{

	public int compare(Movie movie1, Movie movie2) {
		if(movie1.getLength() < movie2.getLength()){
			return 1;
		} else if(movie1.getLength() > movie2.getLength()) {
			return -1;
		} else {
			return 0;
		}
	}

}