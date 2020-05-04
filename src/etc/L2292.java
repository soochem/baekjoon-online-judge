package etc;

import java.util.Scanner;

public class L2292 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int sum = 1;
		int i = 1;
		while(sum<n) {
			sum += i*6;
			i++;
		}
		System.out.println(i);
		s.close();
	}
}
