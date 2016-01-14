package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;
import java.util.Vector;

public class PermutationArrays {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		int t = Integer.parseInt(br.readLine());
		while (t-- != 0) {
			int n = 0;
			Vector<Integer> swapElements = new Vector<>();
			Vector<String> elments = new Vector<>();
			String s = br.readLine();
			while (s.isEmpty())
				s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			while (st.hasMoreTokens())
				swapElements.addElement(Integer.parseInt(st.nextToken()));
			s = br.readLine();
			while (s.isEmpty())
				s = br.readLine();
			st = new StringTokenizer(s);
			while (st.hasMoreTokens())
				elments.addElement(st.nextToken());
			String[] temp = new String[swapElements.size() + 1];
			for (int i = 0; i < swapElements.size(); i++) {
				temp[swapElements.get(i)] = elments.get(i);
			}
			for (int i = 1; i <= swapElements.size(); i++)
				builder.append(temp[i] + "\n");
			if (t != 0)
				builder.append("\n");
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
