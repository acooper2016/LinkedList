import java.util.*;

public class LinkedList<E> implements Iterable, Stack, Queue
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
	
	public LinkedList(LinkedList<E> list)
	{
		size = 0;
		if(list.size() > 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				this.add(list.get(i));
			}
		}
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
		if(size == 0)
		{
			tail = addend;
			head = addend;
		}
		else
		{
			tail.setNext(addend);
			tail = addend;
		}
		size++;
		return true;
	}
	
	
	/**
	*Adds a specified item at a given index.
	*
	*@param index index where item is to be added
	*@param item item to be added
	*
	*@return whether or not item was successfully added.
	*/
	public boolean add(int index, E item)
	{
		
		if(index < 0 || index > size )
			throw new IndexOutOfBoundsException();	
		ListNode<E> n = new ListNode<E>(item);
		if(index == 0)
		{
			n.setNext(head);
			head = n;
		}
		else if(index == size)
		{
			return add(item);
		}
		else	
		{
			add(index - 1, n, head);
		}
		size ++;
		return true;
	}
	/*
		Algorithm is pretty simple recursion.  If adding to the first or last index, it will just use head or tail to figure
		out what what pointer to insert to.  Other than that, it will use a helper method that takes in the location and the 
		current index that decreases with each recursion.  This current index allows the method to insert at an index given by the user.
		Base case occurs when the index passed is 0, and will insert at that point in the list.  
	*/
	
	/* Helper method for add at index.*/
	private void add(int index, ListNode<E> n, ListNode<E> location)
	{
		if(index == 0)	
		{
			n.setNext(location.getNext());
			location.setNext(n);
		}
		else	
			add(index - 1, n, location.getNext());
	}
	
	
	
	/**
	*Returns value at a given index.
	*
	*@param index index of the item to be returned.
	*@return item at given index
	*/
	public E get(int index)
	{
		if(index > size - 1 || index < 0)
			throw new IndexOutOfBoundsException();
		else if(index == 0)
			return head.getItem();
		else if(index == size - 1)
			return tail.getItem();
		else
			return get(index - 1, head.getNext());
	}
	
	public E get(int index, ListNode<E> location)
	{
		if(index == 0)
			return location.getItem();
		return get(index - 1, location.getNext());
	}
	/*
	Algorithm is as follows.  If index equates to head or tail, will just return those items.  Otherwise, will pass to a recursive
	Helper method that counts # of recursions with an index that decreases with each pass.  If index isn't zero, will pass to next
	ListNode.  Once index reaches 0, base case will trigger indicating that this ListNode contains the desired item.  Returns that
	item.
	*/
	
	/**
	*Removes first instance of a given item.  
	*
	*@param o Item to be removed.
	*@return whether LinkList contained any instances of the given item to be removed.
	*/
	public boolean remove(E o)
	{
		if(size() == 0)
			return false;
		else if(head.getItem() == o)
		{
			ListNode<E> holder = head;
			head = head.getNext();
			holder.setNext(null);
			size --;
			return true;
		}	
		else
			return remove(o, head.getNext());
			
	}
	/*
	Algorithm is as follows:  If head matches o, sets head to following ListNode
	and dereferences previous head.  If not, passes to recursive helper function.
	Helper function recurs through each ListNode, checking if following ListNode's
	item = o.  If it does, Links that ListNode to the ListNode 2 links away, dereferences
	the ListNode 1 link away, and returns true.  If it passes to the final ListNode,
	returns false.
	*/
	
	/*Helper method of recursive remove(E) method*/
	private boolean remove(E o, ListNode<E> node)
	{
		if(node.getNext() == null)
			return false;
		else if(node.getNext().getItem() != o)
			return remove(o, node.getNext());
		else
		{
			if(node.getNext().getNext() == null)
			{
				tail = node;
				node.setNext(null);
			}
			else
			{	
				ListNode<E> holder = node.getNext();
				node.setNext(node.getNext().getNext());
				holder.setNext(null);
			}
			size --;
			return true;
		}	
	}
	/**
	*Removes the item at a given index.
	*
	*@param index index of item to be removed
	*@return removed item
	*/
	public E remove(int index)
	{
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		else if(index == 0)
		{
			ListNode<E> holder = head;
			head = head.getNext();
			holder.setNext(null);
			size --;
			return holder.getItem();
		}
		else
		{
			return remove(index -1, head);
		}
	}
	/*
	Algorithm is as follows:  If index = 0, sets head to following ListNode
	and dereferences previous head.  If not, passes to recursive helper function.
	Helper function recurs through each ListNode, reducing a counter parameter by 1.
	When the function passes to a ListNode and the counter hits 0, Links that ListNode 
	to the ListNode 2 links away, dereferences the ListNode 1 link away, and returns the contents
	of the dereferenced ListNode.  If it passes to the final ListNode, returns false.
	*/
	
	/*Helper recursive method of remove(int)*/
	private E remove(int index, ListNode<E> node)
	{
		if(index == 0)
		{
			if(node.getNext().getNext() == null)
			{
				tail = node;
				ListNode<E> holder = node.getNext();
				node.setNext(null);
				size --;
				return holder.getItem();
			}
			else
			{
				ListNode<E> holder = node.getNext();
				node.setNext(node.getNext().getNext());
				holder.setNext(null);
				size --;
				return holder.getItem();
			}
		}
		else
		{
			return remove(index - 1, node.getNext());
		}	
	}
	
	/**
	*Adds item to the front of the Stack
	*
	*@param item item to be added
	*/
	public void push(Object item)
	{
		add(0, (E)item);
	}
	/**
	*Adds item to the front of the Queue
	*
	*@param item item to be added
	*/
	public void offer(Object item)
	{
		add(0, (E)item);
	}
	
	/**
	*Removes first item in LinkedList
	*
	*@return removed item
	*/
	public E removeFirst()
	{
		if(size() == 0)
			throw new NoSuchElementException();
		return remove(0);
	}
	
	/**
	*Removes last item in LinkedList
	*
	*@return removed item
	*/
	public E removeLast()
	{
		if(size() == 0)
			throw new NoSuchElementException();
		return remove(size - 1);
	}
	
	/**
	*Adds an item as the first entry in the LinkedList.
	*
	*@param item item to be added
	*/
	public void addFirst(E item)
	{
		add(0, item);
	}
	
	/**
	*Adds an item as the last entry in The linkedList.
	*
	*@param item item to be added
	*/
	public void addLast(E item)
	{
		add(item);
	}
	
	/**
	*Checks whether a given item is in LinkedList
	*
	*@param o item being checked for
	*@return whether or not LinkedList contains o
	*/
	public boolean contains(E o)
	{
		if(size == 0)
			return false;
		else if(head.getItem() == o)
			return true;
		else 
			return contains(o, head.getNext());		
	}
	
	/*Recursive helper method for Contains(E)*/
	private boolean contains(E o, ListNode<E> node)
	{
		if(node.getItem() == o)
			return true;
		else if(node.getNext() == null)
			return false;
		else
			return contains(o, node.getNext());
	}
	
	/**
	*Removes all data in the LinkedList
	*/
	public void clear()
	{
		size = 0;
		head = null;
		tail = null;
	}
	
	/**
	*Sets the ListNode at a given Index to a new item
	*
	*@param index index where new item is to be set
	*@param o item that is to replace original item at index
	*@return Previous item held at index.
	*/
	public E set(int index, E o)
	{
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		else if(index == 0)
		{
			E holder = head.getItem();
			head.setItem(o);
			return holder;
		}
		else
		{
			return set(index - 1, o, head.getNext());
		}
	}
	
	private E set(int index, E o, ListNode<E> node)
	{
		if(index == 0)
		{
			E holder = node.getItem();
			node.setItem(o);
			return holder;
		}
		else
		{
			return set(index - 1, o, node.getNext());
		}
	}	
	/*
	Algorithm is largely a simplified version of Remove(index).  Rather than dereferencing
	the ListNode at index, simply sets its item to a new item.
	*/
	
	/**
	*Returns index of a given item.  
	*
	*@param o item being searched for.
	*@return index of o.  -1 if LinkedList does not contain o
	*/
	public int indexOf(E o)
	{
		if(size == 0)
			return -1;
		else if(head.getItem() == o)
			return 0;
		else
			return indexOf(o, 1, head.getNext()); 
	}
	
	private int indexOf(E o, int index, ListNode<E> node)
	{
		if(node.getItem() == o)
			return index;
		else if(node.getNext() == null)
			return -1;
		else
			return indexOf(o, index + 1, node.getNext());
	}
	/*
	Algorithm is as follows:  If head's item is o, returns 0.  Else, passes to a recursive
	helper function, along with a counter parameter that increases by one with each 
	pass.  If the object is found, returns current value of counter parameter.  If
	end of LinkedList is reached, returns -1.
	*/
	
	/**
	*Removes first entered item in queue.
	*
	*@return first entered item in queue.
	*/
	public E poll()
	{
		return removeLast();
	}
	
	/**
	*Removes last item entered in stack.
	*
	*@return last item entered in stack
	*/
	public E pop()
	{
		return removeFirst();
	}
	
	/**
	*Returns first entered item in Queue.
	*
	*@return first entered item in Queue.
	*/
	public E peak()
	{
		return tail.getItem();
	}
	
	/**
	*Returns last entered item in Stack.
	*
	*@return last entered item in Stack.
	*/
	public E peek()
	{
		return head.getItem();
	}	
	
	/**
	*Returns whether or not LinkedList is empty.
	*
	*@return whether or not LinkedList is empty.
	*/
	public boolean isEmpty()
	{
		if(size() == 0)
			return true;
		else
			return false;
	}
	
	/**
	*Returns string representation of the LinkedList.
	*
	*@return String representation of the linkedlist.
	*/
	public String toString()
	{
		if(head == null)
			return "";
		return head.getString();
	}
	
	public Iterator<E> iterator()
	{
		return new LinkedListIterator(head);
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
		return (String) item;
	}
	
	/*
	Recursive helper method of LinkedList.toString().  Each recursion appends the next ListNode to the original string, until
	end of chain is reached.
	*/
	public String getString()
	{
		if(getNext() == null)
			return "\n" + item.toString();
		return "\n" + item + getNext().getString();
	}
	
	
}

public class LinkedListIterator implements Iterator
{
	private ListNode<E> curr;
	
	public LinkedListIterator(ListNode<E> head)
	{
		curr = head;
	}

	public boolean hasNext()
	{
		return (curr.getNext() == null);
	}
	
	public E next()
	{
		if(curr.getNext() == null)
			throw new IndexOutOfBoundsException();
		E item = curr.getItem();
		curr = curr.getNext();
		return item;
	}
	
	public void remove()
	{
	}
}

}

