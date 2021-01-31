import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static Node root=null;
	static HashMap<Integer, Node> nodeMap=new HashMap<>();
	
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
		return getDepthOf(root);
	}

	private static int getDepthOf(Node root) {
		// TODO Auto-generated method stub
		if (root.children.isEmpty()) {
			return 0;
		}
		int depth=Integer.MIN_VALUE;
		for (Node child:root.children) {
			int childDepth=getDepthOf(child);
			if (depth<childDepth)
				depth=childDepth;
		}
		return depth+1;
	}

	private static int getTreeDepthBFS() {
		// TODO Auto-generated method stub
		int depth=0;
		
		ArrayList<Node> current=new ArrayList<>();
		current.add(root);
		ArrayList<Node> next=new ArrayList<>();
		
		while (!current.isEmpty()) {
			for (Node node:current) {
				// add all children of node to next list
				next.addAll(node.children);
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
		
		root=new Node();
		root.id=1;
		nodeMap.put(1, root);
		
		for (int i=0;i<lines;i++) {
			int parent=sc.nextInt();
			int child=sc.nextInt();
			Node parentNode=nodeMap.get(parent);
			
			Node childNode=null;
			if (nodeMap.containsKey(child))
			{
				childNode=nodeMap.get(child);
			} else {
				childNode=new Node();
				childNode.id=child;
				nodeMap.put(child, childNode);
			}
			parentNode.children.add(childNode); 
		}
	}

}

class Node {
	int id;
	ArrayList<Node> children=new ArrayList<>();
}
