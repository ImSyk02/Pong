
package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Beau Marwaha
 */
public class window extends JPanel implements ActionListener {
    
    private Timer timer;
    private BufferedImage image;
    
    private paddleLeft paddleLeft;
    private paddleRight paddleRight;
    private ball ball;
   
    public window() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        try {                
           image = ImageIO.read(new File("src\\pong\\images\\back.jpg"));
        } catch (IOException ex) {

        }
        
        paddleLeft = new paddleLeft();
        paddleRight = new paddleRight();
        ball = new ball();
        
        timer = new Timer(0, this);
        timer.start();
    }
    
    @Override
    public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D)g;

            g.drawImage(image, 0, 0, null);
            g2d.drawImage(paddleLeft.getImage(), paddleLeft.getX(), paddleLeft.getY(), this);
            g2d.drawImage(paddleRight.getImage(), paddleRight.getX(), paddleRight.getY(), this);
            g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), this);
            
            //restricts the left paddles movement
            if(paddleLeft.getY() < 0){
                paddleLeft.setY(0); 
            }
            if(paddleLeft.getY() > 400){
                paddleLeft.setY(400); 
            }
            
            //restricts the right paddles movement
            if(paddleRight.getY() < 0){
                paddleRight.setY(0); 
            }
            if(paddleRight.getY() > 400){
                paddleRight.setY(400); 
            }
            
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        paddleLeft.move();
        paddleRight.move();
        ball.move();
        
        repaint();  
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            paddleLeft.keyReleased(e);
            paddleRight.keyReleased(e);
        }
        
        @Override
        public void keyPressed(KeyEvent e) {
            paddleLeft.keyPressed(e);
            paddleRight.keyPressed(e);
        }
    }
    
}
