import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class Desktop extends JPanel {
    int[] info = new int[5];
    Desktop(int[] info){
        this.info[0] = info[0];
        this.info[1] = info[1];
        this.info[2] = info[2];
        this.info[3] = info[3];
        this.info[4] = info[4];
    }
    protected void paintComponent(Graphics gh) { // сделала по аналогии https://upread.ru/art.php?id=237
        Graphics2D drp = (Graphics2D)gh;

        //горизонтальные линии и обозначения
        for (int i=2; i<9; i++) {
            drp.drawLine(50, 50+44*i, 400, 50+44*i);
            int vs = 80 - i*10;
            drp.drawString(vs+"", 30, 50+44*i);
        }

        drp.drawString("Отсюда пляшем :https://weather.rambler.ru/v-sankt-peterburge/", 100, 40);
        drp.drawString("Сайт 1 :https://pogoda7.ru/prognoz/gorod134243-Russia-g_Sankt_Peterburg-Sankt_Peterburg/14days/full", 100, 60);
        drp.drawString("Сайт 2 :https://rp5.ru/Погода_в_Санкт-Петербурге", 100, 80);
        drp.drawString("Сайт 3 :https://www.gismeteo.ru/weather-sankt-peterburg-4079/", 100, 100);
        drp.drawString("Результат линейной регрессии", 100, 120);
        drp.drawString("Температура", 60, 420);

        drp.setColor(Color.BLUE);
        drp.fillRect(80, 30, 10, 10);
        drp.setColor(Color.RED);
        drp.fillRect(80, 50, 10, 10);
        drp.setColor(Color.GREEN);
        drp.fillRect(80, 70, 10, 10);
        drp.setColor(Color.YELLOW);
        drp.fillRect(80, 90, 10, 10);
        drp.setColor(Color.BLACK);
        drp.fillRect(80, 110, 10, 10);

            Color color = Color.RED;
            for (int j=0;j<5;j++) {
                try {
                    Field field = Class.forName("java.awt.Color").getField(GrGis.col[j].toLowerCase());
                    color = (Color)field.get(null);
                } catch (Exception e) {}
                drp.setColor(color);
                int realY = (int) (400-44*GrGis.y[j]/10)+3;
                drp.fillRect(50+20*j+100, realY, 20,(int) (GrGis.y[j]*4.4));
        }
    }
}
