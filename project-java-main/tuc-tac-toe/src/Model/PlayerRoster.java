package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Controller.GController;

public class PlayerRoster {
	private DynamicArrayForPlayers listOfPlayers;
	private GController c;
	
	public PlayerRoster(GController c){
		this.c=c;
		listOfPlayers = new DynamicArrayForPlayers();
		readPlayersFromFile();
	}
	
	public String[] findPlayerName() {
		int num=listOfPlayers.getSizeOfArray();
		String[] names = new String[num];
		for(int i =0;i<num;i++) {
			names[i]=listOfPlayers.getArray()[i].getName();
		}
		return names;
	}
	
	public Player findPlayer(String name) {
		int num=listOfPlayers.getSizeOfArray();
		for(int i =0;i<num;i++) {
			if(name.equals(listOfPlayers.getArray()[i].getName())) {
				return listOfPlayers.getArray()[i];
			}
		}
		return null;
	}
	
	
	public void fromBestPlayerToWorst() {
		Player temp;
		int num=listOfPlayers.getSizeOfArray();
		for(int i=0; i < num; i++){  
            for(int j=1; j < (num-i); j++){  
                     if(listOfPlayers.getArray()[j-1].getScore() < listOfPlayers.getArray()[j].getScore()){  
                            //swap elements  
                            temp = listOfPlayers.getArray()[j-1];  
                            listOfPlayers.getArray()[j-1] = listOfPlayers.getArray()[j];  
                            listOfPlayers.getArray()[j] = temp;  
                    }  
                     
            }  
		} 
	}
	
	public Player[] getTheFirstPlayers(int n) {
		Player[] p = new Player[n];
		for(int i=0;i<n;i++) {
			p[i]=listOfPlayers.getArray()[i];
		}
		return p;
	}
	
	public Player[] findHallOfFame(int n) {
		Player[] p;
		if(listOfPlayers.getSizeOfArray()<n) {
			p= new Player[listOfPlayers.getSizeOfArray()];
			fromBestPlayerToWorst();
			p= getTheFirstPlayers(listOfPlayers.getSizeOfArray());
		}else {
			p= new Player[n];
			fromBestPlayerToWorst();
			p= getTheFirstPlayers(n);
		}
		return p;
	}
	
	public void addPlayer(Player p) {
		listOfPlayers.add(p);
	}

	public int getNumOfPlayers() {
		return listOfPlayers.getSizeOfArray();
	}
	
	public void readPlayersFromFile(){
		try {
		FileInputStream f=new FileInputStream("tuctactoe.sar");
		ObjectInputStream in = new ObjectInputStream(f);
		Player p;
		listOfPlayers= new DynamicArrayForPlayers();
		for(int i =0;i<f.available();i++) {
			p= (Player)in.readObject();
			listOfPlayers.add(p);	
		}
		in.close();
		f.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void savePlayersIntoFile() {
		try {
		FileOutputStream f = new FileOutputStream("tuctactoe.sar");
		ObjectOutputStream out = new ObjectOutputStream(f);
			for(int i=0;i<listOfPlayers.getSizeOfArray();i++) {
				out.writeObject(listOfPlayers.getArray()[i]);
			}
		out.close();
		f.close();
		}catch (Exception e) { 
			// TODO: handle exception
		}
	}

	public DynamicArrayForPlayers getListOfPlayers() {
		return listOfPlayers;
	}

	public GController getC() {
		return c;
	}

	public void setC(GController c) {
		this.c = c;
	}

	
	
}
