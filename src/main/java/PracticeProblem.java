import java.util.ArrayList;
import java.util.Arrays;

public class PracticeProblem {

	public static void main(String args[]) {

	}


	public static ArrayList<String> perms(String str) {
		ArrayList<String> results = new ArrayList<>();
		if (str == null) return results;

		permsHelper("", str, results);
		return results;
	}

	private static void permsHelper(String prefix, String suffix, ArrayList<String> results) {
		int n = suffix.length();
		if (n == 0) results.add(prefix); //base c

		else {
			//recursive i think
			for (int i = 0; i < n; i++) {
				char currentChar = suffix.charAt(i);

				String newPrefix = prefix + currentChar;

				String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1);

				permsHelper(newPrefix, newSuffix, results);
			}
		}
	}


	public static ArrayList<String> permsUnique(String str) {
		ArrayList<String> result = new ArrayList<>();
		if (str == null) return result;

		if (str.isEmpty()) {
			result.add("");
			return result;
		}

		char[] chars = str.toCharArray();
		Arrays.sort(chars); //sort for duplicates
		boolean[] used = new boolean[chars.length];

		//recurse
		generateUniquePermsSortedHelper("", chars, used, result);
		return result;
	}

	private static void generateUniquePermsSortedHelper(
			String currentPermutation,
			char[] sortedChars,
			boolean[] used,
			ArrayList<String> results)
	{
		//base case when perm length match string length
		if (currentPermutation.length() == sortedChars.length) {
			results.add(currentPermutation);
			return;
		}

		//recurse
		for (int i = 0; i < sortedChars.length; i++) {

			//skip if char alr in path
			if (used[i]) {
				continue;
			}

			//skip duplicates
			if (i > 0 && sortedChars[i] == sortedChars[i - 1] && !used[i - 1]) {
				continue;
			}

			//mark char as used for this path
			used[i] = true;

			//recurse
			generateUniquePermsSortedHelper(currentPermutation + sortedChars[i], sortedChars, used, results);

			//unchoose/backtrack
			used[i] = false;
		}
	}

}
