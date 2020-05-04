package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SIM_2455 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
 		int max = 0;
		
 		for (int i=0; i<4; i++) {
 			String[] s = br.readLine().split(" ");
			sum -= Integer.parseInt(s[0]);
			sum += Integer.parseInt(s[1]);
			if (max < sum) max = sum;
		}
 		System.out.println(max);
 		
 		// StringTokenizer token = new StringTokenizer(br.readLine());
 		// Integer.parseInt(token.nextToken());
	}
}
