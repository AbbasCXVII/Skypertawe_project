import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class DrawingPanel extends JPanel {
	
	private enum Tool { LINE, FREEHAND, CLEAR, COLOR, SAVE, NONE };
	
	public final static int FRAME_WIDTH = 500;
	public final static int FRAME_HEIGHT = 300;
    /** An oval's width */
    private final int OVAL_WIDTH = 4;
    /** An oval's height */
    private final int OVAL_HEIGHT = 4;
	public final int MAX_LINES = 100;
	public final int MAX_POINTS = 10000000;
	public Tool m_currentTool = Tool.NONE;
	private int m_pointCount = 0;
	private User currentUser;
	private int m_lineCount = 0;
	private int[][] m_lines = new int[MAX_LINES][2]; //? why [2]
	private ArrayList<Point> m_points = new ArrayList<Point>(MAX_POINTS);
	
	/*
	private int m_x1 = -1;
	private int m_x2 = -1;
	private int m_y1 = -1;
	private int m_y2 = -1;
	*/
	
	void clearAll() {
		for (int[] line : m_lines) {
			line[0] = -10;
			line[1] = -10;
		}
		for (Point p : m_points) {
			p.setLocation(-10,-10);
		}
		repaint();
	}
	
	private class LineHandler implements MouseListener, MouseMotionListener {
		public void mouseDragged(MouseEvent event) {
			 boolean test = true;
	         if (test) {
	           System.out.println(m_pointCount/*+"\t"+m_points.get(m_pointCount).x + " " + m_points.get(m_pointCount).y*/);
	         }
			if (m_currentTool == Tool.FREEHAND) {
				
		         if ( m_pointCount < MAX_POINTS ) {

		            /* find and store point */
		            m_points.add(event.getPoint());
		            /* increment number of points in array **/
		            m_pointCount++;
		            /* repaint JFrame */
		            repaint();

		         } /* end if                             */
		      }    /* end method mouseDragged            */
		}
		public void mouseMoved(MouseEvent event) {

		}
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mouseClicked(MouseEvent event) {
			/*if (SwingUtilities.isRightMouseButton(event)) {
				m_lines[m_lineCount][0] = -1;
				m_lineCount++;*/
			/*} else if (SwingUtilities.isLeftMouseButton(event) && m_currentTool == Tool.LINE) {
				m_lines[m_lineCount][0] = event.getX();
				m_lines[m_lineCount][1] = event.getY();
				m_lineCount++;
				repaint();
			}*/
		}
		public void mousePressed(MouseEvent event) {
			if(m_currentTool == Tool.CLEAR){
				repaint();
				
			}
		}
		public void mouseReleased(MouseEvent event) {
			if (SwingUtilities.isRightMouseButton(event)) {
				m_lines[m_lineCount][0] = -1;
				m_lineCount++;
			} else if (SwingUtilities.isLeftMouseButton(event) && m_currentTool == Tool.LINE) {
				m_lines[m_lineCount][0] = event.getX();
				m_lines[m_lineCount][1] = event.getY();
				m_lineCount++;
				repaint();
			}
		}
	}
	
	//public class SaveImage extends JPanel{
		//private BufferedImage paintImage = new BufferedImage(500, 300, BufferedImage.TYPE_3BYTE_BGR );
			
	/*
	private class FreehandHandler implements MouseListener {
		
	}
	*/
	
	public void paintComponent(Graphics g) {
		if (m_currentTool == Tool.LINE) {
			if (m_lineCount > 0) {
				for (int i=1; i < (m_lineCount); i++) {
					if (m_lines[i][0] == -1) {
						i++;
					} else {
						g.drawLine(m_lines[i-1][0], m_lines[i-1][1], m_lines[i][0], m_lines[i][1]);
					}
				}
			}
		} else if (m_currentTool == Tool.FREEHAND) {
				for ( int i = 0; i < (m_pointCount); i++ ) {
			         g.fillOval( m_points.get(i).x, /* upper-left x coord */
			                     m_points.get(i).y, /* upper-left y coord */
			                     OVAL_WIDTH, OVAL_HEIGHT );
			      } 
			    	  
			} else if (m_currentTool == Tool.CLEAR) {
				super.paintComponent(g);
				clearAll();
				
			} else if (m_currentTool == Tool.SAVE){
				super.paintComponent(g);
			}
	}
		
	
	//private class ColorChooser extends JFrame {
		//public ColorChooser(){
			
		//}
	//}
	
	/*
	public void LineComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		for (int i; i < m_lineCount; i++) {
			
		}
	}
	*/
	
	public void setLineTool() {
		m_currentTool = Tool.LINE;
	}
	
	public void setFreeHandTool() {
		m_currentTool = Tool.FREEHAND;
	}
	
	public void setClearTool(){
		m_currentTool = Tool.CLEAR;
	}
	
	public void setColorTool(){
		m_currentTool = Tool.COLOR;
	}
	
	public void setSaveTool(){
		m_currentTool = Tool.SAVE;
	}
	
	public DrawingPanel() {
		Dimension size = getPreferredSize();
		size.width = 500;
		size.height = 400;
		setPreferredSize(size);
		
		LineHandler handler = new LineHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
	}
	
	/*
	public static void main(String[] args) {
		 		JFrame frame = new JFrame("LineDrawer");
		 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		 		DrawingPanel panel = new DrawingPanel();
		 		frame.setContentPane(panel);
		 		frame.setVisible(true); 
		 	}
	*/
}
