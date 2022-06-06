
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class Window extends JFrame {
    private JPanel Panel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTable table ;
    private JTable table2;
    private JTable table3;
    private JComboBox comboBox1 = new JComboBox();
    private JComboBox comboBox2 = new JComboBox();
    private JComboBox comboBox3 = new JComboBox();
    private JScrollPane scrollPane;
    private JScrollPane scroll;
    private JScrollPane scroll3;

    public Window(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] university = {
                "Не выбрано", "СПбГУ", "НИУ ВШЭ", "МГТУ имени Баумана", "НИЯУ МИФИ", "ТПУ", "ТГУ",
                "СПБПУ", "УрФУ", "ИТМО", "СПбГЭТУ ЛЭТИ", "ДВФУ", "ЮУРГУ", "ВГУ", "МАИ"};
        String[][] Choice = {
                {"","","", "", "", "","","",""},
                {"","265","283", "94,3", "9", "да","да","Санкт-Петербург","30"},
                {"","286", "301", "100,3", "7,2","да", "да", "Москва","135"},
                {"","284", "296", "98,7", "8,8", "да", "да","Москва", "82"},
                {"","БВИ", "БВИ", "Нет информации","9,4", "да","да", "Москва","31"},
                {"", "237", "233", "77,7", "Нет информации", "да","да", "Томск", "100"},
                {"","268","257", "85,7","Нет информации","да","да", "Томск", "75"},
                {"", "259", "276", "92", "5,7", "да", "да", "Санкт-Петербург", "90"},
                {"", "244", "252", "84","4,9", "да", "да","Екатеренбург","250"},
                {"", "286", "БВИ", "100,7", "5,6", "да", "да", "Санкт-Петербург", "39"},
                {"", "244","282", "94", "8,7", "да", "да", "Санкт-Петербург", "40"},
                {"", "205", "197", "65,7", "3,5", "да", "да", "Владивосток", "125"},
                {"","248", "251", "83,7", "Нет информации", "да", "да", "Челябинск", "50"},
                {"", "253", "262", "87,3", "4,2","да", "да","Воронеж", "38"},
                {"", "243", "251", "83,7", "7,3", "да", "да","Москва", "50"}
        };
        String[] columnNames = {
                "Общая информация",
                "Проходные баллы 2020",
                "Проходные баллы 2021",
                "Средневзвешенный балл",
                "Оценка с сайта табитуриент",
                "Наличие общежития",
                "Наличие военной кафедры",
                "Город",
                "Количество бюджетных мест"
        };
        String[][] data2 = {
                {"Комфорт в общежитии","","","","","",""},
                {"Уровень английского","","","","","",""},
                {"Количество пьющих","","","","","",""},
                {"Нагрузка по учебе","","","","","",""},
                {"Отсутствие нелюбимых предметов (физика, физ-ра)","","","","","",""},
                {"Город","","","","","",""},
                {"Контроль посещения","","","","","",""},
        };
        String[][] data = {
                {"","","","","","","","",""},
                {"","","","","","","","",""},
                {"","","","","","","","",""},
                {"","","","","","","","",""},
                {"","","","","","","","",""}
        };
        String[] st = {"Дополнительно","","","","","","Важность характеристики"};
        String[] grade = {"ужасно","плохо","удовлетворительно","хорошо","отлично"};
        Integer[] kr = {0,0,0,0,0,0,0};
        String[] jo = {"1","2","3","4","5","6","7","8","9","10"};
        Double[] result = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        String[][] top = {{"","1"}, {"","2"}, {"","3"}, {"","4"}, {"","5"}};
        String[] mesto = {"1","2","3","4","5"};

        table = new JTable(data, columnNames);

        AddInMas(comboBox1,15,university);
        AddInMas(comboBox2,5,grade);
        AddInMas(comboBox3,10,jo);

        Panel = new JPanel(new BorderLayout());
        Panel.add(button1, BorderLayout.PAGE_END);
        AddComboBox(comboBox1);
        EachRowEditor rowEditor = new EachRowEditor(table);
        for(int i = 0; i < 5; i++){
            rowEditor.setEditorAt(i, new DefaultCellEditor(comboBox1));
        }
        table.getColumn("Общая информация").setCellEditor(rowEditor);

        scrollPane = new JScrollPane(table);
        Panel.add(scrollPane, BorderLayout.CENTER);

        AddComboBox(comboBox2);
        EachRowEditor[] rowEditor0 = new EachRowEditor[5];
        Integer[][] grades = new Integer[5][7];
        for(int j = 0;j < 5; j++){
            for (int i = 0; i < 7; i++){
                grades[j][i]=0;
            }
        }


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoChoice(st,university, data,Choice);
                Panel.remove(button1);
                Panel.add(button2,BorderLayout.PAGE_END);
                revalidate();
                repaint();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel.remove(scrollPane);
                table2 = new JTable(data2, st);
                scroll = new JScrollPane(table2);
                Panel.add(scroll, BorderLayout.CENTER);
                JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame, "В последнем столбце оцените от 1 до 10 насколько вам важен критерий, где 1 - совсем не важно, 10 - очень важно");
                revalidate();
                repaint();
                for(int j = 0; j < 5; j++) {
                    rowEditor0[j] = new EachRowEditor(table2);
                    for (int i = 0; i < 7; i++) {
                        rowEditor0[j].setEditorAt(i, new DefaultCellEditor(comboBox2));
                        table2.getColumn(st[j + 1]).setCellEditor(rowEditor0[j]);
                    }
                }
                EachRowEditor rowEditor1 = new EachRowEditor(table2);
                for (int i = 0; i < 7; i++) {
                    rowEditor1.setEditorAt(i, new DefaultCellEditor(comboBox3));
                }
                table2.getColumn("Важность характеристики").setCellEditor(rowEditor1);
                Panel.remove(button2);
                Panel.add(button3,BorderLayout.PAGE_END);
                revalidate();
                repaint();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double[] result = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0};
                GradesInNum(grades,table2);
                KrInNum(kr,table2);
                Math(grades,kr,result);
                boolean c = true;
                for (int i = 0; i < result.length; i++){
                            if (Double.isNaN(result[i])) {
                                c = false;
                                result[i] = 0.0;
                                JFrame jFrame = new JFrame();
                                JOptionPane.showMessageDialog(jFrame, "Что-то не так, заполните все необходимые поля");
                                break;
                    }
                }
                if(c){
                    End(table2,result,top);
                    Exchange(top);
                    Panel.remove(button3);
                    Panel.remove(scroll);
                    table3 = new JTable(top,new String[]{"Вуз", "Приоритетность"});
                    scroll3 = new JScrollPane(table3);
                    Panel.add(scroll3);
                    revalidate();
                    repaint();
                }
            }
        });
        this.setContentPane(Panel);
        this.pack();
        table.setFillsViewportHeight(true);
        this.setVisible(true);
    }

    public void Math(Integer[][] grade,Integer[] kr,Double[] result){
        Integer o = 0;
        for(int i = 0; i < kr.length; i++){ // сумма всех оценок важности
          o = o + kr[i];
        }
       // System.out.println(o);

        Double[] krit = new Double[kr.length];
        for(int i = 0; i < krit.length; i++){ // подсчет какая важность
            krit[i] =  ((double) kr[i] /(double) o) * 100;
          //  System.out.println(krit[i]);
        }

        Double[] sumKrit = {0.0,0.0,0.0,0.0,0.0,0.0,0.0}; //2 c
        for(int j = 0; j< grade.length; j++) {
            for (int i = 0; i < grade[j].length; i++) {
                sumKrit[i] = sumKrit[i] + grade[j][i];
            }
        }
        for(int i = 0; i < sumKrit.length; i++) {
            krit[i] = sumKrit[i] * krit[i];
            //System.out.println(krit[i]);
        }
        Double[][] res = new Double[grade.length][grade[0].length];
        for(int j = 0; j< res.length; j++){
            for(int i = 0; i < res[j].length; i++){
               res[j][i] = (double) grade[j][i] / krit[i] * 100;
               result[j] = result[j] + res[j][i];
               //System.out.print(res[j][i] + " ");
            }
            //System.out.println();
            System.out.println(result[j]);
        }



    }
    public void AddInMas(JComboBox comboBox, int n, String[] mas){
        for (int i = 0; i < n; i ++){
            comboBox.addItem(mas[i]);
        }
    }

    public void AddComboBox(JComboBox comboBox){
        comboBox.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                final JComponent c = (JComponent) e.getSource();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        c.requestFocus();
                    }
                });
            }
        });
    }

    public void End(JTable table, Double[] result,String[][] top){
        Double[] result2 = new Double[result.length];
        for(int i = 0; i < result.length; i++){
            result2[i] = result[i];
        }
        Arrays.sort(result2);
        for(int i = 0; i < result2.length; i++){
            if (i+1 > result2.length && Math.abs(result2[i] - result2[i+1]) <= 0.0000000001){
                for (int j = 0; j < result.length; j++) {
                    if (Math.abs(result2[i] - result[j]) <= 0.0000000001) {
                        if(!top[i][0].equals("")){
                            top[i+1][0] = table.getColumnName(j + 1);
                        }
                        else {
                            top[i][0] = table.getColumnName(j + 1);
                            System.out.println(top[i][0]);
                        }
                    }
                    }
                i++;
            }
            else{
                for (int j = 0; j < result.length; j++) {
                    if (Math.abs(result2[i] - result[j]) <= 0.0000000001) {
                        top[i][0] = table.getColumnName(j + 1);
                    }
                }
            }
        }
    }

    public  void GradesInNum(Integer[][] grades, JTable table2){
        for(int j = 0; j < grades.length; j++) {
            for (int i = 0; i < grades[j].length; i++) { //в столбце всего 8 строк
                if("ужасно".equals(table2.getValueAt(i, j+1))){
                    grades[j][i] = 1;
                }
                if("плохо".equals(table2.getValueAt(i, j+1))){
                    grades[j][i] = 2;
                }
                if("удовлетворительно".equals(table2.getValueAt(i, j+1))){
                    grades[j][i] = 3;
                }
                if("хорошо".equals(table2.getValueAt(i, j+1))){
                    grades[j][i] = 4;
                }
                if("отлично".equals(table2.getValueAt(i, j+1))){
                    grades[j][i] = 5;
                }
                //System.out.print(grades[j][i] + " ");
            }
            // System.out.println();
        }
    }
    public void KrInNum(Integer[] kr,JTable table2){
        for(int i = 0 ; i < kr.length; i ++){
            String t = (String) table2.getValueAt(i, 6);
            if(!t.equals("")){
                kr[i] = Integer.parseInt(t);
            }
            //System.out.println(kr[i]);
        }
    }

    public void DoChoice(String[] st,String[] university, String[][] data,String[][] Choice){
        for (int i = 0; i < 5; i++){
            st[i+1] = (String) table.getValueAt(i,0);
            for(int o = 0; o < university.length; o++){
                if(st[i+1].equals(university[o])){
                    for(int j = 1; j<data[i].length; j++ ){
                        data[i][j] = Choice[o][j];
                    }
                }
            }
        }
    }
    public void Exchange(String[][] top){
        for(int i = 0; i < 2; i++) {
            String o = top[i][0];
            top[i][0] = top[4 - i][0];
            top[4 - i][0] = o;
        }
    }
    public static void main (String[] args){
        JFrame frame = new Window("Window");
        frame.setSize(new Dimension(1500,400));
        frame.setVisible(true);
    }

}

