package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;

public class NavisParty {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int number = readInt(), actual = readInt();
		int n = number, q = actual;
		Set<String> invited = new HashSet<>();
		while (n-- != 0) {
			invited.add(readString() + "");
		}
		Set<String> checkedthings = new HashSet();
		int ans = 0;
		while (q-- != 0) {
			String s = readString();
			int temp = -1;
			try {
				temp = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				temp = -1;
			}
			if (temp != -1) {
				if (temp <= 20 && !checkedthings.contains(s + "")) {
					checkedthings.add(s + "");
					ans++;
					actual--;
					if (actual == number)
						break;
				} else if (checkedthings.contains(s + "")) {
					actual--;
					if (actual == number)
						break;
				}
			} else {
				if (!invited.contains(s + "") && !checkedthings.contains(s + "")) {
					checkedthings.add(s + "");
					ans++;
					actual--;
					if (actual == number)
						break;
				} else if (checkedthings.contains(s + "")) {
					actual--;
					if (actual == number)
						break;
				}
			}
		}
		out.print(ans);
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
