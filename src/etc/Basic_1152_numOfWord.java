// 2019. 01. 19
// 백준 1152 단어의 개수

package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Basic_1152_numOfWord {
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int l = 0;
		if (s.equals(" ")) System.out.println(l);
		else {
			l = s.split(" ").length;
			if (s.charAt(0) == ' ') System.out.println(l-1);
			else System.out.println(l);
		}
			
	}
}