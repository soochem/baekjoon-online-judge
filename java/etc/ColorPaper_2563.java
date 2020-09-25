package etc;
import java.util.*;
import java.io.*;

public class ColorPaper_2563 {
	public static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int [101][101];
		
		int n = Integer.parseInt(br.readLine());
		while (--n >= 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int i = x; i < x+10; i++) {
				for (int j = 90-y; j < 100-y; j++) {
					map[i][j] = 1;
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if (map[i][j] == 1) ans++;
			}
		}
		System.out.println(ans);
	}
}
