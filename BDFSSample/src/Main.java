import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int[][] treeAr=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readInput();
		int depth=getTreeDepthBFS();
		System.out.println("depth using BFS:"+depth);
		depth=getTreeDepthDFS();
		System.out.println("depth using DFS:"+depth);
	}
	
	private static int getTreeDepthDFS() {
		// TODO Auto-generated method stub
		return getDepthOf(0);
	}

	private static int getDepthOf(int root) {
		// TODO Auto-generated method stub
		int depth=Integer.MIN_VALUE;
		
		for (int i=0;i<treeAr[0].length;i++) {
			if (treeAr[root][i]==1) {
				int subDepth=getDepthOf(i);
				if (depth<subDepth)
					depth=subDepth;
			}
		}
		if (depth==Integer.MIN_VALUE)
			return 0;
		return depth+1;
	}

	private static int getTreeDepthBFS() {
		// TODO Auto-generated method stub
		int depth=0;
		
		ArrayList<Integer> current=new ArrayList<>();
		current.add(0);
		ArrayList<Integer> next=new ArrayList<>();
		
		while (!current.isEmpty()) {
			for (Integer node:current) {
				// add all children of node to next list
				for (int i=0;i<treeAr[0].length;i++) {
					if (treeAr[node][i]==1) {
						next.add(i);
					}
				}
			}
			depth++;
			current=next;
			next=new ArrayList<>();
		}
		
		return depth-1;
	}

	private static void readInput() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int lines=sc.nextInt();
		int nodes=sc.nextInt();
		
		treeAr=new int[nodes][nodes];
		for (int i=0;i<nodes;i++) {
			for (int j=0;j<nodes;j++) {
				treeAr[i][j]=0;
			}
		}
		
		for (int i=0;i<lines;i++) {
			int parent=sc.nextInt()-1;
			int child=sc.nextInt()-1;
			treeAr[parent][child]=1;
		}
	}

}
