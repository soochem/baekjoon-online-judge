package samsung_sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 15686 치킨배달

public class Sam_15686_2 {
	public static int N, M, ans;
	public static final int MAX = 10000;
	
	public static int calc(boolean[] visit, 
			int chi_num, int[][] chi_arr,
			int hous_num, int[][] hous_arr) {
		int answer = 0;
		int min = MAX;
		int i, j, r, c;
		
		for (i = 0; i < hous_num; i++) {
			r = hous_arr[i][0];
			c = hous_arr[i][1];
			min = MAX;
			for (j = 0; j < chi_num; j++) {
				if (!visit[j]) continue;
				int tmp = Math.abs(chi_arr[j][0] - r) + Math.abs(chi_arr[j][1] - c);
				min = (tmp < min)? tmp: min;
			}
			answer += min;
		}
		
		return answer;
	}
	public static void comb(int depth, int cnt, boolean[] visit, 
			int chi_num, int[][] chi_arr, 
			int hous_num, int[][] hous_arr) {
		if (cnt == M) {
//			for (boolean v: visit) System.out.print(v+" ");
//			System.out.println();
			int tmp = calc(visit, chi_num, chi_arr, hous_num, hous_arr);
			if (tmp < ans) ans = tmp;
		}
		if (cnt > M || depth == chi_num) return;

		comb(depth+1, cnt, visit, chi_num, chi_arr, hous_num, hous_arr);
		visit[depth] = true;
		comb(depth+1, cnt+1, visit, chi_num, chi_arr, hous_num, hous_arr);
		visit[depth] = false;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		int i, j, tmp;
		int chi_num = 0;
		int hous_num = 0;
		ans = MAX;
		
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
//		int[][] map = new int[N][N];
		int[][] hous_arr = new int[2*N+1][2];
		int[][] chi_arr = new int[14][2];  // M <= 13, chi_num < 13
		boolean[] visit = new boolean[14];
		
		// M = 13, n = 4
		for (i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (j = 0; j < N; j++) {
				tmp = Integer.parseInt(input[j]);
//				map[i][j] = tmp;
				if (tmp == 2) {
					chi_arr[chi_num][0] = i;
					chi_arr[chi_num][1] = j;
					chi_num++;
				}
				else if (tmp == 1) {
					hous_arr[hous_num][0] = i;
					hous_arr[hous_num][1] = j;
					hous_num++;
				}
			}
		}
		
		comb(0, 0, visit, chi_num, chi_arr, hous_num, hous_arr);
		
		System.out.println(ans);
	}
}
