package assignment2;

import java.util.Random;

public class Deck {
	public static String[] suitsInOrder = { "clubs", "diamonds", "hearts", "spades" };
	public static Random gen = new Random();

	public int numOfCards; // contains the total number of cards in the deck
	public Card head; // contains a pointer to the card on the top of the deck

	/*
	 * TODO: Initializes a Deck object using the inputs provided
	 */
	public Deck(int numOfCardsPerSuit, int numOfSuits) {
		/**** ADD CODE HERE ****/
		if (numOfCardsPerSuit < 1 || numOfCardsPerSuit > 13 || numOfSuits < 1 || numOfSuits > suitsInOrder.length) {
			throw new IllegalArgumentException("Could not create deck with such parameters");
		}
		Card current = head;
		for (int i = 0; i < numOfSuits; i++) {
			for (int j = 1; j <= numOfCardsPerSuit; j++) {
				if (head == null) {
					head = new PlayingCard(suitsInOrder[i], j);
					current = head;
					numOfCards = 1;
					continue;
				}
				Card newCard = new PlayingCard(suitsInOrder[i], j);
				current.next = newCard;
				newCard.prev = current;
				current = current.next;
				numOfCards++;
			}
		}
		Card redJoker = new Joker("red");
		current.next = redJoker;
		redJoker.prev = current;
		current = current.next;
		numOfCards++;

		Card blackJaker = new Joker("black");
		current.next = blackJaker;
		blackJaker.prev = current;
		current = current.next;
		numOfCards++;

		current.next = head;
		head.prev = current;
	}

	/*
	 * TODO: Implements a copy constructor for Deck using Card.getCopy().
	 * This method runs in O(n), where n is the number of cards in d.
	 */
	public Deck(Deck d) {
		/**** ADD CODE HERE ****/
		if (d != null && d.head != null) {
			Card current = head;
			Card copyCurrent = d.head;
			
			for (int i = 0; i < d.numOfCards; i++) {
				if (i == 0) {
					head = copyCurrent.getCopy();
					current = head;
					numOfCards = 1;
					continue;
				}
				Card newCard = copyCurrent.next.getCopy();
				current.next = newCard;
				newCard.prev = current;
				current = current.next;
				copyCurrent = copyCurrent.next;
				numOfCards++;
			}
			current.next = head;
			head.prev = current;
		}
	}

	/*
	 * For testing purposes we need a default constructor.
	 */
	public Deck() {
	}

	/*
	 * TODO: Adds the specified card at the bottom of the deck. This
	 * method runs in $O(1)$.
	 */
	public void addCard(Card c) {
		/**** ADD CODE HERE ****/
		if (c == null) {
			return;
		}
		if (head == null) {
			head = c;
			head.next = head;
			head.prev = head;
			numOfCards = 1;
			return;
		}
		Card lastCard = head.prev;
		lastCard.next = c;
		c.prev = lastCard;
		c.next = head;
		head.prev = c;
		numOfCards++;
	}

	/*
	 * TODO: Shuffles the deck using the algorithm described in the pdf.
	 * This method runs in O(n) and uses O(n) space, where n is the total
	 * number of cards in the deck.
	 */
	public void shuffle() {
		/**** ADD CODE HERE ****/
		if (numOfCards == 0) {
			return;
		}
		Card[] cards = new Card[numOfCards];
		Card current = head;
		for (int i = 0; i < numOfCards; i++) {
			cards[i] = current;
			current = current.next;
		}

		for (int i = numOfCards - 1; i > 0; i--) {
			int j = gen.nextInt(i + 1);
			Card temp = cards[i];
			cards[i] = cards[j];
			cards[j] = temp;
		}
		head = null;
		for (Card card : cards) {
			if (head == null) {
				head = card;
				current = head;
				continue;
			}
			current.next = card;
			card.prev = current;
			current = current.next;

		}
		current.next = head;
		head.prev = current;
	}

	/*
	 * TODO: Returns a reference to the joker with the specified color in
	 * the deck. This method runs in O(n), where n is the total number of
	 * cards in the deck.
	 */
	public Joker locateJoker(String color) {
		/**** ADD CODE HERE ****/
		if (!color.equalsIgnoreCase("red") && !color.equalsIgnoreCase("black")) {
			throw new IllegalArgumentException("Jokers are either red or black");
		}

		Card current = head;
		for (int i = 0; i < numOfCards; i++) {
			if (current instanceof Joker && ((Joker) current).redOrBlack.equalsIgnoreCase(color)) {
				return (Joker) current;
			}
			current = current.next;
		}
		return null;
	}

	/*
	 * TODO: Moved the specified Card, p positions down the deck. You can
	 * assume that the input Card does belong to the deck (hence the deck is
	 * not empty). This method runs in O(p).
	 */
	public void moveCard(Card c, int p) {
		/**** ADD CODE HERE ****/
		if (p == 0) {
			return;
		}
		Card prevCard = c.prev;
		prevCard.next = c.next;
		c.next.prev = prevCard;

		Card pCard = c;
		for (int i = 0; i < p; i++) {
			pCard = pCard.next;
		}
		Card pCardNext = pCard.next;
		pCard.next = c;
		c.prev = pCard;
		c.next = pCardNext;
		pCardNext.prev = c;
	}

