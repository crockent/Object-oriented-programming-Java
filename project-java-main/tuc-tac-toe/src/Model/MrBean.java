package Model;
import java.util.Random;

@SuppressWarnings("serial")
public class MrBean extends Player {
	Random r = new Random();
	PlayerRoster pr;
	
	public MrBean(PlayerRoster pr) {
		super(pr,"Mr.Bean");
	}
	
	public void makeTheMove(Board b) {
		int column,row;
		//column = r.nextInt(3);
		//row = r.nextInt(3);
		column = (int)((Math.random()*100)%3);
		row = (int)((Math.random()*100)%3);
		if(b.getBoard()[column][row]!=0) {
			//This spaces is used
			makeTheMove(b);
			return;
		}else {
			b.makeTheMove(column, row);
			return;
		}
	}
}
