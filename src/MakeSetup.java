import java.io.ByteArrayInputStream;

import com.njupt.operation.Element;
import com.njupt.operation.ElementG1;
import com.njupt.operation.ElementGT;
import com.njupt.operation.ElementZr;
import com.njupt.operation.Group;

//import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.parameters.PropertiesParameters;

/**
 * 构造双线性群
 * 构造公钥PK(pairing p,g1,h1,f1,h2,f2,e(g,g)^alpha)
 * 构造主密钥MK0=(beta1,beta2,g^alpha)
 * @author 蒋余文
 *
 */
public class MakeSetup {
	String pairingDesc;
	public Pairing p;
//	private Element g;
//	private Element g_alpha1;//g^alpha1
//	private Element h1;//g^beta1
//	private Element g_alpha2;//g^alpha2
//	private Element h2;//g^beta2
//	
//	//fi=g^(1/betai),仅用于delegation的时候
//	private Element f1;
//	private Element f2;
	
	private ElementZr beta1;
	private ElementZr beta2;
	private Group g;
	private ElementG1 g1;
	private ElementZr alpha;
	private ElementG1 h1;
	private ElementG1 h2;
	private ElementG1 f1;
	private ElementG1 f2;
	private ElementGT gt;
	
	private ElementG1 g_alpha;
	String curveParams = "type a\n" + "q 87807107996633125224377819847540498158068831994142082"
			+ "1102865339926647563088022295707862517942266222142315585"
			+ "8769582317459277713367317481324925129998224791\n"
			+ "h 12016012264891146079388821366740534204802954401251311"
			+ "822919615131047207289359704531102844802183906537786776\n"
			+ "r 730750818665451621361119245571504901405976559617\n" + "exp2 159\n" + "exp1 107\n" + "sign1 1\n"
			+ "sign0 1\n";
	
	void setup() throws Exception {
		// 设置参数，获取参数
		PropertiesParameters params = new PropertiesParameters().load(new ByteArrayInputStream(curveParams.getBytes()));
		// 读取参数，初始化双线性群
		pairingDesc = curveParams;
		p = PairingFactory.getPairing(params);
		Pairing pairing = p;
		this.g = new Group(pairing);
		this.g1=g.getRandElementG1();
		
		System.out.println("**************Setup函数（构造双线性群）:*****************");
		System.out.println("Element of group G1:");
		System.out.println("The value of g  is :");
		System.out.println(g1.toString());
		
		
		ElementZr alpha=g.getRandElementZr();
		//ElementZr alpha2=g.getRandElementZr();
		ElementZr beta1=g.getRandElementZr();
		ElementZr beta2=g.getRandElementZr();
		this.alpha=alpha;
		this.beta1=beta1;
		this.beta2=beta2;
		
		//得到1/beta1
		ElementZr m1=beta1;
		ElementZr n1=g.getValueElementZr(1);
		ElementZr temp1=(ElementZr)g.div(n1, m1);
		System.out.println("beta1:");
		System.out.println(m1);
		System.out.println("1/beta1:");
		System.out.println(temp1.toString());
		
		// 得到1/beta2
		ElementZr m2 = beta2;
		ElementZr n2 = g.getValueElementZr(1);
		ElementZr temp2 = (ElementZr) g.div(n2, m2);
		System.out.println("beta2:");
		System.out.println(m2);
		System.out.println("1/beta2:");
		System.out.println(temp2.toString());
		
		//得到h1=g^(beta1)
		this.h1=g.getValueElementG1(g1, beta1);
		//h2=g^(beta2)
		this.h2=g.getValueElementG1(g1, beta2);
		//f1=g^(1/beta1)
		this.f1=g.getValueElementG1(g1, temp1);
		//f2=g^(1/beta2)
		this.f2=g.getValueElementG1(g1, temp2);
		System.out.println("g:");
		System.out.println(g1.toString());
		System.out.println("h1:"+h1.toString());
		System.out.println("h2:"+h2.toString());
		System.out.println("f1:"+f1.toString());
		System.out.println("f2:"+f2.toString());
		
		
		
		System.out.println("alpha:");
		System.out.println(alpha.toString());
		
		this.g_alpha=g.getValueElementG1(g1, alpha);
		System.out.println("g^alpha:");
		System.out.println(g_alpha.toString());
		
		this.gt=g.getPairingElementGT(g1, g1, alpha);
		System.out.println("e(g1,g1)^alpha:");
		System.out.println(gt.toString());
		
		
		
	}

	public ElementZr getBeta1() {
		return beta1;
	}

	public void setBeta1(ElementZr beta1) {
		this.beta1 = beta1;
	}

	public ElementZr getBeta2() {
		return beta2;
	}

	public void setBeta2(ElementZr beta2) {
		this.beta2 = beta2;
	}

	public Group getG() {
		return g;
	}

	public void setG(Group g) {
		this.g = g;
	}

	public ElementG1 getG1() {
		return g1;
	}

	public void setG1(ElementG1 g1) {
		this.g1 = g1;
	}

	public ElementZr getAlpha() {
		return alpha;
	}

	public void setAlpha(ElementZr alpha) {
		this.alpha = alpha;
	}

	public ElementG1 getH1() {
		return h1;
	}

	public void setH1(ElementG1 h1) {
		this.h1 = h1;
	}

	public ElementG1 getH2() {
		return h2;
	}

	public void setH2(ElementG1 h2) {
		this.h2 = h2;
	}

	public ElementG1 getF1() {
		return f1;
	}

	public void setF1(ElementG1 f1) {
		this.f1 = f1;
	}

	public ElementG1 getF2() {
		return f2;
	}

	public void setF2(ElementG1 f2) {
		this.f2 = f2;
	}

	public ElementGT getGt() {
		return gt;
	}

	public void setGt(ElementGT gt) {
		this.gt = gt;
	}

	public ElementG1 getG_alpha() {
		return g_alpha;
	}

	public void setG_alpha(ElementG1 g_alpha) {
		this.g_alpha = g_alpha;
	}
	
	
	
	
}
