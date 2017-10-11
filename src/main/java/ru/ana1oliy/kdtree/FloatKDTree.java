package ru.ana1oliy.kdtree;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

public class FloatKDTree<G> extends AbstractKDTree<Float, G> {

	public FloatKDTree(KDRange<Float> range) {
        super(range);
    }
	
	@Override
	protected AbstractKDTreeNode<Float, G> createNode(KDPoint<Float> point, KDRange<Float> range) {
		return new FloatKDTreeNode<>(point, range);
	}

}
