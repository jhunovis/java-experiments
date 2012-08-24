package de.examples.bowling;

public class BowlingGame {
	
	private enum FrameState {NOT_ROLLED, FIRST_ROLLED, NORMAL, SPARE, STRIKE};
	private FrameState[] mFrameState = new FrameState[10];
	{
		for (int i=0; i<10; i++) mFrameState[i] = FrameState.NOT_ROLLED;
	}
	private int[] mRolls = new int[2*10+2];
	private int mCurrentRoll = 0;
	private int mCurrentFrame = 0;
	private int mExtraRolls = 0;
	private boolean mFramesComplete = false;

	
	public BowlingGame() {	
	}

	public BowlingGame(int[] rolls) throws BowlingException{
		for (int roll:rolls) {
			addRoll(roll);
		}
	}

	private void nextFrame() {
		if (mCurrentFrame < 9) mCurrentFrame++;
		else {
			switch (mFrameState[mCurrentFrame]){
			case SPARE: mExtraRolls = 1;break;
			case STRIKE: mExtraRolls = 2;break;
			default: break;
			}
			mFramesComplete  = true;
		}
	}
	
	public void addRoll(int roll) throws BowlingException {
		if (isComplete())
			throw new BowlingException("No more rolls allowed.");
		if (roll < 0 || roll > 10)
			throw new BowlingException("Roll out of bounds.");		
		if ( mExtraRolls > 0 ) {
			mRolls[mCurrentRoll++] = roll;
			mExtraRolls--;
		} else if ( mFrameState[mCurrentFrame] != FrameState.FIRST_ROLLED) {
			mRolls[mCurrentRoll++] = roll;
			// next frame, if strike,
			if (roll == 10){
				mFrameState[mCurrentFrame] = FrameState.STRIKE;
				nextFrame();
			}
			else mFrameState[mCurrentFrame] = FrameState.FIRST_ROLLED;
		}  else if (roll + mRolls[mCurrentRoll-1] <= 10) {
			mRolls[mCurrentRoll] = roll;
			if (roll +  mRolls[mCurrentRoll-1] == 10) mFrameState[mCurrentFrame] = FrameState.SPARE;
			else mFrameState[mCurrentFrame] = FrameState.NORMAL;
			mCurrentRoll++;
			nextFrame();
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
		return mFramesComplete && mExtraRolls == 0;
	}

	/**
	 * Is given frame a strike?
	 * 
	 * @param frame
	 *            the frame to test, first throw is frame 0!
	 * @return true if frame was a strike.
	 */
	public boolean isStrike(int frame) {
		return mFrameState[frame] == FrameState.STRIKE; 
	}

	/**
	 * Is given frame a spare?
	 * 
	 * @param frame
	 *            the frame to test, first throw is frame 0!
	 * @return true if frame was a strike.
	 */
	public boolean isSpare(int frame) {
		return mFrameState[frame] == FrameState.SPARE;
	}

	/** **/
	public int getScore() {
		int roll = 0;
		int score = 0;
		for (int frame = 0; frame < 10; frame++) {
			switch (mFrameState[frame]) {
			case FIRST_ROLLED: score += mRolls[roll++]; break;
			case NORMAL: score += mRolls[roll++] + mRolls[roll++];break; 
			case SPARE: roll += 2; score += 10 + mRolls[roll];break;
			case STRIKE: roll++; score += 10 + mRolls[roll] + mRolls[roll+1]; break;			
			case NOT_ROLLED: return score;				
			}
		}
		return score;
	}

}
