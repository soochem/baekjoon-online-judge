package bfs;
import java.util.*;
import java.io.*;

public class BFS_2665_makingMiro {
	public static int n;
	public static int[][] map, visited;
	public static int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
	// 순서에 따라 답이 달라진다. 어디 방향으로 이동하는지에 따라 루트 길이가 달라지는 듯
	public static boolean[] check;
	
	public static class Pt{
		int x; int y; int cnt;
		Pt(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
	    map = new int[n][n];
	    
	    int[][] mem_odd = new int[n][n];
	    int inf = Integer.MAX_VALUE;
	    for (int[] a : mem_odd) {
	    	Arrays.fill(a, inf);
	    }

	    int[][] mem_cnt = new int[n][n];
	    for (int[] a : mem_cnt) {
	    	Arrays.fill(a, inf);
	    }
	    
	    for (int i=0; i<n; i++) {
	    	String s = br.readLine();
	    	for (int j=0; j<n; j++) {
	    		map[i][j] = (s.charAt(j) - 48 == 0)? 1:0;
	    	}
	    }

	    mem_cnt[0][0] = 1;
	    mem_odd[0][0] = map[0][0];
	    
	    Deque<Integer> qx = new LinkedList<Integer>();
	    Deque<Integer> qy = new LinkedList<Integer>();
	    
	    qx.addFirst(0);
	    qy.addFirst(0);
	    
	    int ans = 0;
//	    int len = 0;
	    
	    while (!qx.isEmpty()) {

	    	int nowy = qy.poll();
	    	int nowx = qx.poll();
	    	
	    	if (nowy == n-1 && nowx == n-1) {
				ans = mem_odd[n-1][n-1];
	    		//len = mem_cnt[n-1][n-1];
                qx = new LinkedList<Integer>();
	    		break;
	    	}
	    	
	    	for (int d = 0; d<4; d++) {
				int newy = nowy+dir[d][0];
				int newx = nowx+dir[d][1];
				
				if (inside(newx, newy)) {
					int cost = map[newy][newx];
					//System.out.println(cost);
					if (mem_odd[newy][newx] > mem_odd[nowy][nowx]+cost)
					{
						mem_odd[newy][newx] = mem_odd[nowy][nowx]+cost;
						mem_cnt[newy][newx] = mem_cnt[nowy][nowx]+1;
						
						if (cost == 0) {
							// 앞으로
							qx.addFirst(newx); qy.addFirst(newy);
						}
						else {
							// 뒤로
						    qx.addLast(newx); qy.addLast(newy);
						}
					}
					
					else if (mem_odd[newy][newx] == mem_odd[nowy][nowx]+cost)
					{
						if (mem_cnt[newy][newx] > mem_cnt[nowy][nowx]+1)
						{
							mem_odd[newy][newx] = mem_odd[nowy][nowx]+cost;
							mem_cnt[newy][newx] = mem_cnt[nowy][nowx]+1;
							
							if (cost == 0) {
								// 앞으로
								qx.addFirst(newx); qy.addFirst(newy);
							}
							else {
								// 뒤로
							    qx.addLast(newx); qy.addLast(newy);
							}
						}
					}
				}
	    	}
    	}

    	System.out.println(ans);
	}
	
	private static boolean inside (int x, int y) {
		if (x >= 0 && y >= 0 && x < n && y < n) return true;
		return false;
	}
	
	private static void print (int[][] ans) {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				System.out.print(ans[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
