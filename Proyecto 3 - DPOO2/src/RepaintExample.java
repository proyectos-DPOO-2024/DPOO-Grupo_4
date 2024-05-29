import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RepaintExample extends JFrame {
    private CustomPanel panel;
    private Random random;

    public RepaintExample() {
        setTitle("Repaint Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        random = new Random();
        panel = new CustomPanel();

        JButton repaintButton = new JButton("Repaint");
        repaintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar el estado del panel para demostrar el repintado
                panel.changeState();
                panel.repaint();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(repaintButton, BorderLayout.SOUTH);
    }

    private class CustomPanel extends JPanel {
        private Color backgroundColor;
        private int rectX, rectY, rectWidth, rectHeight;

        public CustomPanel() {
            changeState();
        }

        public void changeState() {
            backgroundColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            // Asegurarse de que el ancho y alto del panel son mayores que 0
            rectWidth = random.nextInt(Math.max(1, getWidth() - 20)) + 20;
            rectHeight = random.nextInt(Math.max(1, getHeight() - 20)) + 20;
            rectX = random.nextInt(Math.max(1, getWidth() - rectWidth));
            rectY = random.nextInt(Math.max(1, getHeight() - rectHeight));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(backgroundColor);
            g.setColor(Color.BLUE);
            g.fillRect(rectX, rectY, rectWidth, rectHeight);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RepaintExample().setVisible(true);
            }
        });
    }
}
