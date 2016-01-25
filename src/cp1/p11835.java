package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Vector;



public class p11835 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		int game = readInt();
		int players = readInt();
		while (game != 0) {
			int arr[][] = new int[game][players];
			for (int i = 0; i < game; i++) {
				for (int j = 0; j < players; j++) {
					arr[i][readInt() - 1] = j;
				}
			}
			int scoringSystem = readInt();
			while (scoringSystem-- != 0) {
				int k = readInt();
				Vector<Integer> winningVector = new Vector<>();
				int max = Integer.MIN_VALUE;
				int[] playerScore = new int[players];
				int[] scoreCard = new int[k];
				for (int i = 0; i < k; i++) {
					scoreCard[i] = readInt();
				}
				for (int i = 0; i < game; i++) {
					for (int j = 0; j < k; j++) {
						playerScore[arr[i][j]] += scoreCard[j];
						if (playerScore[arr[i][j]] > max) {
							winningVector = new Vector<>();
							max = playerScore[arr[i][j]];
							winningVector.add(arr[i][j]);
						} else if (playerScore[arr[i][j]] == max) {
							winningVector.add(arr[i][j]);
						}
					}

				}
				Collections.sort(winningVector);
				for (int j = 0; j < winningVector.size(); j++) {
					if (j != winningVector.size() - 1)
						builder.append((winningVector.get(j) + 1) + " ");
					else
						builder.append((winningVector.get(j) + 1) + "\n");
				}
			}
			game = readInt();
			players = readInt();
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static int read() throws IOException {
		if (numChar <= curChar) {
			curChar = 0;
			numChar = stream.read(buffer);
			if (numChar <= 0) {
				return -1;
			}
		}
		return buffer[curChar++];
	}

	public static long readLong() throws IOException, InputMismatchException {
		int c = read();
		if (c == -1)
			throw new IOException();
		while (isSpaceChar(c)) {
			c = read();
		}
		boolean negative = false;
		if (c == '-') {
			negative = true;
			c = read();
		}
		long res = 0;
		while (!isSpaceChar(c)) {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += (c - '0');
			c = read();
		}
		if (negative)
			return -res;
		return res;
	}

	public static int readInt() throws IOException, InputMismatchException {
		return (int) readLong();
	}

	public static String readString() throws IOException {
		int c = read();
		if (c == -1)
			throw new IOException();
		while (isSpaceChar(c)) {
			c = read();
		}
		StringBuilder builder = new StringBuilder();
		while (!isSpaceChar(c)) {
			builder.append((char) c);
			c = read();
		}
		return builder.toString();
	}

	public static boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
	}
}
