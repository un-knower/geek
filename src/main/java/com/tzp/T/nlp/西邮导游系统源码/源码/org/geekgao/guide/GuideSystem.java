package com.tzp.T.nlp.西邮导游系统源码.源码.org.geekgao.guide;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GuideSystem extends JFrame{

	//������Ͻ�ͼ��
	private Image icon = GuideUtil.getImage("images/icon.png");
	final private JButton firstPanelDownBut1 = new JButton("���·��");
	final private JButton firstPanelDownBut2 = new JButton("������·");
	final private JButton rightBut1 = new JButton("·����ѯ");
	final private JButton rightBut2 = new JButton("������Ϣ");
	final private JButton rightBut3 = new JButton("��ӽڵ�");
	final private JButton rightBut4 = new JButton("ɾ���ڵ�");
	final private JButton rightBut5 = new JButton("��Ӿ���");
	final private JButton rightBut6 = new JButton("ɾ������");
	final private JButton rightBut7 = new JButton("ͼ���㷨");
	private JPanel cardPanel;//���ɺܶ�panel�Ŀ�Ƭpanel
	private CardLayout card;//��Ƭ���ֹ�����
	JComboBox<String> firstPanelDownBox1;
	JComboBox<String> firstPanelDownBox2;
	
	/**
	 * ������洢��һ��·����������е㣬����������֮�仭�߱�ʾ·��
	 * �洢һ��·���ϵĽڵ�����
	 */
	private Integer[] paintVertex;
	
	/**
	 * ������������ͼƬ������ԭ��
	 * windows7���ǣ�3,25��
	 * Linux�������û�У������ƽ̨���
	 */
	private static final int borderWidth = 3;
	private static final int borderHeight = 25;
	
	private Map<Integer,Vertex> map;//��ͼ
	private Map<String,String> view;//��������������ľ���ĵ����źͼ���Ӧ
	private Map<Integer,String> viewNumNameMap;//������ź����ƶ�Ӧ
	private JLabel secondPanelLab;
	private JList<String> secondPanelList;
	private JLabel thirdPanelUpMapLabel;
	private JLabel thirdPanelDownLab2;
	private JTextField thirdPanelDownText1;
	private JButton thirdPanelDownButton1;
	private JPanel thirdPanelUp;
	private JTextField fourthPanelDownText;
	private JButton fourthPanelDownButton;//ȷ��ɾ���ڵ㰴ť
	
	private JButton clickedButton;//��Ǳ����µ����ĸ���ť
	private JTextField fifthPanelDownText1;
	private JTextField fifthPanelDownText2;
	private JButton fifthPanelDownButton;
	private JTextField fifthPanelDownText3;
	private JTextField sixthPanelDownText;
	private JButton sixthPanelDownButton;
	
	private static final String vertexPath = "C:/Users/geekgao/Desktop/�γ�������/data.txt";
	private static final String viewPath = "C:/Users/geekgao/Desktop/�γ�������/view.txt";
	private JButton sevenPanelBfsButton;
	private JButton sevenPanelDfsButton;
	
	public static void main(String[] args) {
		
		GuideSystem guide = new GuideSystem();
		guide.paintGuideWindow();
		
	}
	
	public GuideSystem() {
		
		map = GuideUtil.getVertex(vertexPath);//����ڵ���Ϣ
		view = GuideUtil.getView(viewPath);//���뾰����Ϣ
		
		Set<String> viewNameSet = view.keySet();
		viewNumNameMap = new HashMap<Integer, String>();
		for (String name:viewNameSet) {
			viewNumNameMap.put(Integer.valueOf(view.get(name).split(" ")[0]),name);
		}
		
	}
	
	@Override
	/**
	 * ����·��
	 */
public void paint(Graphics g) {
		
		super.paint(g);
		Color c = g.getColor();
		g.setColor(Color.RED);
		float lineWidth = 4.0f;//�������
	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
		
	    //���µ���·����ѯ
	    if (clickedButton == rightBut1 ||
	    	clickedButton == firstPanelDownBut1) {
	    	if (paintVertex == null) {
				return;
			}
			
		    for (int i = 0;i + 1 < paintVertex.length;i++) {
		    	g.drawLine(	map.get(paintVertex[i]).x + borderWidth, 
		    				map.get(paintVertex[i]).y + borderHeight, 
		    				map.get(paintVertex[i+1]).x + borderWidth, 
		    				map.get(paintVertex[i+1]).y + borderHeight);
		    }
		    g.setFont(new Font("΢���ź�", Font.BOLD, 15));
		    g.setColor(Color.YELLOW);
		    g.drawString("���", 	map.get(paintVertex[0]).x + borderWidth, 
		    					map.get(paintVertex[0]).y + borderHeight);
		    g.drawString("�յ�", 	map.get(paintVertex[paintVertex.length - 1]).x + borderWidth, 
		    					map.get(paintVertex[paintVertex.length - 1]).y + borderHeight);
	    } else if (	clickedButton == rightBut3 || 
	    			clickedButton == rightBut4 || 
	    			clickedButton == rightBut5 ||
	    			clickedButton == rightBut6 ||
	    			clickedButton == fourthPanelDownButton || 
	    			clickedButton == thirdPanelDownButton1 ||
	    			clickedButton == fifthPanelDownButton  ||
	    			clickedButton == sixthPanelDownButton) {
	    	Set<Integer> vexNum = map.keySet();
	    	Set<String> viewName = view.keySet();
	    	Set<Integer> viewNum = new HashSet<Integer>();//�洢����ı�ţ�����ĵ���ɫҪ���ֳ���
	    	
	    	for (String s:viewName) {
	    		String introduce = view.get(s);
	    		viewNum.add(Integer.valueOf(introduce.split(" ")[0]));
	    	}
	    	
	    	Vertex t;
	    	int r = 3;//��Բ�İ뾶
	    	//����ͼ�Ľڵ㣬��ÿ���ڵ��ϻ�һ����
	    	for (Integer i:vexNum) {
	    		t = map.get(i);
	    		if (viewNum.contains(i)) {
	    			g.setColor(Color.YELLOW);
	    		} else {
	    			g.setColor(Color.RED);
	    		}
	    		g.fillOval(t.x + borderWidth - r, t.y + borderHeight - r, 2 * r, 2 * r);
	    	}
	    	g.setFont(new Font("΢���ź�", Font.BOLD, 12));
		    g.setColor(Color.BLACK);
		    
		    //��������ӡ������
		    for (Integer i:vexNum) {
	    		t = map.get(i);
	    		g.drawString(String.valueOf(i), t.x + borderWidth, t.y + borderHeight);
	    	}
	    }
	    
		g.setColor(c);
		
	}
	
	public void paintGuideWindow() {
		
		setTitle("���ʵ���ϵͳ - geekgao");
		setIconImage(icon);
		/**
		 * ���ڹر�
		 */
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
//		===============================================================
		/**
		 * ��ƴ���
		 */
		Container c = getContentPane();//��ȡ�����������
		card = new CardLayout();
		cardPanel = new JPanel(card);//���Ŀ�Ƭpanel
		JPanel controlPanel = new JPanel(new GridLayout(10,1,0,5));//�Ҳ�Ŀ��ƿ�Ƭpanel���м�Ű�ť
		c.add(cardPanel,BorderLayout.WEST);
		c.add(controlPanel,BorderLayout.EAST);
		
		/**
		 * ���ư�ť
		 */
		controlPanel.add(rightBut1);
		controlPanel.add(rightBut2);
		controlPanel.add(rightBut3);
		controlPanel.add(rightBut4);
		controlPanel.add(rightBut5);
		controlPanel.add(rightBut6);
		controlPanel.add(rightBut7);
		rightBut1.addActionListener(new MyButtonActionListener());
		rightBut2.addActionListener(new MyButtonActionListener());
		rightBut3.addActionListener(new MyButtonActionListener());
		rightBut4.addActionListener(new MyButtonActionListener());
		rightBut5.addActionListener(new MyButtonActionListener());
		rightBut6.addActionListener(new MyButtonActionListener());
		rightBut7.addActionListener(new MyButtonActionListener());
		
		/**
		 * ��ණ��ÿ������panel���ٸ����panel������Ӷ���
		 */
		
		/**
		 * ��һ����Ƭҳ
		 */
		JPanel firstPanel = new JPanel(new BorderLayout());
		cardPanel.add(firstPanel);//��ǰpanel�ӵ�cardPanel����
		
		JPanel firstPanelUp = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		firstPanel.add(firstPanelUp,BorderLayout.NORTH);
		JLabel firstPanelMapLabel = new JLabel(new ImageIcon("src/images/map.jpg"));
		firstPanelUp.add(firstPanelMapLabel);
		
		/**
		 * �����������ѡ��ص���һ��
		 */
		JPanel firstPanelDown = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		firstPanel.add(firstPanelDown,BorderLayout.SOUTH);
		JLabel firstPanelDownlab1 = new JLabel("���");
		firstPanelDown.add(firstPanelDownlab1);
		firstPanelDownBox1 = new JComboBox<String>();
		firstPanelDown.add(firstPanelDownBox1);
		JLabel firstPanelDownlab2 = new JLabel("�յ�");
		firstPanelDown.add(firstPanelDownlab2);
		firstPanelDownBox2 = new JComboBox<String>();
		firstPanelDown.add(firstPanelDownBox2);
		setViewBox();
		
		firstPanelDown.add(firstPanelDownBut1);
		firstPanelDown.add(firstPanelDownBut2);
		firstPanelDownBut1.addActionListener(new MyButtonActionListener());
		firstPanelDownBut2.addActionListener(new MyButtonActionListener());
		
		/**
		 * �ڶ�����Ƭҳ
		 */
		JPanel secondPanel = new JPanel(new BorderLayout());
		cardPanel.add(secondPanel);

		secondPanelList = new JList<String>();
		secondPanelList.setModel(new DefaultListModel<String>());
		JScrollPane scrollPane = new JScrollPane(secondPanelList);//�������б�
		secondPanel.add(scrollPane,BorderLayout.WEST);
		
		secondPanelLab = new JLabel();
		secondPanelLab.setHorizontalAlignment(JLabel.CENTER);
		secondPanel.add(secondPanelLab,BorderLayout.CENTER);
		
		setViewNameList();
		
		//��List�����굥���¼�
		secondPanelList.addMouseListener(new MyMouseListener());
		
		/**
		 * ��������Ƭҳ
		 */
		JPanel thirdPanel = new JPanel(new BorderLayout());
		cardPanel.add(thirdPanel);
		
		thirdPanelUp = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		thirdPanel.add(thirdPanelUp);
		thirdPanelUpMapLabel = new JLabel(new ImageIcon("src/images/map.jpg"));
		thirdPanelUp.add(thirdPanelUpMapLabel,FlowLayout.LEFT);
		thirdPanelUpMapLabel.addMouseListener(new MyMouseListener());//�����ͼƬLabel�������¼�����
		
		JPanel thirdPanelDown = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		thirdPanel.add(thirdPanelDown,BorderLayout.SOUTH);
		JLabel thirdPanelDownLab1 = new JLabel("��ͼѡ���:");
		thirdPanelDown.add(thirdPanelDownLab1);
		thirdPanelDownLab2 = new JLabel();
		thirdPanelDown.add(thirdPanelDownLab2);
		JLabel thirdPanelDownLab3= new JLabel("����Щ������ϵ(���Ÿ���):");
		thirdPanelDown.add(thirdPanelDownLab3);
		thirdPanelDownText1 = new JTextField(14);
		thirdPanelDown.add(thirdPanelDownText1);
		thirdPanelDownButton1 = new JButton("ȷ�����");
		thirdPanelDown.add(thirdPanelDownButton1);
		thirdPanelDownButton1.addActionListener(new MyButtonActionListener());
		
		/**
		 * ���ĸ���Ƭҳ
		 */
		JPanel fourthPanel = new JPanel(new BorderLayout());
		cardPanel.add(fourthPanel);
		
		JPanel fourthPanelUp = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		fourthPanel.add(fourthPanelUp,BorderLayout.NORTH);
		JLabel fourthPanelUpMapLabel = new JLabel(new ImageIcon("src/images/map.jpg"));
		fourthPanelUp.add(fourthPanelUpMapLabel,FlowLayout.LEFT);
		JPanel fourthPanelDown = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		fourthPanel.add(fourthPanelDown,BorderLayout.SOUTH);
		JLabel fourthPanelDownLabel = new JLabel("Ҫɾ���Ľڵ����(���Ÿ���):");
		fourthPanelDown.add(fourthPanelDownLabel);
		fourthPanelDownText = new JTextField(15);
		fourthPanelDown.add(fourthPanelDownText);
		fourthPanelDownButton = new JButton("ȷ��ɾ��");
		fourthPanelDown.add(fourthPanelDownButton);
		fourthPanelDownButton.addActionListener(new MyButtonActionListener());
		
		/**
		 * �������Ƭҳ
		 */
		JPanel fifthPanel = new JPanel(new BorderLayout());
		cardPanel.add(fifthPanel);
		
		JPanel fifthPanelUp = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		fifthPanel.add(fifthPanelUp,BorderLayout.NORTH);
		JLabel fifthPanelUpMapLabel = new JLabel(new ImageIcon("src/images/map.jpg"));
		fifthPanelUp.add(fifthPanelUpMapLabel,FlowLayout.LEFT);
		JPanel fifthPanelDown = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		fifthPanel.add(fifthPanelDown,BorderLayout.SOUTH);
		JLabel fifthPanelDownLabel1 = new JLabel("����ڵ����:");
		fifthPanelDown.add(fifthPanelDownLabel1);
		fifthPanelDownText1 = new JTextField(2);
		fifthPanelDown.add(fifthPanelDownText1);
		JLabel fifthPanelDownLabel2 = new JLabel("��������:");
		fifthPanelDown.add(fifthPanelDownLabel2);
		fifthPanelDownText2 = new JTextField(7);
		fifthPanelDown.add(fifthPanelDownText2);
		JLabel fifthPanelDownLabel3 = new JLabel("�������:");
		fifthPanelDown.add(fifthPanelDownLabel3);
		fifthPanelDownText3 = new JTextField(10);
		fifthPanelDown.add(fifthPanelDownText3);
		fifthPanelDownButton = new JButton("ȷ�����");
		fifthPanelDown.add(fifthPanelDownButton);
		fifthPanelDownButton.addActionListener(new MyButtonActionListener());
		
		/**
		 * ��������Ƭҳ
		 */
		JPanel sixthPanel = new JPanel(new BorderLayout());
		cardPanel.add(sixthPanel);
		
		JPanel sixthPanelUp = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		sixthPanel.add(sixthPanelUp,BorderLayout.NORTH);
		JLabel sixthPanelUpMapLabel = new JLabel(new ImageIcon("src/images/map.jpg"));
		sixthPanelUp.add(sixthPanelUpMapLabel,FlowLayout.LEFT);
		JPanel sixthPanelDown = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		sixthPanel.add(sixthPanelDown,BorderLayout.SOUTH);
		JLabel sixthPanelDownLabel = new JLabel("Ҫɾ���ľ������(��ɫ�������)(���Ÿ���):");
		sixthPanelDown.add(sixthPanelDownLabel);
		sixthPanelDownText = new JTextField(15);
		sixthPanelDown.add(sixthPanelDownText);
		sixthPanelDownButton = new JButton("ȷ��ɾ��");
		sixthPanelDown.add(sixthPanelDownButton);
		sixthPanelDownButton.addActionListener(new MyButtonActionListener());
		
		/**
		 * ���߸���Ƭҳ
		 * �㷨ҳ
		 */
		JPanel seventhPanel = new JPanel(new FlowLayout());
		cardPanel.add(seventhPanel);
		
		sevenPanelBfsButton = new JButton("BFS�㷨");
		sevenPanelDfsButton = new JButton("DFS�㷨");
		
		seventhPanel.add(sevenPanelDfsButton);
		seventhPanel.add(sevenPanelBfsButton);
		
		MyButtonActionListener buttonListener = new MyButtonActionListener();
		sevenPanelBfsButton.addActionListener(buttonListener);
		sevenPanelDfsButton.addActionListener(buttonListener);
		
		
//		===============================================================
		
		setResizable(false);//���ɸ��Ĵ�С
		setVisible(true);
		pack();
		setLocationRelativeTo(null);//��仰����pack()���棬����ʼ���ڴ�СΪ0�����Ͻ�����Ļ�е�
		
	}
	
	public int getStartNum() {
		String viewNameStart = (String) firstPanelDownBox1.getSelectedItem();
		String viewLastStart = view.get(viewNameStart);
		return Integer.parseInt((viewLastStart.split(" "))[0]);
	}
	
	public int getEndNum() {
		String viewNameEnd = (String) firstPanelDownBox2.getSelectedItem();
		String viewLastEnd = view.get(viewNameEnd);
		return Integer.parseInt((viewLastEnd.split(" "))[0]);
	}
	
	public int getMaxNum() {
		/**
		 * ������Ľڵ���
		 */
		Set<Integer> vexNum = map.keySet();
		int max = 0;//��Ŵ�1��ʼ
		for (Integer i:vexNum) {
			if (max < i) {
				max = i;
			}
		}
		
		return max;
	}
	
	public void setViewBox() {
		
		firstPanelDownBox1.removeAllItems();
		firstPanelDownBox2.removeAllItems();
		
		Set<String> viewName = view.keySet();
		for (String s:viewName) {
			firstPanelDownBox1.addItem(s);
			firstPanelDownBox2.addItem(s);
		}
	}
	
	private void setViewNameList() {
		
		DefaultListModel<String> model = (DefaultListModel<String>) secondPanelList.getModel();
		model.clear();
		
		Set<String> viewNameSet = view.keySet();
		for (String viewName:viewNameSet) {
			model.addElement(viewName);
		}
		secondPanelLab.setText("");//���ܾ���ɾ����ԭ�Ȳ鿴���Ǹ����ݣ�������Ҫɾ��Label�е�����
	}
	
	class MyButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			clickedButton = (JButton) e.getSource();
			if (clickedButton == firstPanelDownBut1) {
				/**
				 * ���·���İ�ť
				 */
				int numStart = getStartNum();
				int numEnd = getEndNum();
				int count = getMaxNum();//��Ϊ�����±��·��������Ӧ�����Խ���������Ĵ�СӦ�ð����������
				paintVertex = GuideAlgorithm.Dijkstra(numStart, numEnd, count,map);
				
				if (paintVertex == null) {
					JOptionPane.showMessageDialog(null, "û��·�����Ե������������յ���ͬ����ȷ�����нڵ㶼��ͨ��");
				}
				
				repaint();
			} else if (clickedButton == firstPanelDownBut2) {
				/**
				 * ����·�߰�ť
				 */
				paintVertex = null;
				repaint();
			} else if (clickedButton == thirdPanelDownButton1) {
				/**
				 * ȷ����ӽڵ�İ�ť
				 */
				
				String xy = thirdPanelDownLab2.getText();
				String relationNum = thirdPanelDownText1.getText();
				if (xy.equals("") || relationNum.equals("")) {
					JOptionPane.showMessageDialog(null, "������δ����!");
					return;
				}
				
				Set<Integer> relationNumSet = new HashSet<Integer>();
				String[] relationNumArray = relationNum.split(",");
				for (int i = 0;i < relationNumArray.length;i++) {
					try {
						relationNumSet.add(Integer.valueOf(relationNumArray[i]));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "���벻��ȷ��ֻ����������!");
						return;
					}
				}
				
				//����û�д�ĵ㲻���ڣ���ô����ʹ֮��ӳɹ�
				for (Integer i:relationNumSet) {
					if (!map.containsKey(i)) {
						JOptionPane.showMessageDialog(null, "�������˵�ͼ�ϲ����ڵĵ�!");
						return;
					}
				}
				thirdPanelDownText1.setText("");
				
				int vexNum;//������ӵ�ͼ�еĽڵ����
				for (vexNum = 1;map.containsKey(vexNum);vexNum++);//ѡ��һ����С�Ĳ���δ����ͼ�Ľڵ����
				
				Vertex newVex = new Vertex();
				newVex.num = vexNum;
				newVex.x = Integer.parseInt(xy.split(",")[0]);
				newVex.y = Integer.parseInt(xy.split(",")[1]);
				newVex.pointNum = new HashMap<Integer, Integer>();
				
				//�����¼���ĵ����Ϣ
				for (Integer i:relationNumSet) {
					int x = map.get(i).x;
					int y = map.get(i).y;
					int distance = (int) Math.sqrt((x-newVex.x) * (x-newVex.x) + (y-newVex.y) * (y-newVex.y));
					newVex.pointNum.put(i, distance);
				}
				map.put(newVex.num, newVex);
				
				//�޸����¼ӵĵ��й�ϵ�ĵ����Ϣ����Щ��Ҳ���¼ӵĵ��й�ϵ
				for (Integer i:relationNumSet) {
					Vertex t = map.get(i);
					t.pointNum.put(newVex.num, newVex.pointNum.get(i));
					map.put(i, t);
				}
				
				GuideUtil.setVertex(map, vertexPath);
				repaint();
			} else if (clickedButton == fourthPanelDownButton) {
				/**
				 * ȷ��ɾ���ڵ㰴ť
				 */
				
				if (fourthPanelDownText.getText().equals("")) {
					return;
				}
				
				String[] deleteNumStr = fourthPanelDownText.getText().split(",");
				Set<Integer> deleteNum = new HashSet<Integer>();
				for (String s:deleteNumStr) {
					try{
						deleteNum.add(Integer.valueOf(s));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "���벻��ȷ��ֻ���������ֺͶ���!");
						return;
					}
				}
				fourthPanelDownText.setText("");
				
