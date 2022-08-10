import javax.swing.*;
import java.awt.*;

public class GraphicsWindow{
    public JFrame jFrame;
    public JPanel jPanel;
    public JButton exit;
    public String title = "Duplicate Finder";
    public int width = 800;                        // размеры окна
    public int height = 500;
    GraphicsWindow() {
        this.jFrame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();      // расположение по центру
        Dimension dimension = toolkit.getScreenSize();
        this.jFrame.setBounds(dimension.width/2-width/2,dimension.height/2-height/2, width, height);
        this.jFrame.setTitle(this.title);
        this.jFrame.setResizable(false);
        this.jFrame.setVisible(true);                // видимость окна
        this.jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // для закрытия окна
        this.jFrame.setLayout(null);

        this.jPanel = new JPanel();      // создание панели
        this.jPanel.setSize(this.width, this.height);
        this.jPanel.setVisible(true);
        this.jPanel.setLayout(null);
        this.jFrame.add(this.jPanel);

        this.exit = new JButton("Выход");
        this.exit.setVisible(true);
        this.exit.setBounds(650, 400, 100, 30);
        this.exit.setForeground(Color.BLACK);
        this.exit.addActionListener(e -> {        // обработка нажатия
            this.jPanel.removeAll();                         // очистка панели
            this.jPanel.repaint();
            this.jPanel.revalidate();
            delFrame();
            System.exit(0);
        });
        this.jPanel.add(this.exit);
    }

    public void delFrame() {
        this.jFrame.dispose();
    }
}