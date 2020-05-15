package collections;
import java.util.*;

public class AlexisDirectedGraph<I,V> implements IAlexisDirectedGraph<I,V> {

	private int[][] weigthMatrix;
	private ArrayList<AlexisGraphNode<I,V>> nodes;
	
	
	
	

	public boolean isEmpty() {
		return nodes.size()==0;
	}
	
	public void fixMatrixToAdd() {
		int rows = weigthMatrix.length;
		int columns = weigthMatrix[0].length;
		int[][] auxMatrix= new int[rows+1][columns+1];
		for (int i = 0; i < weigthMatrix.length; i++) {
			for (int j = 0; j < weigthMatrix[0].length; j++) {
				auxMatrix[i][j]=weigthMatrix[i][j];
			}
		}
		weigthMatrix=auxMatrix;
	}
	
	public int breadthFirstSearch(AlexisGraphNode<I,V> from, AlexisGraphNode<I,V> to){
        int[] levels = new int[nodes.size()];
        Queue<AlexisGraphNode<I,V>> q = new LinkedList<AlexisGraphNode<I,V>>();
		q.offer(from);
		levels[(int)from.getIndex()]=0;
		boolean end=false;
		while(!q.isEmpty()&&!end) {
			AlexisGraphNode<I,V> current = q.poll();
			ArrayList<AlexisGraphNode<I,V>> adjc=getAdjacents(current);
			for (int i = 0; i < adjc.size()&&!end; i++) {
	               int indexLevelNodeVisited =(int)adjc.get(i).getIndex();
	                int levelNodeVisited =levels[indexLevelNodeVisited];
	                if(levelNodeVisited==0){
	                    levels[indexLevelNodeVisited]=levels[(int)current.getIndex()]+1;
	                    q.offer(adjc.get(i));
	                }
	                if((int)adjc.get(i).getIndex()==(int)to.getIndex()) {
	                	end=true;
	                }   
			}
		}
		return levels[(int)to.getIndex()];
	}
	
	public ArrayList<AlexisGraphNode<I,V>> getAdjacents(AlexisGraphNode<I,V> toSearch){
		ArrayList<AlexisGraphNode<I,V>> array = new ArrayList<AlexisGraphNode<I,V>>();
		for (int i = 0; i < weigthMatrix[0].length; i++) {
			if(weigthMatrix[(int)toSearch.getIndex()][i]!=0) {
				array.add(nodes.get(i));
			}
		}
		return array;
	}
	
	
	public int[][] floydWarshall() {
		int[][] distanceMatrix= weigthMatrix;
		for (int i = 0; i < distanceMatrix.length; i++) {
			for (int j = 0; j < distanceMatrix[0].length; j++) {
				if(distanceMatrix[i][j]==0) {
					distanceMatrix[i][j]=Integer.MAX_VALUE;
				}
			}
		}
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < distanceMatrix.length; j++) {
				for (int k = 0; k < distanceMatrix.length; k++) {
					distanceMatrix[j][k]= distanceMatrix[j][k]>distanceMatrix[j][i]+distanceMatrix[i][k]?distanceMatrix[j][i]+distanceMatrix[i][k]:distanceMatrix[j][k];
				}
			}
		}
		return distanceMatrix;
	}
	
	
	public void delete(int index) {
		nodes.remove(index);
		fixMatrixForRemove(index);
		
		
	}


	private void fixMatrixForRemove(int index) {
		int[][] matrixAux = new int [weigthMatrix.length-1][weigthMatrix[0].length-1];
		for (int i = 0; i < matrixAux.length; i++) {
			for (int j = 0; j < matrixAux[0].length; j++) {
				
				if(i==index) {
					matrixAux[i][j]=weigthMatrix[i+1][j];
				}
				else if(j==index) {
					matrixAux[i][j]=weigthMatrix[i][j+1];
				}
				else {
					matrixAux[i][j]=weigthMatrix[i][j];
				}
				
				
				
			}
		}
	}

	@Override
	public void addNode(I index,V value) {
		AlexisGraphNode toAdd= new AlexisGraphNode(index,value);
		nodes.add(toAdd);
		
	}

	@Override
	public void addEdge(int indexX, int indexY, int weigth) {
		if(indexX<=weigthMatrix.length-1&&indexY<=weigthMatrix[0].length-1) {
			weigthMatrix[indexX][indexY]=weigthMatrix[indexX][indexY]==0?weigth:weigthMatrix[indexX][indexY]>weigth?weigth:weigthMatrix[indexX][indexY];
		}
	}
	
	
}
