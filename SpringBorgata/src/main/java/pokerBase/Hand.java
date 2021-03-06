package pokerBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;
import exceptions.exHand;
import javax.xml.bind.annotation.XmlElement;

import exceptions.exHand;
import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;

public class Hand {
	private UUID playerID;
	@XmlElement
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;
	
	@XmlElement
	private int bNatural = 1;
	
	@XmlElement
	private int HandStrength;
	@XmlElement
	private int HiHand;
	@XmlElement
	private int LoHand;
	@XmlElement
	private ArrayList<Card> Kickers = new ArrayList<Card>();

	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	private boolean Royal;
	private static Deck dNonWildDeck = new Deck();
	
	private ArrayList<Hand> PossibleHands = new ArrayList<Hand>();

	public Hand()
	{

	}
	public void  AddCardToHand(Card c)
	{
		if (this.CardsInHand == null)
		{
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}

	public Card  GetCardFromHand(int location)
	{
		return CardsInHand.get(location);
	}

	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;
	}
	
	

	public Hand(ArrayList<Card> setCards) {
		this.CardsInHand = setCards;
	}

	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public ArrayList<Card> getBestHand() {
		return BestCardsInHand;
	}

	public void setPlayerID(UUID playerID)
	{
		this.playerID = playerID;
	}
	public UUID getPlayerID()
	{
		return playerID;
	}
	public void setBestHand(ArrayList<Card> BestHand) {
		this.BestCardsInHand = BestHand;
	}

	public int getHandStrength() {		
		return HandStrength;
	}


	public ArrayList<Card> getKicker() {
		return Kickers;
	}

	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public static Hand EvalHand(Hand h) {
		ArrayList<Hand> EvalHands = ExplodeHands(h);

		for (Hand EvalHand : EvalHands) {
			EvalHand.EvalHand();
		}

		Collections.sort(EvalHands, Hand.HandRank);

		return EvalHands.get(0);

	}
	
	public static Hand EvalHand(ArrayList<Hand> hands)
	{
		for (Hand EvalHand: hands)
		{
			EvalHand.EvalHand();
		}
		
		Collections.sort(hands, Hand.HandRank);

		return hands.get(0);
		
	}
	private static ArrayList<Hand> ExplodeHands(Hand h) {
		ArrayList<Hand> HandsToReturn = new ArrayList<Hand>();
		HandsToReturn.add(h);

		for (int a = 0; a < h.CardsInHand.size(); a++) {
			if (h.CardsInHand.get(a).getRank().getRank() == eRank.JOKER.getRank()
					|| h.CardsInHand.get(a).getWild() == true) {
				h.bNatural = 0;
			}
		}

		for (

		int a = 0; a < h.CardsInHand.size(); a++)

		{
			HandsToReturn = SubstituteHand(HandsToReturn, a);
		}

		return HandsToReturn;

	}

	private static ArrayList<Hand> SubstituteHand(ArrayList<Hand> inHands, int SubCardNo) {

		ArrayList<Hand> SubHands = new ArrayList<Hand>();

		for (Hand h : inHands) {
			ArrayList<Card> c = h.getCards();
			if (c.get(SubCardNo).getRank().getRank() == eRank.JOKER.getRank() || c.get(SubCardNo).getWild() == true) {

				for (Card JokerSub : dNonWildDeck.getCards()) {
					ArrayList<Card> SubCards = new ArrayList<Card>();
					SubCards.add(JokerSub);

					for (int a = 0; a < 5; a++) {
						if (SubCardNo != a) {
							SubCards.add(h.getCards().get(a));
						}
					}
					Hand subHand = new Hand(SubCards);
					SubHands.add(subHand);
				}
			} else {
				SubHands.add(h);
			}
		}
		return SubHands;
	}


	public void EvalHand() {
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes

		ArrayList<Card> remainingCards = new ArrayList<Card>();
		
		// Sort the cards!
		Collections.sort(CardsInHand, Card.CardRank);
		
		

		int njokers = 0;
		int nwilds = 0;
		for (Card i : CardsInHand){
			if (i.getRank() == eRank.JOKER)
				njokers++;
			if (i.getWild() == true)
				nwilds++;
		}
		
		// Ace Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == eRank.ACE) {
			Ace = true;
		} else if (nwilds > 0){
			Ace = true;
		}

