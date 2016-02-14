package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class p790 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		Record[] record = new Record[26];
		while (!input.isEmpty() && input != null) {
			String[] temp = input.split(" ");
			int team = Integer.parseInt(temp[0]);
			if (record[team] == null) {
				record[team] = new Record();
				record[team].index = team;
				if (temp[3].compareTo("Y") != 0) {
					record[team].extraTime[temp[1].charAt(0) - 'A']++;
				} else {
					String[] furtherTemp = temp[2].split(":");
					int time = Integer.parseInt(furtherTemp[0]) * 60 + Integer.parseInt(furtherTemp[1]);
					record[team].problem[temp[1].charAt(0) - 'A'] = true;
					record[team].problemSolved++;
					record[team].time += time;
				}
			} else {
				if (!record[team].problem[temp[1].charAt(0) - 'A']) {
					if (temp[3].compareTo("Y") != 0) {
						record[team].extraTime[temp[1].charAt(0) - 'A']++;
					} else {
						String[] furtherTemp = temp[2].split(":");
						int time = Integer.parseInt(furtherTemp[0]) * 60 + Integer.parseInt(furtherTemp[1]);
						record[team].problem[temp[1].charAt(0) - 'A'] = true;
						record[team].problemSolved++;
						record[team].time += time;
					}
				}
			}
			input = reader.readLine();
		}
		for (int i = 0; i < 26; i++) {
			if (record[i] != null) {
				for (int j = 0; j < 7; j++) {
					if (record[i].problem[j]) {
						record[i].time += record[i].extraTime[j] * 20;
					}
				}
			} else {
				record[i] = new Record();
			}
		}
		Arrays.sort(record);
		builder.append("RANK TEAM PRO/SOLVED TIME\n");
		for (int i = 0; i < 26&&record[i].index != 0; i++) {
			
				if (i > 0 && record[i].problemSolved == record[i - 1].problemSolved
						&& record[i].time == record[i - 1].time) {
					record[i].rank = record[i - 1].rank;
				} else {
					record[i].rank = i + 1;
				}
				builder.append(record[i].rank + " " + record[i].index + " " + record[i].problemSolved + " "
						+ record[i].time + "\n");
			
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

class Record implements Comparable<Record> {
	boolean[] problem = new boolean[7];
	int time = 0;
	int[] extraTime = new int[7];
	int index;
	int problemSolved = 0;
	int rank;

	@Override
	public int compareTo(Record o) {
		if (o.index == 0)
			return -1;
		if (this.index == 0)
			return 1;
		if (this.problemSolved > o.problemSolved) {
			return -1;
		} else if (this.problemSolved < o.problemSolved) {
			return 1;
		} else {
			if (this.time > o.time) {
				return 1;
			} else if (this.time < o.time) {
				return -1;
			} else {
				if (this.index < o.index) {
					return -1;
				} else if (this.index > o.index)
					return 1;
			}
		}
		return 0;
	}

}