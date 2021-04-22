package com.njupt.operation;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

public class ElementG1 extends com.njupt.operation.Element {
	Element e;
	
	public ElementG1(Element e) {
		// TODO Auto-generated constructor stub
		this.e=e;
		
	}
	
	static ElementG1 getRandElement(Pairing pair) {
		return new ElementG1(pair.getG1().newRandomElement());
	}
	
	/**
	 * 不知道对不对
	 * @param pair
	 * @param arg0
	 * @return
	 */
	static ElementG1 getValueElement(Pairing pair,byte[] btmp) {
		//return new ElementG1(pair.getG1().newElement(arg0));
		return new ElementG1(pair.getG1().newElementFromHash(btmp,0, btmp.length-1));
	}
	
	static ElementG1 getValueElement(Pairing pair,ElementG1 generator,ElementZr value) {
		return pow(pair, generator, value);
	}
	
	static ElementG1 pow(Pairing pair,ElementG1 generator,ElementZr value){
		Element result = pair.getG1().newElement(generator.e).mulZn(value.e).getImmutable();
		return new ElementG1(result);
	}
	@Override
	public String toString() {
		return e.toString();
	}
	
	public boolean isEqual(ElementG1 ob) {
			return e.isEqual(ob.e);
	}
	
	static ElementG1 mul(Pairing pair,ElementG1 e1,ElementG1 e2){
		return new ElementG1(pair.getG1().newElement().set(e1.e).mul(e2.e));
	}

	public static ElementG1 div(Pairing pair, ElementG1 e1, ElementG1 e2) {
		return new ElementG1(pair.getG1().newElement().set(e1.e).div(e2.e));
	}
	
	public static ElementG1 add(Pairing pair, ElementG1 e1, ElementG1 e2) {
		return new ElementG1(pair.getG1().newElement().set(e1.e).add(e2.e));
	}

	
}
