package com.company;

 public class Cat implements Comparable<Cat> {
    String name;
    String age;
    String color;
    String swim;
    public Cat(String name, String age,String color,String swim){
        this.name = name;
        this.age = age;
        this.color = color;
        this.swim = swim;

    }

     @Override
     public int compareTo(Cat o) {
         return 0;
     }
 }
