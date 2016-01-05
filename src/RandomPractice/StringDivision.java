package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class StringDivision {
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
			StringBuilder builder2 = new StringBuilder(readString());
			int numcharthree = 0;
			char chnumcharthree = '1';
			boolean found = false;
			boolean found4 = false;
			boolean found2 = false;
			char foundfour = '1';
			char foundtwo = '1';
			char foundfive='1';
			boolean found5=false;
			Map<Character, Integer> map = new HashMap<>();
			for (int i = 0; i < builder2.length(); i++) {
				char ch = builder2.charAt(i);
				if (!map.containsKey(ch)) {
					map.put(ch, 1);
					if (map.size() == 4) {
						found = true;
						break;
					}
					if(found5){
						found=true;
						break;
					}
				} else {
					map.put(ch, map.get(ch) + 1);
					if (map.get(ch) == 3) {
						if (numcharthree == 0) {
							numcharthree = 1;
							chnumcharthree = ch;
						} else if (ch != chnumcharthree) {
							found = true;
							break;
						}
					}
					if (map.size() == 3 && numcharthree == 1) {
						found = true;
						break;
					}
					if (map.get(ch) == 10) {
						found = true;
						break;
					}
					if (map.get(ch) == 4) {
						if (found2 && ch != foundtwo) {
							found = true;
							break;
						}
						foundtwo = '1';
						found4 = true;
						foundfour = ch;
					}
					if (map.get(ch) == 2) {
						if (found4) {
							found = true;
							break;
						}
						foundtwo = ch;
						found2 = true;
					}
					if(map.get(ch)==7){
						if(map.size()!=1){
							found=true;
							break;
						}
						found5=true;
					}
				}
			}
			if (found)
				builder.append("YES\n");
			else
				builder.append("NO\n");
		}
		out.println(builder);
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
