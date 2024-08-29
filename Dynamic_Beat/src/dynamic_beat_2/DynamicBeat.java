package dynamic_beat_2;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame {
	
	// 더블 버퍼링 - 현재 프로그램의 전체 화면 크기에 맞는 이미지를 매 순간마다 생성해서 원하는 컴포넌트만 화면에 출력
	// 원하는 컴포넌트만 담아서
	
	private Image screenImage;
	private Graphics screenGraphics;
	
	private Image introBackground;
	
	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// 이미지 인스턴스 초기화
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackGround.jpg")).getImage();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphics = screenImage.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(screenImage, 0, 0, null);
	
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		this.repaint();
	}
	
	
}
