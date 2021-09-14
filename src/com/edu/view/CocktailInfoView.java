package com.edu.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.edu.service.CocktailService;
import com.edu.service.CocktailServiceImpl;
import com.edu.util.ImageUtil;
import com.edu.vo.Cocktail;
import com.edu.vo.CocktailException;
import com.edu.vo.CocktailPageBean;


public class CocktailInfoView{
	
	/**model들 */
	private CocktailService cocktailService;
	
	/** main 화면 */
	private JFrame frame;
	
	/**창 닫기 버튼*/
	private JButton  closeBt;
	/**상품 이미지 표시 Panel*/
	private JLabel	 imgL;
	private JLabel[] cocktailInfoL;
	private JTextField numberTf;
	/**조회 조건*/
	private JComboBox<String> findC; 
	private TextField		  wordTf;
	private JButton			  searchBt;
	
	/**조회 내용 표시할 table*/
	private DefaultTableModel cockModel;
	private JTable			  cockTable;
	private JScrollPane		  cockPan;
	private String[]		  title={"번호","상품명","베이스"};
	/**검색  조건*/
	private String	key;
	/**검색할 단어*/
	private String  word;
	private String[] choice= {"all","name","base","material"};
	/**화면에 표시하고 있는 상품*/
	private Cocktail curCocktail;
	

	

	private ActionListener buttonHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			try {
				if(source == closeBt) { 
					frame.setVisible(false);
				}else if(source == searchBt) { 
					searchCocktails();
				}
			} catch (CocktailException ue) {
			     ue.printStackTrace();	
			}
		}
	};
	MouseAdapter handler = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			int row =  cockTable.getSelectedRow();
			System.out.println("선택된 row :"+row);
			System.out.println("선택된 row의 column 값 :"+cockModel.getValueAt(row, 0));
			try{
				int cid = Integer.parseInt(((String)cockModel.getValueAt(row, 0)).trim());
				showCockInfo(cid);
				
			}catch(CocktailException ue){
				ue.printStackTrace();
			}
			
		}

		
	};

	private void showCockInfo(int cid) {
		curCocktail = cocktailService.search(cid);
		cocktailInfoL[0].setText(curCocktail.getCname());
		cocktailInfoL[1].setText(curCocktail.getBase());
		cocktailInfoL[2].setText(curCocktail.getMaterial());
		
		System.out.println(curCocktail.toString());
		
		BufferedImage img = null;
		try {
			if(curCocktail.getImageURL()!=null) {
				img = ImageIO.read(new URL(curCocktail.getImageURL()));
			}
         } catch (IOException ex) {
        	 ex.printStackTrace();
         }
//		img = (BufferedImage) img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		img = ImageUtil.toBufferedImage(img.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		imgL.setIcon(new ImageIcon(img));
	}
	public CocktailInfoView(){
		cocktailService = new CocktailServiceImpl();
		frame = new JFrame("Cocktail -- 상품 정보");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				frame.dispose();
			}
		});
		setMain();
		frame.setSize(1000, 400);
		frame.setResizable(false);
		frame.setVisible(true);
//		showCockInfo(1);
		showCocktails();
	}

	/**메인 화면인 상품 목록을 위한 화면 셋팅하는 메서드  */
	public void setMain(){
		/*왼쪽 화면을 위한 설정 */
		JPanel left = new JPanel(new BorderLayout());
		JPanel leftCenter = new JPanel(new GridLayout(1, 2));
		JPanel leftR = new JPanel(new GridLayout(10, 2));
		imgL = new JLabel();
		leftCenter.add(imgL);
		leftCenter.add(leftR);
		
		JPanel leftBottom = new JPanel(new GridLayout(5, 2));
		String[] cocks= {"이름","베이스","원재료","도수"};
		int cockSize = cocks.length;
		JLabel[] cockLabel = new JLabel[cockSize];
		cocktailInfoL = new JLabel[cockSize];
		for (int i = 0; i <cockSize; i++) {
			cockLabel[i] = new JLabel(cocks[i]);
			cocktailInfoL[i] = new JLabel("");
			leftBottom.add(cockLabel[i]);
			leftBottom.add(cocktailInfoL[i]);
		}
		numberTf = new JTextField();
		JPanel temp = new JPanel(new GridLayout(1,2));
		temp.add(new JLabel("섭취 수량"));
		temp.add(numberTf);
		
		left.add(new JLabel("상품 정보", JLabel.CENTER),"North");
		left.add(leftCenter,"Center");
		left.add(leftBottom,"South");
		
		
		/*오른쪽 화면을 위한 설정 */
		JPanel right = new JPanel(new BorderLayout());
		
		JPanel rightTop = new JPanel(new GridLayout(1, 3));
		String[] item ={"---all---","이름","베이스","재료"}; 
		findC = new JComboBox<String>(item);
		wordTf= new TextField();
		searchBt= new JButton("검색");
		rightTop.add(findC);
		rightTop.add(wordTf);
		rightTop.add(searchBt);
		
		JPanel rightCenter = new JPanel(new BorderLayout());
		cockModel = new DefaultTableModel(title,20);
		cockTable = new JTable(cockModel);
		cockPan = new JScrollPane(cockTable);
		cockTable.setColumnSelectionAllowed(true);
		rightCenter.add(new JLabel("상품 목록", JLabel.CENTER),"North");
		rightCenter.add(cockPan,"Center");
		
		right.add(rightTop,"North");
		right.add(rightCenter,"Center");
		
		JPanel mainP = new JPanel(new GridLayout(1, 2));
		mainP.add(left);
		mainP.add(right);
		frame.add(mainP,"Center");
		
		/*이벤트 연결*/
		cockTable.addMouseListener(handler);
		searchBt.addActionListener(buttonHandler);
//		closeBt.addActionListener(buttonHandler);
		showCocktails();
	}
	
	
	/**검색 조건에 맞는 상품 정보 검색 */
	private void searchCocktails() {
		word = wordTf.getText().trim();
		key = choice[findC.getSelectedIndex()];
		System.out.println("word:"+word+" key:"+key);
		showCocktails();		
	}
	/**
	 * 섭취 목록을  갱신하기 위한 메서드 
	 * @param list
	 */
	public void showCocktails(){
		List<Cocktail> cocks = cocktailService.searchAll(new CocktailPageBean(key, word, null, 1));
		if(cocks!=null){
			int i=0;
			String[][]data = new String[cocks.size()][3];
			for (Cocktail cock : cocks) {
				data[i][0]= ""+cock.getCid();
				data[i][1]= cock.getCname();
				data[i++][2]= cock.getBase();
			}
			cockModel.setDataVector(data, title);
		}
	}
	public static void main(String[] args) {
		new CocktailInfoView();
	}
	
}

