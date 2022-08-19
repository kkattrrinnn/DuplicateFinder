import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SecondaryPage extends JFrame{
    public JPanel topPanel;
    public static JPanel middlePanel;
    public JPanel bottomPanel;
    public JPanel mainPanel;
    public JScrollPane scrollPane;
    public JButton exitButton;
    public JButton deleteButton;
    public JButton backButton;
    public JLabel topLabel;
    public Container container;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension dimension = toolkit.getScreenSize();
    public int width = 800;
    public int height = 500;
    public static HashMap<String, JCheckBox> map;
    public static int sizeOfListOfDuplicates;
    public static ArrayList<ArrayList<FileObject>> duplicates;
    public static boolean duplicatesFound = false;

    SecondaryPage(ArrayList<ArrayList<FileObject>> listOfDuplicates, int size) {
        super("Duplicate Finder");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(dimension.width/2-width/2,dimension.height/2-height/2, width, height);
        this.setPreferredSize(new Dimension(width, height));
        duplicates = listOfDuplicates;
        sizeOfListOfDuplicates = size;
        CreatePanel();
        if (sizeOfListOfDuplicates > 0) {
            duplicatesFound = true;
            CreateListOfDuplicates(listOfDuplicates);
        } else {
            duplicatesFound = false;
            middlePanel.add(new JLabel("Дубликаты не найдены"));
        }
    }

    private void CreatePanel() {
        container = this.getContentPane();

        topPanel = new JPanel();
        topPanel.setVisible(true);

        topLabel = new JLabel("Найденные дубликаты:");
        topPanel.add(topLabel);

        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(sizeOfListOfDuplicates + duplicates.size() + 1, 1));   // чтобы каждая группа копий разделялась пустой строкой
        scrollPane = new JScrollPane(middlePanel);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setVisible(true);

        exitButton = new JButton("Выход");
        exitButton.setVisible(true);
        exitButton.addActionListener(e -> {        // обработка нажатия
            this.removeAll();                         // очистка панели
            this.revalidate();
            this.dispose();
            System.exit(0);
        });
        bottomPanel.add(exitButton, BorderLayout.EAST);

        deleteButton = new JButton("Удалить отмеченные копии");
        deleteButton.setVisible(true);
        deleteButton.addActionListener(e -> {
            if (duplicatesFound) {
                DuplicateFinder.DeleteFiles(GetListOfFilesToDelete());
                middlePanel.removeAll();
                middlePanel.add(new JLabel("Выбранные файлы были удалены"));
            }
        });
        bottomPanel.add(deleteButton, BorderLayout.CENTER);

        backButton = new JButton("Вернуться");
        backButton.setVisible(true);
        backButton.addActionListener(e -> {
            this.removeAll();                         // очистка панели
            this.revalidate();
            this.dispose();
            String[] args = {"firstArg", "secondArg", "thirdArg"};
            DuplicateFinder.main(args);
        });
        bottomPanel.add(backButton, BorderLayout.WEST);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.setVisible(true);

        container.add(mainPanel);
    }

    public static void CreateListOfDuplicates(ArrayList<ArrayList<FileObject>> listOfDuplicates) {
        map = new HashMap<>();
        /* Создание чекбоксов с именами копий для выбора тех, которые нужно удалить */
        String fileName;
        for (ArrayList<FileObject> item : listOfDuplicates) {
            for (FileObject file : item) {
                fileName = file.toString();
                map.put(fileName, new JCheckBox(fileName));
                middlePanel.add(map.get(fileName));
            }
            middlePanel.add(new JLabel());
        }
    }

    public static ArrayList<FileObject> GetListOfFilesToDelete() {
        ArrayList<FileObject> data = new ArrayList<>();
        for (ArrayList<FileObject> item : duplicates) {
            for (FileObject file : item) {
                if (map.get(file.toString()).isSelected()) {
                    data.add(file);
                }
            }
        }
        return data;
    }
}