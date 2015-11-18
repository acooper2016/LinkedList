

public class LinkedList<E>
{
	private ListNode<E> head;
	private ListNode<E> tail;
	private int size;
	
	/**
	*constructs new empty LinkedList
	*/
	public LinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	*Returns number of nodes in the Linkedlist
	*
	*@return size of the LinkedList
	*/
	public int size()
	{
		return size;	
	}
	
	/**
	*Adds a new node containing item to the end of the LinkedList
	*
	*@return whether or not item was successfully added
	*/
	public boolean add(E item)
	{
		ListNode<E> addend = new ListNode<E>(item);
		if(head == null)	//If LinkedList is empty, just creates new node containing item and sets it to head and tail.
		{
			head = addend;
			tail = addend;
		}
		else
			this.add(addend, head);	//Passes new ListNode to first node 
			tail = addend;
		size ++;
		return true;
	}
	
	private void add(ListNode<E> addend, ListNode<E> location)
	{
		if(location.getNext() == null)	//If location is at end of LinkedList, sets its pointer to addend
			location.setNext(addend);
		else
			add(addend, location.getNext());//Passes addend to location's pointer.
	}
	
	public boolean add(int index, E item)
	{
		
		if(index < 0)
			throw new IndexOutOfBoundsException("index must be > -1");	
		ListNode<E> n = new ListNode<E>(item);
		if(index == 0 && head == null)	//If LinkedList is empty and n is to be added at index 0, sets head to n.
			head = n;
		else if(index == 0)	//If LinkedList is not empty is to be added at index 0, inserts n into pointer chain at head
		{
			n.setNext(head);
			head = n;
		}
		else if(head == null)	//if LinkedList is empty but n is to be added at a value higher than 0, creates new empty ListNode for head and passes n to it.
		{
			head = new ListNode<E>(null);
			add(index - 1, n, head);
		}
		else	//Passes n to ListNode located at head pointer.
		{
			add(index - 1, n, head);
		}
		if(index == size)	//If new ListNode is inserted at the end, sets tail to it.
			tail = n;
		size ++;
		return true;
	}
	
	private void add(int index, ListNode<E> n, ListNode<E> location)
	{
		if(index == 0)	//Base Case.  Inserts n after location.
		{
			n.setNext(location.getNext());
			location.setNext(n);
		}
		else if(location.getNext() == null)	//Creates new empty ListNode after location, and passes n to it.
		{
			location.setNext(new ListNode<E>(null));
			add(index - 1, n, location.getNext());
		} 
		else	//passes n to location's pointer.
			add(index - 1, n, location.getNext());
	}
	
	public String toString()
	{
		if(head == null)
			return "";
		return head.toString();
	}
/*
*	PLACE
*	HOLDER
*	TEXT
*	BREAK
*	TO
*	QUICKLY
*	DISTINGUISH 
*	LINKEDLIST
*	FROM
*	LISTNODE
*/	
	
private class ListNode<E>
{
	private E item;
	private ListNode<E> next;
	
	public ListNode(E i)
	{
		item = i;
		next = null;
	}
	
	public ListNode(E i, ListNode<E> pointer)
	{
		item = i; 
		next = pointer;
	}
	
	public E getItem()
	{
		return item;
	}
	
	public ListNode<E> getNext()
	{
		return next;
	}
	
	public void setItem(E i)
	{	
		item = i;
	}
	
	public void setNext(ListNode<E> pointer)
	{
		next = pointer;
	}
	
	public String toString()
	{
		if(getNext() == null)
			return "\n" + item.toString();
		return "\n" + item + getNext().toString();
	}
	
	
}
}

