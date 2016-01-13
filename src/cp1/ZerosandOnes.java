package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class ZerosandOnes {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int t = 0;
		StringTokenizer st;
		String s = bufferedReader.readLine();
		while (s != null && !s.isEmpty()) {
			t++;
			long n = s.length();
			int q = Integer.parseInt(bufferedReader.readLine());
			int[][] arr = new int[(int) n][2];
			arr[0][s.charAt(0) - '0'] = 1;
			for (int i = 1; i < n; i++) {
				arr[i][0] = arr[i - 1][0];
				arr[i][1] = arr[i - 1][1];
				if (s.charAt(i) == '1')
					arr[i][1]++;
				else
					arr[i][0]++;
			}
			builder.append("Case " + t + ":\n");
			while (q-- != 0) {
				st = new StringTokenizer(bufferedReader.readLine());
				long i = Long.parseLong(st.nextToken());
				long j = Long.parseLong(st.nextToken());
				if (i > j) {
					long temp = i;
					i = j;
					j = temp;
				}

				long temp1 = arr[(int) j][1] - (i > 0 ? arr[(int) i - 1][1] : 0);
				long temp2 = arr[(int) j][0] - (i > 0 ? arr[(int) i - 1][0] : 0);
				if (temp1 == 0 || temp2 == 0)
					builder.append("Yes\n");
				else
					builder.append("No\n");

			}
			s = bufferedReader.readLine();
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
