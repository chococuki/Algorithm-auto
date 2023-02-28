import java.io.*;
import java.util.*;

public class Main {
	public static ArrayList<ArrayList<Integer>> board = new ArrayList<>();
	
	public static ArrayList<Integer> bfs(int start) {
		
		ArrayList<Integer> visited = new ArrayList<>();
		ArrayList<Integer> needVisit = new ArrayList<>();
		needVisit.add(start);
		
		while(needVisit.size() > 0) {
			int node = needVisit.remove(0);
			if(!visited.contains(node)) {
				visited.add(node);
				ArrayList<Integer> al = searchKey(node);
				Collections.sort(al);
				needVisit.addAll(al);
			}
		}
		return visited;
	}
	
	public static ArrayList<Integer> searchKey(int key) {
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i=0; i<board.size(); i++) {
			if(board.get(i).get(0).equals(key)) {
				arr.add(board.get(i).get(1));
			} else if(board.get(i).get(1).equals(key)) {
				arr.add(board.get(i).get(0));
			}
		}
		return arr;
	}
	
	public static ArrayList<Integer> dfs(int start) {
		ArrayList<Integer> visited = new ArrayList<>();
		ArrayList<Integer> needVisit = new ArrayList<>();
		
		needVisit.add(start);
		
		while(needVisit.size() > 0) {
			int node = needVisit.remove(needVisit.size()-1);
			
			if(!visited.contains(node)) {
				visited.add(node);
				ArrayList<Integer> al = searchKey(node);
				Collections.sort(al, Collections.reverseOrder());
				
				for(int i=0; i<al.size(); i++) {
					if(needVisit.contains(al.get(i))) {
						needVisit.remove(al.get(i));
					}
				}
				needVisit.addAll(al);
			}	
		}
		return visited;
	}
	
	
	public static void init(int a, int b) {
		ArrayList<Integer> node = new ArrayList<Integer>(2);
		node.add(a);
		node.add(b);
		board.add(node);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		int dot = Integer.parseInt(str[0]);
		int line = Integer.parseInt(str[1]);
		int start = Integer.parseInt(str[2]);
		
		
		for(int i=0; i<line; i++) {
			String[] tmp = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			
			init(a, b);
		}
			
		ArrayList<Integer> arr = dfs(start);
		for(int i : arr) {
			System.out.print(i+" ");
		}		
		System.out.println();
		arr = bfs(start);
		for(int i : arr) {
			System.out.print(i+" ");
		}
	}

}
