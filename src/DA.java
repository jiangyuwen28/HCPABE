import java.util.ArrayList;
import java.util.Scanner;

import com.njupt.operation.ElementG1;
import com.njupt.operation.ElementZr;
import com.njupt.operation.Group;

import Utils.BytesUtil;

/**
 * domain authority顶级域权限
 * @author 蒋余文
 *
 */
public class DA {
	private String id;
	//private ArrayList<String> arr;
	private int attNum;
	//private HashMap<User,ElementZr> t;
	private ElementG1 d;
	private ElementG1[] Dij;
	private ElementG1[] Dij1;
	private ElementG1[] Ei;
	private ArrayList<User> comps;//顶级权限的属性
	private MKi mki;//密钥结构
	private ElementZr[] rij1;
	
	public MKi getMki() {
		return mki;
	}

	public void setMki(MKi mki) {
		this.mki = mki;
	}

	/**
	 * 创建顶级权限
	 * @param ms
	 */
	public void CreateDA(MakeSetup ms) {
		
		this.id=IDUtils.createID();
		System.out.println("域DA："+id+"MK构造过程");
		
		System.out.println("请输入属性集个数：");
		Scanner scanner=new Scanner(System.in);
		this.attNum=scanner.nextInt();
		
		System.out.println("请输入属性：");
		
		String string=scanner.next();
		String temp=string;
		
		String[] strs=temp.split(";");
//		while(strs.length!=attNum) {
//			System.out.println("输入错误！");
//			System.out.println("请输入属性：");
//			string=scanner.nextLine();
//			temp=string;
//			strs=temp.split(";");
//		}
		
		System.out.println("输入的元素集分别是：");
		int setNum=0;
		for(String tmp:strs) {
			System.out.println("集合A"+setNum+":   "+tmp);
			setNum++;
		}
		//setNum--;
		int[] everySetNum=new int[setNum];
		for(int i=0;i<everySetNum.length;i++) {
			everySetNum[i]++;
		}
		int index1=0;
		for(String str:strs) {
			for(int i=0;i<str.length();i++) {
				if(str.charAt(i)==',') {
					everySetNum[index1]++;
				}
			}
			index1++;
		}
		System.out.println("每个集合的元素个数分别是：");
		for(int i=0;i<everySetNum.length;i++) {
			System.out.println("A"+i+"中的元素个数"+everySetNum[i]);
		}
		
		Group g=ms.getG();
		//r^{u}随机数，标识域和A0集合
		//ri数组存取标识A0,A1...Am的随机数
		ElementZr r=g.getRandElementZr();
		ElementZr[] ri=new ElementZr[attNum];
		for(int i=1;i<attNum;i++) {
			ri[i]=g.getRandElementZr();
		}
		ri[0]=r;
		
		
		int num=0;//记录所有元素的个数
		for(int i=0;i<string.length();i++) {
			if(string.charAt(i)==','||string.charAt(i)==';') {
				num++;
			}
		}
		num++;
		System.out.println("所有属性元素个数："+num);
		
		//rij^{u}随机数，标识每个属性元素
		ElementZr[] rij=new ElementZr[num];
		for(int i=0;i<num;i++) {
			rij[i]=g.getRandElementZr();
		}
		
		//计算D值
		//!!!!!!!!!!!!可能有问题
		ElementZr x1=g.getRandElementZr();
		System.out.println("x1:");
		System.out.println(x1.toString());
		try {
			x1=(ElementZr)g.add(ms.getAlpha(), ri[0]);
			x1=(ElementZr)g.div(x1, ms.getBeta1());
			System.out.println("x1运算后:");
			System.out.println(x1.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("x1在作用域外：");
		System.out.println(x1.toString());
		ElementG1 g1=ms.getG1();
		this.d=g.getValueElementG1(g1, x1);
		
		//String[] allAttributes;
		//将属性集拆分开，变成一个个的例子属性元素
		ArrayList<String> allAttributes=new ArrayList<String>(num);
		ArrayList<User> user=new ArrayList<User>(num);
		String[] tmp3;
		int attSetNum=0;//标注元素所在的集合编号
		for(String tmp2:strs) {
			tmp3=tmp2.split(",");
			for(String tmp4:tmp3) {
				User u=new User(tmp4);
				u.setIndex(attSetNum);
				user.add(u);
				allAttributes.add(tmp4);
			}
			attSetNum++;
		}
		System.out.println("分隔后的粒子元素：");
		System.out.println(allAttributes.toString());
		System.out.println(user.toString());
		
		//这里的H(aij)哈希值生成ElemntG1元素是自己改写的工具包，不知道是否正确
		//提示报错，H还是有错误，在Dtmp那里有错误，自己先用一个随机的ElementG1元素代替了H(ai,j)的值
		this.Dij=new ElementG1[num];
		this.Dij1=new ElementG1[num];
		this.Ei=new ElementG1[attSetNum];
		
		System.out.println("为每个粒子元素计算相应的Dij和Dij'：");
		for(int i=0;i<user.size();i++) {
			User utmp=user.get(i);
			int indexTmp=utmp.getIndex();
			ElementG1 Dij=g.getValueElementG1(g1, ri[indexTmp]);
			
			byte[] btmp=BytesUtil.int2Bytes(utmp.getAttribute().hashCode());
			//ElementG1 Dtmp=g.getValueElementG1(utmp.getAttribute().hashCode());
			//ElementG1 Dtmp=g.getRandElementG1();
			ElementG1 Dtmp=g.getValueElementG1(btmp);
			utmp.setH(Dtmp);
			Dtmp=g.getValueElementG1(Dtmp, rij[i]);
			utmp.setA(rij[i]);
			try {
				Dij=(ElementG1)g.mul(Dij,Dtmp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ElementG1 Dij1=g.getValueElementG1(g1, rij[i]);
			//写回节点中
			utmp.setDij(Dij);
			utmp.setDij1(Dij1);
			this.Dij[i]=Dij;
			this.Dij1[i]=Dij;
			
		}
		
		//计算Ei的值!!!!!!!要写回
		for(int i=1;i<attSetNum;i++) {
			ElementZr tmp5=g.getRandElementZr();
			try {
				 tmp5=(ElementZr)g.add(ri[i], ri[0]);
				 tmp5=(ElementZr)g.div(tmp5, ms.getBeta2());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ElementG1 e=g.getValueElementG1(g1, tmp5);
			this.Ei[i]=e;
			
		}
		
		this.comps=user;
		
		User u=new User();
		for(int i=0;i<comps.size();i++) {
			u=comps.get(i);
			if(u.getIndex()!=0) {
				u.setEi(Ei[u.getIndex()]);
			}
		}
		
		
		
		boolean[] v=new boolean[num];
		for(int i=0;i<v.length;i++) {
			v[i]=true;
		}
		System.out.println("D:"+this.d);
		KeyStructure k = new KeyStructure(comps);
		//k.setComps(comps);
		k.setValid(v);
		
		this.mki=new MKi();
		this.mki.setD(d);
		this.mki.setDij(Dij);
		this.mki.setDij1(Dij1);
		this.mki.setEi(Ei);
		this.mki.setKeystructure(k);
		this.mki.setR(ri[0]);
		
	}
	
	/**
	 * 为用户u创建私钥SKu
	 *
	 * @param mki
	 * @param c
	 * @param k
	 */
	public MKi CreateUser(MakeSetup ms,MKi mki,Consumer c) {
		//为用户或域选取一个随机数r{u}1
		Group g=ms.getG();
		ElementZr r1=g.getRandElementZr();
		c.setR1(r1);
		
		//为每个集合选取一个ri{u}1,并回写回去
		//ElementZr[] ri1=new ElementZr[c.getKey().getSetNum()];
		ElementZr[] ri1=new ElementZr[mki.getKeystructure().getSetNum()];
		
		for(int i=0;i<ri1.length;i++) {
			ri1[i]=g.getRandElementZr();
		}
		ri1[0]=r1;
		User u1=new User();
//		for(int i=0;i<ri1.length;i++) {
//			u1=c.getKey().getComps().get(i);
//			u1.setRi1(ri1[u1.getIndex()]);
//		}
		for(int i=0;i<c.getKey().getComps().size();i++) {
			u1=c.getKey().getComps().get(i);
			u1.setRi1(ri1[u1.getIndex()]);
		}
		
		
		//为每个元素选取一个随机的rij{u}1
		ElementZr[] rij1=new ElementZr[c.getKey().getComps().size()];
		System.out.println("test2:属性个数 "+c.getKey().getComps().size());
		//System.out.println("属性集个数：");
		//rij1=new ElementZr[c.getKey().getAttNum()];
		for(int i=0;i<rij1.length;i++) {
			rij1[i]=g.getRandElementZr();
			u1=c.getKey().getComps().get(i);
			u1.setB(g.getRandElementZr());
			u1.setB(rij1[i]);
			//System.out.println("test2:"+u1.getB());
		}
		
		//计算D1值
		ElementG1 D1=g.getRandElementG1();
		D1=ms.getF1();
		D1=g.getValueElementG1(D1, r1);
		try {
			D1=(ElementG1)g.mul(this.d,D1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("用户"+c.getName()+"的D1值：");
		System.out.println(D1.toString());
		
		//计算每个粒子元素的Dij11
		ElementG1 Dij11=g.getRandElementG1();
		ElementG1[] Dij_11=new ElementG1[c.getKey().getComps().size()];
		for(int i=0;i<Dij_11.length;i++) {
			Dij_11[i]=g.getRandElementG1();
		}
		
		for(int i=0;i<c.getKey().getComps().size();i++) {
			u1=c.getKey().getComps().get(i);
			ElementG1 tmp1=g.getRandElementG1(); 
			//System.out.println("test:"+u1.getB());
			tmp1=g.getValueElementG1(u1.getH(), u1.getB());
			ElementG1 tmp2=g.getRandElementG1();
			//!!!!!!!!!!!!!!!!!!!!!!!
			tmp2=g.getValueElementG1(ms.getG1(), u1.getRi1());
			try {
				tmp1=(ElementG1) g.mul(tmp1,tmp2);
				Dij11=(ElementG1)g.mul(tmp1,u1.getDij());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			u1.setDij11(Dij11);//写回去
			Dij_11[i]=Dij11;
		}
		
		
		//计算每个粒子元素的Dij111
		ElementG1 Dij111=g.getRandElementG1();
		ElementG1[] Dij_111=new ElementG1[c.getKey().getComps().size()];
		for(int i=0;i<Dij_111.length;i++) {
			Dij_111[i]=g.getRandElementG1();
		}
		for(int i=0;i<c.getKey().getComps().size();i++) {
			u1=c.getKey().getComps().get(i);
			ElementG1 tmp1=g.getRandElementG1(); 
			tmp1=g.getValueElementG1(ms.getG1(), u1.getB());
			try {
				Dij111=(ElementG1) g.mul(tmp1,u1.getDij1());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			u1.setDij111(Dij111);
			Dij_111[i]=Dij111;
		}
		
		//计算每个非A0集合的Ei1
		ElementG1 Ei1=g.getRandElementG1();
		int maxSetNum=-1;
		for(int i=0;i<c.getKey().getComps().size();i++) {
			u1=c.getKey().getComps().get(i);
			if(u1.getIndex()>maxSetNum) {
				maxSetNum=u1.getIndex();
			}
		}
		ElementG1[] Ei_1=new ElementG1[maxSetNum+1];
		for(int i=0;i<Ei_1.length;i++) {
			Ei_1[i]=g.getRandElementG1();
		}
		for(int i=0;i<c.getKey().getComps().size();i++) {
			u1=c.getKey().getComps().get(i);
			if(u1.getIndex()!=0) {
				try {
					ElementZr tmp=g.getRandElementZr(); 
					tmp=(ElementZr) g.add(r1, u1.getRi1());
					ElementG1 tmp1=g.getRandElementG1(); 
					tmp1=g.getValueElementG1(ms.getF2(), tmp);
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					Ei1=(ElementG1) g.mul(u1.getEi(),tmp1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//ElementG1 tmp1=g.getValueElementG1(ms.getG1(), u1.getB());
				u1.setEi1(Ei1);
				Ei_1[u1.getIndex()]=Ei1;
			}
		}
		
		MKi sku=new MKi();
		sku.setD(D1);
		sku.setKeystructure(c.getKey());
		sku.setDij(Dij_11);
		sku.setDij1(Dij_111);
		sku.setEi(Ei_1);
		
		return sku;
	}
	
}
