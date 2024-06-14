package lab05;

public class HashTable 
{
	private int size;
	private Currency[] items;
	//private int[] status;
	private int count;
	private int collisions;
	private final int c1;
	private final int c2;
	
	public HashTable(int size)
	{
		this.size = size;
		items = new Dollar[size];
		//status = new int[size];
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
	
	/*
	public void printItem(int i)
	{
		if (items[i] == null) {
			System.out.println("empty");
		} else {
			System.out.println("$" + items[i].getWholeValue() + "." + items[i].getFractionValue());
		}
	}
	*/
	
	private int hash(Currency item)
	{
		return 2 * item.getWholeValue() 
				+ 3 * item.getFractionValue();
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
	 * Resize hash to the next prime > size * 2
	 * old items are reinserted into new array
	 */
	private void resize()
	{
		int newSize = getNextPrime(size * 2);
		
		Currency[] newItems = new Currency[newSize];
		//int[] newStatus = new int[newSize];
		
		int index = 0;
		while (index < size) {
			if (items[index] != null) {
				Currency item = items[index];
				insertHelper(newItems, /*newStatus, */item, newSize);
			}
			
			index++;
		}
		
		items = newItems;
		//status = newStatus;
		size = newSize;
	}
	
	public void insert(Currency item) {
		if (getLoadFactor() > 0.75) {
			resize();
		}
		
		if (insertHelper(items/*, status*/, item, size)) {
			count++;
		}
	}
	
	/*
	 * resize when loadFactor > 0.75
	 * uses quadratic probing algorithm
	 * c1 = 1
	 * c2 = 1
	 * cyclic hashing --> switch from quadratic probe to linear probe
	 */
	private boolean insertHelper(Currency[] items, /*int[] status,*/ Currency item, int size)
	{
		int index = hash(item) % size;
		int i = 0;
		int probed = 0;
		
		
		while (probed < size) {
			if (items[index] == null) {
				items[index] = item;
				/*status[index] = 1;*/
				return true;
			}
			
			i++;
			index = (hash(item) + c1 * i + c2 * i * i) % size;
			probed++;
			collisions++;
		}
		
		return false;
	}
	
	/*
	public boolean remove(Currency item)
	{
		int index = hash(item) % size;
		int i = 0;
		int probed = 0;
		
		while (status[index] != 0 && probed < size) {
			if (items[index] != null && items[index].isEqual(item)) {
				items[index] = null;
				status[index] = -1;
				count--;
				return true;
			}
			
			i++;
			index = (hash(item) + c1 * i + c2 * i * i) % size;
			probed++;
		}
		return false;
	}
	*/
	
	public int search(Currency item)
	{
		for (int i = 0; i < size; i++) {
			if (items[i] != null && items[i].isEqual(item)) {
				return i;
			}
		}
		
		return -1;
		
		/*
		int index = hash(item) % size;
		int i = 0;
		int probed = 0;
		
		while (status[index] != 0 && probed < size) {
			if (items[index] != null && items[index].isEqual(item)) {
				return index;
			}
			
			i++;
			index = (hash(item) + c1 * i + c2 * i * i) % size;
			probed++;
		}
		return -1;
		*/
	}
	
	public void printHash()
	{
		for (int i = 0; i < size; i++) {
			String value;
			if (items[i] == null) {
				value = "empty"; //status[i] + ""
			} else {
				value = "$" + items[i].getWholeValue() + "." + items[i].getFractionValue();
			}
			System.out.println(i + ": " + value + " ");
		}
		
		System.out.println("Number of loaded items: " + getCount());
		System.out.println("Load factor: " + getLoadFactor());
		System.out.println("Number of collisions: " + getCollisions());
		
	}
}
