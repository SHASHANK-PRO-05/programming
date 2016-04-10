package DataStructures;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class MonkAndSomeQueries {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		// out = new PrintWriter("Text.txt");
		StringBuilder builder = new StringBuilder();

		int t = readInt();
		PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
		PriorityQueue<Long> priorityQueue2 = new PriorityQueue<Long>(10, new Comparator<Long>() {

			@Override
			public int compare(Long o1, Long o2) {
				if (o1 > o2) {
					return -1;
				} else if (o1 < o2) {
					return 1;
				}
				return 0;
			}
		});
		while (t-- != 0) {
			long q = readInt();
			if (q == 1) {
				long temp = readLong();
				priorityQueue.add(temp);
				priorityQueue2.add(temp);
			} else if (q == 2) {
				long del = readLong();
				if (!priorityQueue.contains(del)) {
					builder.append("-1\n");
				} else {
					priorityQueue.remove(del);
					priorityQueue2.remove(del);
				}
			} else if (q == 3) {
				builder.append(priorityQueue2.peek() != null ? priorityQueue2.peek() : "-1" + "\n");
			} else {
				builder.append(priorityQueue.peek() != null ? priorityQueue.peek() : "-1" + "\n");
			}
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

class newComp implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {

		return 0;
	}

}