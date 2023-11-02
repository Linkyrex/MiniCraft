import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
public class GamePanel extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BLOCK_SIZE = 50;
    private static final int NUM_BLOCKS_X = WIDTH / BLOCK_SIZE;
    private static final int NUM_BLOCKS_Y = HEIGHT / BLOCK_SIZE;
    private boolean[][] blocks;
    private final Object lock = new Object();
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        blocks = new boolean[NUM_BLOCKS_X][NUM_BLOCKS_Y];
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / BLOCK_SIZE;
                int y = e.getY() / BLOCK_SIZE;
                toggleBlock(x, y);
                repaint();
            }
        });
    }
    private synchronized void toggleBlock(int x, int y) {
        if (x >= 0 && x < NUM_BLOCKS_X && y >= 0 && y < NUM_BLOCKS_Y) {
            blocks[x][y] = !blocks[x][y];
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (lock) {
            for (int x = 0; x < NUM_BLOCKS_X; x++) {
                for (int y = 0; y < NUM_BLOCKS_Y; y++) {
                    if (blocks[x][y]) {
                        int xPos = x * BLOCK_SIZE;
                        int yPos = y * BLOCK_SIZE;
                        g.setColor(Color.GRAY);
                        g.fillRect(xPos, yPos, BLOCK_SIZE, BLOCK_SIZE);
                    }
                }
            }
        }
    }
}