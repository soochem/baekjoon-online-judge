package samsung_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 14888 연산자 끼워넣기

public class Sam_14888_operator {
	static final int MAX = (int)1e9;
	static final int MIN = (-1)*(int)1e9;
	static int n, min, max;
	
	static void dfs (int depth, int ans, int[] left, int[] map) {
		if (depth == n) {
			min = (ans < min)? ans : min;
			max = (ans > max)? ans : max;
			return;
		}
		if (left[0] >= 1) {
			left[0]--;
			dfs(depth+1, ans+map[depth], left, map);
			left[0]++;
		}
		if (left[1] >= 1) {
			left[1]--;
			dfs(depth+1, ans-map[depth], left, map);
			left[1]++;
		}
		if (left[2] >= 1) {
			left[2]--;
			dfs(depth+1, ans*map[depth], left, map);
			left[2]++;
		}
		if (left[3] >= 1) {
//			if (map[depth] == 0) return;
			left[3]--;
//			if (ans <0) System.out.println(ans+" "+map[depth]+" "+ans/map[depth]);
			dfs(depth+1, ans/map[depth], left, map);
			left[3]++;
		}
		return;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] s;
		int i;
		int[] map, left;
		
		// 초기화
		n = Integer.parseInt(br.readLine());
		min = MAX;
		max = MIN;
		map = new int[n];
		left = new int[4];
		
		// 인풋
		s = br.readLine().split(" ");
		for (i=0; i<n; i++) map[i] = Integer.parseInt(s[i]);
		s = br.readLine().split(" ");
		for (i=0; i<4; i++) left[i] = Integer.parseInt(s[i]);
		
		// 부르트 포스
		dfs(1, map[0], left, map);
		
		// 출력
		sb.append(max);
		sb.append("\n");
		sb.append(min);
		System.out.println(sb.toString());
	}
}
