package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class ChildfreeTime {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static long[] dp;
	static ArrayList<Portal>[] array;
	static boolean[] visited;
	static boolean[] found;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int room = readInt(), portal = readInt();
		found = new boolean[room + 1];
		visited = new boolean[room + 1];
		array = new ArrayList[room + 1];
		dp = new long[room + 1];
		long ans = Long.MIN_VALUE;
		for (int i = 0; i < portal; i++) {
			Portal p = new Portal();
			p.initialRoom = readInt();
			p.endRoom = readInt();
			p.time = readLong();
			if (array[p.initialRoom] != null) {
				array[p.initialRoom].add(p);
			} else {
				array[p.initialRoom] = new ArrayList<>();
				array[p.initialRoom].add(p);
			}
			if (array[p.endRoom] == null)
				array[p.endRoom] = new ArrayList<>();
			visited[p.initialRoom] = true;
		}
		long max = 0;
		for (int i = 1; i <= room; i++) {
			max = Math.max(max, Recur(i));
		}

		out.print(max);
		out.flush();
		out.close();
	}

	public static long Recur(int i) {
		long max = 0;
		if (!visited[i]) {
			return 0;
		}
		if (found[i]) {
			return dp[i];
		}
		for (int j = 0; j < array[i].size(); j++) {
			max = Math.max(max, Recur(array[i].get(j).endRoom) + array[i].get(j).time);
		}
		dp[i] = max + 2;
		found[i] = true;
		return dp[i];
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

class Portal {
	int initialRoom;
	int endRoom;
	long time;
}