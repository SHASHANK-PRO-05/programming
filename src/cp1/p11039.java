package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

class Tile implements Comparable<Tile> {
	long num;
	boolean type;

	@Override
	public int compareTo(Tile o) {
		if (this.num > o.num) {
			return 1;
		} else if (this.num < o.num) {
			return -1;
		}
		return 0;
	}

}

public class p11039 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		int t = readInt();
		while (t-- != 0) {
			long n = readLong();
			if (n == 0) {
				builder.append("0\n");
				continue;
			}
			Tile[] tiles = new Tile[(int) n];
			for (int i = 0; i < n; i++) {
				tiles[i] = new Tile();
				long input = readLong();
				if (input < 0) {
					input *= -1;
					tiles[i].num = input;
					tiles[i].type = false;
				} else {
					tiles[i].num = input;
					tiles[i].type = true;
				}
			}
			Arrays.sort(tiles);
			boolean checkRB = false;
			long ans = 1;
			if (tiles[0].type) {
				checkRB = true;
			}
			for (int i = 1; i < n; i++) {
				if (!checkRB == tiles[i].type) {
					ans++;
					checkRB = tiles[i].type;
				}
			}
			builder.append(ans + "\n");
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
