package kakao_sample;

import java.io.*;
import java.util.*;

public class Kakao_15953 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());

		int[] prz = {0, 500, 300, 200, 50, 30, 10};
		while (--test >= 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println((check_1st(a, prz)+check_2nd(b))*10000);
		}
		
	}
	private static int check_1st (int a, int[] prz) {
		int i = 1;
		int num = 1;
		while (i <= 6) {
			if (a >= num && a <= num+i-1) {
				return prz[i];
			}
			num += i;
			i++;
		}
		return 0;
	}
	private static int check_2nd(int a) {
		int i = 1;
		while (i < 17) {
			if (a >= i && a <= i*2-1) {
				return 512/i;
			}
			i *= 2;
		}
		return 0;
	}
}
