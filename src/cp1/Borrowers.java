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

public class Borrowers {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		ArrayList<Book> books = new ArrayList<>();
		while (true) {
			String s = br.readLine();
			if (s.compareTo("END") == 0)
				break;
			Book b = new Book();
			String[] strings = s.split("\" by ");
			b.bookName = strings[0] + "\"";
			b.author = strings[1];
			books.add(b);
		}
		Collections.sort(books);
		String s = br.readLine();
		ArrayList<Book> returnedBooks = new ArrayList<>();
		ArrayList<Book> toBeReturned = new ArrayList<>();
		while (s.compareTo("END") != 0) {
			String[] strings = s.split(" \"");
			String command = strings[0];
			switch (command) {
			case "BORROW":
				String namePlease = "\"" + strings[1];
				for (int i = 0; i < books.size(); i++) {
					if (books.get(i).bookName.compareTo(namePlease) == 0) {
						toBeReturned.add(books.get(i));
						books.remove(i);
						break;
					}
				}
				break;
			case "RETURN":
				String returnedName = "\"" + strings[1];
				for (int i = 0; i < toBeReturned.size(); i++) {
					if (toBeReturned.get(i).bookName.compareTo(returnedName) == 0) {
						returnedBooks.add(toBeReturned.get(i));
						toBeReturned.remove(i);
						break;
					}
				}
				break;
			case "SHELVE":
				Collections.sort(returnedBooks);
				for (int i = 0; i < returnedBooks.size(); i++) {
					int j = 0;
					for (j = 0; j < books.size(); j++) {
						if (books.get(j).compareTo(returnedBooks.get(i)) > 0)
							break;
					}
					if (j == 0) {
						builder.append("Put " + returnedBooks.get(i).bookName + " first\n");
						books.add(j, returnedBooks.get(i));
					} else {
						builder.append(
								"Put " + returnedBooks.get(i).bookName + " after " + books.get(j - 1).bookName + "\n");
						books.add(j, returnedBooks.get(i));
					}
				}
				builder.append("END\n");
				returnedBooks = new ArrayList<>();
				break;
			}
			s = br.readLine();
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

class Book implements Comparable<Book> {
	public String author;
	public String bookName;

	@Override
	public int compareTo(Book obj) {
		if (this.author.compareTo(obj.author) > 0)
			return 1;
		else if (this.author.compareTo(obj.author) < 0)
			return -1;
		else {
			if (this.bookName.compareTo(obj.bookName) > 0)
				return 1;
			else if (this.bookName.compareTo(obj.bookName) < 0)
				return -1;
		}
		return 0;
	}

}