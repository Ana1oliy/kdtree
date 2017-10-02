package ru.ana1oliy.kdtree;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

public class IntKDTree extends AbstractKDTree<Integer> {

	public IntKDTree(KDRange<Integer> range) {
        super(range);
    }
	
	@Override
	protected AbstractKDTreeNode<Integer> createNode(KDPoint<Integer> point, KDRange<Integer> range) {
		return new IntKDTreeNode(point, range);
	}

}
