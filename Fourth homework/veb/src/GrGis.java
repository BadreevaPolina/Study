import javax.swing.*;
import java.awt.*;

public class GrGis extends JFrame {
    public  static int y[] = new int[5];
    public  static String col[] = {"BLUE", "RED", "GREEN","YELLOW","BLACK"};

    public GrGis (String tittle,int[] info) {
        super(tittle);
        y = info;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jcp = new JPanel(new BorderLayout());
        jcp.add(new Desktop(y), BorderLayout.CENTER);
        jcp.setBackground(Color.gray);
        setSize(500, 500);
        setLocationRelativeTo(null);
        this.setContentPane(jcp);
    }

}
