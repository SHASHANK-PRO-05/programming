package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

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

		int t = readInt();
		int g = readInt();
		while (t != 0) {
			Team[] teams = new Team[t];
			Map<String, Integer> map = new HashMap<>();
			for (int i = 0; i < t; i++) {
				String s = readString();
				map.put(s, i);
				teams[i] = new Team();
				teams[i].name = s;
			}
			while (g-- != 0) {
				String name1 = readString();
				int goal1 = readInt();
				readString();
				int goal2 = readInt();
				String name2 = readString();
				int i = map.get(name1);
				int j = map.get(name2);
				if (goal1 > goal2) {
					teams[i].points += 3;
				} else if (goal2 > goal1) {
					teams[i].points += 3;
				} else {
					teams[i].points += 1;
					teams[j].points += 1;
				}
				teams[i].numberGames++;
				teams[j].numberGames++;
				teams[i].forGoals += goal1;
				teams[i].againstGoals += goal2;
				teams[j].forGoals += goal2;
				teams[j].againstGoals += goal1;
			}
			Arrays.sort(teams);
			builder.append("1. ");
			int temp = 15 - teams[0].name.length();
			while (temp-- != 0) {
				builder.append(" ");
			}
			builder.append(
					teams[0].name + "  " + teams[0].points + "  " + teams[0].numberGames + " " + teams[0].forGoals + " "
							+ teams[0].againstGoals + " " + (teams[0].forGoals - teams[0].againstGoals));
			String percentage = "N/A";
			if (teams[0].numberGames != 0) {
				percentage = new String(((new BigDecimal((teams[0].points * 100 / (teams[0].numberGames * 3)) + ""))
						.setScale(3, BigDecimal.ROUND_HALF_UP)).toString());
			}
			builder.append(" " + percentage + "\n");
			for (int i = 1; i < t; i++) {
				if (teams[i].newCompare(teams[i - 1]) == 0) {
					builder.append("   ");
					temp = 15 - teams[i].name.length();
					while (temp-- != 0) {
						builder.append(" ");
					}
					builder.append(teams[i].name +"  " + teams[i].points + "  " + teams[i].numberGames + " " + teams[i].forGoals + " "
							+ teams[i].againstGoals + " " + (teams[i].forGoals - teams[i].againstGoals));
					percentage = "N/A";
					if (teams[i].numberGames != 0) {
						percentage = new String(
								((new BigDecimal((teams[0].points * 100 / (teams[0].numberGames * 3)) + "")).setScale(3,
										BigDecimal.ROUND_HALF_UP)).toString());
					}
					builder.append(" " + percentage + "\n");
				} else {
					builder.append((i + 1) + ". ");
					temp = 15 - teams[i].name.length();
					while (temp-- != 0) {
						builder.append(" ");
					}
					builder.append(teams[1].name +"  " + teams[i].points + "  " + teams[i].numberGames + " " + teams[i].forGoals + " "
							+ teams[i].againstGoals + " " + (teams[i].forGoals - teams[i].againstGoals));
					percentage = "N/A";
					if (teams[i].numberGames != 0) {
						percentage = new String(
								((new BigDecimal((teams[0].points * 100 / (teams[0].numberGames * 3)) + "")).setScale(3,
										BigDecimal.ROUND_HALF_UP)).toString());
					}
					builder.append(" " + percentage + "\n");
				}
			}
			t = readInt();
			g = readInt();
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

class Team implements Comparable<Team> {
	String name;
	int numberGames;
	int forGoals;
	int againstGoals;
	int points;

	@Override
	public int compareTo(Team o) {
		if (this.points > o.points) {
			return -1;
		} else if (this.points < o.points) {
			return 1;
		} else {
			if (this.forGoals - this.againstGoals > o.forGoals - o.againstGoals) {
				return -1;
			} else if (this.forGoals - this.againstGoals < o.forGoals - o.againstGoals) {
				return 1;
			} else {
				if (this.forGoals > o.forGoals) {
					return -1;
				} else if (this.forGoals < o.forGoals) {
					return 1;
				} else {
					return this.name.compareTo(o.name);
				}
			}
		}
	}

	public int newCompare(Team o) {
		if (this.points > o.points) {
			return -1;
		} else if (this.points < o.points) {
			return 1;
		} else {
			if (this.forGoals - this.againstGoals > o.forGoals - o.againstGoals) {
				return -1;
			} else if (this.forGoals - this.againstGoals < o.forGoals - o.againstGoals) {
				return 1;
			} else {
				if (this.forGoals > o.forGoals) {
					return -1;
				} else if (this.forGoals < o.forGoals) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}
}