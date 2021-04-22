package com.njupt.operation;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

public class ElementZr extends com.njupt.operation.Element{

	Element e;//最好设置成私有属性，构造get函数获取
	
	public ElementZr(Element e) {
		// TODO Auto-generated constructor stub
		this.e=e;
	}
	
	public ElementZr(ElementZr elementZr) {
		// TODO Auto-generated constructor stub
		this.e=elementZr.e;
	}

	static ElementZr getRandElementZr(Pairing pair){
		return new ElementZr(pair.getZr().newRandomElement());
	}
	
	 static ElementZr getValueElementZr(Pairing pair,int value){
		return new ElementZr(pair.getZr().newElement(value));
	}
	 static ElementZr getValueElementZr(ElementZr value){
		return new ElementZr(value.e.invert());
	}
	
	 public  ElementZr setToZero() {
		 ElementZr tmp=new ElementZr(e);
		 return new ElementZr(tmp.e.setToZero());
	 }
	 
	 public  ElementZr setToOne() {
		 ElementZr tmp=new ElementZr(e);
		 return new ElementZr(tmp.e.setToOne());
	 }
	 
	 public ElementZr set(int i) {
		 ElementZr tmp=new ElementZr(e);
		 return new ElementZr(tmp.e.set(i));
	 }
	
	@Override
	public String toString() {
		return e.toString();
	}
	
	public boolean isEqual(ElementZr ob) {
			return e.isEqual(ob.e);
	}

	 static ElementZr mul(Pairing pair, ElementZr e1, ElementZr e2) {
		return new ElementZr(pair.getZr().newElement().set(e1.e).mul(e2.e));
	}

	 static ElementZr div(Pairing pair, ElementZr e1, ElementZr e2) {
		return new ElementZr(pair.getZr().newElement().set(e1.e).div(e2.e));
	}
	
	 static ElementZr add(Pairing pair, ElementZr e1, ElementZr e2) {
		 return new ElementZr(pair.getZr().newElement().set(e1.e).add(e2.e));
	 }


}