		// Flush Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameSuit(CardsInHand.get(eCardNo.SecondCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameSuit(CardsInHand.get(eCardNo.ThirdCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameSuit(CardsInHand.get(eCardNo.FourthCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameSuit(CardsInHand.get(eCardNo.FifthCard.getCardNo()))){
			Flush = true;
		} else {
			Flush = false;
		}



		// Straight Evaluation
		if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			ArrayList<eRank> ranks = new ArrayList<eRank>();

			for (Card i : CardsInHand){
				ranks.add(i.getRank());
			}

			int royalty = ranks.indexOf(eRank.ACE) + ranks.indexOf(eRank.JACK) + ranks.indexOf(eRank.KING) + 
					ranks.indexOf(eRank.QUEEN) + ranks.indexOf(eRank.TEN);

			if (royalty == 10)
				Straight = true;				
			else if (royalty == 5 && nwilds == 1)
				Straight = true;
			else if (royalty == 1 && nwilds == 2)
				Straight = true;
			else if (royalty == -2 && nwilds == 3)
				Straight = true;
			else if (royalty == -4 && nwilds == 4)
				Straight = true;
			//Annnnd may as well-
			else if (nwilds == 5)
				Straight = true;
			
			if (Straight == true){
				Royal = true;
			}
			
			// Looks for Ace, 2, 3, 4, 5
			int lowstraight = ranks.indexOf(eRank.ACE) + ranks.indexOf(eRank.TWO) + ranks.indexOf(eRank.THREE) + 
					ranks.indexOf(eRank.FOUR) + ranks.indexOf(eRank.FIVE);

			if (lowstraight == 15)
				Straight = true;
			else if (lowstraight == 9 && nwilds == 1)
				Straight = true;
			else if (lowstraight == 4 && nwilds == 2)
				Straight = true;
			else if (lowstraight == 0 && nwilds == 3)
				Straight = true;
			else if (lowstraight == -3 && nwilds == 4)
				Straight = true;
			//Annnnd may as well-
			else if (nwilds == 5)
				Straight = true;


			// Looks for straight without Ace
		} 

		int r1 = CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank();
		int neededjokers = 0;

		for (int i = 1; i <= 4; i++){

			for (Card x : CardsInHand){
				if (x.getRank().getRank() == r1 - i)
					break;
				else if (x.equals(CardsInHand.get(CardsInHand.size()-1)))
					neededjokers++;
			}		


		}
		
		if (neededjokers<=nwilds){
			Straight = true;
		} else {
			Straight = false;
		}


		// Evaluates the hand type
		if (Straight == true
				&& Flush == true
				&& Royal == true
				&& Ace
				&& njokers<1) {
			ScoreHand(eHandStrength.NaturalRoyalFlush, 0, 0, null);
		}
		else if (Straight == true
				&& Flush == true
				&& Royal == true
				&& Ace) {
			ScoreHand(eHandStrength.RoyalFlush, 0, 0, null);
		}

		// Straight Flush
		else if (Straight == true && Flush == true) {
			remainingCards = null;
			ScoreHand(eHandStrength.StraightFlush,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, remainingCards);
		}
		
		// five of a Kind

		else if(CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.SecondCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.ThirdCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FourthCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FifthCard.getCardNo())))
		{
			remainingCards = null;
			ScoreHand(eHandStrength.FiveOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, remainingCards);
		}
		
		
		// Four of a Kind		
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.SecondCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.ThirdCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FifthCard.getCardNo()))) {
						
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			ScoreHand(eHandStrength.FourOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		}
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.SecondCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FourthCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FifthCard.getCardNo()))) {
						
			remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			ScoreHand(eHandStrength.FourOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		}
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.ThirdCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FourthCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FifthCard.getCardNo()))) {
						
			remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			ScoreHand(eHandStrength.FourOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		}
		
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.SecondCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.ThirdCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FourthCard.getCardNo()))) {
						
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			ScoreHand(eHandStrength.FourOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		}

		else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.ThirdCard.getCardNo()))
				&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FourthCard.getCardNo()))
				&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FifthCard.getCardNo()))) {
			
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			ScoreHand(eHandStrength.FourOfAKind,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		}

		// Full House
		else if (nwilds == 1 
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.SecondCard.getCardNo()))
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FourthCard.getCardNo())))
		{
			remainingCards = null;
			ScoreHand(eHandStrength.FullHouse,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
							.getRank(), remainingCards);
		}
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.SecondCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.ThirdCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FifthCard.getCardNo()))) {
			
			remainingCards = null;
			ScoreHand(eHandStrength.FullHouse,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
							.getRank(), remainingCards);
		}

		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.SecondCard.getCardNo()))
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FourthCard.getCardNo()))
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FifthCard.getCardNo()))) {
			
			remainingCards = null;
			ScoreHand(eHandStrength.FullHouse,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), remainingCards);
		}

		// Flush
		else if (Flush) {
			remainingCards = null;
			ScoreHand(eHandStrength.Flush,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, remainingCards);
		}

		// Straight
		else if (Straight) {
			remainingCards = null;
			ScoreHand(eHandStrength.Straight,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0, remainingCards);
		}
		// Three of a Kind
		
		else if (nwilds == 2){
			
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));			
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		}
		else if (nwilds == 1 && (CardsInHand.get(eCardNo.FirstCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.SecondCard.getCardNo())))){
			
			remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));			
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
			
		}
		else if (nwilds == 1 && (CardsInHand.get(eCardNo.SecondCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.ThirdCard.getCardNo())))){
			
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));			
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
			
		}
		
		else if (nwilds == 1 && (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).sameRank(CardsInHand.get(eCardNo.FourthCard.getCardNo())))){
			
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));			
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
			
		}
		
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()) {
			
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));			
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		}

		else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));			
			
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		} 
		
		else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));				
			ScoreHand(eHandStrength.ThreeOfAKind,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		}

		// Two Pair
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank())) {
			
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank(),
							remainingCards);
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank())) {
			
			remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
							.getRank(),
							remainingCards);
		} else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank())) {
			
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			ScoreHand(eHandStrength.TwoPair,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank(),
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
							.getRank(),
							remainingCards);
		}

		// Pair
		else if (nwilds == 1){				
				remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
				remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
				Collections.sort(remainingCards, Card.CardRank);
				
				ScoreHand(eHandStrength.Pair,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
								.getRank(), 0,
								remainingCards);
		}
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank()) {
			
			remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			Collections.sort(remainingCards, Card.CardRank);
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		} else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank()) {
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			Collections.sort(remainingCards, Card.CardRank);
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		} else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			Collections.sort(remainingCards, Card.CardRank);
			
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		} else if (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			
			remainingCards.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			Collections.sort(remainingCards, Card.CardRank);
			
			ScoreHand(eHandStrength.Pair,
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		}

		else {
			remainingCards.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
			remainingCards.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			Collections.sort(remainingCards, Card.CardRank);
			
			ScoreHand(eHandStrength.HighCard,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0,
							remainingCards);
		}
	}


	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand, ArrayList<Card> kickers) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kickers = kickers;
		this.bScored = true;

	}
	
	public static Hand PickBestHand(ArrayList<Hand> Hands) throws exHand
	{
		ArrayList<Hand> handcopy = new ArrayList<Hand>();
		for(Hand i : Hands){
			Hand copy = new Hand(i.getCards());
			copy.EvalHand();
			handcopy.add(copy);
		}
		
		Collections.sort(handcopy, HandRank);
		if (HandRank.compare(handcopy.get(0), handcopy.get(1)) == 0)
			throw new exHand();
		else 
			return handcopy.get(0);
		
	}


	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.getHandStrength() - h1.getHandStrength();

			if (result != 0) {
				return result;
			}

			result = h2.getHighPairStrength() - h1.getHighPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getLowPairStrength() - h1.getLowPairStrength();
			if (result != 0) {
				return result;
			}

			
			if ((h2.Kickers == null) || (h1.Kickers == null))
			{
				return 0;
			}
			

			try
			{
				if (h2.Kickers.size() >= eCardNo.FirstCard.getCardNo() +1 )
				{
					if (h1.Kickers.size() >= eCardNo.FirstCard.getCardNo() +1)
					{
						result = h2.getKicker().get(eCardNo.FirstCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.FirstCard.getCardNo()).getRank().getRank();
					}
					if (result != 0)
					{
						return result;
					}
				}				
			}
			catch (Exception e)
			{				
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}			

			
			try
			{
				if (h2.Kickers.size() >= eCardNo.SecondCard.getCardNo() +1 )
				{
					if (h1.Kickers.size() >= eCardNo.SecondCard.getCardNo() +1)
					{
						result = h2.getKicker().get(eCardNo.SecondCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.SecondCard.getCardNo()).getRank().getRank();
					}
					if (result != 0)
					{
						return result;
					}
				}				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new RuntimeException(e);				
			}			
			
			try
			{
				if (h2.Kickers.size() >= eCardNo.ThirdCard.getCardNo() +1 )
				{
					if (h1.Kickers.size() >= eCardNo.ThirdCard.getCardNo() +1)
					{
						result = h2.getKicker().get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.ThirdCard.getCardNo()).getRank().getRank();
					}
					if (result != 0)
					{
						return result;
					}
				}				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new RuntimeException(e);
			}
			
			try
			{
				if (h2.Kickers.size() >= eCardNo.FourthCard.getCardNo() +1 )
				{
					if (h1.Kickers.size() >= eCardNo.FourthCard.getCardNo() +1)
					{
						result = h2.getKicker().get(eCardNo.FourthCard.getCardNo()).getRank().getRank() - h1.getKicker().get(eCardNo.FourthCard.getCardNo()).getRank().getRank();
					}
					if (result != 0)
					{
						return result;
					}
				}				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				throw new RuntimeException(e);				
			}
			
			return 0;
		}
	};
	
}
