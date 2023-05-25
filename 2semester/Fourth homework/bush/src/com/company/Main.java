package com.company;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String dir = System.getProperty("user.dir");
        Path basePath = Paths.get(dir);
        Path tempPath = basePath.resolveSibling("temp");
        String c = "";

        File file = new File(String.valueOf(tempPath));
        FileWriter writer = new FileWriter(file);
        writer.close();

        String files = "";

        String[] name = new String[50];
        String[] word = new String[50];
        int str = 0;
        while(!c.equals("exit")){
            String TOrF = "1";
            String p = sc.nextLine();
            int i = 0;
            String[] data = p.split(" ");
            while (i != data.length) {
                c = data[i++];
                if(c.charAt(0) == '$' && c.charAt(1) != '?') { //сначала объявление переменной
                    String t = "";
                    int u,r = c.length();
                    StringBuilder sw,sb;
                    for (u = 1; u < c.length(); u++){
                        if(c.charAt(u) == '=') {
                        if(c.charAt(u) == '=' && c.charAt(u-1) == '+'){
                            int k = 0;
                             sw = new StringBuilder(t);
                            t = "" + sw.delete(u-2,u-1);
                            while (name[k] != null){
                                if(name[k].equals(t)){

                                    break;
                                }
                                k++;
                            }
                            sb = new StringBuilder(c);
                            word[k] = word[k] + sb.delete(0,u+1);
                            break;
                        }
                        else {
                            int k = 0;
                            while (name[k] != null){
                                if(name[k].equals(t)){

                                    break;
                                }
                                k++;
                            }
                             sb = new StringBuilder(c);
                            word[k] = "" + sb.delete(0,u+1);
                            break;
                        }
                        }
                        t = t + c.charAt(u);
                    }
                    if(u == r){
                        name[str] = t;
                        str++;
                    }

                }
                switch (c) {
                    case "pwd":
                        System.out.println(basePath);
                        TOrF = "0";
                        break;
                    case "cat":
                        FileInputStream fin = new FileInputStream(data[i++]);
                        int j;
                        while ((j = fin.read()) != -1) {
                            System.out.print((char) j);
                        }
                        System.out.println();
                        fin.close();
                        TOrF = "0";
                        break;
                    case "echo":
                        for (int r = 1; r < data.length; r++) {
                            System.out.print(data[i]+" ");
                        }
                        break;
                    case "true":
                        TOrF = "0";
                        System.out.println(TOrF);
                        break;
                    case "false":
                        TOrF = "-1";
                        System.out.println(TOrF);
                        break;
                    case "$?":
                        System.out.println(TOrF);
                        break;
                    case "&&":
                        if (TOrF.equals("0")) {
                            break;
                        } else {
                            c = data[i++];
                        }
                        break;
                    case "||":
                        if (TOrF.equals("-1") || TOrF.equals("1")) {
                            break;
                        } else {
                            c = data[i++];
                        }
                        break;
                    case ";":
                        break;
                    case "wc":
                        files = data[i++];
                        FileInputStream fis = new FileInputStream(files);
                        byte[] byteArray = new byte[(int) files.length()];
                        int intArray = 0;
                        int wordArray = 0;
                        fis.read(byteArray);
                        String b = new String(byteArray);
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                String[] probel = line.split(" ");
                                wordArray += probel.length;
                                intArray++;
                            }
                        }
                        System.out.println("    " + intArray + "    " + wordArray + "    " + b.length());
                        fis.close();
                        break;
                    case ">":
                        File f = new File(data[i++]);
                        String text1 = "";
                        for( int q = 0; q < i-2; q++ ){
                            text1 = text1 + data[q] + " ";
                        }
                        PrintWriter pw = new PrintWriter(f);
                        pw.println(text1);
                        pw.close();
                        break;
                    case ">>":
                        String text2 = "";
                        for( int q = 0; q < i-1; q++ ){
                            text2 = text2 + data[q] + " ";
                        }
                        FileWriter writer1 = new FileWriter(data[i++], true);
                        BufferedWriter bufferWriter = new BufferedWriter(writer1);
                        bufferWriter.write(text2);
                        bufferWriter.close();
                        break;
                    case "<":
                        File e2 = new File(data[i++]);
                        FileReader reader1 = new FileReader(e2);
                        while ((j = reader1.read()) != -1) {
                            System.out.print((char) j);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
