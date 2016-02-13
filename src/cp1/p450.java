package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class p450 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		ArrayList<TelephoneExchange> arrayList = new ArrayList<>();
		for (int i = 0; i < t; i++) {
			String department = br.readLine();
			String s = br.readLine();
			while (!s.isEmpty() && s != null) {
				String temp[] = s.split(",");
				TelephoneExchange telephoneExchange = new TelephoneExchange();
				telephoneExchange.campusMaibox = temp[6];
				telephoneExchange.department = department;
				telephoneExchange.designation = temp[0];
				telephoneExchange.firstName = temp[1];
				telephoneExchange.lastName = temp[2];
				telephoneExchange.streetAddress = temp[3];
				telephoneExchange.homePhone = temp[4];
				telephoneExchange.workPhone = temp[5];
				arrayList.add(telephoneExchange);
				s = br.readLine();
			}
		}
		Collections.sort(arrayList);
		for (int i = 0; i < arrayList.size(); i++) {
			builder.append("----------------------------------------\n");
			TelephoneExchange exchange = arrayList.get(i);
			builder.append(exchange.designation + " " + exchange.firstName + " " + exchange.lastName + "\n");
			builder.append(exchange.streetAddress + "\n");
			builder.append("Department: " + exchange.department + "\n");
			builder.append("Home Phone: " + exchange.homePhone + "\n");
			builder.append("Work Phone: " + exchange.workPhone + "\n");
			builder.append("Campus Box: " + exchange.campusMaibox + "\n");
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

class TelephoneExchange implements Comparable<TelephoneExchange> {
	String designation;
	String firstName;
	String lastName;
	String department;
	String streetAddress;
	String homePhone;
	String workPhone;
	String campusMaibox;

	@Override
	public int compareTo(TelephoneExchange o) {
		return this.lastName.compareTo(o.lastName);
	}

}