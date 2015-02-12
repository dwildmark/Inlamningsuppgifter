package main;

import java.io.*;
import java.util.*;

public class FileManager {
	
	public ArrayList<Movie> readFile(String path) throws IOException {
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		ArrayList<Movie> arrayList = new ArrayList<Movie>();
		int numberOfLines = 0;
		while (textReader.readLine() != null) {
			numberOfLines++;
		}
		
		String[] data = new String[numberOfLines];
		for(int i = 0; i < numberOfLines; i++) {
			data[i] = textReader.readLine();
		}
		for(int i = 0; i < data.length; i++) {
			String[] split = data[i].split("!");
			String[] actors = split[2].split("_");
			arrayList.add(new Movie(split[0], split[1], actors, 
					Double.parseDouble(split[3]), split[4], 
					Double.parseDouble(split[5]), 
					Integer.parseInt(split[6])));
		}
		
		textReader.close();
		return arrayList;
	}
}
