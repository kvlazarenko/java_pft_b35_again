package ru.stqa.pft.sandbox;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SquareTests {

	@Test
	public void testArea(){
		Square s = new Square(5);
			Assertions.assertEquals(s.area(),25.0);
			}
}
