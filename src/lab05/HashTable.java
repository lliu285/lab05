package lab05;

public class HashTable 
{
	private int size;
	private Currency[] items;
	private int[] status;
	private int count;
	private int collisions;
	private final int c1;
	private final int c2;
	
	public HashTable(int size)
	{
		this.size = size;
		items = new Dollar[size];
		status = new int[size];
		count = 0;
		collisions = 0;
		c1 = 1;
		c2 = 2;
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
	
	private int hash(Dollar item)
	{
		return 2 * item.getWholeValue() 
				+ 3 * item.getFractionValue();
	}
	
	public boolean insert(Dollar item)
	{
		int value = (2 * item.getWholeValue() 
					+ 3 * item.getFractionValue());
		int index = value % size;
		
		int i = 0;
		int probed = 0;
		
		while (probed < size) {
			if (items[index] == null) {
				items[index] = item;
				status[index] = 1;
				count++;
				return true;
			}
			
			i++;
			index = (value + c1 * i + c2 * i * i) % size;
			probed++;
			collisions++;
		}
		
		return false;
	}
	
	public boolean remove(Dollar item)
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
	
	public int search(Dollar item)
	{
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
	}
	
	public void printHash()
	{
		for (int i = 0; i < size; i++) {
			String value;
			if (items[i] == null) {
				value = status[i] + "";
			} else {
				value = "$" + items[i].getWholeValue() + "." + items[i].getFractionValue();
			}
			System.out.println(i + ": " + value + " ");
		}
	}
}
