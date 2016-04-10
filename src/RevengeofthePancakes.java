
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class RevengeofthePancakes {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter("Revenge.txt");
		StringBuilder builder = new StringBuilder();

		int t = readInt();
		for (int input = 1; input <= t; input++) {
			StringBuilder in = new StringBuilder(readString());
			int ans = 0;
			while (true) {
				if (checkConverted(in)) {
					break;
				}
				int i = 0;
				if (in.charAt(i) == '+') {
					ans++;
				}
				while (in.charAt(i) == '+' && i < in.length()) {

					in.setCharAt(i, '-');
					i++;
				}
				occurence occ = firstLast(in);
				while (occ.first < occ.last) {
					in.setCharAt(occ.last, in.charAt(occ.last) == '-' ? '+' : '-');
					in.setCharAt(occ.first, in.charAt(occ.first) == '-' ? '+' : '-');
					char temp = in.charAt(occ.last);
					in.setCharAt(occ.last, in.charAt(occ.first));
					in.setCharAt(occ.first, temp);
					occ.first++;
					occ.last--;
				}
				if (occ.first == occ.last) {
					in.setCharAt(occ.last, in.charAt(occ.last) == '-' ? '+' : '-');
				}
				ans++;
			}
			builder.append("Case #" + input + ": " + ans + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static boolean checkConverted(StringBuilder in) {
		for (int i = 0; i < in.length(); i++) {
			if (in.charAt(i) == '-') {
				return false;
			}
		}
		return true;
	}

	public static occurence firstLast(StringBuilder in) {
		occurence occ = new occurence();
		occ.first = 0;
		occ.last = 0;
		for (int i = 0; i < in.length(); i++) {
			if (in.charAt(i) == '-') {
				occ.last = i;
			}
		}
		return occ;
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

class occurence {
	int first;
	int last;
}