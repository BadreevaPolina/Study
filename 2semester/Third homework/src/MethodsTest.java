import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class MethodsTest<j> {
    JComboBox comboBox1 = new JComboBox(), comboBox2 = new JComboBox();
    JTable table1,table2;


     Methods c = new Methods();

    @org.junit.jupiter.api.Test
    void math() {
        Double[] result = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        Integer[][] grade1 = new Integer[5][7];
        for(int t = 0; t < grade1.length; t++) {
            for (int r = 0; r < grade1[0].length; r++) {
                grade1[t][r] = t+1;
            }
        }
        Integer kr[] = new Integer[7];
        for (int r = 0; r < kr.length; r++) {
            kr[r] = r+1;
        }
        c.Math(grade1,kr,result);
        assertEquals(result[0], 4.84);
        assertEquals(result[1], 9.68);
        assertEquals(result[2],  14.520000000000001);
        assertEquals(result[3], 19.36);
        assertEquals(result[4], 24.2);
        }

    @org.junit.jupiter.api.Test
    void addInMas() {
        String mas[] = {"1","2","3"};
        c.AddInMas(comboBox1,3,mas);
        assertEquals(comboBox1.getItemAt(0),"1");
        assertEquals(comboBox1.getItemAt(1),"2");
        assertEquals(comboBox1.getItemAt(2),"3");
    }

    @org.junit.jupiter.api.Test
    void end() {
        String[][] top = {{"","1"}, {"","2"}, {"","3"}, {"","4"}, {"","5"}};
        Double[] result = {6.0,3.0,7.0,4.0,5.0};
        table1 = new JTable(c.data, new String[]{"","1", "2", "3", "4", "5"});
        c.End(table1,result,top);
        assertEquals(top[0][0],"2");
        assertEquals(top[1][0],"4");
        assertEquals(top[2][0],"5");
        assertEquals(top[3][0],"1");
        assertEquals(top[4][0],"3");
    }


    @org.junit.jupiter.api.Test
    void krInNum() {
        Integer kr[] = new Integer[7];

        String[][] data2 = {
                {"Комфорт в общежитии","","","","","","1"},
                {"Уровень английского","","","","","","2"},
                {"Количество пьющих","","","","","","3"},
                {"Нагрузка по учебе","","","","","","4"},
                {"Отсутствие нелюбимых предметов (физика, физ-ра)","","","","","","5"},
                {"Город","","","","","","6"},
                {"Контроль посещения","","","","","","7"},
        };
        table2 = new JTable(data2,c.st);
        c.KrInNum(kr,table2);
        assertEquals(kr[0],1);
        assertEquals(kr[1],2);
        assertEquals(kr[2],3);
        assertEquals(kr[3],4);
        assertEquals(kr[4],5);
        assertEquals(kr[5],6);
        assertEquals(kr[6],7);

    }


    @org.junit.jupiter.api.Test
    void exchange() {
        String[][] top = {{"11","1"}, {"22","2"}, {"33","3"}, {"44","4"}, {"55","5"}};
        c.Exchange(top);
        assertEquals(top[0][0],"55");
        assertEquals(top[1][0],"44");
        assertEquals(top[2][0],"33");
        assertEquals(top[3][0],"22");
        assertEquals(top[4][0],"11");

    }
}