package main;

import java.util.*;

public class Library {

	private ArrayList<Movie> list = new ArrayList<Movie>();
	private ArrayList<Movie> searchList = new ArrayList<Movie>();

	public void setList(ArrayList<Movie> inList) {
		list = inList;
	}

	public ArrayList<Movie> getList() {
		return list;
	}

	public ArrayList<Movie> getSearchList() {
		return searchList;
	}

	public void add(Movie movie) {
		list.add(movie);
	}

	public void remove(Movie movie) {
		list.remove(movie);
	}

	public int getIndex(Movie movie) {
		return list.indexOf(movie);
	}

	public void setIndex(Movie movie, int index) {
		list.set(index, movie);
	}

	public void clearLib() {
		list = new ArrayList<Movie>();
	}

	public void addFromList(ArrayList<Movie> inList) {
		for (Iterator<Movie> it = inList.iterator(); it.hasNext();) {
			list.add((Movie) it.next());
		}
	}

	public void shuffle(int index) {
		Random rand = new Random();
		if (index < list.size()) {
			int random = rand.nextInt(list.size());
			Sort.swap(list, index, random);
			shuffle(index + 1);
		}
	}

	public void linearSearch(String searchString) {
		searchList.clear();
		Movie nextMovie;
		for (Iterator<Movie> it = list.iterator(); it.hasNext();) {
			nextMovie = it.next();
			if (nextMovie.getTitle().toLowerCase()
					.contains(searchString.toLowerCase())) {
				searchList.add(nextMovie);
			}
		}
	}

	public void binarySearch(String searchString) {
		searchList.clear();
		if(searchString.length() != 0) {
			binarySearch(list, searchString, 0, list.size() - 1);
		}
	}

	public void binarySearch(ArrayList<Movie> movieList, String searchString, int min,
			int max) {
		if (min <= max) {
			int mid = (max + min) / 2;
			if (movieList.get(mid).getTitle().toLowerCase().substring(0, Math.min(searchString.length(), movieList.get(mid).getTitle().length()))
					.equals(searchString.toLowerCase().substring(0, Math.min(searchString.length(), movieList.get(mid).getTitle().length())))) {
				searchList.add(movieList.get(mid));
			} else if (movieList.get(mid).getTitle().compareTo(searchString) > 0) {
				binarySearch(movieList, searchString, min, mid - 1);
			} else {
				binarySearch(movieList, searchString, mid + 1, max);
			}
		}
	}
}
