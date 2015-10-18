package pokerBase;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.exHand;
import pokerEnums.eHandStrength;

import java.lang.Math;

public class Play {
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Autoplay how many hands?");
		int nGames = Math.abs((int)input.nextDouble());
		
		System.out.println("How many jokers in the deck?");
		int nJokers = Math.abs((int)input.nextDouble());
		
		System.out.println("How many players?");
		int nPlayers = Math.abs((int)input.nextDouble());
		
		for(int i = 1; i <= nGames; i++){
			System.out.println("Game" + i);
			playGame(nJokers, nPlayers);		
			
			
		}
		
		input.close();
	}
	
	private static void ShowHand(Hand x){
		char s = 0;
		
		for (Card i : x.getCards()){
			int n = i.getSuit().getSuit();
			switch (n){
			
			case 1 : s = 'H'; break;
			case 2 : s = 'S'; break;
			case 3 : s = 'C'; break;
			case 4 : s = 'D'; break;
			case 99 : s = '?'; break;
			
			}
			
			System.out.print(i.getRank().getRank() +"" + s + "  ");
		}
		System.out.println("");
		
	}
	
	public static void playGame(int jokers, int players){
		
		Hand besthand = null;
		Deck dealer = new Deck(jokers);
		ArrayList<Hand> playerhands = new ArrayList<Hand>();
		
		for (int i = 0; i < players; i++){
			Hand z = new Hand(dealer);
			z.EvalHand();
			playerhands.add(z);
			
		}
		
		try {
			besthand = Hand.PickBestHand(playerhands);
		} catch (exHand e) {
			System.out.println(e.getErrormessage());
		}
		int x = 1;
		for (Hand i : playerhands){
			System.out.print("Player " + x + " holds: ");
			Play.ShowHand(i);
			x++;
		}
		x = 1;
		
		for (Hand q : playerhands){
			if (Hand.HandRank.compare(besthand, q) == 0)
			{
				System.out.println("Player "+ x + " Wins!");
				System.out.print("He had a ");
				eHandStrength.translate(q.getHandStrength());
				break;
			}
			x++;
				
			
		}
			
		
		
		
		
	}
}