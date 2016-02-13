package cp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SearchingQuickly {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		Set<String> set = new HashSet<>();
		String s = bufferedReader.readLine();
		while (s.compareTo("::") != 0) {
			set.add(s);
			s = bufferedReader.readLine();
		}
		String sentence = bufferedReader.readLine();
		ArrayList<StringArray> arrayList = new ArrayList<>();
		while (sentence != null && !sentence.isEmpty()) {
			sentence = sentence.toLowerCase();
			String sub[] = sentence.split(" ");
			for (int i = 0; i < sub.length; i++) {
				if (sub[i].compareTo("") != 0 && sub[i].compareTo("  ") != 0 && sub[i] != null
						&& !set.contains(sub[i])) {
					StringArray array = new StringArray();
					array.string = sub[i];
					array.sentence = "";
					for (int j = 0; j < sub.length; j++) {
						if (i == j) {
							if (i != sub.length - 1) {
								array.sentence += sub[i].toUpperCase() + " ";
							} else {
								array.sentence += sub[i].toUpperCase();
							}
						} else {
							if (j != sub.length - 1) {
								array.sentence += sub[j] + " ";
							} else {
								array.sentence += sub[j];
							}
						}
					}
					arrayList.add(array);
				}
			}
			sentence = bufferedReader.readLine();
		}
		Collections.sort(arrayList);
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.println(arrayList.get(i).sentence);
		}
	}
}

class StringArray implements Comparable<StringArray> {
	String string;
	String sentence;

	@Override
	public int compareTo(StringArray o) {
		return this.string.compareTo(o.string);
	}

}
