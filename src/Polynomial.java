

import com.njupt.operation.ElementZr;

import it.unisa.dia.gas.jpbc.Element;

/**
 * 多项式
 * @author 蒋余文
 *树访问结构中的每个节点都将与一个多项式相关联
 */
public class Polynomial {
	private int degree;//多项式度数（最高次数）
	private ElementZr[] coef;//多项式系数数组
	public ElementZr[] getCoef() {
		return coef;
	}
	public void setCoef(ElementZr[] coef) {
		this.coef = coef;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	

}
