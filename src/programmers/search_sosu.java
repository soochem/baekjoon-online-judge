package programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
//import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class search_sosu {
	public static StringBuilder sb = new StringBuilder();
    public HashMap<Integer, Integer> hm = new HashMap<>();
    public static boolean[] visited;
    
	public static String findBig(String s){
        // 스트링으로 만들 수 있는 가장 큰 숫자 찾기
		String[] str = s.split("");
		Arrays.sort(str);
		for(String tmp : str) sb.append(tmp);
//		System.out.println(sb.reverse().toString());
        return sb.reverse().toString();
    }
    
    public static void search(int big){
        int i = 2;
        int j = 1;
        visited = new boolean[big+1];
        visited[1] = true;
        for (i = 2; i <= big; i++){
            if (visited[i]) continue;
            for (j = 2; j*i <= big; j++){
                visited[i*j] = true;
            }
        }
    }
	
    public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "912223";
		s.length();
		HashMap<Integer, Integer> hm = new HashMap<>();
//	    hm.size();

		s = findBig(s);
//		search(Integer.parseInt(s));
		
		// 소수 찾기
		System.out.println(s);
        int max = Integer.parseInt(s);
        boolean[] prime = new boolean[max+1];
        int i, j;
        prime[1] = true;
        for (i = 2; i <= max; i++){
            if (prime[i]) continue;
            for (j = 2; i*j <= max; j++){
                prime[i*j] = true; // 소수가 아님
            }
        }
		
        System.out.println(prime[2]);
        System.out.println(prime[93221]);
        System.out.println(prime[932221]);
	}
}
