/**
 * @file DrawingBtnsPanel.java
 * This class creates the buttons for the paint program
 * CS-230
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Robot;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DrawingBtnsPanel extends JPanel {
	public DrawingBtnsPanel() {
		Dimension size = getPreferredSize();
		size.width = 500;
		size.height =100;
		setPreferredSize(size);
		setBackground(MainUI.getBackgroundColor());

		JButton lineDrawtBtn = new JButton("Line");
		lineDrawtBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lineDrawtBtnPressed();
			}
		});

		JButton freeHandBtn = new JButton("Freehand");
		freeHandBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				freeHandBtnPressed();
			}
		});
		
		JButton rectBtn = new JButton("Rectangle");
		rectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rectBtnPressed();
			}
		});
		
		JButton ovalBtn = new JButton("Oval");
		ovalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ovalBtnPressed();
			}
		});
		
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//repaint();
				clearBtnPressed();
			}
		});
		
		JButton redBtn = new JButton("RED");
		redBtn.setBackground(Color.RED);
		redBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redBtnPressed();
			}
		});
		
		JButton blueBtn = new JButton("BLUE");
		blueBtn.setBackground(Color.BLUE);
		blueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blueBtnPressed();
			}
		});
		
		JButton yellowBtn = new JButton("YELLOW");
		yellowBtn.setBackground(Color.YELLOW);
		yellowBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yellowBtnPressed();
			}
		});
		
		JButton blackBtn = new JButton("BLACK");
		blackBtn.setBackground(Color.BLACK);
		blackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blackBtnPressed();
			}
		});
		
		JButton greyBtn = new JButton("GREY");
		greyBtn.setBackground(Color.GRAY);
		greyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				greyBtnPressed();
			}
		});
		
		JButton whiteBtn = new JButton("WHITE");
		whiteBtn.setBackground(Color.WHITE);
		whiteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whiteBtnPressed();
			}
		});
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 Robot screencap = null;
				 File file = null;
				 try {
					 screencap = new Robot();
				 } catch (Exception d) {
					 ;
				 }
				 BufferedImage image = screencap.createScreenCapture(new Rectangle(15,30,470,160));
			     //(g2d);
			     try {
			    	 file = new File("Example.jpg");
			         ImageIO.write(image,"jpg", file);
			     } catch(Exception ex){
			         ex.printStackTrace();
			     }
			     saveBtnPressed();
			     User currentUser = Main.getLoggedInUser();
				User selectedUser = UI_Frame.getSelectedUser();
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss").format(new Date());
				TextMessage msg = new TextMessage(selectedUser, currentUser, timeStamp, 
						"<a href='file:///" + file.getAbsolutePath() + "'>" + "Drawing Image" + "</a>");
				Writer.writeTextMsg(msg);
	        	 saveBtnPressed();
	      }
		});

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 1;
		gc.gridy = 0;
		add(freeHandBtn, gc);

		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 2;
		gc.gridy = 0;
		add(lineDrawtBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 3;
		gc.gridy = 0;
		add(rectBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 4;
		gc.gridy = 0;
		add(ovalBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 5;
		gc.gridy = 0;
		add(clearBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 6;
		gc.gridy = 0;
		add(saveBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 1;
		gc.gridy = 1;
		add(redBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 2;
		gc.gridy = 1;
		add(blueBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 3;
		gc.gridy = 1;
		add(yellowBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 4;
		gc.gridy = 1;
		add(blackBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 5;
		gc.gridy = 1;
		add(greyBtn, gc);
		
		gc.anchor = GridBagConstraints.PAGE_END;
		gc.gridx = 6;
		gc.gridy = 1;
		add(whiteBtn, gc);
	}
	

	private void lineDrawtBtnPressed() {
		Painter.drawingPanel.setLineTool();
	}
	private void freeHandBtnPressed() {
		Painter.drawingPanel.setFreeHandTool();
	}
	private void rectBtnPressed() {
		Painter.drawingPanel.setRectTool();
	}
	private void ovalBtnPressed() {
		Painter.drawingPanel.setOvalTool();
	}
	private void clearBtnPressed(){
		Painter.drawingPanel.setClearTool();
	}
	private void saveBtnPressed(){
		Painter.drawingPanel.setSaveTool();
	}
	private void redBtnPressed(){
		Painter.drawingPanel.setColor(Color.RED);
	}
	private void blueBtnPressed(){
		Painter.drawingPanel.setColor(Color.BLUE);
	}
	private void yellowBtnPressed(){
		Painter.drawingPanel.setColor(Color.YELLOW);
	}
	private void blackBtnPressed(){
		Painter.drawingPanel.setColor(Color.BLACK);
	}
	private void greyBtnPressed(){
		Painter.drawingPanel.setColor(Color.GRAY);
	}
	private void whiteBtnPressed(){
		Painter.drawingPanel.setColor(Color.WHITE);
	}
}