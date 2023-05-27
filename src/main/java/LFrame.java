import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LFrame extends JFrame {
    public static boolean box1UpPress = false;
    public static boolean box1DownPress = false;
    public static boolean box2UpPress = false;
    public static boolean box2DownPress = false;
    public JPanel box1 = new JPanel();
    public JPanel box2 = new JPanel();

    public LFrame() {
        // initialise JFrame
        int fWidth = 500;
        Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(fWidth, displaySize.height - 100));
        setLocation(new Point((displaySize.width - fWidth) / 2, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Container cp = getContentPane(); // get contentPane from JFrmae

        // initialise two black JPanels
        box1.setBounds(50, displaySize.height - 200, 50, 50);
        box1.setBackground(Color.black);

        box2.setBounds(200, displaySize.height - 200, 50, 50);
        box2.setBackground(Color.black);

        // add a Key listener for the key actions
        addKeyListener(new KeyAdapter() {
            /*
            * The Key listener does not change any values of the JPanel,
            * it changes a variable to true when a key is pushed down and
            * make it false again, when it is released.
            * this have to be done for every key what should be detected
            */
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    box1UpPress = true;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    box1DownPress = true;
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    box2UpPress = true;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    box2DownPress = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    box1UpPress = false;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    box1DownPress = false;
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    box2UpPress = false;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    box2DownPress = false;
                }
            }
        });
        cp.add(box1);
        cp.add(box2);
        setVisible(true);
        timerAction();
    }

    public void timerAction() {
        // Make a timer with 10 milli sec
        Timer t = new Timer(10, (e) -> {
            /*
            * Now a timer gets started and every 10 milli sec it checks
            * the variables and ist some of them are true it changes
            * the value of the JPanel.
            * With this method you can handle as many keys you want at the same time,
            * and you can adjust every param of how fast the timer should check e.t.c
            * hope that helps
            */
            if (box1UpPress) {
                box1.setLocation(new Point(box1.getLocation().x, box1.getLocation().y - 1));
            }
            if (box2UpPress) {
                box2.setLocation(new Point(box2.getLocation().x, box2.getLocation().y - 1));
            }
            if (box1DownPress) {
                box1.setLocation(new Point(box1.getLocation().x, box1.getLocation().y + 1));
            }
            if (box2DownPress) {
                box2.setLocation(new Point(box2.getLocation().x, box2.getLocation().y + 1));
            }
            /*
            * Solution of a lag issue in Linux not required in windows
            * recommended to use with an os check
            */
            if(System.getProperty("os.name").equalsIgnoreCase("linux")){
                Toolkit.getDefaultToolkit().sync();
            }
        });
        t.start();
    }
}
