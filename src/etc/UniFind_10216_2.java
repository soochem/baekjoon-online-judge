// circle group
// union find
package etc;

import java.util.*;
import java.io.*;

public class UniFind_10216_2 {
	static class Top {
		public int x, y, r;
		public ArrayList<Integer> adj;
		public Top (int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
			this.adj = new ArrayList<Integer>();
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testcase = Integer.parseInt(br.readLine());
		while (--testcase >= 0) {
			int n = Integer.parseInt(br.readLine());
			Top[] arr = new Top[n];
			ArrayList<ArrayList<Integer>> set = new ArrayList<>();
			for (int i=0; i<n; i++) {
				set.add(new ArrayList<>());
			}
			
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				arr[i] = new Top(x,y,r);
	 		}
			
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					if (contact(arr[i].x, arr[i].y, arr[i].r, arr[j].x, arr[j].y, arr[j].r)) {
						arr[i].adj.add(j); arr[j].adj.add(i);
					}
				}
			}
			
			Queue<Integer> q = new LinkedList<>();
			int[] v = new int[n+1];
			int cnt = 0;
			for (int i=0; i<n; i++) {
				if (v[i] == 1) continue;
				
				cnt++;
				q.offer(i);
				v[i] = 1;
				
				while (!q.isEmpty()) {
					int a = q.poll();
					Top cur = arr[a];
					for (int item : cur.adj) {
						if (v[item] == 0) {
							v[item] = 1;
							q.offer(item);
						}
					}
				}
			}
			System.out.println(cnt);
		}
	}
	private static boolean contact (int x1, int y1, int r1, int x2, int y2, int r2) {
		if ((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) <= (r1+r2)*(r1+r2)) return true;
		return false;
	}
}