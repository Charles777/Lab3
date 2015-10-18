package pokerBase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
import pokerEnums.eSuit;
import pokerBase.Deck;
import pokerBase.Hand;

public class Hand_Test {
	Deck deckydeck;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void DeckRatioTest() {
		
		//make a counter for each type of Hand 
		ArrayList<Integer> evalList = new ArrayList<Integer>();
		
		for (int i = 0; i< 12; i++){
			evalList.add(0);
		}
		
		for (int i = 1; i< 100000; i++){
			Deck deckydeck = new Deck(3);
			Hand h = new Hand(deckydeck);
			h.EvalHand();
			if (h.getHandStrength()==105){
				int x = evalList.get(0);
				x++;
				evalList.set(0,  x);
			}if (h.getHandStrength() == 100){
				int x = evalList.get(1);
				x++;
				evalList.set(1, x);
			}if (h.getHandStrength() == 90){
				int x = evalList.get(2);
				x++;
				evalList.set(2, x);
			}if (h.getHandStrength() == 85){
				int x = evalList.get(3);
				x++;
				evalList.set(3, x);
			}if (h.getHandStrength() == 80){
				int x = evalList.get(4);
				x++;
				evalList.set(4, x);
			}if (h.getHandStrength() == 70){
				int x = evalList.get(5);
				x++;
				evalList.set(5, x);
			}if (h.getHandStrength() == 60){
				int x = evalList.get(6);
				x++;
				evalList.set(6, x);
			}if (h.getHandStrength() == 50){
				int x = evalList.get(7);
				x++;
				evalList.set(7, x);
			}if (h.getHandStrength() == 40){
				int x = evalList.get(8);
				x++;
				evalList.set(8, x);
			}if (h.getHandStrength() == 30){
				int x = evalList.get(9);
				x++;
				evalList.set(9, x);
			}if (h.getHandStrength() == 20){
				int x = evalList.get(10);
				x++;
				evalList.set(10, x);
			}if (h.getHandStrength() == 10){
				int x = evalList.get(11);
				x++;
				evalList.set(11, x);
			}
		}
		System.out.println("NRFs:" + evalList.get(0));
		System.out.println("Royal Flushes:" +evalList.get(1));
		System.out.println("Straight Flushes:" +evalList.get(2));
		System.out.println("5oKinds:" +evalList.get(3));
		System.out.println("4oKinds:" +evalList.get(4));
		System.out.println("Fullhouses:" +evalList.get(5));
		System.out.println("Flushes:" +evalList.get(6));
		System.out.println("Straights:" +evalList.get(7));
		System.out.println("3oKinds:" +evalList.get(8));
		System.out.println("Two-Pairs:" +evalList.get(9));
		System.out.println("Pairs:" +evalList.get(10));
		System.out.println("High Cards:" +evalList.get(11));
	}
	
