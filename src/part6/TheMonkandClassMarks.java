package part6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class TheMonkandClassMarks {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		PrintWriter printWriter = new PrintWriter("Dumm.txt");
		StringBuilder builder = new StringBuilder();
		int t = readInt();
		Vector<Marks> vector = new Vector<>();
		while (t-- != 0) {
			Marks mark = new Marks();
			mark.name = readString();
			mark.marks = readInt();
			vector.add(mark);
		}
		Collections.sort(vector);
		Iterator<Marks> iterator = vector.iterator();
		while (iterator.hasNext()) {
			Marks mark = iterator.next();
			builder.append(mark.name + " " + mark.marks + "\n");
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

class Marks implements Comparable<Marks> {
	public int marks;
	public String name;

	@Override
	public int compareTo(Marks o) {
		if (this.marks > o.marks)
			return -1;
		else if (this.marks < o.marks)
			return 1;
		else {
			return this.name.compareTo(o.name);
		}
	}

}
