package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Sam_17779 {
	static int N, min, tot;
	
	static void comb(int stx, int sty, int x, int y, int depth,
			int[][] varr, int[][] map) {
		if (x >= N || y >= N || x < 0 || y <0)
			return;
		
		if (depth == 3) {
			int time = go(varr, map);
//			System.out.println("time: "+time);
			min = (time < min)? time : min;
			return;
		}
		
		if (depth == 1) {
			comb(stx, sty, x+1, y+1, depth, varr, map);
			if (x >= N-1) return;
			varr[depth][0] = x+1; varr[depth][1] = y+1;
			comb(stx, sty, stx, sty, depth+1, varr, map);
//			varr[depth][0] = 0; varr[depth][1] = 0;
			return;
		}
		
		if (depth == 2) {
			comb(stx, sty, x+1, y-1, depth, varr, map);
			varr[depth][0] = x+1; varr[depth][1] = y-1;
			comb(stx, sty, x+1, y-1, depth+1, varr, map);
//			varr[depth][0] = 0; varr[depth][1] = 0;
			return;
		}
	}
	
	static int go(int[][] varr, int[][] map) {
		int ans = 0;
		int sum = 0;
		int i, j, k;
		int[] parr = new int[5];

		// 4번째 점
		int x0 = varr[0][0]; 
		int y0 = varr[0][1];
		int x1 = varr[1][0]; 
		int y1 = varr[1][1];
		int x2 = varr[2][0]; 
		int y2 = varr[2][1];
		int x3 = x2+(x1-x0); 
		int y3 = y2+(y1-y0);

//		for (int[] v : varr) System.out.print(v[0]+" "+v[1]+", ");
//		System.out.println();
		
		// 왼위
		k = y0;
		for (i = 0; i < x2; i++) {
			if (i >= x0) k--;
			for (j = 0; j <= k; j++) {
				parr[1] += map[i][j];
			}
		}
		sum += parr[1];
		
		// 오른위
		k = y0;
		for (i = 0; i <= x1; i++) {
			for (j = N-1; j > k; j--) {
				parr[2] += map[i][j];
			}
			if (i >= x0) k++;
		}
		sum += parr[2];
		
		// 왼아래
		k = y2;
		for (i = x2; i < N; i++) {
			for (j = 0; j < k; j++) {
				parr[3] += map[i][j];
			}
			if (i < x3) k++;
		}
		sum += parr[3];
		
		// 오른아래
		k = y1-1;
		for (i = x1+1; i < N; i++) {
			for (j = N-1; j > k; j--) {
				parr[4] += map[i][j];
			}
			if (i <= x3) k--;
		}
		sum += parr[4];
		
		// 가운데
		parr[0] = tot - sum;
		
//		for (int p : parr) System.out.print(p+" ");
//		System.out.println();
		Arrays.sort(parr);
		ans = parr[4] - parr[0];
		
		return ans;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int i, j;
		String[] input;
		int[][] map = new int[20][20];
		int[][] varr = new int[4][2];
		
		tot = 0;
		min = 10000;
		N = Integer.parseInt(br.readLine());
		
		int tmp;
		for (i = 0; i< N; i++) {
			input = br.readLine().split(" ");
			for (j = 0; j < N; j++) {
				tmp = Integer.parseInt(input[j]);
				map[i][j] = tmp;
				tot += map[i][j];
			}
		}
		
		for (i = 0; i< N; i++) {
			for (j = 0; j < N; j++) {
				varr[0][0] = i; varr[0][1] = j;
				comb(i, j, i, j, 1, varr, map);
			}
		}
		
		bw.write(String.valueOf(min));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
