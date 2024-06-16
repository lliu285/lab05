package lab05;

/*
 * Lab #5
 * Names: Lucia Liu, Nithya Ramasubramonian
 * Due date: 6/16/24
 * Purpose: The purpose of this lab is to practice implementing a Hash Table through a Dollar modeling scenario.
 */
public class HashTable 
{
	private int size;
	private Currency[] items;
	private int count;
	private int collisions;
	private final int c1;
	private final int c2;
	
	public HashTable(int size)
	{
		this.size = size;
		items = new Dollar[size];
		count = 0;
		collisions = 0;
		c1 = 1;
		c2 = 1;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public int getCollisions()
	{
		return collisions;
	}
	
	public double getLoadFactor()
	{
		return (double) count / size;
	}
	
	private int hash(Currency item)
	{
		return 2 * item.getWholeValue() +
			   3 * item.getFractionValue();
	}
	
	private int getNextPrime(int input)
	{
		int count;
		input++;
		
		while(true){
			int l = (int) Math.sqrt(input);
			count = 0;
			for(int i = 2; i <= l; i ++) {
		    	if(input % i == 0)
		    		count++;
			}
			
			if(count == 0)
		    	return input;
			else {
		    	input++;
		    }
		}
	}
	
	/*
	 * Resize hash to a size of the next prime that is greater than the current size * 2
	 * Number of collisions are reset when hash table is resized
	 */
	private void resize()
	{
		int newSize = getNextPrime(size * 2);
		collisions = 0;
		
		Currency[] newItems = new Currency[newSize];
		
		int index = 0;
		while (index < size) {
			if (items[index] != null) {
				Currency item = items[index];
				insertHelper(newItems, item, newSize);
			}
			
			index++;
		}
		
		items = newItems;
		size = newSize;
	}
	
	/*
	 * HashTable automatically resizes when the load factor > 0.75
	 */
	public void insert(Currency item) {
		if (getLoadFactor() > 0.75) {
			resize();
		}
		
		if (insertHelper(items, item, size)) {
			count++;
		}
	}
	
	/*
	 * c1 = 1, c2 = 1
	 * Uses quadratic probing algorithm by default
	 * Switches to linear probing if cyclic collision occurs
	 */
	private boolean insertHelper(Currency[] items, Currency item, int size)
	{
		int index = hash(item) % size;
		int i = 0;
		int probed = 0;
		
		// Note: Cyclic collision occurs when i >= size
		// (hash(item) + c1 * i + c2 * i^2) % size ==
		// (hash(item) + c1 * (i+size) + c2 * (i+size)^2) % size
		while (probed < size) {
			if (items[index] == null) {
				items[index] = item;
				return true;
			}
			
			if (i < size) { // quadratic probing
				i++;
				index = (hash(item) + c1 * i + c2 * i * i) % size;
			} else { // linear probing
				index = (index + 1) % size; 
			}
				
			probed++;
			collisions++;
		}
		
		return false;	
	}
	
	/*
	 * If there are duplicate Dollars, the first matched index is returned
	 */
	public int search(Currency item)
	{
		int index = hash(item) % size;
		int i = 0;
		int probed = 0;
		
		while (probed < size) {
			if (items[index] != null && items[index].isEqual(item)) {
				return index;
			}
			
			i++;
			index = (hash(item) + c1 * i + c2 * i * i) % size;
			probed++;
		}
		return -1;
		
	}
	
	public void printHash()
	{
		for (int i = 0; i < size; i++) {
			String value;
			if (items[i] == null) {
				value = "Empty"; 
			} else {
				value = "$" + items[i].getWholeValue() + "." + items[i].getFractionValue();
			}
			System.out.println(i + ": " + value);
		}
		
		System.out.println("Number of loaded items: " + getCount());
		System.out.println("Load factor: " + getLoadFactor());
		System.out.println("Number of collisions: " + getCollisions() + "\n");
	}
}
