import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Main a = Main.mainz;
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        try {
            g.fillOval(a.pos.peek().x,a.pos.peek().y,20,20);
            for (Point q: a.trail) {
                g.fillOval(q.x + 5,q.y + 5,4,4);
            }
        }catch (NullPointerException e) {
            //
        }



    }
}
