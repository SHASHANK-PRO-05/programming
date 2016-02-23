package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class p10258 {
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
		Scanner scanner = new Scanner(stream);
		int t = Integer.parseInt(scanner.nextLine());
		scanner.nextLine();
		while (t-- != 0) {
			String s;
			boolean[] contestentants = new boolean[101];
			Sortable sorty[] = new Sortable[101];
			for (int i = 0; i < 101; i++) {
				sorty[i] = new Sortable();
				sorty[i].num = i;
			}
			while (scanner.hasNextLine()) {
				s = scanner.nextLine();
				if (s.isEmpty())
					break;
				String[] sArray = s.split(" ");
				int num = Integer.parseInt(sArray[0]);
				int problem = Integer.parseInt(sArray[1]);
				int time = Integer.parseInt(sArray[2]);
				contestentants[num] = true;
				if (sArray[3].compareTo("I") == 0) {
					sorty[num].problemWrong[problem]++;
				} else if (sArray[3].compareTo("C") == 0) {
					if (!sorty[num].solved[problem]) {
						sorty[num].numberSolved++;
						sorty[num].solved[problem] = true;
						sorty[num].penaltytime += time + (sorty[num].problemWrong[problem] * 20);
						sorty[num].problemWrong[problem] = 0;
					}

				}
			}
			Arrays.sort(sorty);
			for (int i = 0; i < 101; i++) {
				if (contestentants[sorty[i].num]) {
					builder.append(sorty[i].num + " " + sorty[i].numberSolved + " " + sorty[i].penaltytime + "\n");
				}
			}
			if (t != 0) {
				builder.append("\n");
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

class Sortable implements Comparable<Sortable> {
	int num;
	int[] problemWrong = new int[10];
	boolean[] solved = new boolean[10];
	int numberSolved;
	int penaltytime;

	@SuppressWarnings("unused")
	@Override
	public int compareTo(Sortable o) {
		if (o == null)
			return -1;
		if (this == null)
			return 1;
		if (this.numberSolved > o.numberSolved) {
			return -1;
		} else if (this.numberSolved < o.numberSolved) {
			return 1;
		} else {
			if (this.penaltytime > o.penaltytime) {
				return 1;
			} else if (this.penaltytime < o.penaltytime) {
				return -1;
			} else {
				if (this.num > o.num)
					return 1;
				else
					return -1;
			}
		}
	}

}
