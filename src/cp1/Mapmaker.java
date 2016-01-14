package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class Mapmaker {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt(), r = readInt();
		Map<String, Integer[]> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			String nameOfArray = readString();
			int base = readInt();
			int sizeByte = readInt();
			int dimensions = readInt();
			Integer[] ci = new Integer[dimensions + 1];
			int[][] dim = new int[dimensions + 1][2];
			for (int j = 1; j <= dimensions; j++) {
				dim[j][0] = readInt();
				dim[j][1] = readInt();
			}
			ci[dimensions] = new Integer(sizeByte);
			int temp = sizeByte * dim[dimensions][0];
			for (int j = dimensions - 1; j >= 1; j--) {
				ci[j] = ci[j + 1] * (dim[j + 1][1] - dim[j + 1][0] + 1);
				temp += (ci[j] * dim[j][0]);
			}
			ci[0] = base - temp;
			map.put(nameOfArray, ci);
		}
		while (r-- != 0) {
			String s = readString();
			Integer[] ci = map.get(s);
			int temp = ci[0];
			builder.append(s + "[");
			for (int i = 1; i < ci.length; i++) {
				int res = readInt();
				temp += ci[i] * res;
				if (i == 1)
					builder.append(res);
				else
					builder.append(", " + res);
			}
			builder.append("] = " + temp + "\n");
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
