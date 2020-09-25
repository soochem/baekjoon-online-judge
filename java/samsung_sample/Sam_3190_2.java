package samsung_sample;

import java.io.*;
import java.util.*;

public class Sam_3190_2 {
	public static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		int[][] map = new int [n+1][n+1];
		
		int k = Integer.parseInt(br.readLine());
		while (--k >= 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 2;
		}
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
		
		int tailx=1, taily=1;  // head 필요없?
		int x=1, y=1;
		int time = 0;
		map[1][1] = 1;
		int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
		int cur = 0;
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
			
			int nextx = x + dir[cur][0]; // head
			int nexty = y + dir[cur][1];
			time++;
			x = nextx; y = nexty;
			System.out.println(nextx+" "+nexty+" time"+time);
			
			if (nextx<1 || nextx>n || nexty<1 || nexty>n || map[nextx][nexty] == 1) {
				System.out.println("주금");
				break;
			}
			
			if (map[nextx][nexty] == 0) {
				map[nextx][nexty] = 1;
				map[tailx][taily] = 0;
				tailx += dir[cur][0]; taily += dir[cur][1];
				// 꼬리가 짧아지고 하나 이동
			}
			if (map[nextx][nexty] == 2) {
				map[nextx][nexty] = 1;
			}
			
//			for (int i=0; i<bamx.size(); i++) {
//				map[bamx.get(i) + dir[cur][0]][bamy.get(i) + dir[cur][1]] = 1;
//				bamx.set(i, bamx.get(i) + dir[cur][0]);
//				bamy.set(i, bamy.get(i) + dir[cur][1]);
//			}
			mapview(map);
			
		}
		
		System.out.println(time);
	}
	private static void mapview(int[][] map) {
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
