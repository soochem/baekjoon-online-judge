//package string;
//
//import java.io.*;
//import java.util.ArrayList;
//
//// trie 자료구조
//
//class Node {
//	public int val;
//	public boolean end;
//	public Node[] child;
//	Node(int next) {
//		this.val = next;
//		this.end = false;
//		this.child = new Node[10];
//	}
//}
//
//class Trie {
//	Node root = new Node(-1);
//	Trie() {
//		this.root.child = new Node[10];
//	}
//	
//	boolean search(String s) {
//		int i, j;
//		j =
//		Node now = this.root;
//		
//		for (i = 0; i < s.length(); i++) {
//			j = s.charAt(i) - '0';
//			
//			if (now.end) return true;  // 트라이 끝
////			if (now.child[j] != null) {
//////				if (i == s.length()-1) return true;  // ㅇ
////			}
//			if (now.child[j] == null) {
//				// 끝입니다
////				System.out.println("삽입 : "+now.val+" "+j);
//				insert(now, j);
//			}
//			now = now.child[j];
//		}
//		if (now.child[j] != null) return true;
//		now.end = true;  // 마지막 거에 마지막 표시
//		return false;
//	}
//	
//	void insert(Node n, int next) {
//		n.child[next] = new Node(next);
//	}
//}
//
//public class BOJ_5052 {	
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		StringBuilder sb = new StringBuilder();
//		
//		String s;
//		int tc, i, j, n;
//		Trie t;
//		boolean flag;
//		
//		tc = Integer.parseInt(br.readLine());
//		
//		for (i = 0; i < tc; i++) {
//			t = new Trie();
//			n = Integer.parseInt(br.readLine());
//			flag = false;
//			
//			for (j = 0; j < n; j++) {
//				s = br.readLine();
//				if (t.search(s)) {
//					flag = true;
////					break;
//				}
//			}
//			
//			sb.append((flag)? "NO" : "YES");
//			sb.append("\n");
//		}
//		
//		bw.write(sb.toString());
//		bw.flush();
//		bw.close();
//	}
//}
