package main;

import java.util.ArrayList;
import java.util.Comparator;

public class Sort {
	
	public static void bubbleSort(ArrayList<Movie> array, Comparator<Movie> comp) {
		bubbleSort(array, comp, 0, 0);
	}
	
	public static void bubbleSort(ArrayList<Movie> array, Comparator<Movie> comp, int i, int j) {
		if(i < array.size() - 1 ) {
			if(j < array.size() - 1) {
				if(comp.compare(array.get(j), array.get(j + 1)) > 0) {
					swap(array, j, j + 1);
				}
				bubbleSort(array, comp, i, j + 1);
			}
			bubbleSort(array, comp, i + 1, 0);
		}
	}
	
	public static void swap(ArrayList<Movie> array, int elem1, int elem2) {
		Movie temp = array.get(elem1);
		array.set(elem1, array.get(elem2));
		array.set(elem2, temp);
	}
}
