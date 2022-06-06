import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class WindowConsole extends Methods{
    WindowConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберете вузы и напишите их номера. Когда выбор будет сделан - введите 0.");
        for (int i = 1; i < university.length; i++) {
            System.out.println(i + ") " + university[i]);
        }
        int c = sc.nextInt();
        int i = 1;
        while (c != 0) {
            st[i] = university[c];
            i++;
            c = sc.nextInt();
        }
        for (int o = 0; o < st.length; o++) {
            System.out.print(st[o] + "  ");
            if (o == 0) {
                System.out.print("                         ");
            }
        }
        System.out.println();
        for (int o = 0; o < data2.length; o++) {
            System.out.println(data2[o][0]);
        }
        System.out.println("Далее оцените хар-ки вузов, где 1 - ужасно, а 5 - отлично");

        Integer[][] grades = new Integer[5][7];
        for (int j = 0; j < 5; j++) {
            for (int r = 0; r < 7; r++) {
                grades[j][r] = 0;
            }
        }
        for (int o = 0; o < data2.length; o++) {
            int j = 1;
            while (!st[j].equals("")) {
                System.out.println("Оцените характеристику " + data2[o][0] + " вуза " + st[j]);
                c = sc.nextInt();
                grades[j][o] = c;
                j++;
            }
        }
        for (int o = 0; o < data2.length; o++) {
            System.out.println("Далее оцените насколько важен критерий " + data2[o][0] + " где 1 - совсем не важно, а 10 - очень важно");
            c = sc.nextInt();
            kr[o] = c;
        }

        Double[] result = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        Math(grades, kr, result);
        boolean e = true;
        for (int w = 0; w < result.length; w++) {
            if (Double.isNaN(result[w])) {
                e = false;
                result[w] = 0.0;
                System.out.println("Что-то не так, попробуйте еще раз");
                break;
            }
        }
        if (e) {
            End(st, result, top);
            Exchange(top);
        }


        for (int w = 0; w < top.length; w++){
            System.out.println(top[w][0]);
        }

    }
    public void End(String[] st, Double[] result,String[][] top){
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
                            top[i+1][0] = st[j];
                        }
                        else {
                            top[i][0] =  st[j];
                         //   System.out.println(top[i][0]);
                        }
                    }
                }
                i++;
            }
            else{
                for (int j = 0; j < result.length; j++) {
                    if (Math.abs(result2[i] - result[j]) <= 0.0000000001) {
                        top[i][0] = st[j];
                    }
                }
            }
        }
    }

    public static void main(String args[]){
        new WindowConsole();
    }
}
