import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minecraft 2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel gamePanel = new GamePanel();
        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}