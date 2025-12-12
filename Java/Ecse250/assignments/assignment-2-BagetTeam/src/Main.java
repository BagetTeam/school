import java.util.Random;

import assignment2.*;
import assignment2.Deck.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------CREATING DECKS-------");

        Deck deck1 = new Deck(13, 2);
        System.out.println("DECK 1");
        Card current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        System.out.println("\nDECK 2  -- should be the same as deck 1");
        Deck deck2 = new Deck(deck1);
        current = deck2.head;
        for (int i = 0; i < deck2.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        // REVERSE
        System.out.println("\nREVERSING DECK 1");
        current = deck1.head.prev;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.prev;
        }

        System.out.println("\nREVERSING DECK 2");
        current = deck2.head.prev;
        for (int i = 0; i < deck2.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.prev;
        }

        System.err.println("\n------ADDING CARD--------");
        Deck dummy = new Deck();
        Card card = dummy.new PlayingCard(Deck.suitsInOrder[3], 3);
        deck1.addCard(card);

        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("");
        current = deck1.head.prev;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.prev;
        }
        System.out.println("\n -- Checking Deck 2 (should not be modified) --");
        current = deck2.head;
        for (int i = 0; i < deck2.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        System.out.println("\n---------SHUFFLING--------");
        Deck.gen = new Random(10);
        deck1.shuffle();
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("");
        deck1.shuffle();
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        dummy.shuffle();

        System.out.println("\n---------FINDING JOKERS--------");
        Joker joker = deck1.locateJoker("red");
        System.out.println(joker);
        joker = deck2.locateJoker("red");
        System.out.println("Expected: RJ BJ \nOutput: " + joker + " " + joker.next);
        joker = deck2.locateJoker("black");
        System.out.println("Expected: BJ RJ \nOutput: " + joker + " " + joker.prev);

        System.out.println("---------MOVING CARD--------");
        System.out.println("Before: ");
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        Card card2 = deck1.head;
        deck1.moveCard(card2, 3);
        System.out.println("\n\nAfter moving card " + card2 + " (head) by 3:  --head should not change");
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        card2 = card2.next;
        deck1.moveCard(card2, 5);
        System.out.println("\nAfter moving card " + card2 + " by 5:");
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        Card card3 = deck1.head.prev;
        deck1.moveCard(card3, 2);
        System.out.println("\nAfter moving card " + card3 + " by 2:");
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        deck1.moveCard(card3, 16);
        System.out.println("\nAfter moving card " + card3 + " by 16:");
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        deck1.moveCard(card3, 0);
        System.out.println("\nAfter moving card " + card3 + " by 0:");
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }
        // 1 2 3 4 5 
        // 1 3 2 4 5
        // 1 3 4 2 4
        // 1 3 4 5 2 
        // 1 2 3 4 5
        deck1.moveCard(card3, deck1.numOfCards);
        System.out.println("\nAfter moving card " + card3 + " by numofcards:");
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        deck1.moveCard(card3, deck1.numOfCards-1);
        System.out.println("\nAfter moving card " + card3 + " by numofcards:");
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        deck1.moveCard(card3, deck1.numOfCards+1);
        System.out.println("\nAfter moving card " + card3 + " by numofcards:");
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        deck1.moveCard(card3, deck1.numOfCards-2);
        System.out.println("\nAfter moving card " + card3 + " by numofcards:");
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        System.out.println("\n---------TRIPLE CUT--------");

        System.out.println("Triple Cutting at card " + card2 + " and " + card3);
        deck1.tripleCut(card2, card3);
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        Deck copyDeck1 = new Deck(deck1);
        System.out.println("\nTriple Cutting at head " + copyDeck1.head + " and " + card3);
        copyDeck1.tripleCut(copyDeck1.head, card3);
        current = copyDeck1.head;
        for (int i = 0; i < copyDeck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        Deck copyDeck2 = new Deck(copyDeck1);
        System.out.println("\nTriple Cutting at card " + card2 + " and tail " + copyDeck2.head.prev);
        copyDeck2.tripleCut(card2, copyDeck2.head.prev);
        current = copyDeck2.head;
        for (int i = 0; i < copyDeck2.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        System.out.println("\n---------COUNT CUT--------");
        System.out.println("Count cutting with " + deck1.head.prev + " (value = " + deck1.head.prev.getValue() + ")");
        deck1.countCut();
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("\n");
        
        Card cardJoker = dummy.new Joker("red");
        deck1.addCard(cardJoker);
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println("");
        deck1.countCut();
        current = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            System.out.print(current + " ");
            current = current.next;
        }

        System.out.println("\n---------CARD LOOK UP--------");
        System.out.println("Looking at card #" + deck1.head.getValue());
        Card lookupCard = deck1.lookUpCard();
        if (lookupCard == null) {
            System.out.println("JOKER CARD FOUND");
        } else {
            System.out.println("FOUND CARD " + lookupCard);
        }

        System.out.println("\n-------TESTING--------");
        Deck deck = new Deck(5, 2);
        System.out.println(deck);
        deck.shuffle();
        System.out.println(deck);
        System.out.println("\n   --- Generating Key Stream ---");
        SolitaireCipher code = new SolitaireCipher(deck);
        SolitaireCipher code1 = new SolitaireCipher(deck);
        SolitaireCipher code2 = new SolitaireCipher(deck);

        int[] keyStream = code1.getKeystream(deck.numOfCards);
        for (int i = 0; i < keyStream.length; i++) {
            System.out.print(keyStream[i] + " ");
        }

        String msg = "Is that you, Bob?";
        String encodedMsg = code.encode(msg);
        System.out.println("\n" + encodedMsg);

        String decodedMsg = code2.decode(encodedMsg);
        System.out.println(decodedMsg);
    }
}
