package part3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class XsquareAndNumberList {
	/*
	 * These variables will be used to get the input from the dom judge.
	 * 
	 * br=is a static buffered reader st=is a string tokenizer. it uses spaces
	 * to split
	 * 
	 */
	static long maxpow = (long) 1e9 + 7;;
	static BufferedReader br;
	static StringTokenizer st;

	/*
	 * Driver program
	 */
	public static void main(String[] args) throws Exception {
		InputReader inputReader = new InputReader(System.in);
		// br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
		long n = inputReader.readLong(), q = inputReader.readLong();
		long[] integers = new long[(int) n + 1];
		for (int i = 1; i <= n; i++) {
			integers[i] = inputReader.readLong();
		}
		Arrays.sort(integers);
		StringBuilder builder = new StringBuilder("");
		while (q-- != 0) {
			char s = inputReader.readString().charAt(0);
			long k = inputReader.readLong();
			long ans = 0;
			long tosub = pow(2, n);
			long index = bsearchUpper(integers, k);
			if (s == '>') {
				ans = (tosub - pow(2, index) + maxpow) % maxpow;
			} else if (s == '<') {
				long sub = bsearchLower(integers, k);
				ans = pow(2, sub - 1);
			} else {
				long sub = bsearchLower(integers, k);
				ans = (pow(2, index) - pow(2, sub - 1) + maxpow) % maxpow;
			}
			if (ans < 0)
				ans += maxpow;
			builder.append(ans + "\n");
		}
		out.println(builder);
		out.flush();
		out.close();
	}

	/*
	 * build your functions below this
	 */
	public static long pow(long mantisa, long expo) {
		long res = 1;
		while (expo != 0) {
			if ((expo & 1) != 0) {
				res = (res * mantisa) % maxpow;
			}
			expo = expo / 2;
			mantisa = (mantisa * mantisa) % maxpow;
		}
		return res;
	}

	public static int bsearchUpper(long arr[], long key) {
		int lo = 1, hi = arr.length - 1;
		int mid;
		int res = 0;
		while (lo <= hi) {
			mid = (hi + lo) / 2;
			if (arr[mid] <= key) {
				res = mid;
				lo = mid + 1;
			} else if (key < arr[mid]) {
				hi = mid - 1;
			}
		}
		return res;
	}

	public static int bsearchLower(long arr[], long key) {
		int lo = 1, hi = arr.length - 1;
		int mid;
		int res = arr.length;
		while (lo <= hi) {
			mid = (hi + lo) / 2;
			if (arr[mid] < key) {
				lo = mid + 1;
			} else if (key <= arr[mid]) {
				res = mid;
				hi = mid - 1;
			}
		}
		return res;
	}

}

class InputReader {
	InputStream stream;
	byte[] buf = new byte[1024];
	int curChar;
	int numChar;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() throws IOException {
		if (curChar >= numChar) {
			curChar = 0;
			numChar = stream.read(buf);
			if (numChar <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public long readLong() throws IOException {
		int c = read();
		while (isSpaceCharacter(c)) {
			c = read();
			if (c == -1) {
				throw new IOException();
			}
		}
		boolean negative = false;
		if (c == '-') {
			negative = true;
			c = read();
		}
		long res = 0;
		while (!isSpaceCharacter(c)) {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += (c - '0');
			c = read();
		}
		return negative ? (-res) : res;
	}

	public int readInt() throws IOException {
		return (int) readInt();
	}

	public String readString() throws IOException {
		int c = read();
		while (isSpaceCharacter(c)) {
			c = read();
		}
		StringBuilder stringBuilder = new StringBuilder("");
		while (!isSpaceCharacter((char) c)) {
			stringBuilder.append((char) c);
			c = read();
		}
		return stringBuilder.toString();
	}

	boolean isSpaceCharacter(int c) {
		return c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == -1;
	}
}
