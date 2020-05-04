package divide_conquer;

import java.io.*;

public class QuadTree_1992 {
	public static int n;
	public static int[][] map;
	
	public static void main (String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		
		for (int i=0; i<n; i++) {
			String s = br.readLine();
			for (int j=0; j<n; j++) {
				map[i][j] = (int)s.charAt(j)-48;
			}
		}
		String s = divide(0, 0, n-1, n-1);
		bw.write(s);
        bw.flush();
         
	}
	
	private static String divide(int x1, int y1, int x2, int y2) {
		// 시작점 열 행/ 끝점 열 행
		System.out.println(y1+" "+x1+" "+y2+" "+x2);
		if (x1 == x2) {  // n == 1
			return String.valueOf(map[y1][x1]);
		}

		if (x2-x1 < 2 || y2-y1 < 2) {
			if (map[y1][x1] == map[y1][x2] && map[y1][x2] == map[y2][x2] && map[y2][x2] == map[y2][x1]) {
				return String.valueOf(map[y1][x1]);
			}
			else {
				return "("+String.valueOf(map[y1][x1])+String.valueOf(map[y1][x2])+String.valueOf(map[y2][x1])+String.valueOf(map[y2][x2])+")";
			}
		}
		
		int len = (x2-x1)/2;
		String a1 = divide(x1, y1, x1+len, x1+len);  // x1->y1
		String a2 = divide(x1+len+1, y1, x2, y1+len);
		String a3 = divide(x1, y1+len+1, x1+len, y2);
		String a4 = divide(x1+len+1, y1+len+1, x2, y2);
		String ans = concat(a1, a2, a3, a4);
		
		System.out.println(ans);
		return ans;
	}
	private static String concat(String a1, String a2, String a3, String a4) {
		if (a1.length() == 1) {
			if (a1.equals(a2) && a2.equals(a3) && a3.equals(a4)) {
				return a1;
			}
		}
		return "("+a1+a2+a3+a4+")";
	}
	
}
