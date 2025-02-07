package ru.stqa.pft.sandbox;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class PointTests {

	public double pointdistance;

	@Test
	public void testsPoint() {
		Point p1 = new Point(1, 6);
		Point p2 = new Point(2, 4);
		pointdistance = p1.distance(p2);
		System.out.println(p1.distance(p2));
		Assertions.assertEquals(pointdistance, 2.0024984394500787);
	}

	@Test
	public void testsPoint2() {
		Point p1 = new Point(1, 6);
		Point p2 = new Point(2, 4);
		pointdistance = p2.distance(p1);
		System.out.println(p2.distance(p1));
		Assertions.assertNotEquals(pointdistance,3);
	}
}
