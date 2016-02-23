package cp1;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class p10194 {
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
		int t = Integer.parseInt(bufferedReader.readLine());
		while (t-- != 0) {
			String s = bufferedReader.readLine();
			int n = Integer.parseInt(bufferedReader.readLine());
			Map<String, Integer> map = new HashMap<>();
			SoccerTeam[] soccerTeams = new SoccerTeam[n];
			for (int i = 0; i < n; i++) {
				String s1 = bufferedReader.readLine();
				map.put(s1, i);
				soccerTeams[i] = new SoccerTeam();
				soccerTeams[i].name = s1;
			}
			int query = Integer.parseInt(bufferedReader.readLine());
			while (query-- != 0) {
				String[] process = bufferedReader.readLine().split("#");
				int i = map.get(process[0]);
				int j = map.get(process[2]);
				String[] goals = process[1].split("@");
				int teamA = Integer.parseInt(goals[0]);
				int teamB = Integer.parseInt(goals[1]);
				if (teamA > teamB) {
					soccerTeams[i].wins++;
					soccerTeams[i].points += 3;
					soccerTeams[j].loss++;
				} else if (teamB > teamA) {
					soccerTeams[j].wins++;
					soccerTeams[j].points += 3;
					soccerTeams[i].loss++;
				} else {
					soccerTeams[i].ties++;
					soccerTeams[j].ties++;
					soccerTeams[j].points += 1;
					soccerTeams[i].points += 1;
				}
				soccerTeams[i].against += teamB;
				soccerTeams[i].scored += teamA;
				soccerTeams[j].against += teamA;
				soccerTeams[j].scored += teamB;
				soccerTeams[i].gamesPlayed++;
				soccerTeams[j].gamesPlayed++;
			}
			Arrays.sort(soccerTeams);
			builder.append(s + "\n");
			for (int i = 0; i < n; i++) {
				builder.append((i + 1) + ") " + soccerTeams[i].name + " " + soccerTeams[i].points + "p, "
						+ soccerTeams[i].gamesPlayed + "g (" + soccerTeams[i].wins + "-" + soccerTeams[i].ties + "-"
						+ soccerTeams[i].loss + "), " + (soccerTeams[i].scored - soccerTeams[i].against) + "gd ("
						+ soccerTeams[i].scored + "-" + soccerTeams[i].against + ")\n");
			}

			builder.append("\n");

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

class SoccerTeam implements Comparable<SoccerTeam> {
	int points;
	int gamesPlayed;
	int wins;
	int ties;
	int loss;
	int scored;
	int against;
	String name;

	@Override
	public int compareTo(SoccerTeam o) {
		if (this.points > o.points) {
			return -1;
		} else if (this.points < o.points) {
			return 1;
		} else {
			if (this.wins > o.wins) {
				return -1;
			} else if (this.wins < o.wins) {
				return 1;
			} else {
				if (this.scored - this.against > o.scored - o.against) {
					return -1;
				} else if (this.scored - this.against < o.scored - o.against) {
					return 1;
				} else {
					if (this.scored > o.scored) {
						return -1;
					} else if (this.scored < o.scored) {
						return 1;
					} else {
						if (this.gamesPlayed > o.gamesPlayed) {
							return 1;
						} else if (this.gamesPlayed < o.gamesPlayed)
							return -1;
						else
							return this.name.compareTo(o.name);
					}
				}
			}
		}
	}
}