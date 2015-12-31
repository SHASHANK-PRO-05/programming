package part7;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Vector;

public class Monksbirthdaytreat {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		int n = readInt(), d = readInt();
		Vector<Integer>[] graph = new Vector[n];
		for (int i = 0; i < n; i++)
			graph[i] = new Vector<>();
		for (int i = 0; i < d; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			graph[a].add(b);
		}
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			boolean[] visited = new boolean[n];
			res = Math.min(res, visitNode(i, visited, graph));
		}
		out.println(res);
		out.flush();
		out.close();
	}

	public static int visitNode(int i, boolean[] visited, Vector<Integer>[] graph) {
		int res = 1;
		visited[i] = true;
		for (int j = 0; j < graph[i].size(); j++) {
			int temp = graph[i].get(j);
			if (!visited[temp])
				res += visitNode(temp, visited, graph);
		}
		return res;
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
