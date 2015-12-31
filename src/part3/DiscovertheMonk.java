package part3;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class DiscovertheMonk {
	public static void main(String[] args) throws Exception {
		InputReaderSup inputReader = new InputReaderSup(System.in);
		PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
		int n = inputReader.readInt(), q = inputReader.readInt();
		StringBuilder builder = new StringBuilder("");
		Long[] arr = new Long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = inputReader.readLong();
		}
		Arrays.sort(arr);
		while (q-- != 0) {
			if (Arrays.binarySearch(arr, inputReader.readLong()) > -1) {
				builder.append("YES\n");
			} else {
				builder.append("NO\n");
			}
		}
		out.println(builder);
		out.flush();
		out.close();
	}
}

class InputReaderSup {
	InputStream stream;
	byte[] buf = new byte[1024];
	int curChar;
	int numChar;

	public InputReaderSup(InputStream stream) {
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
		return (int) readLong();
	}

	public String readString() throws IOException {
		int c = read();
		while (isSpaceCharacter(c)) {
			c = read();
		}
		StringBuilder builder = new StringBuilder();
		while (!isSpaceCharacter(c)) {
			builder.append(c);
		}
		return builder.toString();
	}

	boolean isSpaceCharacter(int c) {
		return c == ' ' || c == '\t' || c == '\n' || c == '\r' || c == -1;
	}
}
