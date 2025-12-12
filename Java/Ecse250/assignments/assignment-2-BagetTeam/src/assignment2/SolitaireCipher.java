package assignment2;

public class SolitaireCipher {
	public Deck key;

	public SolitaireCipher(Deck key) {
		this.key = new Deck(key); // deep copy of the deck
	}

	/*
	 * TODO: Generates a keystream of the given size
	 */
	public int[] getKeystream(int size) {
		int[] keyStream = new int[size];
		for (int i = 0; i < size; i++) {
			keyStream[i] = key.generateNextKeystreamValue();
		}
		return keyStream;
	}

	/*
	 * TODO: Encodes the input message using the algorithm described in the pdf.
	 */
	public String encode(String msg) {
		/**** ADD CODE HERE ****/
		if (msg == null) {
			return "";
		}
		char[] chars = new char[msg.length()];

		msg = msg.toUpperCase();
		int i = 0;
		for (int j = 0; j < msg.length(); j++) {
			if ('A' <= msg.charAt(j) && msg.charAt(j) <= 'Z') {
				chars[i] = msg.charAt(j);
				i++;
			}
		}

		int[] keyStream = getKeystream(i);

		for (int j = 0; j < i; j++) {
			chars[j] = charSifter(chars[j], keyStream[j], true);
		}

		return String.valueOf(chars, 0, i);
	}

	private char charSifter(char ch, int shift, boolean isRight) {
		if (ch < 'A' || ch > 'Z') {
			return ch;
		}
		int position = (int) ch - 'A';
		if (!isRight) {
			shift = 26 - shift % 26;
		}
		return (char) ((position + shift) % 26 + 'A');
	}

	/*
	 * TODO: Decodes the input message using the algorithm described in the pdf.
	 */
	public String decode(String msg) {
		/**** ADD CODE HERE ****/
		if (msg == null) {
			return "";
		}
		int n = msg.length();
		int[] keyStream = getKeystream(n);
		char[] chars = new char[n];

		for (int i = 0; i < n; i++) {
			chars[i] = charSifter(msg.charAt(i), keyStream[i], false);
		}
		return String.valueOf(chars);
	}

}
