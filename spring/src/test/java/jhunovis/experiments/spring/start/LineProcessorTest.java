package jhunovis.experiments.spring.start;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineProcessorTest {

	@Test
	public void sort() {
		LineProcessor lp = new LineProcessor();
		
		String input = "A\nC\nF\nD\nB\nE";
		String output = lp.process("sort", input);
		
		assertEquals("A\nB\nC\nD\nE\nF", output);
	}

}
