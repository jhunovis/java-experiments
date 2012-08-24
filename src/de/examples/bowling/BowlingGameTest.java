package de.examples.bowling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BowlingGameTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void addingValidRolls() {
		BowlingGame game = new BowlingGame();

		try {
			game.addRoll(1);
			assertEquals(1, game.getFirstRollOfFrame(0));

			game.addRoll(5);
			assertEquals(5, game.getSecondRollOfFrame(0));

			game.addRoll(0);
			assertEquals(0, game.getFirstRollOfFrame(1));

			game.addRoll(10);
			assertEquals(10, game.getSecondRollOfFrame(1));
		} catch (BowlingException x) {
			fail("BowlingException thrown: " + x.getMessage());
		}
	}

	@Test
	public void addingTooSmallRoll() {
		BowlingGame game;
		boolean caught;

		game = new BowlingGame();
		caught = false;
		try {
			game.addRoll(-1);
		} catch (BowlingException x) {
			caught = true;
		}
		if (!caught)
			fail("A bowling exception should have been thrown.");

		game = new BowlingGame();
		caught = false;
		try {
			game.addRoll(11);
		} catch (BowlingException x) {
			caught = true;
		}
		if (!caught)
			fail("A bowling exception should have been thrown.");

		game = new BowlingGame();
		caught = false;
		try {
			game.addRoll(2);
			game.addRoll(9);
		} catch (BowlingException x) {
			caught = true;
		}
		if (!caught)
			fail("A bowling exception should have been thrown.");

		game = new BowlingGame();
		caught = false;
		try {
			game.addRoll(2);
			game.addRoll(-2);
		} catch (BowlingException x) {
			caught = true;
		}
		if (!caught)
			fail("A bowling exception should have been thrown.");

	}

	@Test(expected = BowlingException.class)
	public void tooManyFrames() throws BowlingException {
		BowlingGame game = new BowlingGame();
		for (int i = 0; i < 10; i++) {
			game.addRoll(1);
			game.addRoll(2);
		}
		game.addRoll(9);
	}

	@Test
	public void isComplete() {
		BowlingGame game = new BowlingGame();
		try {
			for (int i = 0; i < 9; i++) {
				game.addRoll(2);
				assert !game.isComplete();
				game.addRoll(3);
				assert !game.isComplete();				
			}
			game.addRoll(4);
			assert !game.isComplete();
			game.addRoll(5);
			assert game.isComplete();
			
			// 10th frame is spare
			game = new BowlingGame();
			for (int i = 0; i < 9; i++) {
				game.addRoll(2);
				game.addRoll(3);			
			}
			game.addRoll(4);
			assert !game.isComplete();
			game.addRoll(6);
			assert game.isComplete();

			// 10th frame is strike
			game = new BowlingGame();
			for (int i = 0; i < 9; i++) {
				game.addRoll(2);
				game.addRoll(3);			
			}
			game.addRoll(10);
			assert !game.isComplete();
			game.addRoll(10);
			assert !game.isComplete();
			game.addRoll(10);
			assert game.isComplete();
		} catch (BowlingException ex) {
			ex.printStackTrace();
			fail("Caught bowling exception: " + ex.getMessage());
		}		
	}

	@Test
	public void strikesAndSpares() {
		BowlingGame game = new BowlingGame();
		game.addRoll(5);
		assert !game.isSpare(0);
	}
	/*
	 * @Test public void completeFrames() { BowlingGame game = new
	 * BowlingGame(new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10 });
	 * assertEquals(300, game.getScore());
	 * 
	 * game = new BowlingGame(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 * 0, 0, 0, 0, 0, 0, 0, }); assertEquals(0, game.getScore()); }
	 * 
	 * public void incompleteFrames() { BowlingGame game = new BowlingGame(); }
	 * 
	 * @Test public void invalidFrames() { BowlingGame game = new
	 * BowlingGame(new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10 });
	 * 
	 * }
	 */
}
