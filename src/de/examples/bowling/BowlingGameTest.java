package de.examples.bowling;

import static org.junit.Assert.*;
import org.junit.Test;

public class BowlingGameTest {

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
		try{
			game.addRoll(5);
			assert !game.isSpare(0);
			assert !game.isStrike(0);
			
			game.addRoll(5);
			assert game.isSpare(0);
			assert !game.isStrike(0);

			game.addRoll(10);
			assert !game.isSpare(0);
			assert game.isStrike(0);

		}catch(BowlingException ex) {
			ex.printStackTrace();
			fail("Caught bowling exception: " + ex.getMessage());			
		}
	}

 
	@Test 
	public void scoreValidGames() throws BowlingException{
		BowlingGame game = new BowlingGame(new int[] {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10});
		assertEquals(300, game.getScore());
		
		game = new BowlingGame(new int[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		});
		assertEquals(0, game.getScore());

		game = new BowlingGame(new int[] {
				1, 2, 3, 4, 5, 1, 2, 3, 4, 5,
				1, 2, 3, 4, 5, 1, 2, 3, 4, 5
		});
		assertEquals(60, game.getScore());

		game = new BowlingGame(new int[] {
				1, 2, 3, 4, 5, 1, 2, 3, 4, 5,
				1, 2, 3, 4, 5, 1, 2, 3, 5, 5, 10
		});
		assertEquals(71, game.getScore());


		game = new BowlingGame(new int[] {
				0, 10, 1, 9, 2, 8, 3, 7, 4, 6,
				5, 5, 6, 4, 7, 3, 8, 2, 9, 1, 10
		});
		assertEquals(155, game.getScore());

		game = new BowlingGame(new int[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 10
		});
		assertEquals(30, game.getScore());

	}
	
	/*
	 * @Test public void completeFrames() { BowlingGame2 game = new
	 * BowlingGame2(new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10 });
	 * assertEquals(300, game.getScore());
	 * 
	 * game = new BowlingGame2(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 * 0, 0, 0, 0, 0, 0, 0, }); assertEquals(0, game.getScore()); }
	 * 
	 * public void incompleteFrames() { BowlingGame2 game = new BowlingGame2(); }
	 * 
	 * @Test public void invalidFrames() { BowlingGame2 game = new
	 * BowlingGame2(new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10 });
	 * 
	 * }
	 */
}
