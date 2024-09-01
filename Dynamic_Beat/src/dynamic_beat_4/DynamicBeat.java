package dynamic_beat_4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {
	
	// 더블 버퍼링 - 현재 프로그램의 전체 화면 크기에 맞는 이미지를 매 순간마다 생성해서 원하는 컴포넌트만 화면에 출력
	// 원하는 컴포넌트만 담아서
	
	private Image screenImage;
	private Graphics screenGraphics;
	
	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackGround_.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.jpg")));
//	private JButton exitButton = new JButton(new ImageIcon(Main.class.getResource("../images/exit.png")));
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exit4.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exit3.png"));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY;
	
	public DynamicBeat() {
		// 실행 했을 때 기본 메뉴바 보이지 않음
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// 페인트 컴포넌트 메뉴바 투명
		setBackground(new Color(0, 0, 0, 0));
		// 버튼이나 J라벨이 그대로
		setLayout(null);
		
		menuBar.setBounds(0, 0, 1280, 31);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {	// 마우스로 버튼 클릭했을 때 마우스 좌표 얻어오기
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// 마우스로 드래그하면서 창 이동하기(프레임 위치 바꾸기)
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
		
		exitButton.setBounds(1240, 2, 28, 28);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {	// 클릭
				System.exit(0);
			}
		});
		
		add(exitButton);
		
		setComponentZOrder(exitButton, 0);
		
		Music introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null);
	
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		// J라벨 같은 것을 J프레임 안에 넣으면 그려줌
		// 메뉴바는 움직이는 것이 아니고 항상 존재하는 고정된 이미지기 때문에 페인트컴포넌트를 이용한다.
		paintComponents(g);
		this.repaint();
	}
	
	
}
