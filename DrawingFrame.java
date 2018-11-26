/**
 * @file DrawingFrame.java
 * @date 10 Dec 2016
 * CS-230
 * 
 * This class sets the layout of the paint application
 */
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;

public class DrawingFrame extends JFrame {
	private DrawingBtnsPanel btns;
	private DrawingPanel draw;
	

	public DrawingFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		btns = new DrawingBtnsPanel();
		draw = new DrawingPanel();
		
		Container c = getContentPane();
		c.add(btns, BorderLayout.SOUTH);
		c.add(draw, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
