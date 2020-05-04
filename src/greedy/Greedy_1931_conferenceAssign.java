package greedy;

// 1931 회의실 배정
// 그리디 알고리즘
// 소트

import java.util.*;
import java.io.*;

public class Greedy_1931_conferenceAssign {
	static int n;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][2];
		int a,b;
		
		for (int i=0; i<n; i++) {
			 st = new StringTokenizer(br.readLine());
			 a = Integer.parseInt(st.nextToken());
			 b = Integer.parseInt(st.nextToken());
			 map[i][0] = a; map[i][1] = b;
		}
		
		// sorting 2d array
		Arrays.sort(map, new Comparator<int[]>() {
			@Override
			public int compare(int[] x1, int[] x2) {
				if (x1[1] < x2[1]) return -1;
				else if (x1[1] == x2[1]) {
					return x1[0] > x2[0] ? 1:-1; 
				};
				return 1;
			}
		});
		
		int right = -1;
		int cnt = 0;
		
		// 그리디 (선형탐색)
		for (int i=0; i<n; i++) {
			//int idx = bisearch(i, n, right);
			if (right > map[i][0]) continue;
			right = map[i][1];
			cnt++;
		}
		
		bw.write(String.valueOf(cnt));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
	
//	private static void print(int[][] map) {
//		for (int i=0; i<map.length; i++) {  // lenght : row
//			System.out.print(map[i][0]+ " " + map[i][1]);
//			System.out.println();
//		}
//	}
}

//11
//1 4
//3 5
//0 6
//5 7
//3 8
//5 9
//6 10
//8 11
//8 12
//2 13
//12 14
