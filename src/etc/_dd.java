package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class _dd {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sb.append('a');
		System.out.println(sb.toString());
		int n = Integer.parseInt(br.readLine());
		
		int ans = 0;
		for (int i=1; i<=n; i++) {
			if (n%i == 0) ans += i;
		}
		
		System.out.println(ans);
	}
}
