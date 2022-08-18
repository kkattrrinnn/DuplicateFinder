import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SecondaryPage extends JFrame{
    public JPanel topPanel;
    public JPanel middlePanel;
    public JPanel bottomPanel;
    public JPanel mainPanel;
    public JScrollPane scrollPane;
    public JButton exitButton;
    public JLabel topLabel;
    public Container container;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension dimension = toolkit.getScreenSize();
    public int width = 800;
    public int height = 500;

    SecondaryPage(ArrayList<String> duplicates) {
        super("Duplicate Finder");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(dimension.width/2-width/2,dimension.height/2-height/2, width, height);
        this.setPreferredSize(new Dimension(width, height));
        CreatePanel();
        CreateListOfDuplicates(duplicates);
    }

    private void CreatePanel() {
        container = this.getContentPane();

        topPanel = new JPanel();
        topPanel.setVisible(true);

        topLabel = new JLabel("Найденные дубликаты:");
        topPanel.add(topLabel);

        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(24, 1));
        scrollPane = new JScrollPane(middlePanel);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setVisible(true);

        exitButton = new JButton("Выход");
        exitButton.setVisible(true);
        exitButton.setSize(50, 30);
        exitButton.addActionListener(e -> {        // обработка нажатия
            this.removeAll();                         // очистка панели
            this.repaint();
            this.revalidate();
            this.dispose();
            System.exit(0);
        });
        bottomPanel.add(exitButton, BorderLayout.EAST);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.setVisible(true);

        container.add(mainPanel);
    }

    public void CreateListOfDuplicates(ArrayList<String> duplicates) {
        HashMap<String, JCheckBox> map = new HashMap<>();
        /* Создание чекбоксов с именами копий для выбора тех, которые нужно удалить */
        //ArrayList<JCheckBox> listOfSelectedDuplicates = new ArrayList<>();
        String fileName;
        for (String duplicate : duplicates) {
            fileName = duplicate;
            map.put(fileName, new JCheckBox(fileName));
            middlePanel.add(map.get(fileName));
        }
    }

}