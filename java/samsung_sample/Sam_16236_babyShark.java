// 아기상어
package samsung_sample;

import java.util.*;
import java.io.*;
public class Sam_16236_babyShark {
	public static int n, time, size;
	public static int[][] map;
	
	public static class shark{
		public int x, y, dis;
		public shark(int x, int y, int dis) {
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}
	static class newSort implements Comparator<shark> {
		public int compare(shark i1, shark i2) {
	        if (i1.x < i2.x) {
	            return -1;
	        } else if (i1.x == i2.x) {
	            if (i1.y < i2.y) {
	                return -1;
	            } else if (i1.y == i2.y) {
	                return 0;
	            }
	            return 1;
	        }
	        return 1;
	    }
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		size = 2; time = 0;
		int fish = 0;
		
		map = new int[n+1][n+1];
		int x = 0; int y = 0;
		for (int i=0; i<n; i++) {
			String[] sar = br.readLine().trim().split(" ");
			for (int j=0; j<n; j++) {
				int num = Integer.parseInt(sar[j]);
				if (num == 9) {
					x = i; y = j;
					num = 0;
				}
				else if (num != 0) fish++;
				map[i][j] = num;
			}
		}

		int[][] pass = new int [n][n];
		int cnt = 0;
		while (true) {
			shark s = find(x, y);
			if (s.x == -1) break;
			
			//int prey = map[s.x][s.y];
			time += s.dis;
			map[s.x][s.y] = 0;
			cnt++;
			if (cnt == size) {
				size++;
				cnt = 0;
			}
			if (fish == cnt) break;
			pass[s.x][s.y] = time;
			x = s.x; y = s.y;
			//System.out.println(s.dis+" "+time);
		}
		System.out.println(time);
		//print(pass);
	}
	private static shark find (int x, int y) {
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		int[][] dir = {{-1,0}, {0,-1}, {0,1}, {1,0}};
		int[][] pass = new int [n][n];
		//int resx = -1; int resy = -1;
		int dis = 0;
		int res = 0;
		//shark[] arr = new shark[n*n];
		ArrayList<shark> arr = new ArrayList<shark>();
		qx.offer(x); qy.offer(y);
		
		while (!qx.isEmpty() && !qy.isEmpty()) {
			if (res != 0) break;
			dis++;
			int qsize = qx.size();
			
			while (--qsize >= 0) {
				int curx = qx.poll(); int cury = qy.poll();
				//System.out.println(dis+" "+curx+" "+cury);
				if (map[curx][cury] < size && map[curx][cury] != 0) {
					arr.add(new shark(curx, cury, dis-1));
					res++;
					//System.out.println("차즘 "+" "+resx+" "+resy);
					//break; // next에서 break하면 depth가 잘못 얻어짐
				}
				
				for (int d=0; d<4; d++) {
					int nextx = curx + dir[d][0]; 
					int nexty = cury + dir[d][1];
					//System.out.println("next" +nextx+" "+nexty);
					if (inside(nextx, nexty)) {
						if (size >= map[nextx][nexty] && pass[nextx][nexty] == 0) {
							qx.offer(nextx); qy.offer(nexty);
							pass[nextx][nexty] = dis; // 확인
						}
					}
				}
			}
		}
		//Arrays.sort(arr);
		if (res > 0) arr.sort(new newSort());
		else return new shark(-1,-1,-1);
		return arr.get(0);
	}
	private static boolean inside (int x, int y) {
		if (x >= 0 && y>= 0 && x < n && y < n) return true;
		else return false;
	}
//	private static void print (int[][] arr) {
//		for (int i=0; i<n; i++) {
//			for (int j=0; j<n; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
//	}
}
