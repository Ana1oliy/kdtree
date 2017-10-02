package ru.ana1oliy.kdtree;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

public class DoubleKDTree extends AbstractKDTree<Double> {

	public DoubleKDTree(KDRange<Double> range) {
        super(range);
    }
	
	@Override
	protected AbstractKDTreeNode<Double> createNode(KDPoint<Double> point, KDRange<Double> range) {
		return new DoubleKDTreeNode(point, range);
	}

}
