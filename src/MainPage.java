import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainPage extends JFrame{
    JPanel mainPanel;
    JPanel firstButtonPanel;
    JPanel secondButtonPanel;
    JLabel firstLineOfTopLabel;
    JLabel secondLineOfTopTable;
    static JTextField originalFileName;
    JLabel secondLine;
    static JTextField searchDirectoryName;
    JButton searchButton;
    JButton exitButton;
    Container container;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension dimension = toolkit.getScreenSize();
    public int width = 800;
    public int height = 500;
    Font BigFontCS = new Font("ComicSans", Font.PLAIN, 20);

    public MainPage() {
        super("Duplicate Finder");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(dimension.width/2-width/2,dimension.height/2-height/2, width, height);
        this.setPreferredSize(new Dimension(width, height));
        CreatePanel();
    }

    private void CreatePanel() {
        container = this.getContentPane();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 1));

        firstLineOfTopLabel = new JLabel("    Введите полный путь к оригинальному файлу, указывая его расширение");
        firstLineOfTopLabel.setFont(BigFontCS);
        mainPanel.add(firstLineOfTopLabel);

        secondLineOfTopTable = new JLabel("    Или оставьте поле пустым, чтобы выполнить поиск всех дубликатов");
        secondLineOfTopTable.setFont(BigFontCS);
        mainPanel.add(secondLineOfTopTable);

        originalFileName = new JTextField();
        originalFileName.setFont(BigFontCS);
        mainPanel.add(originalFileName);
//--------------------------------------------------------------------------------
        secondLine = new JLabel("    Введите полный путь к директории, в которой необходимо провести поиск");
        secondLine.setFont(BigFontCS);
        mainPanel.add(secondLine);

        searchDirectoryName = new JTextField();
        searchDirectoryName.setFont(BigFontCS);
        mainPanel.add(searchDirectoryName);
//--------------------------------------------------------------------------------
        firstButtonPanel = new JPanel();
        firstButtonPanel.setLayout(new GridLayout(1, 3));
        firstButtonPanel.setVisible(true);
        firstButtonPanel.add(new JLabel());                      // визуальные отступы

        searchButton = new JButton("Поиск");
        searchButton.setVisible(true);
        searchButton.addActionListener(e -> {        // обработка нажатия
            DuplicateFinder.PerformTheSearchFunction();
        });
        firstButtonPanel.add(searchButton);
        firstButtonPanel.add(new JLabel());

        secondButtonPanel = new JPanel();
        secondButtonPanel.setLayout(new GridLayout(2, 6));

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
        for (int i = 0; i < 11; i++) {
            secondButtonPanel.add(new JLabel());
        }
        secondButtonPanel.add(exitButton);

        mainPanel.add(new JLabel());
        mainPanel.add(firstButtonPanel);
        mainPanel.add(secondButtonPanel);

        container.add(mainPanel, BorderLayout.CENTER);
        this.repaint();
    }

    public static String GetFileName() {
        if (originalFileName.getText() != null || originalFileName.getText().equals("")) {
            return originalFileName.getText();
        } else return "";
    }

    public static String GetDirectoryName() {
        if (searchDirectoryName.getText() != null || searchDirectoryName.getText().equals("")) {
            return searchDirectoryName.getText();
        } else return "";
    }
}