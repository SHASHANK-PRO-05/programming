package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class SynchingSignals {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st;
		int t = 0;
		while (s != null && !s.isEmpty()) {
			st = new StringTokenizer(s);
			t++;
			int n = 0;
			int[] signals = new int[11];
			int[] cycle = new int[11];
			int finalAns = -1;
			while (st.hasMoreTokens()) {
				signals[n] = Integer.parseInt(st.nextToken());
				cycle[n] = signals[n];
				n++;
			}
			boolean notFirst = false;
			for (int i = 0; i <= 3600; i++) {
				int j;
				for (j = 0; j < n; j++) {
					if ((i / signals[j]) % 2 == 0 && (i / signals[j] + 1) * signals[j] - 5 > i) {
						if (i / signals[j] != 0)
							notFirst = true;
						continue;
					} else {
						break;
					}
				}
				if (j == n && notFirst) {
					finalAns = i;
					break;
				}
			}
			if (finalAns != -1) {
				builder.append("Set " + t + " synchs again at " + finalAns / 60 + " minute(s) and " + finalAns % 60
						+ " second(s) after all turning green.\n");
			} else {
				builder.append("Set " + t + " is unable to synch after one hour.\n");
			}
			s = br.readLine();
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
