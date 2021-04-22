import com.njupt.operation.ElementGT;
import com.njupt.operation.Group;

public class Dec {
	public void decrypt(MKi sk,Policy p) {
		if(p.getAttr()!=null) {
			String at;
			at=p.getAttr();
			for(int i=0;i<sk.getKeystructure().getComps().size();i++) {
				if(at.compareTo(sk.getKeystructure().getComps().get(i).getAttribute())==0) {
					p.setSatisfy(true);
					return;
				}
			}
		}
		else {
			//非叶子节点
			for(int i=0;i<p.getChild().length;i++) {
				decrypt(sk,p.getChild()[i]);
			}
		}
	}
	
	boolean check(Policy p) {
		int count=0;
		for(int i=0;i<p.getChild().length;i++) {
			if(p.getChild()[i].isSatisfy()==true) {
				count++;
			}
			
		}
		System.out.println("满足的门限值为："+count);
		if(count>=p.getK()) {
			System.out.println("访问允许！");
			return true;
		}
		System.out.println("无权访问！");
		return false;
	}
	
	
	void getValue(Cyphertext t,MKi k,MakeSetup m) {
		System.out.println("尝试获取密文：");
		System.out.println("密钥相应的D值：");
		System.out.println(k.getD());
		
		Group g=m.getG();
		ElementGT tmp1=g.getPairingElementGT(t.getC1(), k.getD(), null);
		ElementGT tmp=g.getValueElementGT(t.getCs1(), k.getR());
		try {
			tmp1=(ElementGT) g.div(tmp1, tmp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//tmp1=t.getCs();
		
		System.out.println("tmp1:");
		System.out.println(tmp1);
		
		ElementGT[] tmp2=new ElementGT[t.getCtext().length];
		int[] tmp3=new int[t.getCtext().length];
		for(int i=0;i<tmp2.length;i++) {
			try {
				tmp2[i]=(ElementGT)g.div(t.getCtext()[i],tmp1);
				tmp3[i]=tmp2[i].toInt().intValue();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		StringBuffer tmp4=new StringBuffer();
		for(int i=0;i<tmp3.length;i++) {
			//System.out.println(tmp3[i]);
			tmp4.append((char)tmp3[i]);
		}
		System.out.println(tmp4);
		for(int i=0;i<tmp2.length;i++) {
			System.out.println(tmp2[i]);
		}
	}
	
}
