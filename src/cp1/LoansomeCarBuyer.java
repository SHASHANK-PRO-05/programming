package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class LoansomeCarBuyer {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();

		while (true) {
			int duration = readInt();
			if (duration < 0)
				break;
			double downPayment = Double.parseDouble(readString());
			double payment = Double.parseDouble(readString());
			int deprecations = readInt();
			double subside = payment / duration;
			double carValue = (payment + downPayment);
			int finalans = 0;
			double[] valPercent = new double[duration + 1];
			for (int i = 0; i < deprecations; i++) {
				valPercent[readInt()] = Double.parseDouble(readString());
			}
			for (int i = 0; i <= duration; i++) {
				if (valPercent[i] == 0) {
					valPercent[i] = valPercent[i - 1];
				}
				if (i != 0)
					payment -= subside;
				carValue = carValue - (carValue * valPercent[i]);
				if (carValue > payment) {
					finalans = i;
					break;
				}
			}
			if (finalans == 1)
				builder.append(finalans + " month\n");
			else
				builder.append(finalans + " months\n");
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
