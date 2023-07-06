import java.util.*;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	
	protected Node head; // Head reference
	protected Node tail; // Tail reference
	protected int size; // Number of nodes in the list

	/**
	 * No-arg constructor:
	 * Constructor initializes head and tail references to null, size of list starts at 0.
	 */
	protected BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	protected void addToFront(T newEntry) {
		Node newNode = new Node(newEntry);
		
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.setNextNode(head);
			head.setPrevNode(newNode);
			head = newNode;
		}
		size++;
	}
	
	protected void addToEnd(T newEntry) {
		Node newNode = new Node(newEntry);
		
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setNextNode(newNode);
			newNode.setPrevNode(tail);
			tail = newNode;
		}
		size++;
	}
	
	protected T retrieveFirstElement() {
		
		T firstElement = null;	// Set to null at first
		
		switch (size) {
			case 0: return firstElement;			// CASE 1: Cannot retrieve from empty list
			case 1:							// CASE 2: Retrieving first element >> empty list
				firstElement = head.getData();
				head = null;
				tail = null;
				size--;
				return firstElement;
			case 2:							// CASE 3: Retrieving first element >> one-element list
				firstElement = head.getData();
				head = tail;
				size--;
				return firstElement;
			default:						// CASE 4: Retrieving first element >> non-empty list
				firstElement = head.getData();
				head = head.getNextNode();
				size--;
				return firstElement;
		}
	}
	
	protected T retrieveLastElement() {
		
		T lastElement = null;	// Set to null at first
		
		switch (size) {
			case 0: return lastElement;			// CASE 1: Cannot retrieve from empty list
			case 1:							// CASE 2: Retrieving last element >> empty list
				lastElement = tail.getData();
				head = null;
				tail = null;
				size--;
				return lastElement;
			case 2:							// CASE 3: Retrieving last element >> one-element list
				lastElement = tail.getData();
				tail = head;
				size--;
				return lastElement;
			default:						// CASE 4: Retrieving last element >> non-empty list
				lastElement = tail.getData();
				tail = tail.getPrevNode();
				size--;
				return lastElement;
		}
	}
	
	protected T getFirst() {
		if (isEmpty())
			return null;
		else
			return head.getData();
	}
	
	protected T getLast() {
		if (isEmpty())
			return null;
		else
			return tail.getData();
	}
	
	protected Node remove(T targetData, Comparator<T> comparator) {
		
		Node result = null;
		
		
		switch (size) {
			case 0: return null;													// Nothing to remove
			case 1:
				if (comparator.compare(targetData, head.getData()) == 0) {			// Removal results in empty list
					result = new Node(head.getData());
					head = null;
					tail = null;
					size--;
					return result;
				} else
					return null;
				
			case 2:
				if (comparator.compare(targetData, head.getData()) == 0) {			// Removal results in one-element list
					result = new Node(head.getData());
					head = head.getNextNode();
					size--;
					return result;
				
				} else if (comparator.compare(targetData, tail.getData()) == 0) {
					result = new Node(tail.getData());
					tail = tail.getPrevNode();
					size--;
					return result;
					
				} else
					return null;
			
			default:																// Removal results in at least two-element list
				if (comparator.compare(targetData, head.getData()) == 0) { 			// Remove first element if it matches
					result = new Node(head.getData());
					head = head.getNextNode();
					size--;
					return result;
				
				} else if (comparator.compare(targetData, tail.getData()) == 0) { 	// Remove last element if it matches
					result = new Node(tail.getData());
					tail = tail.getPrevNode();
					size--;
					return result;
				
				} else {										// Remove element between first and last element if it matches
					result = head.getNextNode(); 				// Start at second element
					
					while ((result != null) && (result != tail)) {
						if (comparator.compare(targetData, result.getData()) == 0) {
							Node nodeBefore = result.getPrevNode();
							Node nodeAfter = result.getNextNode();
							result = new Node(result.getData());
							nodeBefore.setNextNode(nodeAfter);
							size--;
							return result;
						}
						
						result = result.getNextNode();
					}
					
					return result; // If element was not removed, return null because targetData was not found in the list
				}
		}
	}
	
	protected ArrayList<T> toArrayList() {
		ArrayList<T> result =  new ArrayList<>();
		Node current = head;
		for(int i = 0; i < size; i++) {
			result.add(current.getData());
			current = current.getNextNode();
		}
		
		return result;
	}
	
	protected int getSize() {
		return size;
	}
	
	protected boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}
	
	/**
	 * Inner class: Node
	 * @author Sebastian Ashcallay
	 */
	protected class Node {
		private T data; // Entry in bag
		private Node next; // Link to next node
		private Node prev; // Link to previous node

		protected Node(T dataNode) {
			this(dataNode, null, null);
		}

		protected Node(T dataNode, Node nextNode, Node prevNode) {
			data = dataNode;
			next = nextNode;
			prev = prevNode;
		}

		protected T getData() {
			return data;
		} 

		protected void setData(T newData) {
			data = newData;
		} 
		
		protected Node getNextNode() {
			return next;
		} 

		protected void setNextNode(Node nextNode) {
			next = nextNode;
		}
		
		protected Node getPrevNode() {
			return prev;
		}
		
		protected void setPrevNode(Node prevNode) {
			prev = prevNode;
		}
	}
	
	/**
	 * Inner iterator class: DoubleLinkedListIterator 
	 * @author Sebastian Ashcallay
	 *
	 */
	protected class DoubleLinkedListIterator implements ListIterator<T> {
		
		private Node currentN;
		private Node lastN;
		// private int idx;
		
		protected DoubleLinkedListIterator() {
			// idx = 0;
			currentN = head;
			lastN = null;
		}

		@Override
		public boolean hasNext() {
			return currentN != null; // idx < size
		}

		@Override
		public T next() throws NoSuchElementException {
			T result;
			if (hasNext()) {
				lastN = currentN;
				result = currentN.getData();
				currentN = currentN.getNextNode(); // Advance iterator
				// idx++;
			
			} else
				throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
			
			return result; // Return next entry in iteration
		}

		@Override
		public boolean hasPrevious() {
			return lastN != null; // idx > 0
		}

		@Override
		public T previous() throws NoSuchElementException {
			T result;
			if (hasPrevious()) {
				currentN = lastN;
				result = lastN.getData();
				lastN = lastN.getPrevNode();
				// idx--;
			
			} else
				throw new NoSuchElementException("Illegal call to previous(); iterator is before start of list.");
			
			return result; // Return previous entry in iteration
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException { 
			throw new UnsupportedOperationException(); 
		}

		@Override
		public void set(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
	}

}
