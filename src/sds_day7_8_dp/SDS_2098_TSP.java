package sds_day7_8_dp;

import java.util.*;
import java.io.*;

// int 값 최대로 잡지 말라
// 백트레킹은 앞에거에 뒤에거 넣는거
// 경로 존재하지 않을 땐 0

public class SDS_2098_TSP {
	public static int n;
//	public static int MAX = Integer.MAX_VALUE;
	public static final int MAX = (int) (1e8 + 1);
	public static int min;
	public static int[][] w;
	public static int[][] d;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		w = new int[n+1][n+1];
		d = new int[n+1][1<<n+1];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j=0; j<n; j++)
				if (st.hasMoreTokens())
					w[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for (int[] tmp: d) Arrays.fill(tmp, MAX);

//		d[0][1] = 0;
		tsp(0, 1);  // 1만 방문

//		for (int[] tmp: d) {
//			for (int a:tmp) System.out.print(a+" ");
//			System.out.println();
//		}
		
		// 답은 d[0][1....1]		
//		bw.write(String.valueOf(d[0][(1<<n)-1]));  // 틀림
//		bw.write(String.valueOf(tsp(0, 1)));  // 어째서?
		if (d[0][1] == MAX) 
			bw.write(String.valueOf(0));
		else 
			bw.write(String.valueOf(d[0][1]));
		bw.write("\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int tsp (int i, int state) {
		// d[i][state] : i 번 도시에서 도시 방문 상태 s일 때 남은 도시 순회하고  i번도시로 다시 오는 최소 비용
		
		// 완료 조건 -> 완료시 값 채우면서 돌아옴
		// 모두 방문 & 마지막 원소(1)로 가는 길 있음
		if (state == (1<<n)-1) {  // 111...11 상태
			if (w[i][0] != 0) { // i -> 0으로 가는 경로가 있다
				return w[i][0];
			}
			else return MAX;
			//경로가 존재하지 않음 (비용 무제한) int_max로 하면 더하면서 값이 달라짐
 		}

		//새로 방문 하려는 데가 차있다 (백트레킹일 경우, 끝에서부터 채워야)
		if (d[i][state] != MAX) {
			return d[i][state];
		}
		
		// 방문할 곳 (j) 찾기
		for (int j=0; j<n; j++) { 
			if ((w[i][j] != 0) && (1<<j & state) == 0) { // 아직 방문 안함
//				System.out.println("dfs" + " "+i+" -> "+j);
				int news = (1<<j) | state;
				
				// 끝에서부터 앞으로 채움 !!
				//d[is][state] = tsp(j, news);
				d[i][state] = Math.min(d[i][state], tsp(j, news)+w[i][j]);
//				System.out.println(j+" "+news+ " "+d[j][news]);
			}
		}
		return d[i][state];
	}
}

