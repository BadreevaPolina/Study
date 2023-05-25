package com.company;
import java.util.Iterator;
public class Main {

    public static void main(String[] args) {
	CircleList<Integer> CircleList = new CircleList<Integer>();

    CircleList.AddAfter(1);
    CircleList.AddAfter(2);
    CircleList.AddAfter(3);
    CircleList.AddFirst(0);
    CircleList.AddAfter(4);
    CircleList.AddLast(5);
    CircleList.AddAfter(6);
    CircleList.Print();

    System.out.println("//////////////////////////////////");
    System.out.println(CircleList.Any(CircleList.iterator()));
    System.out.println(CircleList.Count(CircleList.iterator()));
    System.out.println(CircleList.ElementAt(CircleList.iterator(),3));
    System.out.println(CircleList.ElementAtOrDefault(CircleList.iterator(),7));
    System.out.println(CircleList.Last(CircleList.iterator()));
        }
}


