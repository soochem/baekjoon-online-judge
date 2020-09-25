package samsung_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sam_15685_dragonCurve {
	static int n;
	static ArrayList<Integer> map;
	static boolean[][] visit;
	
	static void spin (int depth, int g) {
		if (depth == g) {
//			for (int i=0; i<map.size(); i++) {
//				System.out.print(map.get(i)+" ");
//			}System.out.println();
			return;
		}
		
		int size = map.size();
		for (int i=size-1; i>=0; i--) {
			map.add((map.get(i)+1)%4);
		}
		spin(depth+1, g);  // 1세대 일때 depth=1 대입
	}
	
	static void draw (int x, int y) {
		visit[y][x] = true;
		
		for (int i=0; i<map.size(); i++) {
			int dir = map.get(i);
			switch (dir) {
				case 0: 
					x += 1;
					break;
				case 1:
					y -= 1;
					break;
				case 2:
					x -= 1;
					break;
				case 3:
					y += 1;
					break;
			}
			visit[y][x] = true;
		}
	}
	
	static int count () {
		int ans = 0;
		
		for (int i=0; i<=99; i++) {
			for (int j=0; j<=99; j++) {
				if (visit[i][j]) {
					if (visit[i+1][j] && visit[i][j+1] && visit[i+1][j+1]) {
						ans++;
					}
				}
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int x, y, d, g;
		
		n = Integer.parseInt(br.readLine());
		
		map = new ArrayList<>();
		visit = new boolean[101][101];
		
		for (int i=0; i<n; i++) {

			String[] s = br.readLine().split(" ");

			x = Integer.parseInt(s[0]);
			y = Integer.parseInt(s[1]);
			d = Integer.parseInt(s[2]);
			g = Integer.parseInt(s[3]);
			
			map.add(d);
			spin(0, g);
			draw(x, y);
			
			map.clear();
		}

		int ans = count();
		sb.append(ans);
		System.out.println(sb.toString());
	}
}
