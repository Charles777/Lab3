package pokerEnums;

public enum eHandStrength {

	
	NaturalRoyalFlush(105){
		public String toString()
		{
			return "Natural Royal Flush";
		}
	},
	RoyalFlush(100){
		public String toString()
		{
			return "Royal Flush";
		}
	},
	StraightFlush(90){
		public String toString()
		{
			return "Straight Flush";
		}
	},
	FiveOfAKind(85){
		public String toString()
		{
			return "Five of a Kind";
		}
	},
	FourOfAKind(80){
		public String toString()
		{
			return "Four of a Kind";
		}
	},
	FullHouse(70){
		public String toString()
		{
			return "Full House";
		}
	},
	Flush(60){
		public String toString()
		{
			return "Flush";
		}
	},
	Straight(50){
		public String toString()
		{
			return "Straight";
		}
	},
	ThreeOfAKind(40){
		public String toString()
		{
			return "Three of a Kind";
		}
	},
	TwoPair(30){
		public String toString()
		{
			return "Two Pairs";
		}
	},
	
	Pair(20){
		public String toString()
		{
			return "One Pair";
		}
	},
	HighCard(10){
		public String toString()
		{
			return "High Card";
		}
	};
	
	private eHandStrength(final int handstrength){
		this.iHandStrength = handstrength;
	}

	private int iHandStrength;
	
	public int getHandStrength(){
		return iHandStrength;
	}
	public static void translate(int hs) {
		String x = "";
		
		switch (hs) {
		case 105: x = "Natural Royal Flush"; break;
		case 100: x = "Royal Flush"; break;
		case 90: x = "Straight Flush"; break;
		case 85: x = "Five of a Kind"; break;
		case 80: x = "Four of a Kind"; break;
		case 70: x = "Full House"; break;
		case 60: x = "Flush"; break;
		case 50: x = "Straight"; break;
		case 40: x = "Three of a Kind"; break;
		case 30: x = "Two Pair"; break;
		case 20: x = "Pair"; break;
		case 10: x = "High Card"; break;
		
		}
		
		System.out.println(x);
	}
	
	
	
}
