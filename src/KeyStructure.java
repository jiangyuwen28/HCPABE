import java.util.ArrayList;

/**
 * 密钥结构
 * @author 蒋余文
 *
 */
public class KeyStructure {
	private ArrayList<User> comps;
	private boolean[] valid;
	private int setNum;
	private int attNum;
	
	public KeyStructure() {
		this.comps=new ArrayList<User>();
	}
	
	public KeyStructure(ArrayList<User> comps) {
		this.comps=comps;
		this.attNum=comps.size();
		int tmp=-1;
		User u=new User();
		for(int i=0;i<comps.size();i++) {
			u=comps.get(i);
			if(tmp!=u.getIndex()) {
				setNum++;
				tmp=u.getIndex();
			}
		}
	}
	
	
	public ArrayList<User> getComps() {
		return comps;
	}
	public void setComps(ArrayList<User> comps) {
		this.comps = comps;
	}
	public boolean[] getValid() {
		return valid;
	}
	public void setValid(boolean[] valid) {
		this.valid = valid;
	}
	public int getSetNum() {
		setNum=0;
		int tmp=-1;
		User u=new User();
		for(int i=0;i<comps.size();i++) {
			u=comps.get(i);
			if(tmp!=u.getIndex()) {
				setNum++;
				tmp=u.getIndex();
			}
		}
		return setNum;
	}
	public void setSetNum(int setNum) {
		this.setNum = setNum;
	}
	public int getAttNum() {
		return attNum;
	}
	public void setAttNum(int attNum) {
		this.attNum = attNum;
	}
	
	
}
