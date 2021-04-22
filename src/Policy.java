
import com.njupt.operation.ElementG1;

import it.unisa.dia.gas.jpbc.Element;

/**
 * 访问策略类
 * @author 蒋余文
 *
 */
public class Policy {
	private String attr;
    private int k;//门限值/阈值
    private Policy[]child;
    private ElementG1 c1;//Cy
    private ElementG1 c2;//Cy'
    private ElementG1 c3;//门限节点的C
    private Polynomial q;
    //策略是否被满足
    private boolean satisfy=false;
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public Policy[] getChild() {
		return child;
	}
	public void setChild(Policy[] child) {
		this.child = child;
	}
	public Polynomial getQ() {
		return q;
	}
	public void setQ(Polynomial q) {
		this.q = q;
	}
	public ElementG1 getC1() {
		return c1;
	}
	public void setC1(ElementG1 c1) {
		this.c1 = c1;
	}
	public ElementG1 getC2() {
		return c2;
	}
	public void setC2(ElementG1 c2) {
		this.c2 = c2;
	}
	public boolean isSatisfy() {
		return satisfy;
	}
	public void setSatisfy(boolean satisfy) {
		this.satisfy = satisfy;
	}
	public ElementG1 getC3() {
		return c3;
	}
	public void setC3(ElementG1 c3) {
		this.c3 = c3;
	}
	
}
