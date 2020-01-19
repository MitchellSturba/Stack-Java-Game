/**
 * Program uses a stack to track the disttance traveled by the dot on the screen
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class Main implements ActionListener, KeyListener {


    JFrame jframe = new JFrame("NewtonsGame");
    public static Main mainz;
    RenderPanel renderpanel = new RenderPanel();
    public Timer tim = new Timer(10,this);
    public int ticks = 0;
    public Point player = new Point(50,50);
    public Stack<Point> pos;
    public Stack<Point> trail;
    boolean right = false,down = false, up = false, left = false, reverse = false;


    public Main() {

        this.pos = new Stack<>();
        this.trail = new Stack<>();
        pos.push(player);

        //sets up the jframe
        jframe.setTitle("SpaceInvaders");
        jframe.setSize(600,600);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        jframe.add(renderpanel);
        jframe.setVisible(true);

        tim.start();


    }

    public static void main(String[] args) {

        mainz = new Main();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;
        renderpanel.repaint();

        //player movement
        if (right) {
            player.x += 4;
        }
        if (left) {
            player.x -= 4;
        }
        if (down) {
            player.y += 4;
        }
        if (up) {
            player.y -= 4;
        }
        //if the player is moving, log the position of him
        if (right || left || up || down && !reverse) {
            Point tmp = new Point(player.x,player.y);
            pos.push(tmp);

            //create a trail every 2 ticks
            if (ticks % 2 == 0 && !reverse) {
                Point newtrail = new Point(tmp.x, tmp.y);
                trail.push(newtrail);
            }
        }
        //reverse the trail of the player
        if (reverse) {
            if (pos.size() > 5) {
                if (trail.size() > 2) {
                    trail.pop();
                    trail.pop();
                }
                pos.pop();
                pos.pop();
                pos.pop();
                player = pos.pop();
            }else {
                while (!trail.empty()) {
                    trail.pop();
                }
            }
        }
        //player can't escape the board
        if (player.y > 605) {
            player.y = -4;
        }else if (player.y < -10) {
            player.y = 601;
        }
        if (player.x > 602) {
            player.x = -10;
        }else if(player.x < -20) {
            player.x = 600;
        }


    }

    @Override
    public void keyPressed(KeyEvent g) {

    int i = g.getKeyCode();

    if (i == KeyEvent.VK_D) {
        right = true;
    }
    if (i == KeyEvent.VK_W) {
        up = true;
    }
    if (i == KeyEvent.VK_S) {
        down = true;
    }
    if (i == KeyEvent.VK_A) {
        left = true;
    }

    if (i == KeyEvent.VK_SPACE) {
        reverse = true;

    }
    if (i == KeyEvent.VK_M) {
        for(Point x: pos) {
            System.out.print(x.x + " " + x.y + "\n");
        }
    }

    }

    @Override
    public void keyReleased(KeyEvent g) {

        int i = g.getKeyCode();
        if (i == KeyEvent.VK_D) {
            right = false;
        }
        if (i == KeyEvent.VK_W) {
            up = false;
        }
        if (i == KeyEvent.VK_S) {
            down = false;
        }
        if (i == KeyEvent.VK_A) {
            left = false;
        }
        if (i == KeyEvent.VK_SPACE) {
            reverse = false;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

        int i = e.getKeyCode();

    }
}
