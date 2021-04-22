package com.njupt.operation;

import java.math.BigInteger;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

public class ElementGT extends com.njupt.operation.Element {
	
	private Element e;

	public ElementGT(Element e){
		this.setE(e);
	}
	
	public BigInteger toInt() {
		return e.toBigInteger();
	}
	 static ElementGT pow(Pairing pair,ElementGT generator,ElementZr value){
		Element result = pair.getGT().newElement().set(generator.getE()).powZn(value.e);
		return new ElementGT(result);
	}
	 static ElementGT getValueElement(Pairing pair,ElementG1 g1,ElementG1 g2,ElementZr value){
		Element result = value == null ? pair.pairing(g1.e, g2.e) : pair.pairing(g1.e, g2.e).powZn(value.e);
		
		return new ElementGT(result);
	}
	 
	
	@Override
	public String toString() {
		return getE().toString();
	}
	
	public boolean isEqual(ElementGT ob) {
			return getE().isEqual(ob.getE());
	}

	 static ElementGT mul(Pairing pair, ElementGT e1, ElementGT e2) {
		return new ElementGT(pair.getGT().newElement().set(e1.getE()).mul(e2.getE()));
	}
	 
	 static ElementGT mul(Pairing pair, ElementGT e1, Integer i) {
			return new ElementGT(pair.getGT().newElement().set(e1.getE()).mul(i));
		}

	 static ElementGT div(Pairing pair, ElementGT e1, ElementGT e2) {
		// TODO Auto-generated method stub
		return new ElementGT(pair.getGT().newElement().set(e1.getE()).div(e2.getE()));
	}
	 
	 
	 static ElementGT add(Pairing pair, ElementGT e1, ElementGT e2) {
			// TODO Auto-generated method stub
			return new ElementGT(pair.getGT().newElement().set(e1.getE()).add(e2.getE()));
		}

	public Element getE() {
		return e;
	}

	public void setE(Element e) {
		this.e = e;
	}
	
	

}
