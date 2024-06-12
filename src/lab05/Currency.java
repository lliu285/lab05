package lab05;

// Names: Lucia Liu, Nithya Ramasubramonian
// Due date: 5/8/24

public abstract class Currency 
{
	private int wholeValue;
	private int fractionValue;
	
	public Currency()
	{
		wholeValue = 0;
		fractionValue = 0;
	}
	
	public Currency(double value)
	{
		//TODO: Need to be revised such at 24.997 can round 
		//up to 25.00 ->  wholeValue is 25,  fractionValue is 0.
		wholeValue = (int) value;
		
		if (value - wholeValue > 0.005) // to round up
			fractionValue = (int) (100 * (value - wholeValue + 0.005));
		else
			fractionValue = (int) (100 * (value - wholeValue));
	}
	
	// copy constructor --> might not need, created an abstract copy method instead
	public Currency(Currency other)
	{
		this.wholeValue = other.wholeValue;
		this.fractionValue = other.fractionValue;
	}
	
	public int getWholeValue()
	{
		return wholeValue;
	}
	
	public void setWholeValue(int wholeValue)
	{
		// throw exception if input is negative
		this.wholeValue = wholeValue;
	}
	
	public int getFractionValue()
	{
		return fractionValue;
	}
	
	public void setFractionValue(int fractionValue)
	{
		// throw exception if input is negative
		this.fractionValue = fractionValue;
	}
	
	public abstract String getName();
	public abstract Currency copy();
	
	public Currency add(Currency otherCurrency) 
	{
		if (!getName().equals(otherCurrency.getName())) {
			throw new IllegalArgumentException();
		}
		
		Currency sum = otherCurrency.copy(); // used copy method to create object with same type
		int fractionSum = this.fractionValue + otherCurrency.fractionValue;
		
		if (fractionSum >= 100) {
			sum.fractionValue = fractionSum - 100;
			sum.wholeValue = this.wholeValue + otherCurrency.wholeValue + 1;
		} else {
			sum.fractionValue = fractionSum;
			sum.wholeValue = this.wholeValue + otherCurrency.wholeValue;
		}
				
		return sum;
	}
	
	public Currency subtract(Currency otherCurrency)
	{
		if (!getName().equals(otherCurrency.getName())) {
			throw new IllegalArgumentException();
		}
		
		if (!isGreater(otherCurrency)) {
			throw new ArithmeticException();
		}
		
		Currency diff = otherCurrency.copy(); // used copy method to create object with same type
		
		if (this.fractionValue > otherCurrency.fractionValue) {
			diff.fractionValue = this.fractionValue - otherCurrency.fractionValue;
			diff.wholeValue = this.wholeValue - otherCurrency.wholeValue;
		} else { 
			diff.fractionValue = otherCurrency.fractionValue - this.fractionValue;
			diff.wholeValue = this.wholeValue - otherCurrency.wholeValue - 1;
		}
				
		return diff;
	}
	
	public boolean isEqual(Currency otherCurrency)
	{
		if (!getName().equals(otherCurrency.getName())) {
			throw new IllegalArgumentException();
		}
		
		if (this.wholeValue == otherCurrency.wholeValue && 
			this.fractionValue == otherCurrency.fractionValue)
			return true;
		else
			return false;
	}
	
	public boolean isGreater(Currency otherCurrency)
	{
		if (!getName().equals(otherCurrency.getName())) {
			throw new IllegalArgumentException();
		}
		
		if (this.wholeValue > otherCurrency.wholeValue) {
			return true;
		} else if (this.wholeValue < otherCurrency.wholeValue) {
			return false;
		} else {
			if (this.fractionValue > otherCurrency.fractionValue) 
				return true;
			else
				return false;
		}
	}
	
	public void print()
	{
		System.out.println(wholeValue + "." + fractionValue + " " /*+ getName()*/);
	}
}
