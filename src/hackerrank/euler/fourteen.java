package hackerrank.euler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class fourteen {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;
	//static Map<Long, Long> map = new HashMap<>();
	static long[] arr = new long[5000001];

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 3;
		arr[6] = 6;
		arr[7] = 7;
		arr[9] = 9;
		arr[18] = 18;
		arr[19] = 19;
		arr[25] = 25;
		arr[27] = 27;
		arr[54] = 54;
		arr[55] = 55;
		arr[73] = 73;
		arr[97] = 97;
		arr[129] = 129;
		arr[171] = 171;
		arr[231] = 231;
		arr[235] = 235;
		arr[313] = 313;
		arr[327] = 327;
		arr[649] = 649;
		arr[654] = 654;
		arr[655] = 655;
		arr[667] = 667;
		arr[703] = 703;
		arr[871] = 871;
		arr[1161] = 1161;
		arr[2223] = 2223;
		arr[2322] = 2322;
		arr[2323] = 2323;
		arr[2463] = 2463;
		arr[2919] = 2919;
		arr[3711] = 3711;
		arr[6171] = 6171;
		arr[10971] = 10971;
		arr[13255] = 13255;
		arr[17647] = 17647;
		arr[17673] = 17673;
		arr[23529] = 23529;
		arr[26623] = 26623;
		arr[34239] = 34239;
		arr[35497] = 35497;
		arr[35655] = 35655;
		arr[52527] = 52527;
		arr[77031] = 77031;
		arr[106239] = 106239;
		arr[142587] = 142587;
		arr[156159] = 156159;
		arr[216367] = 216367;
		arr[230631] = 230631;
		arr[410011] = 410011;
		arr[511935] = 511935;
		arr[626331] = 626331;
		arr[837799] = 837799;
		arr[1117065] = 1117065;
		arr[1126015] = 1126015;
		arr[1501353] = 1501353;
		arr[1564063] = 1564063;
		arr[1723519] = 1723519;
		arr[2298025] = 2298025;
		arr[3064033] = 3064033;
		arr[3542887] = 3542887;
		arr[3732423] = 3732423;
		for (long i = 1; i <= 5000000; i++) {
			if (arr[(int) i] == 0)
				arr[(int) i] = arr[(int) i - 1];
		}
		int t = readInt();
		while (t-- != 0) {
			long n = readLong();
			builder.append(arr[(int) n] + "\n");
		}
		out.print(builder);
		out.flush();
		out.close();
	}

	// public static void find() {
	// map.put(1L, 1L);
	// long temp = 0;
	// long i = 1;
	// for (; i <= 5000000; i++) {
	// long dummy1 = jump(i);
	// if (dummy1 >= temp) {
	// System.out.println("arr[" + i + "]=" + i + ";");
	// temp = dummy1;
	// }
	// }
	// System.out.println(temp);
	// }
	//
	// public static long jump(long n) {
	// if (n == 1)
	// return 1;
	// if (map.containsKey(n))
	// return map.get(n);
	// long ans = 1;
	// if (n % 2 == 0)
	// ans += jump(n / 2);
	// else {
	// ans += jump(3 * n + 1);
	// }
	// map.put(n, ans);
	//
	// return ans;
	// }

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
