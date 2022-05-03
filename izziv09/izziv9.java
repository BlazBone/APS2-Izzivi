import java.util.ArrayList;
import java.util.Scanner;

class Node{	
	int id;
	//marks for the algorithm
	//------------------------------------
	boolean marked = false;
	Edge augmEdge = null; //the edge over which we brought the flow increase
	int incFlow = -1; //-1 means a potentially infinite flow
	boolean neg = false; //true if the node is a negative node
	int fromNeg = -1; //the id of the node from which we came to this node
	//------------------------------------
	ArrayList<Edge> inEdges;
	ArrayList<Edge> outEdges;
	
	public Node(int i) {
		id = i;
		inEdges = new ArrayList<Edge>();
		outEdges = new ArrayList<Edge>();
	}
}

class Edge{
	int startID; 
	int endID;
	int capacity; 
	int currFlow;
	
	public Edge(int fromNode, int toNode, int capacity2) {
		startID = fromNode;
		endID = toNode;
		capacity = capacity2;
		currFlow = 0;
	}
}

class Network{
	Node[] nodes;
	
	/**
	 * Create a new network with n nodes (0..n-1).
	 * @param n the size of the network.
	 */
	public Network(int n){
		nodes = new Node[n];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i]= new Node(i);
		}
	}
	/**
	 * Add a connection to the network, with all the corresponding in and out edges.
	 * @param fromNode
	 * @param toNode
	 * @param capacity
	 */
	public void addConnection(int fromNode, int toNode, int capacity){
		Edge e = new Edge(fromNode, toNode, capacity);
		nodes[fromNode].outEdges.add(e);
		nodes[toNode].inEdges.add(e);
	}

	/**
	 * Reset all the marks of the algorithm, before the start of a new iteration.
	 */
	public void resetMarks(){
		for (int i = 0; i < nodes.length; i++) {
			nodes[i].marked = false;
			nodes[i].augmEdge = null;
			nodes[i].incFlow = -1;
			nodes[i].neg = false;
			nodes[i].fromNeg = -1;
		}
	}
}

/**
 * izziv9
 */
public class izziv9 {


	public static Node nextNode(boolean[] visited, Node[] nodes){
		for (int i = 0; i < nodes.length; i++) {
			if(!visited[i] && nodes[i].marked){
				visited[i] = true;
				return nodes[i];
			}
		}
		// System.out.println("No more nodes to visit");
		return null;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Network net = new Network(n);
		while (sc.hasNext()) {
	
			int prvo = sc.nextInt();
			int drugo = sc.nextInt();
			int cap = sc.nextInt();
			net.addConnection(prvo, drugo, cap);
		}
		sc.close();

		Node temp = null;
		temp = net.nodes[0];
		
		while(true){
			boolean[] visited = new boolean[n];
			// se sprehodimo in najdemo nenasiceno pot
			// ko pridemo do ponora potem popravljamo pot za nzaj
	
			if (temp == null) {
				break;
			}
			while(temp != null){
				net.nodes[0].marked = true;
				for (int i = 0; i < temp.outEdges.size(); i++) {
					Edge e = temp.outEdges.get(i);
					if(!net.nodes[e.endID].marked && e.currFlow < e.capacity){
			
						net.nodes[e.endID].marked = true;
						net.nodes[e.endID].augmEdge = e;
						net.nodes[e.endID].incFlow = Math.min(e.capacity - e.currFlow, net.nodes[temp.id].incFlow == -1 ? Integer.MAX_VALUE : net.nodes[temp.id].incFlow);
					}
				}
				for (int i = 0; i < temp.inEdges.size(); i++) {
					Edge e = temp.inEdges.get(i);
					// System.out.println("Negative: "+ e.startID + " " + e.endID  + " " + e.currFlow);
					if (!net.nodes[e.startID].marked && e.currFlow != 0) {
				

						net.nodes[e.startID].marked = true;
						net.nodes[e.startID].augmEdge = e;
						net.nodes[e.startID].neg = true;
						net.nodes[e.startID].fromNeg = e.endID;
						net.nodes[e.startID].incFlow = Math.min(e.currFlow, 
								net.nodes[temp.id].incFlow == -1 ? Integer.MAX_VALUE : net.nodes[temp.id].incFlow);
					}
				}
				visited[temp.id] = true;
				// System.out.println(temp.id);
				temp = nextNode(visited,net.nodes);
				if (temp == null) {
					break;
				}
				if (temp.id == n - 1) {
					Edge ee = temp.augmEdge;
					int start = ee.startID;
					int end = ee.endID;
					int flow = net.nodes[end].incFlow;
					// System.out.println("Flow: " + flow);
					ArrayList<Integer> pot = new ArrayList<Integer>();
					System.out.printf("%d:",flow);
					while (ee != null) {
						// ee.currFlow += flow;
						// System.out.println("Sprehajamo po poti " + ee.startID + " " + ee.endID + " incflow " + flow + net.nodes[ee.startID].neg);
						if (net.nodes[ee.startID].neg && net.nodes[ee.startID].fromNeg == ee.endID) {
							ee.currFlow -= flow;
							pot.add(ee.startID);
							System.out.printf("%2d-", ee.startID);

							// neg povezava odstejmo
							// System.out.println("-----------------------------------------------------------------------------");
							// for (int i = 1; i < 6; i++) {
							// 	ee = net.nodes[i].augmEdge;
							// 	System.out.println(ee.startID + " " + ee.endID + " " + ee.currFlow);
							// }
							// System.out.println(ee == net.nodes[ee.endID].augmEdge);
							Edge eee = net.nodes[ee.endID].augmEdge;
							// System.out.println(ee.startID + " " + ee.endID );
							// System.out.println(eee.startID + " " + eee.endID );
							ee = net.nodes[ee.startID].augmEdge;
							ee = eee;
							// net.nodes[ee.endID].neg = false;
						}else {
							//poz povezava normalno
							System.out.printf("%2d+ ", ee.endID);

							pot.add(ee.endID);
							ee.currFlow += flow;
							ee = net.nodes[ee.startID].augmEdge;
						}
							
						
					}
					System.out.printf(" 0\n" );

					// pot.add(0);
					// for (int i = 0; i < pot.size(); i++) {
					// 	System.out.print(pot.get(i) + " ");
					// }
					
					net.resetMarks();
					visited = new boolean[n];
					temp = net.nodes[0];
					break;
				}
			}
			// ce smo nasli pot
			
			
			if (temp == net.nodes[n-1]) {
				break;
			}
			
			// temp = nextNode(visited,net.nodes);
		}


	}

}