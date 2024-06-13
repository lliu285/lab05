package lab05;

public class Main5 
{
	public static void main(String[] args)
	{
		HashTable hash = new HashTable(29);
		hash.insert(new Dollar(5.00));
		//hash.insert(new Dollar(5.00));
		System.out.println(hash.search(new Dollar(5.00)));
		//hash.remove(new Dollar(5.00));
		hash.printHash();
		System.out.println("Load factor: " + hash.getLoadFactor());
		
	}
}
