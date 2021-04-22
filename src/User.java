import com.njupt.operation.ElementG1;
import com.njupt.operation.ElementZr;

public class User {

	private String attribute;
	private ElementZr a;//rij
	private ElementZr b;//rij1
	private ElementG1 Dij;
	private ElementG1 Dij1;//Dij'
	private ElementG1 Dij11;//Dij~
	private ElementG1 Dij111;//Dij~'
	private int index;//记录所在的集合索引
	private ElementG1 Ei;
	private ElementG1 Ei1;
	private boolean v;//记录元素是否有效
	private ElementZr ri1;
	private ElementZr ri;
	private ElementG1 h;//记录它的Hash值
	
	
	public User(String attribute) {
		// TODO Auto-generated constructor stub
		this.attribute=attribute;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public ElementZr getA() {
		return a;
	}

	public void setA(ElementZr a) {
		this.a = a;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
	
	public ElementG1 getDij() {
		return Dij;
	}

	public void setDij(ElementG1 dij) {
		Dij = dij;
	}

	public ElementG1 getDij1() {
		return Dij1;
	}

	public void setDij1(ElementG1 dij1) {
		Dij1 = dij1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//System.out.println();
		return " 属性:"+this.attribute+" 所在集合："+index;
	}

	public ElementG1 getEi() {
		return Ei;
	}

	public void setEi(ElementG1 ei) {
		Ei = ei;
	}

	public boolean isV() {
		return v;
	}

	public void setV(boolean v) {
		this.v = v;
	}

	public ElementZr getRi1() {
		return ri1;
	}

	public void setRi1(ElementZr ri1) {
		this.ri1 = ri1;
	}

	public ElementZr getRi() {
		return ri;
	}

	public void setRi(ElementZr ri) {
		this.ri = ri;
	}

	public ElementG1 getH() {
		return h;
	}

	public void setH(ElementG1 h) {
		this.h = h;
	}

	public ElementZr getB() {
		return b;
	}

	public void setB(ElementZr b) {
		this.b = b;
	}

	public ElementG1 getDij11() {
		return Dij11;
	}

	public void setDij11(ElementG1 dij11) {
		Dij11 = dij11;
	}

	public ElementG1 getDij111() {
		return Dij111;
	}

	public void setDij111(ElementG1 dij111) {
		Dij111 = dij111;
	}

	public ElementG1 getEi1() {
		return Ei1;
	}

	public void setEi1(ElementG1 ei1) {
		Ei1 = ei1;
	}
	
	
}
