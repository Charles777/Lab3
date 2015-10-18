package pokerBase;

import java.util.Comparator;

import javax.xml.bind.annotation.XmlElement;

import pokerEnums.eRank;
import pokerEnums.eSuit;

public final class Card {
	@XmlElement
	private eSuit Suit;
	@XmlElement
	private eRank Rank;
	@XmlElement
	private boolean Wild = false;
	@XmlElement
	private String CardImg;
	
	
	/**
	 * Keep the no-arg constructor private.  I don't want 'Card' created without attributes.
	 */
	private Card()
	{
	}
	
	/**
	 * Create a new card of a given rank and suit.
	 * @param suit
	 * @param rank
	 */
	public Card(eSuit suit, eRank rank, int CardNbr ) {
		Suit = suit; 
		Rank = rank; 
		this.Wild = false;
		if (Rank.getRank() == 99 || Suit.getSuit() == 99)
			this.Wild = true;
		this.CardImg = CardNbr + ".png";
		
	}

	public Card(eSuit suit, eRank rank, boolean Wild) {
		Suit = suit; 
		Rank = rank; 
		this.Wild = Wild;
	}
	
	/**
	 * Getter for Rank
	 * @return
	 */
	public eRank getRank() {
		return this.Rank;
	}

	/**
	 * Getter for Suit
	 * @return
	 */
	public eSuit getSuit() {
		return this.Suit;
	}
	
	public boolean getWild()
	{
		return this.Wild;
	}
	
	public void setWild()
	{
		this.Wild = true;
	}
	
	public String getCardImg()
	{
		return this.CardImg;
	}
	
	public boolean sameSuit(Card card){
		if (this.getSuit() == card.getSuit())
			return true;
		else if (this.getRank().getRank() == 99 ||card.getRank().getRank() == 99)
			return true;
		else if (this.getWild() || card.getWild())
			return true;
		else
			return false;
		
					
	}
	
	public boolean sameRank(Card card){
		if (this.getRank() == card.getRank())
			return true;
		else if (this.getRank().getRank() == 99 ||card.getRank().getRank() == 99)
			return true;
		else if (this.getWild() ||card.getWild())
			return true;
		else
			return false;
		
					
	}
	
	/**
	 * CardRank Comparator is used for sorting the collection by rank
	 */
	public static Comparator<Card> CardRank = new Comparator<Card>() {

		public int compare(Card c1, Card c2) {

		   int Cno1 = c1.getRank().getRank();
		   int Cno2 = c2.getRank().getRank();
		   
		   if (Cno1 == 99)
				   Cno1 = 0;
		   if (Cno2 == 99)
				   Cno2 = 0;
		   

		   /*For descending order*/
		   return Cno2 - Cno1;

	   }};

}