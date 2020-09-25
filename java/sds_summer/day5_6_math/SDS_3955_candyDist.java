package sds_summer.day5_6_math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SDS_3955_candyDist {
	static int k, c;
	static int MAX = (int) 1e9;
//	static ArrayList<Data> pair;
		
	static class Data {
		long s, t, r, q;
		Data(long s, long t, long r, long q){
			this.s = s;
			this.t = t;
			this.r = r;
			this.q = q;
		}
		void copy (Data d2){
			this.s = d2.s;
			this.t = d2.t;
			this.r = d2.r;
			this.q = d2.q;
		}
	}
	
	static int GCD (int a, int b) {
		if (b == 0) return a;
		return GCD(b, a%b);
	}
	
	static long extGCD (int a, int b, int gcd) {
		// ax+by = gcd(a,b)
//		pair.clear(); // s, t, r, q 순  // 너무 큰 수로 만들면 x, 메모리 초과남

		// 초기화
		Data p1 = new Data(1, 0, a, 0);
		Data p2 = new Data(0, 1, b, a/b);
		Data p3 = new Data(0, 0, 0, 0);
//		pair.add(p1);
//		pair.add(p2);
//		pair.add(p3);
//		pair[0][0] = 1; pair[0][1] = 0; r[0] = a;
//		pair[1][0] = 0; pair[1][1] = 1; r[1] = b; q[1] = a/b;
		
//		int i = 1;
		while (true) {
//			i++;
//			System.out.println(i);
//			p1 = pair.get(i-2);
//			p2 = pair.get(i-1);
//			p3 = pair.get(i);

			p3.r = p1.r%p2.r; 
			if (p3.r == 0) break;  // 산술에러

			p3.q = p2.r/p3.r;
			p3.s = p1.s - p2.s * p2.q;
			p3.t = p1.t - p2.t * p2.q;
//			System.out.println(p1.s + " "+p1.t +" "+p1.r);
//			System.out.println(p2.s + " "+p2.t +" "+p2.r);
//			System.out.println(p3.s + " "+p3.t +" "+p3.r);
			p1.copy(p2);  // 그냥 넣으면 p3 바꿀 때 p2도 바뀜
			p2.copy(p3);
		}
		
		long ans = p2.t;
//		System.out.println(ans);
		while (ans < 0) {
			ans += a;  // +a*b-a*b 양변에 더하기
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		
		int testcase = Integer.parseInt(br.readLine());
		String[] s;
//		pair = new ArrayList<>();
		
		while (--testcase >= 0) {
			s = br.readLine().split(" ");
			k = Integer.parseInt(s[0]);
			c = Integer.parseInt(s[1]);
			
			if (GCD(k, c) != 1) bw.write("IMPOSSIBLE");
			else if (c == 1) {
				// -x*a + y = 1
				if (k == MAX) bw.write("IMPOSSIBLE");
				else bw.write(String.valueOf((k+1)));
			}
			else if (k == 1) bw.write(String.valueOf(1));
			// 모든 사탕 봉지수에 맞출 수 있다.
			// -x + y*b = 1
			else {
				// 배수도 아니고 확장 유클리드 계산하면 되는 경우
				// -x*a + y*b = 1
				long ans = extGCD(k, c, 1);
				if (ans > MAX) bw.write("IMPOSSIBLE");
				else bw.write(String.valueOf(ans));
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();	
	}
}
