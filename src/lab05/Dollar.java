package lab05;

//Names: Lucia Liu, Nithya Ramasubramonian
//Due date: 5/8/24

public class Dollar extends Currency
{
	private String name = "dollar";

	@Override
	public String getName() 
	{
		return name;
	}

	@Override
	public Dollar copy() 
	{
		Dollar dollarCopy = new Dollar();
		dollarCopy.setWholeValue(getWholeValue());
		dollarCopy.setFractionValue(getFractionValue());
		
		return dollarCopy;
	}
}
