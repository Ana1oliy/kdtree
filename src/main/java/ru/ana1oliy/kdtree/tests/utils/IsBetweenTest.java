package ru.ana1oliy.kdtree.tests.utils;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.ana1oliy.kdtree.KDTreeUtils;

@RunWith(Parameterized.class)
public class IsBetweenTest {

	public IsBetweenTest(Integer value, Integer boundA, Integer boundB, boolean isBetween) {
		this.value = value;
		this.boundA = boundA;
		this.boundB = boundB;
		this.isBetween = isBetween;
	}
	
	private Integer value;
	
	private Integer boundA;
	
	private Integer boundB;
	
	private boolean isBetween;
	
	@Parameterized.Parameters
	public static List<Object[]> pointsSet() {
	    return Arrays.asList(new Object[][] {
		    {0,		-10,	10,		true},
		    {1,		1,		1,		true},
		    {5,		15,		2,		true},
		    {-10,	4,		18,		false},
		    {4,		23,		11,		false},
		    {40,	3,		20,		false},
		    {52,	16,		-10,	false}
	    });
	}
	
	@Test
	public void testIsBeetween() {
		assertEquals(KDTreeUtils.isBeetween(value, boundA, boundB), isBetween);
	}

}
