package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DP_2156 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n+1];
		int[] memo = new int[n+1];
		
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());	
		}
		
		if (n>=0) memo[0] = arr[0];
		if (n>=1) memo[1] = arr[0]+arr[1];
		if (n>=2) memo[2] = Math.max(Math.max(arr[0], arr[1])+arr[2], memo[1]);
		for (int i=3; i<n; i++) {	
			memo[i] = Math.max(memo[i-2]+arr[i], Math.max(memo[i-3]+arr[i-1]+arr[i], memo[i-1]));
			//System.out.println(memo[i]);
		}
		System.out.println(memo[n-1]);
	}
}
