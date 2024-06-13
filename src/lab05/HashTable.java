package lab05;

public class HashTable 
{
	private int size;
	private Dollar[] items;
	private int count;
	private int collisions;
	
	public HashTable(int size)
	{
		this.size = size;
		items = new Dollar[size];
		count = 0;
		collisions = 0;
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
	
	public Dollar[] getItems()
	{
		return items;
	}
	
	public void insert()
	{
		
	}
	
	public void remove()
	{
		
	}
	
	public void search()
	{
		
	}
}
