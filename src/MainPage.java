import javax.swing.*;
import java.awt.*;

public class MainPage extends GraphicsWindow{
    Font FontCS = new Font("ComicSans", Font.PLAIN, 15);
    JLabel firstLine;
    JTextField originalFileName;
    JLabel secondLine;
    JTextField searchDirectoryName;
    JButton search;
    String Tur_name;
    JComboBox box_tur;
    JButton Book_a_tour;
    JButton ent_button_MainPage;
    JButton reg_button;
    JButton Tour_design;
    JLabel error_no_tour;

    public MainPage() {
        constructor();
    }

    void constructor() {
        this.firstLine = new JLabel("Введите полный путь к оригинальному файлу:");
        this.firstLine.setFont(FontCS);
        this.firstLine.setBounds(50, 100, 600, 30);
        this.jPanel.add(this.firstLine);

        this.originalFileName = new JTextField();
        this.originalFileName.setBounds(50, 150, 700, 50);
        this.originalFileName.setFont(FontCS);
        this.jPanel.add(this.originalFileName);
//--------------------------------------------------------------------------------
        this.secondLine = new JLabel("Введите полный путь к директории, в которой необходимо провести поиск:");
        this.secondLine.setFont(FontCS);
        this.secondLine.setBounds(50, 200, 600, 30);
        this.jPanel.add(this.secondLine);

        this.searchDirectoryName = new JTextField();
        this.searchDirectoryName.setBounds(50, 250, 700, 50);
        this.searchDirectoryName.setFont(FontCS);
        this.jPanel.add(this.searchDirectoryName);
//--------------------------------------------------------------------------------
        this.search = new JButton("Поиск");
        this.search.setVisible(true);
        this.search.setBounds(300, 350, 200, 40);
        this.search.setForeground(Color.BLACK);
        this.search.addActionListener(e -> {        // обработка нажатия
            this.jPanel.removeAll();                         // очистка панели
            this.jPanel.repaint();
            this.jPanel.revalidate();
            delFrame();
            System.exit(0);
        });
        this.jPanel.add(this.search);

        this.jFrame.repaint();

        /*try {
            this.box_tur = new JComboBox(DBProcessor.getTours());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.jPanel.add(this.box_tur);
        this.box_tur.setFont(BigFontCS);
        this.box_tur.setBounds(x-100,y+100,width+100,height);

//-----------------------------------------------------------------
        ActionListener actionListener = new ActionListener() { // отслеживаем элемент
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                Tur_name = (String)box.getSelectedItem();
            }
        };
        this.box_tur.addActionListener(actionListener);*/
//-----------------------------------------------------------------
        /*this.Book_a_tour = new JButton("Подробнее");
        this.jPanel.add(this.Book_a_tour);

        this.Book_a_tour.setBounds(x-50, y+3*height, width, height);
        this.Book_a_tour.setForeground(Color.BLACK);
        this.Book_a_tour.setFont(BigFontCS);
        /*this.Book_a_tour.addActionListener(e -> {                   // обработка нажатия
            if (Tur_name != null){                                  //если тур не был выбран
                this.jPanel.removeAll();                            // очистка панели
                this.jPanel.repaint();
                this.jPanel.revalidate();
                delFrame();
                try {
                    new OrderingTure(this.id, Tur_name, DBProcessor.getInfoAboutTheTour(Tur_name));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }else {
                this.error_no_tour = new JLabel("Тур не выбран"); // сообщение об ошибке
                this.error_no_tour.setVisible(true);
                this.error_no_tour.setFont(BigFontCS);
                this.error_no_tour.setForeground(Color.red);
                this.error_no_tour.setBounds(x, y + 4 * height + 100, width, height);
                this.jPanel.add(this.error_no_tour);
                this.jPanel.repaint();
            }
        });*/
    }
}