//				ɾ����������Ϣ,����������ɾ���ĵ����ŵļ���
				for (Integer i:deleteNum) {
					//ͼ��ɾ�������
					if (map.containsKey(i)) {
						map.remove(i);
					}
				}
				
				Set<Integer> vexNum = map.keySet();//ͼ�еĵ�����
				Map<Integer,Integer> pointNum;//ĳ���ڵ�ָ��ʲô��
				for (Integer i:vexNum) {
					pointNum = map.get(i).pointNum;
					for (Integer j:deleteNum) {
						if (pointNum.containsKey(j)) {
							pointNum.remove(j);
						}
					}
				}
				GuideUtil.setVertex(map, vertexPath);
				
				//����ڵ������һ�����㣬�����н�����Ϣ��ҲҪɾ��
				Set<String> viewName = view.keySet();
				String t;
				for (Iterator<String> it = viewName.iterator();it.hasNext();) {
					t = view.get(it.next()).split(" ")[0];//�õ������������
					if (deleteNum.contains(Integer.valueOf(t))) {
						it.remove();
					}
				}
				GuideUtil.setView(view, viewPath);
				
				setViewBox();//������ʾ����BOX
				setViewNameList();//������ʾѡ��ص��������������
				
				paintVertex = null;//ɾ���ڵ��Ͳ���ʾ·���ˣ���ʱԭ��ѡ���·���������յ㻹��
				
				repaint();
			} else if (clickedButton == fifthPanelDownButton) {
				/**
				 * ȷ����Ӿ��㰴ť
				 */
				
				String viewNum = fifthPanelDownText1.getText();
				String viewName = fifthPanelDownText2.getText();
				String viewIntroduce = fifthPanelDownText3.getText();
				if (viewNum.equals("") || viewName.equals("") || viewIntroduce.equals("")) {
					JOptionPane.showMessageDialog(null, "������δ����!");
					return;
				}
				
				//�㲻���ڲ�����
				try {
					if (!map.containsKey(Integer.valueOf(viewNum))) {
						JOptionPane.showMessageDialog(null, "�ڵ㲻����!");
						return;
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "�ڵ�������벻��ȷ��ֻ����������!");
					return;
				}
				
				Set<String> viewNameSet = view.keySet();
				for (String name:viewNameSet) {
					if (view.get(name).split(" ")[0].equals(viewNum)) {
						JOptionPane.showMessageDialog(null, "�˽ڵ����Ǿ��㣬Ҫ������ɾ���������!");
						return;
					}
				}
				
				fifthPanelDownText1.setText("");
				fifthPanelDownText2.setText("");
				fifthPanelDownText3.setText("");
				
				view.put(viewName, viewNum + " " +viewIntroduce);
				
				GuideUtil.setView(view, viewPath);
				
				setViewBox();//������ʾ����BOX
				setViewNameList();//������ʾѡ��ص��������������
				
				repaint();
			} else if (clickedButton == sixthPanelDownButton) {
				/**
				 * ȷ��ɾ�����㰴ť
				 */
				
				String inputText = sixthPanelDownText.getText();
				if (inputText.equals("")) {
					JOptionPane.showMessageDialog(null, "����δ����!");
					return;
				}
				
				Set<Integer> deleteNumSet = new HashSet<Integer>();
				String[] deleteNumArray = inputText.split(",");
				for (int i = 0;i < deleteNumArray.length;i++) {
					try {
						deleteNumSet.add(Integer.valueOf(deleteNumArray[i]));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "ֻ���������ֺͶ���!");
						return;
					}
				}
				
				Set<Integer> viewNumSet = new HashSet<Integer>();//�洢����ı��,�ж��Ƿ�Ҫɾ���ĵ��Ƿ���һ������
				Set<String> viewNameSet = view.keySet();
				for (String viewName:viewNameSet) {
					viewNumSet.add(Integer.valueOf(view.get(viewName).split(" ")[0]));
				}
				for (Integer i:deleteNumSet) {
					if (!viewNumSet.contains(i)) {
						JOptionPane.showMessageDialog(null, "������ĵ��а������Ǿ���ĵ�!");
						return;
					}
				}
				sixthPanelDownText.setText("");
				
				for (Iterator<String> it = viewNameSet.iterator();it.hasNext();) {
					if (deleteNumSet.contains(Integer.valueOf(view.get(it.next()).split(" ")[0]))) {
						it.remove();
					}
				}
				
				setViewBox();
				setViewNameList();
				GuideUtil.setView(view, viewPath);
				
				repaint();
			} else if (	clickedButton == sevenPanelBfsButton ||
						clickedButton == sevenPanelDfsButton) {
				/**
				 * �㷨��һ��
				 */
				
				JFrame newWindow = new JFrame();
				Container c = newWindow.getContentPane();

				JLabel roadText = new JLabel();
				JScrollPane scrollPanel = new JScrollPane(roadText);
				c.add(scrollPanel);
				
				int startNum = 1;//����С�ĵ㿪ʼ��ȱ���
				int i;
				for (i = 1;i <= getMaxNum() && !map.containsKey(i);i++);
				startNum = i;
				Integer[] roadVexNum = null;
				if (clickedButton == sevenPanelBfsButton) {
					roadVexNum = GuideAlgorithm.Bfs(startNum, map, view,viewNumNameMap);
					newWindow.setTitle("������ȱ���");
				} else if (clickedButton == sevenPanelDfsButton) {
					roadVexNum = GuideAlgorithm.Dfs(startNum, map, view,viewNumNameMap);
					newWindow.setTitle("������ȱ���");
				}
				
				StringBuffer roadStr = new StringBuffer();
				roadStr.append("<html>");
				roadStr.append(viewNumNameMap.get(roadVexNum[0]));
				int length = 0;
				for (i = 1;i < roadVexNum.length;i++) {
					roadStr.append("==>" + viewNumNameMap.get(roadVexNum[i]));
					length += viewNumNameMap.get(roadVexNum[i]).length();
					
					if (length > 30) {
						length = 0;
						roadStr.append("<br>");
					}
				}
				roadStr.append("</html>");
				roadText.setText(roadStr.toString());
				
				newWindow.pack();
				newWindow.setLocationRelativeTo(null);//��仰����pack()���棬����ʼ���ڴ�СΪ0�����Ͻ�����Ļ�е�
				newWindow.setVisible(true);
				newWindow.setResizable(false);
			} else if (clickedButton == rightBut1) {
				/**
				 * ·����ѯ��ť
				 */
				card.first(cardPanel);
				for (int i = 0;i < 0;i++) {
					card.next(cardPanel);
				}
				repaint();
			} else if (clickedButton == rightBut2) {
				/**
				 * ������Ϣ��ť
				 */
				card.first(cardPanel);
				for (int i = 0;i < 1;i++) {
					card.next(cardPanel);
				}
			} else if (clickedButton == rightBut3) {
				/**
				 * ��ӽڵ㰴ť
				 */
				card.first(cardPanel);
				for (int i = 0;i < 2;i++) {
					card.next(cardPanel);
				}
				repaint();
			} else if (clickedButton == rightBut4) {
				/**
				 * ɾ���ڵ㰴ť
				 */
				card.first(cardPanel);
				for (int i = 0;i < 3;i++) {
					card.next(cardPanel);
				}
				repaint();
			} else if (clickedButton == rightBut5) {
				/**
				 * ��Ӿ��㰴ť
				 */
				card.first(cardPanel);
				for (int i = 0;i < 4;i++) {
					card.next(cardPanel);
				}
				repaint();
			} else if (clickedButton == rightBut6) {
				/**
				 * ɾ�����㰴ť
				 */
				card.first(cardPanel);
				for (int i = 0;i < 5;i++) {
					card.next(cardPanel);
				}
				repaint();
			} else if (clickedButton == rightBut7) {
				/**
				 * ɾ�����㰴ť
				 */
				card.first(cardPanel);
				for (int i = 0;i < 6;i++) {
					card.next(cardPanel);
				}
			}
		}
		
	}
	
	class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == thirdPanelUpMapLabel) {
				String x = String.valueOf(e.getX());
				String y = String.valueOf(e.getY());
				thirdPanelDownLab2.setText(x + "," + y);
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == secondPanelList) {
				if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
		             String viewName = (String) secondPanelList.getSelectedValue();
		             String introduction = view.get(viewName).split(" ")[1];
		             secondPanelLab.setText(introduction);
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
