package lab05;

public class Main5 
{
	public static void main(String[] args)
	{
		HashTable hash = new HashTable(29);
		hash.insert(new Dollar(5.00));
		hash.insert(new Dollar(7.50));
		hash.insert(new Dollar(5.00));
		hash.remove(new Dollar(8.00));
		hash.printHash();
		System.out.println("Index found: " + hash.search(new Dollar(7.50)));
		System.out.println("Number of loaded items: " + hash.getCount());
		System.out.println("Load factor: " + hash.getLoadFactor());
		System.out.println("Number of collisions: " + hash.getCollisions());
		
	}
}
