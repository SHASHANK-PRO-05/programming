package searchingAndSorting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class KhaolinTemple {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt();
		MergeSorter[] arr = new MergeSorter[n];
		for (int i = 0; i < n; i++) {
			arr[i] = new MergeSorter();
			arr[i].index = i;
			arr[i].num = readLong();
		}
		merge(arr, 0, n - 1);
		Arrays.sort(arr);
		for (int i = 0; i < n; i++) {
			builder.append(arr[i].num + " ");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	public static void merge(MergeSorter[] arr, int q, int r) {
		if (q < r) {
			int mid = (q + r) / 2;
			merge(arr, q, mid);
			merge(arr, mid + 1, r);
			mergeSort(arr, q, mid, r);
		}
	}

	public static void mergeSort(MergeSorter[] arr, int q, int mid, int r) {
		int len1 = mid - q + 1;
		int len2 = r - mid;
		MergeSorter[] temp1 = new MergeSorter[len1];
		MergeSorter[] temp2 = new MergeSorter[len2];
		for (int i = 0; i < len1; i++) {
			temp1[i] = new MergeSorter();
			temp1[i].index = arr[q + i].index;
			temp1[i].num = arr[q + i].num;
		}
		for (int i = 0; i < len2; i++) {
			temp2[i] = new MergeSorter();
			temp2[i].index = arr[mid + 1 + i].index;
			temp2[i].num = arr[mid + 1 + i].num;
		}
		int i = 0, j = 0;
		while (i < len1 && j < len2) {
			if (temp1[i].num + j >= temp2[j].num) {
				arr[q].num = temp2[j].num;
				arr[q++].index = temp2[j++].index;
			} else {
				arr[q].num = temp1[i].num + j;
				arr[q++].index = temp1[i++].index;
			}
		}
		while (i < len1) {
			arr[q].num = temp1[i].num + j;
			arr[q++].index = temp1[i++].index;
		}
		while (j < len2) {
			arr[q].num = temp2[j].num;
			arr[q++].index = temp2[j++].index;
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

class MergeSorter implements Comparable<MergeSorter> {
	long num;
	int index;

	@Override
	public int compareTo(MergeSorter o) {
		if (this.index > o.index)
			return 1;
		else if (this.index < o.index)
			return -1;
		return 0;
	}

}