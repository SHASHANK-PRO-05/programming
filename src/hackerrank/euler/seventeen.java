package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class seventeen {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int t = readInt();
		while (t-- != 0) {
			long n = readLong();
			if (n != 0)
				builder.append(findAns(n + "") + "\n");
			else
				builder.append("Zero\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static String findAns(String number) {
		StringBuilder ans = new StringBuilder("");
		for (int i = number.length(); i > 0; i--) {
			switch (i) {
			case 12:
			case 9:
			case 6:
			case 3:
				if (number.charAt(number.length() - i) != '0')
					ans.append(ones(number.charAt(number.length() - i) - '0') + " Hundred ");
				break;
			case 11:
			case 8:
			case 5:
			case 2:
				if (number.charAt(number.length() - i) != '0')
					if (number.charAt(number.length() - i) == '1')
						ans.append(specialOnes(
								Integer.parseInt(number.substring(number.length() - i, number.length() - i + 2)))
								+ " ");
					else {
						ans.append(tens(number.charAt(number.length() - i) - '0') + " ");
					}
				break;
			case 1:
				if (number.charAt(number.length() - i) != '0')
					if (number.length() - i - 1 >= 0 && number.charAt(number.length() - i - 1) != '1') {
						ans.append(ones(number.charAt(number.length() - i) - '0') + " ");
					} else if (number.length() - i - 1 < 0) {
						ans.append(ones(number.charAt(number.length() - i) - '0') + " ");
					}
				break;
			case 4:
				if (number.charAt(number.length() - i) != '0')
					if (number.length() - i - 1 >= 0 && number.charAt(number.length() - i - 1) != '1') {
						ans.append(ones(number.charAt(number.length() - i) - '0') + " ");
					} else if (number.length() - i - 1 < 0) {
						ans.append(ones(number.charAt(number.length() - i) - '0') + " ");
					}
				ans.append("Thousand ");
				break;
			case 7:
				if (number.charAt(number.length() - i) != '0')
					if (number.length() - i - 1 >= 0 && number.charAt(number.length() - i - 1) != '1') {
						ans.append(ones(number.charAt(number.length() - i) - '0') + " ");
					} else if (number.length() - i - 1 < 0) {
						ans.append(ones(number.charAt(number.length() - i) - '0') + " ");
					}
				ans.append("Million ");
				break;
			case 10:
				if (number.charAt(number.length() - i) != '0')
					if (number.length() - i - 1 >= 0 && number.charAt(number.length() - i - 1) != '1') {
						ans.append(ones(number.charAt(number.length() - i) - '0') + " ");
					} else if (number.length() - i - 1 < 0) {
						ans.append(ones(number.charAt(number.length() - i) - '0') + " ");
					}
				ans.append("Billion ");
				break;

			}
		}
		return ans.toString();
	}

	public static String ones(int n) {
		switch (n) {
		case 1:
			return "One";
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		}
		return "";
	}

	public static String tens(int n) {
		switch (n) {
		case 2:
			return "Twenty";
		case 3:
			return "Thirty";
		case 4:
			return "Forty";
		case 5:
			return "Fifty";
		case 6:
			return "Sixty";
		case 7:
			return "Seventy";
		case 8:
			return "Eighty";
		case 9:
			return "Ninety";
		}
		return "";
	}

	public static String specialOnes(int n) {
		switch (n) {
		case 10:
			return "Ten";
		case 11:
			return "Eleven";
		case 12:
			return "Twelve";
		case 13:
			return "Thirteen";
		case 14:
			return "Fourteen";
		case 15:
			return "Fifteen";
		case 16:
			return "Sixteen";
		case 18:
			return "Eighteen";
		case 19:
			return "Nineteen";
		case 17:
			return "Seventeen";
		}
		return "";
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
