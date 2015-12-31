package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class SecondSonsjoinUnsullied {
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
			StringBuilder buffer = new StringBuilder(readString());
			int numU = 0;
			int numS = 0;
			for (int i = 0; i < buffer.length(); i++) {
				if (buffer.charAt(i) == 'U')
					numU++;
				else
					numS++;
			}
			int res = 0;
			if (Math.abs(numS - numU) > 1) {
				builder.append("-1\n");
			} else if (numS > numU) {

				for (int i = 0; i < buffer.length(); i += 2)
					if (buffer.charAt(i) != 'S')
						res++;
			} else if (numS < numU) {
				for (int i = 0; i < buffer.length(); i += 2)
					if (buffer.charAt(i) != 'U')
						res++;
			} else {
				int resS = 0, resU = 0;
				for (int i = 0; i < buffer.length(); i += 2)
					if (buffer.charAt(i) != 'S')
						resS++;
				for (int i = 0; i < buffer.length(); i += 2)
					if (buffer.charAt(i) != 'U')
						resU++;
				res = Math.min(resS, resU);
			}
			builder.append(res + "\n");
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
