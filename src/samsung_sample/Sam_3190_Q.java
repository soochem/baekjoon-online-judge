package samsung_sample;

import java.io.*;
import java.util.*;

public class Sam_3190_Q {
	public static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		int[][] map = new int [n+1][n+1];
		Queue<Integer> bamx = new LinkedList<Integer>();
		Queue<Integer> bamy = new LinkedList<Integer>();
		
		int k = Integer.parseInt(br.readLine());
		while (--k >= 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 2;
		}
		
		// 라운드로빈
//		if (a=='L') {
//			dir = (dir+3)%4;
//		}
		Queue<Integer> qtime = new LinkedList<Integer>();
		Queue<Integer> qdir = new LinkedList<Integer>();
		
		int l = Integer.parseInt(br.readLine());
		while (--l >= 0) {
			st = new StringTokenizer(br.readLine());
			qtime.offer(Integer.parseInt(st.nextToken()));
			char b = st.nextToken().charAt(0);
			if (b == 'D') qdir.offer(1); // 오른쪽
			else qdir.offer(0);
		}
		
		bamx.add(1); bamy.add(1);
		int nextx = 1, nexty = 1;
		int time = 0;
		int cur = 0; // 방향
		map[1][1] = 1;
		int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
		// 행-열 오른쪽 0,1 -> 1,0 -> 0,-1 -> -1,0 -> 0,1
		while (true) {
			// 방향바꾸기
			if (!qtime.isEmpty()) {
				if (time == qtime.peek()) {
					qtime.poll();
					int d = qdir.poll();
					if (d == 1) {
						cur += 1;
						if (cur == 4) cur = 0;
					}
					else {
						cur -= 1;
						if (cur == -1) cur = 3;
					}
				}
			}
			
			nextx += dir[cur][0];
			nexty += dir[cur][1];
			time++;
			//System.out.println(nextx+" "+nexty+" time"+time);
			
			if (nextx<1 || nextx>n || nexty<1 || nexty>n || map[nextx][nexty] == 1) {
				//System.out.println("주금");
				break;
			}
			
			if (map[nextx][nexty] == 0) {
				int curx = bamx.poll();
				int cury = bamy.poll();
				map[curx][cury] = 0;
			}
			
			bamx.add(nextx);
			bamy.add(nexty);
			map[nextx][nexty] = 1;
			
			//mapview(map);
		}
		
		System.out.println(time);
	}
//	private static void mapview(int[][] map) {
//		for (int i=1; i<=n; i++) {
//			for (int j=1; j<=n; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}
