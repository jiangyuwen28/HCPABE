package com.njupt.ui;



import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Screen {
	private JFrame jframe,jframe1;
	private JPanel jpanel,jpanel1;
	private JLabel jlabel1, jlabel2,jlabel3,jlabel4;
	private JLabel jlabel11, jlabel22,jlabel33,jlabel44;
	private JTextField jtextfield1, jtextfield2,jtextfield3,jtextfield4;
	private JTextField jtextfield11, jtextfield22,jtextfield33,jtextfield44;
	private JButton jbutton,jbutton1,jbutton2;
	private JButton jbutton0,jbutton11,jbutton22;
	private JTextArea jtextarea1;
	// private JScrollPane MLScrollPane1;

	public void Doit() {

		jframe = new JFrame("基于用户分层的属性加密方案");
		jframe.setSize(1000, 800);
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		jframe1=new JFrame("基于用户分层的属性加密方案");
		jframe1.setSize(1000,800);
		jframe1.setLocationRelativeTo(null);
		jframe1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		jlabel1 = new JLabel("属性集个数：");
		jlabel1.setFont(new Font(null, Font.PLAIN, 25));
		jlabel2 = new JLabel("顶级域权限属性：");
		jlabel2.setFont(new Font(null, Font.PLAIN, 25));
		jlabel3 = new JLabel("密文策略：");
		jlabel3.setFont(new Font(null, Font.PLAIN, 25));
		jlabel4 = new JLabel("加密原文：");
		jlabel4.setFont(new Font(null, Font.PLAIN, 25));
		
		jlabel11 = new JLabel("可选属性：");
		jlabel11.setFont(new Font(null, Font.PLAIN, 25));
		jlabel22 = new JLabel("选择：");
		jlabel22.setFont(new Font(null, Font.PLAIN, 25));
		jlabel33 = new JLabel("用户名称");
		jlabel33.setFont(new Font(null, Font.PLAIN, 25));
		jlabel44 = new JLabel("解密结果：");
		jlabel44.setFont(new Font(null, Font.PLAIN, 25));
		

		jtextfield1 = new JTextField(20);
		jtextfield1.setFont(new Font(null, Font.PLAIN, 25));
		jtextfield2 = new JTextField(20);
		jtextfield2.setFont(new Font(null, Font.PLAIN, 25));
		jtextfield3 = new JTextField(20);
		jtextfield3.setFont(new Font(null, Font.PLAIN, 25));
		jtextfield4 = new JTextField(20);
		jtextfield4.setFont(new Font(null, Font.PLAIN, 25));
		//jtextfield2.setEditable(false);
		
		jtextfield11 = new JTextField(20);
		jtextfield11.setFont(new Font(null, Font.PLAIN, 25));
		jtextfield22 = new JTextField(20);
		jtextfield22.setFont(new Font(null, Font.PLAIN, 25));
		jtextfield33 = new JTextField(20);
		jtextfield33.setFont(new Font(null, Font.PLAIN, 25));
		jtextfield44 = new JTextField(20);
		jtextfield44.setFont(new Font(null, Font.PLAIN, 25));
		jtextfield44.setEditable(false);

		
		
		
		
		jbutton = new JButton("生成顶级域权限");
		jbutton.setFont(new Font(null, Font.PLAIN, 25));
		jbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 响应器
				String a = jtextfield1.getText();
				int attNum=Integer.parseInt(a);
				
				String b=jtextfield2.getText();
				
				//jframe.setVisible(false);
				//jframe1.setVisible(true);
				jtextfield2.setText("已生成顶级域权限");
				//jtextfield2.setText(b);
				jtextfield2.setEditable(false);

			}
		});

		jbutton1 = new JButton("加密");
		jbutton1.setFont(new Font(null, Font.PLAIN, 25));
		jbutton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ�����¼�Դ���ǰ�ť����
				String a = jtextfield1.getText();
				int attNum=Integer.parseInt(a);
				
				String b=jtextfield2.getText();
				
				jframe.setVisible(false);
				jframe1.setVisible(true);
				//jtextfield2.setText(" ");
				//jtextfield2.setText(b);

			}
		});
		
		jbutton2 = new JButton("生成下一级用户私钥");
		jbutton2.setFont(new Font(null, Font.PLAIN, 25));
		jbutton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ�����¼�Դ���ǰ�ť����
			
				jframe.setVisible(false);
				jframe1.setVisible(true);
			
			}
		});
		
		jbutton0 = new JButton("生成新用户私钥");
		jbutton0.setFont(new Font(null, Font.PLAIN, 25));
		jbutton0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ�����¼�Դ���ǰ�ť����
				String a = jtextfield1.getText();
				int attNum=Integer.parseInt(a);
				
				String b=jtextfield2.getText();
				
				jframe.setVisible(false);
				jframe1.setVisible(true);
				//jtextfield2.setText(" ");
				//jtextfield2.setText(b);

			}
		});

		jbutton11 = new JButton("解密：使用主密钥");
		jbutton11.setFont(new Font(null, Font.PLAIN, 25));
		jbutton11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ�����¼�Դ���ǰ�ť����
				String a = jtextfield1.getText();
				int attNum=Integer.parseInt(a);
				
				String b=jtextfield2.getText();
				
				jframe.setVisible(false);
				jframe1.setVisible(true);
				//jtextfield2.setText(" ");
				//jtextfield2.setText(b);

			}
		});
		
		jbutton22 = new JButton("解密：使用新用户私钥");
		jbutton22.setFont(new Font(null, Font.PLAIN, 25));
		jbutton22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡ�����¼�Դ���ǰ�ť����
				String a = jtextfield1.getText();
				int attNum=Integer.parseInt(a);
				
				String b=jtextfield2.getText();
				
				jframe.setVisible(false);
				jframe1.setVisible(true);
				//jtextfield2.setText(" ");
				//jtextfield2.setText(b);

			}
		});
		
		
		jtextarea1 = new JTextArea();
		jtextarea1.setLineWrap(true);
		jtextarea1.setFont(new Font(null, Font.PLAIN, 25));

		GridBagLayout gridBag = new GridBagLayout(); // ���ֹ�����
		GridBagConstraints c = null; // Լ��
		jpanel = new JPanel(gridBag);
		jpanel1 = new JPanel(gridBag);
		

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		c.weighty = 0.05;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jlabel1, c);
		gridBag.setConstraints(jlabel11, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		c.weighty = 0.05;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jlabel2, c);
		gridBag.setConstraints(jlabel22, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		c.weighty = 0.05;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jlabel3, c);
		gridBag.setConstraints(jlabel33, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		c.weighty = 0.05;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jlabel4, c);
		gridBag.setConstraints(jlabel44, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.8;
		c.weighty = 0.05;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jtextfield1, c);
		gridBag.setConstraints(jtextfield11, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		c.weighty = 0.05;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jtextfield2, c);
		gridBag.setConstraints(jtextfield22, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		c.weighty = 0.05;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jtextfield3, c);
		gridBag.setConstraints(jtextfield33, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		c.weighty = 0.05;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jtextfield4, c);
		gridBag.setConstraints(jtextfield44, c);

		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jbutton, c);
		gridBag.setConstraints(jbutton0, c);
		
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jbutton1, c);
		gridBag.setConstraints(jbutton11, c);
		
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.fill = GridBagConstraints.BOTH;
		gridBag.setConstraints(jbutton2, c);
		gridBag.setConstraints(jbutton22, c);

		jpanel.add(jlabel1);
		jpanel.add(jlabel2);
		jpanel.add(jlabel3);
		jpanel.add(jlabel4);
		jpanel.add(jtextfield1);
		jpanel.add(jtextfield2);
		jpanel.add(jtextfield3);
		jpanel.add(jtextfield4);
		jpanel.add(jbutton);
		jpanel.add(jbutton1);
		jpanel.add(jbutton2);
		
		jpanel1.add(jlabel11);
		jpanel1.add(jlabel22);
		jpanel1.add(jlabel33);
		jpanel1.add(jlabel44);
		jpanel1.add(jtextfield11);
		jpanel1.add(jtextfield22);
		jpanel1.add(jtextfield33);
		jpanel1.add(jtextfield44);
		jpanel1.add(jbutton0);
		jpanel1.add(jbutton11);
		jpanel1.add(jbutton22);
		
		
//		JCheckBox checkBoxes[]=new JCheckBox[10];
//		
//		for(int i=0;i<checkBoxes.length;i++) {
//			checkBoxes[i]=new JCheckBox(" "+i);
//			checkBoxes[i].addChangeListener(new ChangeListener() {
//	            public void stateChanged(ChangeEvent e) {
//	                // ��ȡ�¼�Դ������ѡ����
//	                JCheckBox checkBox = (JCheckBox) e.getSource();
//	                System.out.println(checkBox.getText() + " �Ƿ�ѡ��: " + checkBox.isSelected());
//	            }
//	        });
//			jpanel1.add(checkBoxes[i]);
//
//		}
		
		
		

		jframe.setContentPane(jpanel);
		jframe.setVisible(true);
		jframe1.setContentPane(jpanel1);
		jframe1.setVisible(false);
		
		
	}

	public static void main(String[] args) {
		Screen lan = new Screen();
		lan.Doit();
	}

}
