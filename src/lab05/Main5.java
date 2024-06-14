package lab05;

public class Main5 
{
	public static void main(String[] args)
	{
    	Dollar[] dollars = new Dollar[20];
    	dollars[0] = new Dollar(57.12);
    	dollars[1] = new Dollar(23.44);
    	dollars[2] = new Dollar(87.43);
    	dollars[3] = new Dollar(68.99);
    	dollars[4] = new Dollar(111.22);
    	dollars[5] = new Dollar(44.55);
    	dollars[6] = new Dollar(77.77); 
    	dollars[7] = new Dollar(18.36);
     	dollars[8] = new Dollar(543.21); 
      	dollars[9] = new Dollar(20.21);
      	dollars[10] = new Dollar(345.67);
      	dollars[11] = new Dollar(36.18);
      	dollars[12] = new Dollar(48.48);
      	dollars[13] = new Dollar(101.00);
     	dollars[14] = new Dollar(11.00);
      	dollars[15] = new Dollar(21.00);
      	dollars[16] = new Dollar(51.00);
     	dollars[17] = new Dollar(1.00);
      	dollars[18] = new Dollar(251.00);
     	dollars[19] = new Dollar(151.00);
        
		HashTable hash = new HashTable(20);
		
		for (int i = 0; i < 14; i++) {
			hash.insert(dollars[i]);
		}
		
		hash.printHash();
		System.out.println();
		
		for (int i = 14; i < dollars.length; i++) {
			hash.insert(dollars[i]);
		}
		
//		hash.insert(new Dollar(5.00));
//		hash.insert(new Dollar(7.50));
//		hash.insert(new Dollar(5.00));
		
		hash.printHash();
		System.out.println("Index found: " + hash.search(new Dollar(7.50)));
//		System.out.println("Number of loaded items: " + hash.getCount());
//		System.out.println("Load factor: " + hash.getLoadFactor());
//		System.out.println("Number of collisions: " + hash.getCollisions());
		
	}
}
