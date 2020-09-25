// 인구이동
package samsung_sample;

import java.util.*;
import java.io.*;

public class Sam_16234_populMove {
	public static int n, l, r;
	public static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		arr = new int[n+1][n+1];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		int day = 0;
		//int num = -1; int sum = 0;
		boolean change = true;
		
		while (change) {
			change = false; // bfs 탐색을 못하면 변화가 업슴
			day++;
			int[][] pass = new int [n+1][n+1]; // 변화마다 초기화
			int union = 0; // 1부터 연합 국가 수, 변화마다 초기화
			int[] sum = new int [(n+1)*(n+1)];
			int[] num = new int [(n+1)*(n+1)];
			
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					// 모든 방문하지 않은 정점 탐색
					if (pass[i][j] == 0) {
						qx.offer(i); qy.offer(j);
						//memx.offer(i); memy.offer(j);
						//sum = 0; num = 0;
						pass[i][j] = ++union;
						//System.out.println(union);
						
						while(!qx.isEmpty() && !qy.isEmpty()) {
							int curx = qx.poll();
							int cury = qy.poll();
							sum[union] += arr[curx][cury]; ++num[union];
							
							for (int d=0; d<4; d++) {
								int nextx = curx+dir[d][0];
								int nexty = cury+dir[d][1];
								
								if (inside(nextx, nexty)) {
									int dif = arr[nextx][nexty] - arr[curx][cury];
									if (dif < 0) dif *= -1;
									if (pass[nextx][nexty] == 0 && dif >= l && dif <= r) {
										change = true;
										qx.offer(nextx); qy.offer(nexty);
										pass[nextx][nexty] = union;
									}
								}	
							}
						}
					}
				}
			}
			
			for (int x=0; x<n; x++) {
				for (int y=0; y<n; y++) {
					int p = pass[x][y];
					//System.out.println(p+" "+sum[p]+" "+num[p]);
					arr[x][y] = (int) sum[p]/num[p];
				}
			}
			//print(arr);
			//print(pass);
		}
		System.out.println(--day);
		
	}
	private static boolean inside (int x, int y) {
		if (x >= 0 && y>= 0 && x < n && y < n) return true;
		else return false;
	}
//	private static void print(int[][] arr) {
//		for (int i=0; i<n; i++) {
//			for (int j=0; j<n; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
//	}
}
