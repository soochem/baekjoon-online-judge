package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BinarySearch_L1920 {
	public static int n;
	public static int[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new int[n];  // 100000으로 정의하면 sort할 떄 안됨
		
		String[] s = br.readLine().split(" ");
		int m = Integer.parseInt(br.readLine());
		String[] s2 = br.readLine().split(" ");
		
		for (int i=0; i<n; i++) list[i] = Integer.parseInt(s[i]);
		Arrays.sort(list);
		
		for (int i=0; i<m; i++) {
			System.out.println(bsearch(0, n-1, Integer.parseInt(s2[i]), 0));
			System.out.println();
		}
		
	}
	
	private static int bsearch(int min, int max, int x, int result) {
		int mid = (int) (min+max)/2;
		System.out.println(min+" "+max+" "+mid+" "+result);
		if (min > max) return result;
		else if (list[mid] == x) {
			result = 1;
			System.out.println(result);
		}
		else if (list[mid] < x) {
			min = mid + 1;
			bsearch(min, max, x, result);
		}
		else if (list[mid] > x) {
			max = mid - 1;
			bsearch(min, max, x, result);
		}
		System.out.println("End");
		System.out.println(result);
		return result; // 마지막에 실행되기 때문에 0이라고 하면 안됨
	}
}
