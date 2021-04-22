package com.njupt.operation;

import it.unisa.dia.gas.jpbc.Pairing;

public class Group {
	private Pairing pair;
	
	public Group(Pairing pair) {
		this.pair = pair;
	}
	/**
	 * 
	 * @return one random Element from group G1
	 */
	public ElementG1 getRandElementG1() {
		return ElementG1.getRandElement(pair);
	}
	
	public ElementGT getValueElementGT(ElementGT g,ElementZr v){
		return ElementGT.pow(pair, g, v);
	}
	/**
	 * ʹ��ӳ������,������e(g1,g2)^value
	 * @param g1
	 * @param g2
	 * @param value
	 * @return
	 */
	public ElementGT getPairingElementGT(ElementG1 g1,ElementG1 g2,ElementZr value){
		return ElementGT.getValueElement(pair, g1, g2, value);
	}
	
	public ElementZr getRandElementZr(){
		return ElementZr.getRandElementZr(pair);
	}
	public ElementZr getValueElementZr(int v){
		return ElementZr.getValueElementZr(pair, v);
	}
	/**
	 * 自己改写的
	 * @param v
	 * @return
	 */
	public ElementG1 getValueElementG1(byte[] btmp) {
		return ElementG1.getValueElement(pair, btmp);
	}
	
	public Element mul(Element ... es) throws Exception{
		Element result = null;
		for (int i = 0; i < es.length; i++) {
			if (i ==0) 
				result = es[i];
			else {
				result = mul(result, es[i]);
			}
			
		}
		return result;
	}
	
	private Element mul(Element e1,Element e2) throws Exception{
		if (e1 instanceof ElementG1 && e2 instanceof ElementG1) {
			return ElementG1.mul(pair, (ElementG1)e1, (ElementG1)e2);
		}
		if (e1 instanceof ElementGT && e2 instanceof ElementGT) {
			return ElementGT.mul(pair, (ElementGT)e1, (ElementGT)e2);
		}
		if (e1 instanceof ElementZr && e2 instanceof ElementZr) {
			return ElementZr.mul(pair, (ElementZr)e1, (ElementZr)e2);
		}
		
		throw new Exception("e1  e2");
	}
	
	//自己改写的方法，用于GT×系数
	public  ElementGT mul2(ElementGT e1,Integer i) {
		return ElementGT.mul(pair, e1, i);
	}
	public Element div(Element e1,Element e2) throws Exception{
		if (e1 instanceof ElementG1 && e2 instanceof ElementG1) {
			return ElementG1.div(pair, (ElementG1)e1, (ElementG1)e2);
		}
		if (e1 instanceof ElementGT && e2 instanceof ElementGT) {
			return ElementGT.div(pair, (ElementGT)e1, (ElementGT)e2);
		}
		if (e1 instanceof ElementZr && e2 instanceof ElementZr) {
			return ElementZr.div(pair, (ElementZr)e1, (ElementZr)e2);
		}
		throw new Exception("e1 �� e2���Ͳ�ͬ��");
	}
	
	public Element add(Element e1,Element e2) throws Exception{
		if (e1 instanceof ElementG1 && e2 instanceof ElementG1) {
			return ElementG1.add(pair, (ElementG1)e1, (ElementG1)e2);
		}
		if (e1 instanceof ElementGT && e2 instanceof ElementGT) {
			return ElementGT.add(pair, (ElementGT)e1, (ElementGT)e2);
		}
		if (e1 instanceof ElementZr && e2 instanceof ElementZr) {
			return ElementZr.add(pair, (ElementZr)e1, (ElementZr)e2);
		}
		throw new Exception("e1 �� e2���Ͳ�ͬ��");
	}
	public ElementG1 getValueElementG1(ElementG1 g, ElementZr a) {
		return ElementG1.getValueElement(pair, g, a);
	}

}
