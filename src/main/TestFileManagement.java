package main;

import java.io.IOException;
import java.util.ArrayList;

public class TestFileManagement {
	public static void main(String[] args) throws IOException {
		Movie m1 = new Movie("Test1", "Testttt", new String[] {"Jag", "Du"}, 3.4, "Direktor", 3.4, 1);
		Movie m2 = new Movie("TestMovie2", "Testttt", new String[] {"Jag", "Du", "Nån annan"}, 3.4, "Direktor", 3.4, 1);
		FileManager fm = new FileManager();
		ArrayList<Movie> list = new ArrayList<Movie>();
		list = fm.readFile("/Users/DennisW/Documents/filmer/test1.txt");
		list.add(m1);
		list.add(m2);
		System.out.println(list.get(0).toString());
		fm.writeFile("/Users/DennisW/Documents/filmer/test1.txt", list);
	}
}
