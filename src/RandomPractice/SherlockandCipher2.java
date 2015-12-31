package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class SherlockandCipher2 {
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
			StringBuilder builder3 = new StringBuilder(readString());
			int prev = -1;
			for (int i = 0; i < builder2.length(); i++) {
				prev = -1;
				for (int j = 0; j < builder3.length(); j++) {
					if (builder2.charAt(i + j) == builder3.charAt(j) && (builder2.charAt(i + j) < 65
							|| (builder2.charAt(i + j) > 90 && builder2.charAt(i + j) < 97)
							|| (builder2.charAt(i + j) > 122))) {
						continue;
					} else {
						int temp = builder2.charAt(i + j) - builder3.charAt(j);
						if (temp < 0) {
							temp = (temp + 26) % 26;
						}
						if (prev == -1)
							prev = temp;
						else if (prev != temp) {
							prev = -1;
							break;
						} else {
							continue;
						}
					}
				}
				if (prev != -1) {
					break;
				}
			}
			for (int i = 0; i < builder2.length(); i++) {
				if (builder2.charAt(i) < 65
						|| (builder2.charAt(i) > 90 && builder2.charAt(i) < 97)
						|| (builder2.charAt(i) > 122)) {
					builder.append(builder2.substring(i, i + 1));
				} else {
					int ch = builder2.charAt(i);
					if (ch < 97) {
						ch = ch - 65 - prev;
						if (ch < 0) {
							ch = (ch + 26) % 26;
						}
						ch += 65;
						builder.append((char) ch + "");
					} else {
						ch = ch - 97 - prev;
						if (ch < 0) {
							ch = (ch + 26) % 26;
						}
						ch += 97;
						builder.append((char) ch + "");
					}
				}
			}
			builder.append("\n");
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
