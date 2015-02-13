package main;

public class Movie implements Comparable<Movie>{
	private String title;
	private String genre;
	private String[] actors;
	private double length;
	private String director;
	private double rating;
	private int dvdOrBlueray;
	

	public Movie(String title, int dvdOrBlueray){
		this(title, "okänd", new String[] {"okänd"}, 0.0, "okänd", 0.0, dvdOrBlueray);
	}
	
	public Movie(String title, String genre, String[] actors, double length, 
			String director, double rating, int dvdOrBlueray){
		setTitle(title);
		setGenre(genre);
		setActors(actors);
		setLength(length);
		setDirector(director);
		setRating(rating);
		setType(dvdOrBlueray);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getType() {
		return dvdOrBlueray;
	}

	public void setType(int dvdOrBlueray) {
		this.dvdOrBlueray = dvdOrBlueray;
	}

	public String toString() {
		String res = getTitle() + getType();
		return res;
	}

	public int compareTo(Movie movie) {
		String res = "";
		return 0;
	}
}
