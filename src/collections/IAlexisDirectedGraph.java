package collections;

public interface IAlexisDirectedGraph<I,V> {
	
	 boolean isEmpty();
	 void addNode(I index, V value);
	 void addEdge(int indexX,int indexY, int weigth);
	 int breadthFirstSearch(AlexisGraphNode<I,V> from, AlexisGraphNode<I,V> to);
	 int[][] floydWarshall();
	 void delete(int index);
	
	
	
}
