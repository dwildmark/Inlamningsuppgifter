package main;

import java.util.ArrayList;
import java.util.Comparator;

public class Sort {
	
	public static void bubbleSort(ArrayList<Movie> array, Comparator<Movie> comp) {
//		for(int i = 0; i < array.size() - 1; i++) {
//			for(int j = 0; j < array.size() - 1; j++) {
//				if(comp.compare(array.get(j), array.get(j + 1) ) > 0) {
//					swap(array, j, j + 1);
//				}
//			}
//		}
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
	
	public static void partitionSort(ArrayList<Movie> array, Comparator<Movie> comp, int start, int end) {
		int splitIndex;
		if(start < end) {
			splitIndex = split(array, start, end, (start + end) / 2, comp);
			partitionSort(array, comp, start, splitIndex - 1);
			partitionSort(array, comp, splitIndex + 1, end);
		}
	}
	
	public static void partitionSort(ArrayList<Movie> array, Comparator<Movie> comp) {
		partitionSort(array, comp, 0, array.size() - 1);
	}
	
	private static int split(ArrayList<Movie> array, int start, int end, int splitIndex, Comparator<Movie> comp) {
		Movie splitValue = array.get(splitIndex);
		int retVal = start;
		swap(array, splitIndex, end);
		for(int i = start; i < end; i++) {
			if(comp.compare(array.get(i), splitValue) < 0) {
				swap(array, i, retVal);
				retVal++;
			}
		}
		swap(array, retVal, end);
		return retVal;
	}
	
	public static void swap(ArrayList<Movie> array, int elem1, int elem2) {
		Movie temp = array.get(elem1);
		array.set(elem1, array.get(elem2));
		array.set(elem2, temp);
	}
}
