package programmers.brute_force;

// 소수 찾기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Lv2_find_sosu {
    public static StringBuilder sb = new StringBuilder();
    public static boolean[] prime; // 소수인지 ? (소수면 FALSE)
    public static int[] map;       // 문자를 이루는 모든 CHAR => INT
    public static int cnt;
    public static int len;

    public static String findBig(){
        // 스트링으로 만들 수 있는 가장 큰 숫자 찾기
		Arrays.sort(map);
		for(int tmp : map) {
		    sb.append(tmp);
        }
//		System.out.println(sb.reverse().toString());
        return sb.reverse().toString();
    }
    
    public static void search(int big){
	    // 소수 찾기
        int i, j;
        prime = new boolean[big+1];
        prime[0] = true;
        prime[1] = true;
        for (i = 2; i <= big; i++){
            if (prime[i]) continue;
            for (j = 2; j*i <= big; j++){
                prime[i*j] = true;
            }
        }
    }

    public static void dfs(int depth, String sample, int[] map, boolean[] visited) {
//        System.out.println(sample);

        // 소수 검사 (찾으면 cnt++)
        if (depth != 0) {
            int intSample = Integer.parseInt(sample);
            if (!prime[intSample]) {
                cnt++;
                System.out.println(intSample);
                prime[intSample] = true;
            }
        }
        // depth가 len을 넘어가면 return
        if (depth == len) {
            return;
        }

	    for (int i=0; i<len; i++) {
	        if (visited[i] | (depth == 0 & map[i] == 0)) {
                continue;
            }
	        visited[i] = true;
	        dfs(depth+1, sample+map[i], map, visited);
	        visited[i] = false;
        }
    }
	
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String numbers = "912223";
//        String numbers = "17";
        String numbers = "00000011";

        len = numbers.length();

        map = new int[len];

        for (int i = 0; i < len; i++) {
            map[i] = numbers.charAt(i) - '0';
        }

        numbers = findBig();
        search(Integer.parseInt(numbers));

        // dfs 모든 경우의 수
        cnt = 0;
        dfs(0, "", map, new boolean[len]);
        System.out.println(cnt);

	}
}
