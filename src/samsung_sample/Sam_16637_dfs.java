package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 반례
//3
//1-5

// MAX 초기화 ***
// 16637 괄호 추가하기

public class Sam_16637_dfs {
	static int n, len, max;
	static String s;
	static int[] iArr, visit;
	static char[] cArr;
	
	static int calc (int x, int y, char c) {		
		switch (c) {
			case '+':
				return x+y;
			case '-':
				return x-y;
			case '*':
				return x*y;
		}
		return 0;	
	}

	static int go () {
		int ans = 0;
		int j = 1;
		int tmp = 0;
		
		if (visit[0] == 1) {
			ans = calc(iArr[0], iArr[1], cArr[0]);
			j++;
		}
		else ans = iArr[0];
		
		for (int i = j; i<len; i++) {
			if (visit[i] == 1) { // 왼괄호
				tmp = calc(iArr[i], iArr[i+1], cArr[i]);
				ans = calc(ans, tmp, cArr[i-1]);
				i++;  // 결론적으로 +2
			}
			else {
				ans = calc(ans, iArr[i], cArr[i-1]);
			}
		}
//		System.out.print(ans+" ");
//		for(int v:visit) System.out.print(v+" ");
//		System.out.println();
		
		return ans;
	}
	
	static void dfs(int depth) {
		if (depth >= len) {
			int ans = go();
			if (ans > max) {		
//				for(int v:visit) System.out.print(v+" ");
//				System.out.println();
				max = ans;
			}
			return;
		}
		
		if (visit[depth] == 0) {
			dfs(depth+1);
			if (depth >= len-1) return;
			visit[depth] = 1;
			visit[depth+1] = 2;
			dfs(depth+2);
			visit[depth] = 0;
			visit[depth+1] = 0;
		}
		
//		for (int i=depth; i<len-1; i++) {
//			if (visit[i] == 0) {
//				visit[i] = 1;  // 왼쪽 괄호
//				visit[i+1] = 2;
//				dfs(i+1);
//				visit[i] = 0;
//				visit[i+1] = 0;
//				return;
//			}
//		}
	}
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		len = n/2+1;
		
		iArr = new int[len];
		cArr = new char[len];
		visit = new int[len];
		
		s = br.readLine();
			
		for (int i=0; i<n; i++) { // 두자리 수 이상은 이렇게 하면 x
			if (i%2 == 0) iArr[i/2] = s.charAt(i) - '0';
			else cArr[i/2] = s.charAt(i);
		}
	
		max = Integer.MIN_VALUE;
		dfs(0);
		bw.write(String.valueOf(max));
		bw.write("\n");
		
		bw.flush();
		bw.close();
	}
}
