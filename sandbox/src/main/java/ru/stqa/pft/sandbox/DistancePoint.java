package ru.stqa.pft.sandbox;

public class DistancePoint {
	public static void main(String[] args) {
		Point p1 = new Point(9,5);
		Point p2 = new Point(4,6);
		System.out.println("Расстояние между точками p1 и p2 = " + p1.distance(p2));
	}
}
