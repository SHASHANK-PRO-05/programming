package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Vector;

public class p101 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	static int[] posArray;
	static ArrayList<Integer>[] stack;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt();
		posArray = new int[n];
		stack = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			posArray[i] = i;
			stack[i] = new ArrayList<>();
			stack[i].add(i);
		}
		while (n != 0) {
			String com = readString();
			if (com.compareTo("quit") == 0)
				break;
			int a = readInt();
			String command = readString();
			int b = readInt();
			if (posArray[a] != posArray[b])
				if (com.compareTo("move") == 0) {
					move(a, b, command);
				} else {
					pile(a, b, command);
				}
		}
		for (int i = 0; i < n; i++) {
			if (stack[i].size() != 0) {
				builder.append(i + ": ");
			} else {
				builder.append(i + ":");
			}
			for (int j = 0; j < stack[i].size(); j++) {
				if (j != stack[i].size() - 1) {
					builder.append(stack[i].get(j) + " ");
				} else {
					builder.append(stack[i].get(j));
				}
			}
			builder.append("\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void move(int a, int b, String overOnto) {
		int i = 0;
		for (; i < stack[posArray[a]].size(); i++) {
			if (stack[posArray[a]].get(i) == a)
				break;
		}
		int j = 0;
		for (; j < stack[posArray[b]].size(); j++) {
			if (stack[posArray[b]].get(j) == b)
				break;
		}
		j++;
		int posa = posArray[a];
		if (overOnto.compareTo("over") == 0) {
			stack[posArray[a]].remove(i);
			stack[posArray[b]].add(a);
			posArray[a] = posArray[b];
		} else {
			while (j < stack[posArray[b]].size()) {
				int temp = stack[posArray[b]].get(j);
				stack[posArray[b]].remove(j);
				stack[temp].add(0, temp);
				posArray[temp] = temp;
			}
			stack[posArray[a]].remove(i);
			stack[posArray[b]].add(j, a);
			posArray[a] = posArray[b];
		}
		while (i < stack[posa].size()) {
			int temp = stack[posa].get(i);
			stack[posa].remove(i);
			stack[temp].add(0, temp);
			posArray[temp] = temp;
		}
	}

	public static void pile(int a, int b, String command) {
		int i = 0;
		for (; i < stack[posArray[a]].size(); i++) {
			if (stack[posArray[a]].get(i) == a)
				break;
		}
		int j = 0;
		for (; j < stack[posArray[b]].size(); j++) {
			if (stack[posArray[b]].get(j) == b)
				break;
		}
		int posa = posArray[a];
		j++;
		if (command.compareTo("over") == 0)
			j = stack[posArray[b]].size();
		else {
			while (j < stack[posArray[b]].size()) {
				int temp = stack[posArray[b]].get(j);
				stack[posArray[b]].remove(j);
				stack[temp].add(0, temp);
				posArray[temp] = temp;
			}
		}
		while (i < stack[posa].size()) {
			int temp = stack[posa].get(i);
			stack[posa].remove(i);
			if (j > stack[posArray[b]].size())
				stack[posArray[b]].add(temp);
			else
				stack[posArray[b]].add(j, temp);
			j++;
			posArray[temp] = posArray[b];
		}
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
