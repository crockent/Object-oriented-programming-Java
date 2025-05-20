package Model;
//this class is used to store the players
public class DynamicArrayForPlayers {
	private Player[] array;
	private int sizeOfArray;
	
	//create a array with specific size and items inside
	public DynamicArrayForPlayers(int size,Player[] staticArray) {
		array=new Player[size];
		array=staticArray;
		sizeOfArray=size;
	}
	
	//create a array with one item inside
	public DynamicArrayForPlayers(Player n) {
		array=new Player[1];
		array[0]=n;
		sizeOfArray=1;
	}
	
	public DynamicArrayForPlayers() {
		array=null;
		sizeOfArray=0;
	}
	
	private void copyArray(Player[] newArray,Player[] oldArray,int statingPoint) {
		for(;statingPoint<sizeOfArray;statingPoint++) {
			newArray[statingPoint]=oldArray[statingPoint];
		}
	}
	
	//this grow the size by 1
	private void growSize() {
		Player[] temp=null;
		temp = new Player[sizeOfArray+1];
		copyArray(temp,array,0);
		sizeOfArray++;
		array=temp;
	}
	
	//this add one player to the list
	public void add(Player p) {
		growSize();
		array[sizeOfArray-1]=p;
	}

	
	/**********Getters/Setters************/
	public Player[] getArray() {
		return array;
	}

	public int getSizeOfArray() {
		return sizeOfArray;
	}

	public void setArray(Player[] array) {
		this.array = array;
	}
	
	
	
	
}
