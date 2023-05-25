import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window2 extends Methods {
        Window2(){
            JPanel Panel;
            JButton button1 = new JButton("Получить информацию"),button2 = new JButton("Создать"),button3 = new JButton("Определить");
            JTable table;
            final JTable[] table2 = new JTable[1];
            JTable table3;
            JComboBox comboBox1 = new JComboBox(),comboBox2 = new JComboBox(),comboBox3 = new JComboBox();
            JScrollPane scrollPane;
            final JScrollPane[] scroll = new JScrollPane[1];
            JScrollPane scroll3;

            JFrame a = new JFrame("Window2");
            a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Panel = new JPanel();
            Panel.setBounds(0,0,1200,800);
            table = new JTable(data, columnNames);
            table.setBounds(0,0,1200,800);

            AddInMas(comboBox1,15,university);
            AddInMas(comboBox2,5,grade);
            AddInMas(comboBox3,10,jo);

            AddComboBox(comboBox1);
            EachRowEditor rowEditor = new EachRowEditor(table);
            for(int i = 0; i < 5; i++){
                rowEditor.setEditorAt(i, new DefaultCellEditor(comboBox1));
            }
            table.getColumn("Общая информация").setCellEditor(rowEditor);

            scrollPane = new JScrollPane(table);
            Panel.add(scrollPane);
            Panel.add(button1);
            a.add(Panel);

            AddComboBox(comboBox2);
            EachRowEditor[] rowEditor0 = new EachRowEditor[5];
            Integer[][] grades = new Integer[5][7];
            for(int j = 0; j < 5; j++){
                for (int i = 0; i < 7; i++){
                    grades[j][i]=0;
                }
            }
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DoChoice(st,university, data,Choice,table);
                    Panel.remove(button1);
                    Panel.add(button2, BorderLayout.PAGE_END);
                    Panel.revalidate();
                    Panel.repaint();
                }
            });
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    table2[0] = new JTable(data2, st);
                    scroll[0] = new JScrollPane(table2[0]);
                    Panel.remove(scrollPane);
                    Panel.add(scroll[0], BorderLayout.CENTER);
                    JFrame jFrame = new JFrame();
                    JOptionPane.showMessageDialog(jFrame, "В последнем столбце оцените от 1 до 10 насколько вам важен критерий, где 1 - совсем не важно, 10 - очень важно");
                    Panel.revalidate();
                    Panel.repaint();
                    for(int j = 0; j < 5; j++) {
                        rowEditor0[j] = new EachRowEditor(table2[0]);
                        for (int i = 0; i < 7; i++) {
                            rowEditor0[j].setEditorAt(i, new DefaultCellEditor(comboBox2));
                        }
                        table2[0].getColumn(st[j + 1]).setCellEditor(rowEditor0[j]);
                    }
                    EachRowEditor rowEditor1 = new EachRowEditor(table2[0]);
                    for (int i = 0; i < 7; i++) {
                        rowEditor1.setEditorAt(i, new DefaultCellEditor(comboBox3));
                    }
                    table2[0].getColumn("Важность характеристики").setCellEditor(rowEditor1);
                    Panel.remove(button2);
                    Panel.add(button3);
                    Panel.revalidate();
                    Panel.repaint();
                }
            });
            table3 = new JTable(top,new String[]{"Вуз", "Приоритетность"});
            scroll3 = new JScrollPane(table3);
            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Double[] result = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0};
                    GradesInNum(grades, table2[0]);
                    KrInNum(kr, table2[0]);
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
                        End(table2[0],result,top);
                        Exchange(top);
                        Panel.remove(button3);
                        Panel.remove(scroll[0]);
                        Panel.add(scroll3);
                        Panel.revalidate();
                        Panel.repaint();
                    }
                }
            });

            a.setSize(1400,400);
            a.setLayout(null);
            a.setVisible(true);
        }


    public static void main(String args[]){
        new Window2();
    }
}
