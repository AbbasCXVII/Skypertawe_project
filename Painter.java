/**
 * @file Painter.java
 * @date 10 Dec 2016
 * CS-230
 *
 *  A simple application for creating images to be sent in messages
 *
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Painter {
	public static DrawingPanel drawingPanel;

   public static void main( String args[] ) {

      /** create a new JFrame **/
      JFrame application = new JFrame( "Drawing Environment" );
      DrawingBtnsPanel btnsPanel = new DrawingBtnsPanel();
      /** create a new paint panel */
      drawingPanel = new DrawingPanel();
      /** position it in the center */
      application.add( drawingPanel, BorderLayout.CENTER ); 
      /** create a label and place it in SOUTH of BorderLayout */
      application.add(btnsPanel, BorderLayout.SOUTH );
      /** set frame size */
      application.setSize( DrawingPanel.FRAME_WIDTH, DrawingPanel.FRAME_HEIGHT );
      /** display frame -won't appear without this */
      application.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
      application.setVisible( true ); 

   } /* end main          */

}    /* end class Painter */
