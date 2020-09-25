// »ï¼º
// ½ÃÇè°¨µ¶
package samsung_sample;
import java.util.*;
import java.io.*;

public class Sam_13458 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n, b, c;
		long ans = 0;
		
		n = Integer.parseInt(br.readLine());
		int[] a = new int[n+1];

		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		ans += n;
		for (int i=0; i<n; i++) {
			a[i] -= b;
			//ans++;
			if (a[i] <= 0) {
				continue; // break ¾ÈµÅ
			}
			ans += a[i]/c;
			if (a[i]%c != 0) ans++;
//			while (true) {
//				ans++;
//				a[i] -= c;
//				if (a[i] <= 0) break;
//			}
			//System.out.println(a[i]+" "+ans);
		}
		
		System.out.println(ans);
	}
}
