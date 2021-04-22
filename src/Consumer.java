import java.util.Scanner;

import com.njupt.operation.ElementZr;

/**
 * 实际上的用户类
 * @author 蒋余文
 *
 */
public class Consumer {
	private KeyStructure key;
	private String name;
	private ElementZr r1;
	
	public Consumer() {
		// TODO Auto-generated constructor stub
		this.name="unknow";
	}
	
	public  Consumer(String name,KeyStructure k) {
		this.name=name;
		
		//this.key=k;
		this.key=new KeyStructure();
		
		System.out.println("可供选择的元素有：");
		System.out.println(k.getComps().toString());
		Scanner reader=new Scanner(System.in);
		System.out.println("请输入还所需要的属性编号，-1表示选择结束：");
		int option=reader.nextInt();
		
		while(option==-1) {
			System.out.println("请至少选择一个元素：");
			option=reader.nextInt();
		}
		while(option!=-1) {
			User user=new User();
			user=k.getComps().get(option);
			this.key.getComps().add(user);
			System.out.println("请输入还所需要的属性编号，-1表示选择结束：");
			option=reader.nextInt();
		}
		
	}

	public KeyStructure getKey() {
		return key;
	}

	public void setKey(KeyStructure key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ElementZr getR1() {
		return r1;
	}

	public void setR1(ElementZr r1) {
		this.r1 = r1;
	}
	
	
}
