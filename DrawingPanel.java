/**
 * @file DrawingPanel.java
 * @date 10/12/2016
 * This class implements the core functionality for the paint program
 */

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	
	private enum Tool { LINE, FREEHAND, CLEAR, COLOR, RECTANGLE, OVAL, SAVE, NONE };
	public final static int FRAME_WIDTH = 500;
	public final static int FRAME_HEIGHT = 300;
    private final int POINT_WIDTH = 4;
    private final int POINT_HEIGHT = 4;
	private Tool m_currentTool = Tool.NONE;
	private Color m_currentColor = Color.BLACK;
	private int m_pointCount = 0;
	private int m_lineCount = 0;
	private int m_rectangleCount = 0;
	private int m_ovalCount = 0;

	private List<List<Point>> m_lines = new ArrayList<List<Point>>();
	private List<Color> m_lineColor = new ArrayList<Color>();
	private List<Point> m_points = new ArrayList<Point>();
	private List<Color> m_pointColor = new ArrayList<Color>();
	private List<List<Object>> m_rectangles = new ArrayList<List<Object>>();
	private List<Color> m_rectColor = new ArrayList<Color>();
	private List<List<Object>> m_ovals = new ArrayList<List<Object>>();
	private List<Color> m_ovalColor = new ArrayList<Color>();

	void clearAll() {
		// Reset all shape counts to 0
	    m_pointCount = 0;
		m_lineCount = 0;
		m_rectangleCount = 0;
		m_ovalCount = 0;
		m_lines.clear();
		m_lineColor.clear();
		m_points.clear();
		m_pointColor.clear();
		m_rectangles.clear();
		m_rectColor.clear();
		m_ovals.clear();
		m_ovalColor.clear();
		repaint();
	}
	
	
	private class DrawingHandler implements MouseListener, MouseMotionListener {
		// 0 is point1, 1 is point2
		private List<Point> m_currentLine = new ArrayList<Point>();
		// 0 is start point, 1 is width, 2 is height
		private List<Object> m_currentRectangle = new ArrayList<Object>();
		// 0 is start point, 1 is width, 2 is height
		private List<Object> m_currentOval = new ArrayList<Object>();
		
		// Used to calculate width and height of drawn ovals and rectangles
		private int m_tempWidth;
		private int m_tempHeight;
		
		public void mouseDragged(MouseEvent event) {
	         if (m_currentTool == Tool.FREEHAND) {
	        	 m_pointColor.add(m_currentColor);	// Get the current colour
	        	 m_points.add(event.getPoint());	// Get the current point
	        	 m_pointCount++;					// increment pointCount
	        	 repaint();
	         }
		}
		public void mouseMoved(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mouseClicked(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {
			if(m_currentTool == Tool.CLEAR){
				repaint();
			} else if (m_currentTool == Tool.LINE) {
				m_lineColor.add(m_currentColor);
				m_currentLine.add(event.getPoint());
				System.out.println(m_currentLine);
				
			} else if (m_currentTool == Tool.RECTANGLE) {
				m_currentRectangle.add(event.getPoint());
				m_tempWidth = event.getX();
				m_tempHeight = event.getY();
				
			} else if (m_currentTool == Tool.OVAL) {
				m_currentOval.add(event.getPoint());
				m_tempWidth = event.getX();
				m_tempHeight = event.getY();
			}
		}
		public void mouseReleased(MouseEvent event) {
			if (m_currentTool == Tool.LINE) {
				m_currentLine.add(event.getPoint());
				m_lines.add(new ArrayList<Point>(m_currentLine));
				m_currentLine.clear();
				repaint();
				m_lineCount++;
				
			} else if (m_currentTool == Tool.RECTANGLE) {
				// If rectangle was drawn to the left, change top left corner to new X
				if (event.getX() < m_tempWidth) {
					m_currentRectangle.set(	0, 
											new Point(event.getX(), 
													 (int)((Point)m_currentRectangle.get(0)).getY()));
				}
				// If rectangle was drawn up, change top left corner to new Y
				if (event.getY() < m_tempHeight) {
					m_currentRectangle.set(	0, 
											new Point((int)(((Point)m_currentRectangle.get(0)).getX()), 
													  event.getY()));
				}
				m_currentRectangle.add(Math.abs(event.getX() - m_tempWidth));
				m_currentRectangle.add(Math.abs(event.getY() - m_tempHeight));
				m_rectangles.add(new ArrayList<Object>(m_currentRectangle));
				m_currentRectangle.clear();
				m_rectColor.add(m_currentColor);
				repaint();
				m_rectangleCount++;
			} else if (m_currentTool == Tool.OVAL) {
				// If oval was drawn to the left, change top left corner to new X
				if (event.getX() < m_tempWidth) {
					m_currentOval.set(	0, 
											new Point(event.getX(), 
													 (int)((Point)m_currentOval.get(0)).getY()));
				}
				// If oval was drawn up, change top left corner to new Y
				if (event.getY() < m_tempHeight) {
					m_currentOval.set(	0, 
											new Point((int)(((Point)m_currentOval.get(0)).getX()), 
													  event.getY()));
				}
				m_currentOval.add(Math.abs(event.getX() - m_tempWidth));
				m_currentOval.add(Math.abs(event.getY() - m_tempHeight));
				m_ovals.add(new ArrayList<Object>(m_currentOval));
				m_currentOval.clear();
				m_ovalColor.add(m_currentColor);
				repaint();
				m_ovalCount++;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		// Draw rectangles
		if (m_rectangleCount > 0) {
		for (int i = 0; i < m_rectangleCount; i++ ) {
			g.setColor(	m_rectColor.get(i) );
			g.fillRect(	((int)((Point)m_rectangles.get(i).get(0)).getX()),
						((int)((Point)m_rectangles.get(i).get(0)).getY()),
						 (int)m_rectangles.get(i).get(1),
						 (int)m_rectangles.get(i).get(2)
						 );
		}
		}
		// Draw ovals
		if (m_ovalCount > 0) {
		for (int i = 0; i < m_ovalCount; i++ ) {
			g.setColor(	(Color)m_ovalColor.get(i));
			g.fillOval(	((int)((Point)m_ovals.get(i).get(0)).getX()),
						((int)((Point)m_ovals.get(i).get(0)).getY()),
						 (int)m_ovals.get(i).get(1),
						 (int)m_ovals.get(i).get(2)
						 );
		}
		}
		// Draw lines
		if (m_lineCount > 0) {
		for ( int i = 0; i < m_lineCount; i++ ) {
			g.setColor(	m_lineColor.get(i) );
			System.out.println(m_lines);
			g.drawLine((int)(m_lines.get(i).get(0).getX()), // Get point 1's X coord
					   (int)(m_lines.get(i).get(0).getY()), // Get point 1's Y coord
					   (int)(m_lines.get(i).get(1).getX()), // Get point 2's X coord
					   (int)(m_lines.get(i).get(1).getY())  // Get point 2's Y coord
					   );
		}
		}
		// Draw particles
		if (m_pointCount > 0) {
		for ( int i = 0; i < m_pointCount; i++ ) {
			g.setColor(	m_pointColor.get(i));
	        g.fillOval(	(int)m_points.get(i).getX(), // Get the point's X coord
	        			(int)m_points.get(i).getY(), // Get the point's Y coord
	                    POINT_WIDTH, POINT_HEIGHT ); // Final width and height
	    }
		}
		if (m_currentTool == Tool.CLEAR) {
			super.paintComponent(g);
			clearAll(); // Clear all arrays
		} else if (m_currentTool == Tool.SAVE){
			super.paintComponent(g);
		}
		
	}

	public void setLineTool() {
		m_currentTool = Tool.LINE;
	}
	
	public void setFreeHandTool() {
		m_currentTool = Tool.FREEHAND;
	}
	
	public void setRectTool() {
		m_currentTool = Tool.RECTANGLE;
	}
	
	public void setOvalTool() {
		m_currentTool = Tool.OVAL;
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
	
	public void setColor(Color c) {
		m_currentColor = c;
	}
	
	public DrawingPanel() {
		Dimension size = getPreferredSize();
		size.width = 500;
		size.height = 400;
		setPreferredSize(size);
		
		DrawingHandler handler = new DrawingHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
	}
}
