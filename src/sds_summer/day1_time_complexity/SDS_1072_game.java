// count
//1000000000 980000000

package sds_summer.day1_time_complexity;

import java.util.*;
import java.io.*;

public class SDS_1072_game {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		long x = Integer.parseInt(st.nextToken());
		long y = Integer.parseInt(st.nextToken());
		
		long z = (long)(y*100/x) +1;  // 달라지는가?
		
		long ans = -1;
		if (z < 100) {
			ans = (long)(z*x-100*y)/(100-z);
			if ((z*x-100*y)%(100-z) != 0) ans+=1;
		}
		//else if (z == 100) ans = x-y;
		
		//System.out.println((y+ans)*100/(x+ans));
		bw.write(String.valueOf(ans)+"\n");
		bw.flush(); bw.close();
	}
}
