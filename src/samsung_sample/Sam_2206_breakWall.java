// 삼성
// 시험감독
package samsung_sample;
import java.io.*;

public class Sam_2206_breakWall {
	static int n, m, min;
	static int[][] map;
	static final int MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] s = br.readLine().split(" ");

		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		min = MAX;
		map = new int[n][m];
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		sb.append(min);
		System.out.println(sb.toString());
	}
}
