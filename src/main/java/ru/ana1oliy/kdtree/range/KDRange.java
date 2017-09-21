package ru.ana1oliy.kdtree.range;

import ru.ana1oliy.kdtree.points.KDPoint;

public interface KDRange<T extends Comparable<T>> {

	KDPoint<T> from();
	KDPoint<T> to();
	boolean contains(KDPoint<T> point);
	KDRange<T> union(KDRange<T> range);
	KDRange<T> intarsection(KDRange<T> range);
	KDRange<T> subtraction(KDRange<T> range);
	boolean intersect(KDRange<T> range);
	KDRange<T>[] split(T coordinate, char dimension);
	double distanceTo(KDPoint<T> point);
	double squaredDistanceTo(KDPoint<T> point);
	char size();
}
