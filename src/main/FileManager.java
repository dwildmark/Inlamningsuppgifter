package main;

import java.io.*;
import java.util.*;

public class FileManager {
	
	public ArrayList<Movie> readFile(File file) throws IOException {
		String path = file.toPath().toString();
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		ArrayList<Movie> arrayList = new ArrayList<Movie>();
		int numberOfLines = getNbrOfLines(file);
		String[] data = new String[numberOfLines];
		for(int i = 0; i < numberOfLines; i++) {
			data[i] = textReader.readLine();
		}
		for(int i = 0; i < data.length; i++) {
			String[] split = new String[7];
			split = data[i].split("!");
			String[] actors = split[2].split("_");
			arrayList.add(new Movie(split[0], split[1], actors, 
					Double.parseDouble(split[3]), split[4], 
					Double.parseDouble(split[5]), 
					Integer.parseInt(split[6])));
		}
		
		textReader.close();
		return arrayList;
	}
	
	public int getNbrOfLines(File file) throws IOException {
		String path = file.toPath().toString();
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		int numberOfLines = 0;
		while (textReader.readLine() != null) {
			numberOfLines++;
		}
		textReader.close();
		return numberOfLines;
	}
	
	public void writeFile(File file, ArrayList<Movie> movieList) throws IOException {
		String path = file.toPath().toString();
		FileWriter fw = new FileWriter(path);
		BufferedWriter textWriter = new BufferedWriter(fw);
		for(int i = 0; i < movieList.size(); i++) {
			Movie currentMovie = movieList.get(i);
			String actors = "";
			for(int j = 0; j < currentMovie.getActors().length; j++) {
				if(j > 0) {
					actors += "_";
				}
				actors += currentMovie.getActors()[j];
			}
			textWriter.write(currentMovie.getTitle() + "!" + currentMovie.getGenre() + "!" +  actors + "!" + currentMovie.getLength() + "!" 
					+ currentMovie.getDirector() + "!" + currentMovie.getRating() + "!" + currentMovie.getType() + "\n");
		}
		textWriter.close();
	}
}
