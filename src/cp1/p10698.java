package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

class Team implements Comparable<Team> {
	String name;
	int points;
	int gamesPlayed;
	int scored;
	int suffered;

	@Override
	public int compareTo(Team t) {
		if (this.points > t.points) {
			return -1;
		} else if (this.points < t.points) {
			return 1;
		} else {
			if (this.scored - this.suffered > t.scored - t.suffered) {
				return -1;
			} else if (this.scored - this.suffered < t.scored - t.suffered) {
				return 1;
			} else {
				if (this.scored > t.scored) {
					return -1;
				} else if (this.scored < t.scored) {
					return 1;
				} else {
					return this.name.compareTo(t.name);
				}
			}
		}
	}

}

class CompareSort implements Comparator<Team> {

	@Override
	public int compare(Team t1, Team t2) {

		if (t1.points > t2.points) {
			return -1;
		} else if (t1.points < t2.points) {
			return 1;
		} else {
			if (t1.scored - t1.suffered > t2.scored - t2.suffered) {
				return -1;
			} else if (t1.scored - t1.suffered < t2.scored - t2.suffered) {
				return 1;
			} else {
				if (t1.scored > t2.scored) {
					return -1;
				} else if (t1.scored < t2.scored) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

}

public class p10698 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int numbeOfTeams = sc.nextInt();
			int games = sc.nextInt();
			Map<String, Integer> map = new HashMap<>();
			Team[] teams = new Team[numbeOfTeams];
			for (int i = 0; i < numbeOfTeams; i++) {
				String name = sc.next();
				map.put(name, i);
				teams[i] = new Team();
				teams[i].name = name;
			}
			while (games-- != 0) {
				String team1 = sc.next();
				int scored1 = sc.nextInt();
				sc.next();
				int scored2 = sc.nextInt();
				String team2 = sc.next();
				int pos1 = map.get(team1);
				int pos2 = map.get(team2);
				teams[pos2].gamesPlayed = +1;
				teams[pos1].gamesPlayed = +1;
				teams[pos2].scored += scored2;
				teams[pos2].suffered += scored1;
				teams[pos1].scored += scored1;
				teams[pos1].suffered += scored2;
				if (scored1 > scored2) {
					teams[pos1].points = +3;
				} else if (scored1 < scored2) {
					teams[pos2].points = +3;
				} else {
					teams[pos2].points = +1;
					teams[pos1].points = +1;
				}
			}
			Arrays.sort(teams);
			System.out.print("1.               " + teams[0].name + " " + teams[0].points + " " + teams[0].gamesPlayed
					+ " " + teams[0].scored + " " + teams[0].suffered + " " + (teams[0].scored - teams[0].suffered)
					+ " ");
			if (teams[0].gamesPlayed != 0) {
				System.out.print(((double) (teams[0].points) * 100) / ((double) teams[0].gamesPlayed * 3) + "\n");
			}else{
				
			}
			CompareSort cs = new CompareSort();
			for (int i = 1; i < teams.length; i++) {
				if (cs.compare(teams[i], teams[i - 1]) == 0) {
					System.out.print("                 ");
				} else {
					System.out.print((i + 1) + ".               ");
				}
			}
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
