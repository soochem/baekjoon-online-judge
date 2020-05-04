// 2019. 01. 19
// 백준 2178 미로찾기

package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class BFS_M2178 {
	public static int n, m;
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		//readLine()이용해 String 형태로 개행문자(엔터)까지 포함해 한줄을 통째로 입력받아 int로 형변환
		//int b = Integer.parseInt(br.readLine()); // int값+엔터
		
		int[][] arr = new int[n][m];
		for (int i=0; i<n; i++) {
			String s2 = br.readLine();
			for (int j=0; j<m; j++) {
				arr[i][j] = (int) (s2.charAt(j) - 48);  // 아스키 숫자는 수+48
				//System.out.println(i+" "+j+" "+arr[i][j]);
			}
		}
		System.out.println(miro(arr));
	}
	private static int miro(int[][] arr) {
		int x,y, next_x, next_y;
		int[][] dir = {{-1,0}, {0,-1}, {1,0}, {0,1}};
		int[][] pass = new int[n][m];
		
		Queue<int[]> q = new LinkedList<int[]>();
		int[] a2 = {0,0};
		q.offer(a2);
		pass[0][0] = 1;
		
		while (!q.isEmpty()) {
			x = q.peek()[0];  // 다음 테스트할 노드는 큐에서 빠져나간 노드
			y = q.peek()[1];
			if (x!=n-1 || y!=m-1) {
				// 경로는 goal에 도달하는 데까지 지나온 노드 수
				//System.out.println(q.peek()[0]+" "+ q.peek()[1]);
				for (int i=0; i<4; i++) {
					next_x = x + dir[i][0];
					next_y = y + dir[i][1];
					//System.out.println(next_i+" "+next_j);
					if (next_x>-1 && next_y>-1 && next_x<n && next_y<m) {
						// 미로 안에 존재, 지나온 적 없는지 확인
						if (pass[next_x][next_y]==0 && arr[next_x][next_y] == 1) {
							int[] item = {next_x, next_y};
							q.offer(item); // 배열
							pass[next_x][next_y] = pass[x][y] + 1;
						}
					}
				}
			}
			q.poll();
		}
		return pass[n-1][m-1];
	}
}
