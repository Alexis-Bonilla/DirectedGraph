package collections;

import java.util.ArrayList;

public class AlexisGraphNode<I,V> {
	
	private I index;
	private V value;
	
	
	public AlexisGraphNode(I index, V value) {
		this.index=index;
		this.value=value;
	}


	public I getIndex() {
		return index;
	}


	public void setIndex(I index) {
		this.index = index;
	}


	public V getValue() {
		return value;
	}


	public void setValue(V value) {
		this.value = value;
	}
	

	
	
	
	
	
	
}
