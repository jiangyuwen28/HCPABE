import java.util.Scanner;

import com.njupt.operation.ElementZr;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MakeSetup m=new MakeSetup();
		m.setup();
		
		DA a=new DA();
		a.CreateDA(m);
		//测试案例
		//daxue:njupt,sex:male;role:student;role:teacher
		Consumer c=new Consumer("jiang",a.getMki().getKeystructure());
		MKi mki=new MKi();
		mki=a.getMki();
		
		MKi sk=new MKi();
		sk=a.CreateUser(m, mki, c);
		Cyphertext test=new Cyphertext();
		ElementZr s_p=test.encrypt(m);
		System.out.println("秘密数值s："+s_p);
		
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入密文策略：");
		String s=sc.nextLine();
		test.setP(test.access_Structure(s));
		System.out.println("密文策略的门限值k："+test.getP().getK());
		test.fillPolicy(test.getP(), m, s_p);
		
		Thread thread=new Thread();
		thread.sleep(10000);
		
		Dec d1=new Dec();
		d1.decrypt(sk, test.getP());
		//d1.decrypt(mki, test.getP());
		boolean x=d1.check(test.getP());
		System.out.println("解密结果为："+x);
		if(x) {
			d1.getValue(test, mki, m);
		}
		
		
		
	}

}
