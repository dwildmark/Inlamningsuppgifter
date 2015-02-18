package main;

import java.util.*;

public class Library {
	
	private ArrayList<Movie> list = new ArrayList<Movie>();
	
	public void setList(ArrayList<Movie> inList){
		list = inList;
	}
	
	public ArrayList<Movie> getList(){
		return list;
	}
	
	public void add(Movie movie){
		list.add(movie);
	}
	
	public void remove(Movie movie){
		list.remove(movie);
	}
	
	public int getIndex(Movie movie){
		return list.indexOf(movie);
	}
	
	public void setIndex(Movie movie, int index){
		list.set(index, movie);
	}
	
	public void clearLib(){
		list = new ArrayList<Movie>();
	}
	
	public void addFromList(ArrayList<Movie> inList){
		for(Iterator<Movie> it = inList.iterator(); it.hasNext();){
			list.add((Movie)it.next());
		}
	}
}
