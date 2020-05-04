package etc;
import java.util.Scanner;

public class Tree_1991 {
	public static class Node{
		public Node l,r; // 왼, 오 자식
		public char c;
		public Node (char c) {
			this.c = c; // 데이터
		}
	}
	public static void preorder(Node n) {
		System.out.print(n.c);
		if (n.l != null) preorder(n.l);
		if (n.r != null) preorder(n.r);
	}
	public static void inorder(Node n) {
		if (n.l != null) inorder(n.l);
		System.out.print(n.c);
		if (n.r != null) inorder(n.r);
	}
	public static void postorder(Node n) {
		if (n.l != null) postorder(n.l);
		if (n.r != null) postorder(n.r);
		System.out.print(n.c);
	}
	public static void treep(int n, Node[] tree) {
		for (int i=0; i<n; i++) {
			System.out.print(tree[i].c);
			if (tree[i].l != null) System.out.printf(" %c", tree[i].l.c);
			if (tree[i].r != null) System.out.printf(" %c", tree[i].r.c);
			System.out.println();
		}
	}
	public static void main (String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		Node[] tree = new Node[n];
		
		// 트리 만들기
		for (int i=0; i<n; i++) {
			tree[i] = new Node((char) ('A' + i));
		}
		
		for (int i=0; i<n; i++){
			char par = s.next().charAt(0);
			char l = s.next().charAt(0);
			char r = s.next().charAt(0);
			if (l != '.') {
				// 글자 - A = 저장된 순서 (a,b,c,d... 순 입력)
				tree[par-'A'].l = tree[l-'A'];
			}if (r != '.') {
				tree[par-'A'].r = tree[r-'A'];
			}
		}
		treep(n, tree);
		
		// 전중후위 순회
		preorder(tree[0]);
		System.out.println();
		inorder(tree[0]);
		System.out.println();
		postorder(tree[0]);
		
		s.close();
	}

}
