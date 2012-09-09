/**
 * 
 */
package com.xsloth.aku.game;

public class Round {
	
	int numRounds;
	int[] roundStandings;
	
	protected static final int P1 = 1;
	protected static final int P2 = 2;
	protected static final int NOTWON = -1;
	
	public Round(int numRounds) {
		this.numRounds = numRounds;
		roundStandings = new int[this.numRounds];
		
		reset();
	}
	
	/**
	 * Getters and Setters
	 */
	public int getNumRounds() {
		return numRounds;
	}

	public void setNumRounds(int numRounds) {
		this.numRounds = numRounds;
	}

	public int[] getRoundStandings() {
		return roundStandings;
	}
	
	/**
	 * Methods
	 */
	public int isWon(){
		int p1Vics=0, p2Vics=0;
		for(int i=0; i < numRounds; i++){
			//System.out.println("i " + i + " Standings " + roundStandings[i]);
			if(roundStandings[i] == P1){
				p1Vics += 1;
			}
			else if(roundStandings[i] == P2){
				p2Vics += 1;
			}
		}
		//System.out.println("NumRounds/2 " + Math.floor(numRounds/2) + " p1 " + p1Vics + " p2 " + p2Vics);
		if(p1Vics > Math.floor(numRounds/2)){
			return P1;
		}
		else if(p2Vics > Math.floor(numRounds/2)){
			return P2;
		}
		else {
			return NOTWON;
		}
	}
	
	public void setWinner(int winner){
		if(winner == P1 || winner == P2){
			for(int i=0; i < numRounds; i++){
				//System.out.println("setWinner i: " + i);
				if(roundStandings[i] == 0){
					roundStandings[i] = winner;
					//System.out.println("Winner " + winner + " set at " + i + " " + roundStandings[i]);
					return;
				}
				//System.out.println("Never showed");
			}
		}
	}
	
	public void reset(){
		for(int i=0; i < numRounds; i++){
			roundStandings[i] = 0;
		}
	}
	
	public void print(){
		for(int i : roundStandings)
			System.out.println(i);
	}
}