	/*
	 * TODO: Performs a triple cut on the deck using the two input cards. You
	 * can assume that the input cards belong to the deck and the first one is
	 * nearest to the top of the deck. This method runs in O(1)
	 */
	public void tripleCut(Card firstCard, Card secondCard) {
		/**** ADD CODE HERE ****/
		// BJ 3S JD 8C KD 6C QC 9D AD 3D JC 4D RJ 3D

		// QD (2e card) -> 3S (head)
		// KD (1rst prev) -> 5C (2e next)
		// 8D (tail) -> 6C (first)
		// (2e next) = head
		// 5C 10D AC 8D 6C QC 9D AD 3D JC 4D 2C 10C RJ QD 3S JD 8C KD

		// 6C QC 9D AD 3D JC 4D 2C 10C RJ QD 3S
		if (firstCard == head) {
			head = secondCard.next;
			return;
		}
		if (secondCard == head.prev) {
			head = firstCard;
			return;
		}

		Card prevFirst = firstCard.prev;
		Card nextSecond = secondCard.next;
		Card prevHead = head.prev;

		secondCard.next = head;
		head.prev = secondCard;

		prevFirst.next = nextSecond;
		nextSecond.prev = prevFirst;

		prevHead.next = firstCard;
		firstCard.prev = prevHead;

		head = nextSecond;
	}

	/*
	 * TODO: Performs a count cut on the deck. Note that if the value of the
	 * bottom card is equal to a multiple of the number of cards in the deck,
	 * then the method should not do anything. This method runs in O(n).
	 */
	public void countCut() {
		/**** ADD CODE HERE ****/
		// 3S JD 8C KD 6C QC 9D AD 3D JC 4D 2C 10C RJ QD 2D 5D 7C 6D 3C KC 9C BJ 4C 7D
		// AD 3D JC 4D 2C 10C RJ QD 2D 5D 7C 6D 3C KC 9C BJ 4C 3S JD 8C KD 6C QC 9D 7D
		Card lastCard = head.prev;
		Card lastlastCard = lastCard.prev;
		
		int cuts = lastCard.getValue() % numOfCards;
		if (cuts == numOfCards - 1 || cuts == 0 || lastCard instanceof Joker) {
			return;
		}

		Card iCard = head;
		for (int i = 1; i < cuts; i++) {
			iCard = iCard.next;
		}
		Card nextICard = iCard.next;

		lastlastCard.next = head;
		head.prev = lastlastCard;

		iCard.next = lastCard;
		lastCard.prev = iCard;

		lastCard.next = nextICard;
		nextICard.prev = lastCard;

		head = nextICard;
	}

	/*
	 * TODO: Returns the card that can be found by looking at the value of the
	 * card on the top of the deck, and counting down that many cards. If the
	 * card found is a Joker, then the method returns null, otherwise it returns
	 * the Card found. This method runs in O(n).
	 */
	public Card lookUpCard() {
		/**** ADD CODE HERE ****/
		int value = head.getValue();
		Card current = head;
		for (int i = 0; i < value; i++) {
			current = current.next;
		}
		if (current instanceof Joker) {
			return null;
		}
		return current;
	}

	/*
	 * TODO: Uses the Solitaire algorithm to generate one value for the keystream
	 * using this deck. This method runs in O(n).
	 */
	public int generateNextKeystreamValue() {
		/**** ADD CODE HERE ****/
		Joker rJoker = locateJoker("red");
		moveCard(rJoker, 1);

		Joker bJoker = locateJoker("black");
		moveCard(bJoker, 2);

		Card temp = rJoker;
		while (temp != bJoker && temp != head) {
			temp = temp.next;
		}
		if (temp == head && head != rJoker) {
			tripleCut(bJoker, rJoker);
		} else {
			tripleCut(rJoker, bJoker);
		}
		countCut();
		Card lookupCard = lookUpCard();
		if (lookupCard == null) {
			return generateNextKeystreamValue();
		} else {
			return lookupCard.getValue();
		}
	}

	public abstract class Card {
		public Card next;
		public Card prev;

		public abstract Card getCopy();

		public abstract int getValue();

	}

	public class PlayingCard extends Card {
		public String suit;
		public int rank;

		public PlayingCard(String s, int r) {
			this.suit = s.toLowerCase();
			this.rank = r;
		}

		public String toString() {
			String info = "";
			if (this.rank == 1) {
				// info += "Ace";
				info += "A";
			} else if (this.rank > 10) {
				String[] cards = { "Jack", "Queen", "King" };
				// info += cards[this.rank - 11];
				info += cards[this.rank - 11].charAt(0);
			} else {
				info += this.rank;
			}
			// info += " of " + this.suit;
			info = (info + this.suit.charAt(0)).toUpperCase();
			return info;
		}

		public PlayingCard getCopy() {
			return new PlayingCard(this.suit, this.rank);
		}

		public int getValue() {
			int i;
			for (i = 0; i < suitsInOrder.length; i++) {
				if (this.suit.equals(suitsInOrder[i]))
					break;
			}

			return this.rank + 13 * i;
		}

	}

	public class Joker extends Card {
		public String redOrBlack;

		public Joker(String c) {
			if (!c.equalsIgnoreCase("red") && !c.equalsIgnoreCase("black"))
				throw new IllegalArgumentException("Jokers can only be red or black");

			this.redOrBlack = c.toLowerCase();
		}

		public String toString() {
			// return this.redOrBlack + " Joker";
			return (this.redOrBlack.charAt(0) + "J").toUpperCase();
		}

		public Joker getCopy() {
			return new Joker(this.redOrBlack);
		}

		public int getValue() {
			return numOfCards - 1;
		}

		public String getColor() {
			return this.redOrBlack;
		}
	}

}
