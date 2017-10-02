package ru.ana1oliy.kdtree;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

public class FloatKDTree extends AbstractKDTree<Float> {

	public FloatKDTree(KDRange<Float> range) {
        super(range);
    }
	
	@Override
	protected AbstractKDTreeNode<Float> createNode(KDPoint<Float> point, KDRange<Float> range) {
		return new FloatKDTreeNode(point, range);
	}

}
