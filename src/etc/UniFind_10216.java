package etc;

import java.util.*;
import java.io.*;

public class UniFind_10216 {
	static class Top {
		public int x, y, r, group;
		public Top (int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
			this.group = -1;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int testcase = Integer.parseInt(br.readLine());
		while (--testcase >= 0) {
			int n = Integer.parseInt(br.readLine());
			Top[] arr = new Top[n];
			
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				arr[i] = new Top(x,y,r);
	 		}
			
			int group = 0; int max = -1;
			
			for (int i=0; i<n; i++) {
				if (arr[i].group == -1) arr[i].group = group+1;
				
				for (int j=0; j<n; j++) {
					System.out.println(arr[i].x+" "+arr[i].y+"¿Í "+arr[j].x+" "+arr[j].y);
					if (contact(arr[i].x, arr[i].y, arr[i].r, arr[j].x, arr[j].y, arr[j].r)) {
							System.out.println("find group");
							arr[j].group = arr[i].group;
							//if (arr[j].group != -1) arr[i].group = arr[j].group;
							//else arr[j].group = arr[i].group;
					}
				}
				System.out.println();
				group = arr[i].group;
			}
			//System.out.println();
			for (Top t : arr) {
				if (max < t.group) max = t.group;
				System.out.println(t.group);
			}
			System.out.println(max);
		}
	}
	private static boolean contact (int x1, int y1, int r1, int x2, int y2, int r2) {
		//if (y1-r1 <= y2+r2 && y1+r1 >= y2-r2 && x2-r2 <= x1+r1 && x2+r2 >= x1-r1) return true;
		if ((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) <= (r1+r2)*(r1+r2)) return true;
		return false;
	}
}