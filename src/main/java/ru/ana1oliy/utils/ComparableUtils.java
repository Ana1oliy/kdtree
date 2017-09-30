package ru.ana1oliy.utils;

public class ComparableUtils {
	public static <T extends Comparable<T>> boolean isBeetween(T value, T boundA, T boundB) {
    	T hi = max(boundA, boundB);
    	T lo = min(boundA, boundB);
    	
    	return value.compareTo(lo) >= 0 && value.compareTo(hi) <= 0;
    }
	
	@SafeVarargs
	public static <T extends Comparable<T>> T min(T... values) {
		if (values == null || values.length == 0)
			throw new IllegalArgumentException();
		
		T min = null;
		
		for (T item: values) {
			if (min == null || min.compareTo(item) > 0)
				min = item;
		}
		
		return min;
	}
	
	public static <T extends Comparable<T>> T min(T a, T b) {
		if (a == null || b == null)
			throw new IllegalArgumentException();
		
		if (a.compareTo(b) < 0)
			return a;
		else
			return b;
	}
	
	@SafeVarargs
	public static <T extends Comparable<T>> T max(T... values) {
		if (values == null || values.length == 0)
			throw new IllegalArgumentException();
		
		T max = null;
		
		for (T item: values) {
			if (max == null || max.compareTo(item) < 0)
				max = item;
		}
		
		return max;
	}
	
	public static <T extends Comparable<T>> T max(T a, T b) {
		if (a == null || b == null)
			throw new IllegalArgumentException();
		
		if (a.compareTo(b) > 0)
			return a;
		else
			return b;
	}
}
