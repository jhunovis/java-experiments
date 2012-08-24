package de.examples.bowling;

import java.util.ArrayList;

public class BowlingGame {

	private int[][] mFrames = new int[12][2];
	private int mCurrentFrame = 0;
	private boolean mFirstRoll = true;

	{
		for (int i = 0; i < mFrames.length; i++) {
			mFrames[i][0] = 0;
			mFrames[i][1] = 0;
		}
	}
	
	private ArrayList<Integer> mRolls = new ArrayList<Integer>();

	
	public void addRoll(int roll) throws BowlingException {
		if (isComplete())
			throw new BowlingException("No more rolls allowed.");
		if (roll < 0 || roll > 10)
			throw new BowlingException("Roll out of bounds.");
		if (mFirstRoll) {
			mFrames[mCurrentFrame][0] = roll;
			// next frame, if strike,
			if (roll == 10)	mCurrentFrame++;
			else mFirstRoll = false;
		} else if (roll + mFrames[mCurrentFrame][0] <= 10) {
			mFrames[mCurrentFrame][1] = roll;
			mFirstRoll = true;
			mCurrentFrame++;
		} else
			throw new BowlingException("Frame out of bounds.");
	}

	/**
	 * Game over?
	 * 
	 * @return true, only if last frame was played. No more rolls are allowed
	 *         then.
	 **/
	public boolean isComplete() {
		if (mCurrentFrame == 10) {
			if (mFirstRoll) {
				return !(isSpare(9) || isStrike(9));
			} else {
				return !isStrike(9);
			}
		} else
			return false;
	}

	/**
	 * Is given frame a strike?
	 * 
	 * @param frame
	 *            the frame to test, first throw is frame 0!
	 * @return true if frame was a strike.
	 */
	public boolean isStrike(int frame) {
		return mFrames[frame][0] == 10;
	}

	/**
	 * Is given frame a spare?
	 * 
	 * @param frame
	 *            the frame to test, first throw is frame 0!
	 * @return true if frame was a strike.
	 */
	public boolean isSpare(int frame) {
		return !isStrike(frame) && mFrames[frame][0] + mFrames[frame][1] == 10;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/** **/
	public int getScore() {
		int score = 0;
		for (int i = 0; i < 10; i++) {
			if (isStrike(i)) {
				score += 10 + mFrames[i+1][0] + mFrames[i+1][1];  
			} else if (isSpare(i)) {
				score += 10 + mFrames[i+1][0];
			} else score += mFrames[i][0] + mFrames[i][1];
		}
		return score;
	}

	private int getRollOfFrame(int roll, int frame)
			throws IndexOutOfBoundsException {
		if (frame >= 0 && frame <= 11) {
			return mFrames[frame][roll];
		} else {
			throw new IndexOutOfBoundsException(
					"Frame number must be between 0 and 10");
		}
	}

	public int getFirstRollOfFrame(int frame) throws IndexOutOfBoundsException {
		return getRollOfFrame(0, frame);
	}

	public int getSecondRollOfFrame(int frame) throws IndexOutOfBoundsException {
		return getRollOfFrame(1, frame);
	}

}
