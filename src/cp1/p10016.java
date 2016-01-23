package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class p10016 {
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
			int n = readInt();
			int arr[][] = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = readInt();
				}
			}
			int numRing = n % 2 == 0 ? n / 2 : n / 2 + 1;
			for (int i = 0; i < numRing; i++) {
				int m = readInt();
				while (m-- != 0) {
					switch (readInt()) {
					case 1:
						swayUpside(arr, i, n);
						break;
					case 2:
						swapLeftSide(arr, i, n);
						break;
					case 3:
						swapMainDiag(arr, i, n);
						break;
					case 4:
						swapInverseDiag(arr, i, n);
						break;
					}
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (j != n - 1)
						builder.append(arr[i][j] + " ");
					else
						builder.append(arr[i][j] + "\n");
				}
			}
		}

		out.print(builder);
		out.flush();
		out.close();
	}

	public static void swayUpside(int arr[][], int ring, int n) {
		for (int i = ring; i < n - ring; i++) {
			int temp = arr[ring][i];
			arr[ring][i] = arr[n - ring - 1][i];
			arr[n - 1 - ring][i] = temp;
		}
		int i = ring + 1;
		int j = n - 2 - ring;
		while (i < j) {
			int temp = arr[i][ring];
			arr[i][ring] = arr[j][ring];
			arr[j][ring] = temp;
			temp = arr[i][n - 1 - ring];
			arr[i][n - 1 - ring] = arr[j][n - ring - 1];
			arr[j][n - ring - 1] = temp;
			i++;
			j--;
		}
	}

	public static void swapLeftSide(int[][] arr, int ring, int n) {
		for (int i = ring; i < n - ring; i++) {
			int temp = arr[i][ring];
			arr[i][ring] = arr[i][n - 1 - ring];
			arr[i][n - 1 - ring] = temp;
		}
		int i = ring + 1;
		int j = n - 2 - ring;
		while (i < j) {
			int temp = arr[ring][i];
			arr[ring][i] = arr[ring][j];
			arr[ring][j] = temp;
			temp = arr[n - 1 - ring][i];
			arr[n - 1 - ring][i] = arr[n - 1 - ring][j];
			arr[n - ring - 1][j] = temp;
			i++;
			j--;
		}
	}

	public static void swapInverseDiag(int arr[][], int ring, int n) {
		for (int i = ring; i < n - ring; i++) {
			int temp = arr[n - i - 1][n - 1 - ring];
			arr[n - i - 1][n - 1 - ring] = arr[ring][i];
			arr[ring][i] = temp;
			if (i != n - 1 - ring) {
				temp = arr[n - i - 1][ring];
				arr[n - i - 1][ring] = arr[n - 1 - ring][i];
				arr[n - 1 - ring][i] = temp;
			}
		}
	}

	public static void swapMainDiag(int arr[][], int ring, int n) {
		for (int i = ring; i < n - ring; i++) {
			int temp = arr[ring][i];
			arr[ring][i] = arr[i][ring];
			arr[i][ring] = temp;
			if (i != ring) {
				temp = arr[n - ring - 1][i];
				arr[n - 1 - ring][i] = arr[i][n - ring - 1];
				arr[i][n - ring - 1] = temp;
			}
		}
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
