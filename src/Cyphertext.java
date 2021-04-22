

import java.util.ArrayList;

import com.njupt.operation.ElementG1;
import com.njupt.operation.ElementGT;
import com.njupt.operation.ElementZr;
import com.njupt.operation.Group;


import it.unisa.dia.gas.jpbc.Element;

//import com.njupt.operation.Element;

/**
 * 密文类，包含加密函数
 * @author 蒋余文
 *
 */
public class Cyphertext {
	private ElementG1 c1;//h1^s
	private ElementG1 c2;//h2^s
	private ElementGT cs;//e(g,g)^as
	private ElementGT cs1;//e(g,g)^s
	private Policy p;//密文的访问策略
	ElementGT[] ctext;//处理后的密文 C~=Me(g,g)^as
	Integer[] ct;//将要加密的密文转换成相应的ASCII码
	
	public ElementZr encrypt(MakeSetup ms) {
		//Element s;
		Group g=ms.getG();
		//获取随机s
		ElementZr s=g.getRandElementZr();
		this.c1=ms.getH1();
		this.c2=ms.getH2();
		this.c1=g.getValueElementG1(c1, s);
		this.c2=g.getValueElementG1(c2, s);
		this.cs1=g.getPairingElementGT(ms.getG1(), ms.getG1(), s);
		System.out.println("**************************加密******************************");
		System.out.println("s的值：");
		System.out.println(s);

		System.out.println("c1=h1^s的值：");
		System.out.println(c1);
		
		System.out.println("c2=h2^s的值：");
		System.out.println(c2);
		
		//测试要加密的密文，先用abc测试
		String message="abc";
		int l=message.length();
		
		ct=new Integer[l];
		for(int i=0;i<l;i++) {
			ct[i]=(int)message.charAt(i);
		}
		
		this.cs=ms.getGt();
		cs=g.getValueElementGT(cs, s);
		System.out.println("e(g,g)^as的值即cs的值为：");
		System.out.println(cs);
		
		//对信息进行加密
		ctext=new ElementGT[l];
		for(int i=0;i<l;i++) {
			ctext[i]=g.mul2(cs, ct[i]);
		}
		
		System.out.println("加密后的密文：");
		for(int i=0;i<l;i++) {
			System.out.println(ctext[i]);
		}
		
		return s;//s就是秘密数
	}
	
	Policy access_Structure(String cypherPolicy) {
		String[] toks;
        String tok;
        ArrayList<Policy>stack=new  ArrayList<>();
        toks=cypherPolicy.split(" ");
        int index;
        index = toks.length;
        
        for(int i=0;i<index;i++)
        {
            int k,n;
            tok=toks[i];
            //叶节点，属性节点
            if(!tok.contains("of"))
            {
                Policy node=new Policy();
                node.setAttr(tok);
                node.setK(1);
                stack.add(node);      
            }
            //非叶子节点，门限节点 k为门限值，n为孩子节点数量 形如1of2或门 2of2与门    kofn
            else
            {
                String[]again=tok.split("of");
                k=Integer.parseInt(again[0]);
                n=Integer.parseInt(again[1]);
                Policy node2=new Policy();
                node2.setAttr(null);
                node2.setK(k);
                node2.setChild(new Policy[n]);
                int it;
                for(it=n-1;it>=0;it--)
                {
                    node2.getChild()[it]=new Policy();
                    node2.getChild()[it]=stack.remove(stack.size()-1);
                }             
                stack.add(node2);
                
            }
        }
       setP(stack.get(0));
       return getP();     
	}
	
	
	void fillPolicy(Policy p,MakeSetup m,ElementZr e) {
		Group g=m.getG();
		ElementZr r=g.getRandElementZr();
		ElementZr t=g.getRandElementZr();
		ElementG1 h1=g.getRandElementG1();
		h1=m.getH1();
		ElementG1 h2=g.getRandElementG1();
		h2=m.getH2();
		
		p.setQ(randPoly(p.getK()-1,e,m));
		
		if(p.getChild()==null||p.getChild().length==0) {
			//属性节点
			p.setC1(g.getRandElementG1());
			p.setC2(g.getRandElementG1());
			
			p.setC1(m.getG1());
			p.setC1(g.getValueElementG1(p.getC1(), p.getQ().getCoef()[0]));
			p.setC2(m.getH1());
			p.setC2(g.getValueElementG1(p.getC2(), p.getQ().getCoef()[0]));
			
			
			
		}
		else {
			//门限节点
			p.setC3(g.getRandElementG1());
			p.setC3(g.getValueElementG1(h2,p.getQ().getCoef()[0] ));
			for(int i=0;i<p.getChild().length;i++) {
				r=r.set(i+1);
				evalPoly(t,p.getQ(),r,m);
				fillPolicy(p.getChild()[i],m,t);
				
			}
		}
		
	}
	
	
	Polynomial randPoly(int deg,ElementZr zeroVal,MakeSetup m) {
		Polynomial q=new Polynomial();
		q.setDegree(deg);
		q.setCoef(new ElementZr[q.getDegree()+1]);
		
		Group g=m.getG();
		for(int i=0;i<q.getDegree()+1;i++) {
			q.getCoef()[i]=g.getRandElementZr();
		}
		q.getCoef()[0]=zeroVal;//常数项，即是要保存的秘密数
			
		return q;
	}
	
	void evalPoly(ElementZr r,Polynomial q,ElementZr x,MakeSetup m) {
		Group g=m.getG();
		ElementZr s=g.getRandElementZr();
		ElementZr t=g.getRandElementZr();
		s=r;
		t=r;
		
		r=r.setToZero();
		t=t.setToOne();
		
		for(int i=0;i<q.getDegree()+1;i++) {
			s=new ElementZr(q.getCoef()[i]);
			s=q.getCoef()[i];
			try {
				s=(ElementZr) g.mul(s,t);
				r=(ElementZr) g.add(r, s);
				t=(ElementZr)g.mul(t,x);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}

	public Policy getP() {
		return p;
	}

	public void setP(Policy p) {
		this.p = p;
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

	public ElementGT getCs() {
		return cs;
	}

	public void setCs(ElementGT cs) {
		this.cs = cs;
	}

	public ElementGT[] getCtext() {
		return ctext;
	}

	public void setCtext(ElementGT[] ctext) {
		this.ctext = ctext;
	}

	public Integer[] getCt() {
		return ct;
	}

	public void setCt(Integer[] ct) {
		this.ct = ct;
	}

	public ElementGT getCs1() {
		return cs1;
	}

	public void setCs1(ElementGT cs1) {
		this.cs1 = cs1;
	}
	
}
