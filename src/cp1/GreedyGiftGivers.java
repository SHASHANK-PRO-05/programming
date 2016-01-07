package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.StringTokenizer;

public class GreedyGiftGivers {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		StringTokenizer st;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder stringBuilder = new StringBuilder("");
		String s = bufferedReader.readLine();
		while (true) {
			if (s == null || s.isEmpty())
				break;
			Map<String, Long> map = new HashMap<>();
			st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(bufferedReader.readLine());
			String[] names = new String[n];
			for (int i = 0; i < n; i++) {
				names[i] = st.nextToken();
				map.put(names[i], 0L);
			}
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bufferedReader.readLine());
				String name = st.nextToken();
				long val = Long.parseLong(st.nextToken());
				int div = Integer.parseInt(st.nextToken());
				long temp;
				if (div != 0) {
					temp = val / div;
					map.put(name, map.get(name) - (temp * div));
					for (int j = 0; j < div; j++) {
						String secondName = st.nextToken();
						map.put(secondName, map.get(secondName) + temp);
					}
				}
			}
			for (int i = 0; i < n; i++) {
				stringBuilder.append(names[i] + " " + map.get(names[i]) + "\n");
			}

			s = bufferedReader.readLine();
			if (s == null || s.isEmpty())
				break;
			else
				stringBuilder.append("\n");
		}
		out.print(stringBuilder);
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
