import java.util.*;
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	
	private Comparator<T> compareObj;
	
	public SortedDoubleLinkedList(Comparator<T> comparableObject) {
		super();
		compareObj = comparableObject;
	}
	
	public void add(T data) {
		
		Node newNode = new Node(data);
		
		switch(size) {
			case 0: head = newNode; tail = newNode; size++; break; // CASE 1: Empty list
			case 1:												   // CASE 2: One element
				if (compareObj.compare(data, head.getData()) < 0) {
					newNode.setNextNode(head);
					head.setPrevNode(newNode);
					head = newNode;
					size++;
					
				} else {
					newNode.setPrevNode(tail);
					tail.setNextNode(newNode);
					tail = newNode;
					size++;
				}
				break;
			
			default:												  // CASE 3: Multiple elements
				if (compareObj.compare(data, head.getData()) < 0) { // data < head
					newNode.setNextNode(head);
					head.setPrevNode(newNode);
					head = newNode;
					size++;
				
				} else if (compareObj.compare(tail.getData(), data) <= 0) { // tail <= data
					newNode.setPrevNode(tail);
					tail.setNextNode(newNode);
					tail = newNode;
					size++;
				
				} else {							// firstParam <= data < secondParam
					Node firstParam = head;
					Node secondParam = head.getNextNode();

					while (!((compareObj.compare(firstParam.getData(), data) <= 0)
							&& (compareObj.compare(data, secondParam.getData()) < 0))) {
					
						firstParam = secondParam;
						secondParam = secondParam.getNextNode();
					}
					
					newNode.setPrevNode(firstParam);
					newNode.setNextNode(secondParam);
					firstParam.setNextNode(newNode);
					secondParam.setPrevNode(newNode);
					size++;
				}
				break;
				
		}   
	}
	
	public Node remove(T data, Comparator<T> comparator) {
		return super.remove(data, comparator);
	}
	
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	public void addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	@Override
	public ListIterator<T> iterator() {
		return super.iterator();
	}

}
