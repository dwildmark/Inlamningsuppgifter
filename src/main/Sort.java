package main;

import java.util.ArrayList;
import java.util.Comparator;

public class Sort {
	
	public static void bubbleSort(ArrayList<Movie> array, Comparator<Movie> comp) {
		bubbleSort(array, comp, 0);
	}
	
	private static void bubbleSort(ArrayList<Movie> array, Comparator<Movie> comp, int index) {
		if(index < array.size()) {
			if(index > 0 && comp.compare(array.get(index), array.get(index - 1)) < 0) {
				swap(array, index, index - 1);
			}
			bubbleSort(array, comp, index + 1);
		}
	}
	
	public static void swap(ArrayList<Movie> array, int elem1, int elem2) {
		Movie temp = array.get(elem1);
		array.set(elem1, array.get(elem2));
		array.set(elem2, temp);
	}
}
