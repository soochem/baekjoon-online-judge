package sds_summer.day5_6_math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SDS_14565_inverse {
	static long a, n;
//	static int MAX = (int) 1e12;
		
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
	
	static long GCD (long a, long b) {
		if (b == 0) return a;
		return GCD(b, a%b);
	}
	
	static long extGCD (long a, long b, long gcd) {
		// ax+by = gcd(a,b)
		// 초기화
		Data p1 = new Data(1, 0, a, 0);
		Data p2 = new Data(0, 1, b, a/b);
		Data p3 = new Data(0, 0, 0, 0);
		
		while (true) {
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
		while (ans < 0) {
			ans += a;  // +a*b-a*b 양변에 더하기
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		String[] s;

		s = br.readLine().split(" ");
		n = Long.parseLong(s[0]);
		a = Long.parseLong(s[1]);
		
		long sumInv = n-a;
		
		long mulInv = -1;
		if (GCD(n, a) == 1) {
			// n이 1이 되는 경우는 없긴 함, a가 1일떈?
			// 배수도 아니고 확장 유클리드 계산하면 되는 경우
			// -x*a + y*b = 1
			long ans = extGCD(n, a, 1);
			if (ans < n) mulInv = ans;
		}
		
		bw.write(String.valueOf(sumInv)+" ");
		bw.write(String.valueOf(mulInv));
		bw.write("\n");
		bw.flush();
		bw.close();	
	}
}