	@Test
	public void FiveOFAKind1(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.JACK,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FiveOfAKind.getHandStrength());
	}
	
	@Test
	public void FiveOFAKind2(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.JACK,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FiveOfAKind.getHandStrength());
	}
	
	@Test
	public void FiveOFAKind3(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.JACK,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FiveOfAKind.getHandStrength());
	}
	
	@Test
	public void FiveOFAKind4(){
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.JACK,0));
		h.EvalHand();
		
		assertFalse(h.getHandStrength() == eHandStrength.FiveOfAKind.getHandStrength());
	}
	
	@Test
	public void RoyalFlush1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.NaturalRoyalFlush.getHandStrength());
		
		
	}
	
	@Test
	public void RoyalFlush2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h.EvalHand();

		
		assertTrue(h.getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
		
	}
	
	@Test
	public void RoyalFlush3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
		
	}
	
	@Test
	public void RoyalFlush4() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
		
	}
	
	@Test
	public void RoyalFlush5() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
		
	}
	
	@Test
	public void RoyalFlush6() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());
		
	}
	
	@Test
	public void StraightFlush1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		
		assertTrue(h.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
	}
	
	@Test
	public void StraightFlush2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.JOKER,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();

		
		assertTrue(h.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
	}
	
	@Test
	public void StraightFlush3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.EvalHand();
		
		assertFalse(h.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
	}
	
	@Test
	public void StraightFlush4() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
	}
	
	@Test
	public void StraightFlush5() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
	}
	
	@Test
	public void StraightFlush6() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.EvalHand();
		
		assertFalse(h.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
	}
	
	@Test
	public void StraightFlush7() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.EvalHand();

		
		assertFalse(h.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());
	}
	
	
	
	@Test
	public void Flush1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TWO,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.Flush.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
	}
	
	@Test
	public void Flush2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TWO,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.Flush.getHandStrength());
	}
	
	@Test
	public void Flush3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TWO,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.Flush.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
	}
	
	@Test
	public void Flush4() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TWO,0));
		h.EvalHand();
		
		assertFalse(h.getHandStrength() == eHandStrength.Flush.getHandStrength());
	}
	

	@Test
	public void Straight1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.Straight.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
	}
	
	@Test
	public void Straight2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JACK,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.Straight.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
	}
	
	@Test
	public void Straight3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.Straight.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.KING.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker() == null);
	}
	
	@Test
	public void Straight4() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.QUEEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertFalse(h.getHandStrength() == eHandStrength.Straight.getHandStrength());
	}
	
	
	@Test
	public void FourOfAKind_1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 1);
		
		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());
		
		//	Check to see if the kicker is a NINE
		assertTrue(c1.getRank().getRank() == eRank.NINE.getRank());
		
	}		

	
	@Test
	public void FourOfAKind_3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 1);
		
		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());
		
		//	Check to see if the kicker is a NINE
		assertTrue(c1.getRank().getRank() == eRank.NINE.getRank());
		
	}
	
	@Test
	public void FourOfAKind_4() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 1);
		
		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());
		
		//	Check to see if the kicker is a NINE
		assertTrue(c1.getRank().getRank() == eRank.NINE.getRank());
		
	}		


	
	@Test
	public void FourOfAKind1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 1);
	}	
	
	@Test
	public void FourOfAKind2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 1);
	}
	
	@Test
	public void FourOfAKind3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 1);
	}	
	
	@Test
	public void FourOfAKind4() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.KING,0));
		h.EvalHand();

		assertTrue(h.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
	}		

	

	@Test
	public void ThreeOfAKind_11() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.FOUR,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 2);
	}	
	

	@Test
	public void ThreeOfAKind_12() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.FOUR,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 2);
	}
	

	@Test
	public void ThreeOfAKind_13() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.FOUR,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 2);
	}		
	@Test
	public void ThreeOfAKind_22() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 2);
		
		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());
		Card c2 = h.getKicker().get(eCardNo.SecondCard.getCardNo());
		
		//	Check to see if the first kicker is a KING
		assertTrue(c1.getRank().getRank() == eRank.KING.getRank());
		
		//	Check to see if the second kicker is a NINE
		assertTrue(c2.getRank().getRank() == eRank.NINE.getRank());
	}		
	
	@Test
	public void ThreeOfAKind_23() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 2);
		
		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());
		Card c2 = h.getKicker().get(eCardNo.SecondCard.getCardNo());
		
		//	Check to see if the first kicker is a KING
		assertTrue(c1.getRank().getRank() == eRank.KING.getRank());
		
		//	Check to see if the second kicker is a NINE
		assertTrue(c2.getRank().getRank() == eRank.NINE.getRank());
	}		
	@Test
	public void ThreeOfAKind_31() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.THREE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 2);
		
		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());
		Card c2 = h.getKicker().get(eCardNo.SecondCard.getCardNo());
		
		//	Check to see if the first kicker is a THREE
		assertTrue(c1.getRank().getRank() == eRank.THREE.getRank());
		
		//	Check to see if the second kicker is a TWO
		assertTrue(c2.getRank().getRank() == eRank.TWO.getRank());		
	}
	
	@Test
	public void ThreeOfAKind_32() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.THREE,0));
		h.EvalHand();
		
		assertFalse(h.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());
	}		
	
	@Test
	public void FullHouse_11() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TWO,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FullHouse.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == eRank.TWO.getRank());
		assertTrue(h.getKicker() == null);	
	}	
	
	@Test
	public void FullHouse_12() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TWO,0));
		h.EvalHand();
		
		
		assertTrue(h.getHandStrength() == eHandStrength.FullHouse.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());		
		assertTrue(h.getLowPairStrength() == eRank.TWO.getRank());
		assertTrue(h.getKicker() == null);	
	}
	

	@Test
	public void FullHouse_22() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TWO,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.FullHouse.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TWO.getRank());
		assertTrue(h.getLowPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getKicker() == null);	
	}	
	
	@Test
	public void FullHouse_23() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TWO,0));
		h.EvalHand();
		
		assertFalse(h.getHandStrength() == eHandStrength.FullHouse.getHandStrength());
	}		
	
	@Test
	public void TwoPair1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.TwoPair.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == eRank.TWO.getRank());
		assertTrue(h.getKicker().size() == 1);
		
		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());
		
		//	Check to see if the first kicker is a THREE
		assertTrue(c1.getRank().getRank() == eRank.ACE.getRank());
	}
	
	@Test
	public void TwoPair2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h.EvalHand();
		
		assertFalse(h.getHandStrength() == eHandStrength.TwoPair.getHandStrength());

	}
	
	@Test
	public void TwoPair3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.EvalHand();
		

		assertFalse(h.getHandStrength() == eHandStrength.TwoPair.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == eRank.TWO.getRank());
	}		
	
	@Test
	public void Pair1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.THREE,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.TEN.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 3);
		
		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());
		Card c2 = h.getKicker().get(eCardNo.SecondCard.getCardNo());
		Card c3 = h.getKicker().get(eCardNo.ThirdCard.getCardNo());
		
		//	Check value of kicker
		assertTrue(c1.getRank().getRank() == eRank.ACE.getRank());

		//	Check value of kicker
		assertTrue(c2.getRank().getRank() == eRank.THREE.getRank());

		//	Check value of kicker
		assertTrue(c3.getRank().getRank() == eRank.TWO.getRank());

	}	
	
	@Test
	public void Pair2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.THREE,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.Pair.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.ACE.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 3);
		
		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());
		Card c2 = h.getKicker().get(eCardNo.SecondCard.getCardNo());
		Card c3 = h.getKicker().get(eCardNo.ThirdCard.getCardNo());
		
		//	Check value of kicker
		assertTrue(c1.getRank().getRank() == eRank.TEN.getRank());

		//	Check value of kicker
		assertTrue(c2.getRank().getRank() == eRank.THREE.getRank());

		//	Check value of kicker
		assertTrue(c3.getRank().getRank() == eRank.TWO.getRank());

	}	
	
	@Test
	public void Pair3() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.JOKER,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h.EvalHand();
		
		assertFalse(h.getHandStrength() == eHandStrength.Pair.getHandStrength());
		

	}	
	
	
	
	@Test
	public void HighCard1() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.THREE,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h.EvalHand();
		
		assertTrue(h.getHandStrength() == eHandStrength.HighCard.getHandStrength());
		assertTrue(h.getHighPairStrength() == eRank.ACE.getRank());
		assertTrue(h.getLowPairStrength() == 0);
		assertTrue(h.getKicker().size() == 4);
		
		Card c1 = h.getKicker().get(eCardNo.FirstCard.getCardNo());
		Card c2 = h.getKicker().get(eCardNo.SecondCard.getCardNo());
		Card c3 = h.getKicker().get(eCardNo.ThirdCard.getCardNo());
		Card c4 = h.getKicker().get(eCardNo.FourthCard.getCardNo());
		
		//	Check value of kicker
		assertTrue(c1.getRank().getRank() == eRank.KING.getRank());

		//	Check value of kicker
		assertTrue(c2.getRank().getRank() == eRank.TEN.getRank());

		//	Check value of kicker
		assertTrue(c3.getRank().getRank() == eRank.THREE.getRank());

		//	Check value of kicker
		assertTrue(c4.getRank().getRank() == eRank.TWO.getRank());

	}		
	
	@Test
	public void HighCard2() {
		Hand h = new Hand();
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.KING,0));
		h.AddCardToHand(new Card(eSuit.SPADES,eRank.THREE,0));
		h.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h.AddCardToHand(new Card(eSuit.CLUBS,eRank.JOKER,0));
		h.EvalHand();
		
		assertFalse(h.getHandStrength() == eHandStrength.HighCard.getHandStrength());
		
	}		
	
	
	@Test
	public void CompareTwoHands1() {
		Hand h1 = new Hand();
		h1.AddCardToHand(new Card(eSuit.CLUBS,eRank.TEN,0));
		h1.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.TEN,0));
		h1.AddCardToHand(new Card(eSuit.SPADES,eRank.TEN,0));
		h1.AddCardToHand(new Card(eSuit.HEARTS,eRank.TWO,0));
		h1.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h1.EvalHand();
		
		Hand h2 = new Hand();
		h2.AddCardToHand(new Card(eSuit.CLUBS,eRank.NINE,0));
		h2.AddCardToHand(new Card(eSuit.DIAMONDS,eRank.NINE,0));
		h2.AddCardToHand(new Card(eSuit.SPADES,eRank.NINE,0));
		h2.AddCardToHand(new Card(eSuit.HEARTS,eRank.NINE,0));
		h2.AddCardToHand(new Card(eSuit.CLUBS,eRank.ACE,0));
		h2.EvalHand();	
		
		ArrayList<Hand> TwoHands = new ArrayList<Hand>();
		TwoHands.add(h1);
		TwoHands.add(h2);
		
		Collections.sort(TwoHands,Hand.HandRank);
		
		Hand winningHand = new Hand();
		
		winningHand = TwoHands.get(0);
		
		assertTrue(winningHand.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());
		assertTrue(winningHand.getHighPairStrength() == eRank.NINE.getRank());
		
	}	
}




