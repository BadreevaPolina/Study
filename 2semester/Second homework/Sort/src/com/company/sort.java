package com.company;

import java.lang.Comparable;

class Sort <T extends Comparable<T>>{

    public T[] sort(T[] mas,int low,int high){
        if(mas.length == 0)
            return null;
        if(low >= high)
            return null;

        int middle = low +(high - low)/2;
        T opora =mas[middle];
        int i = low, j = high;
        while (i <= j) {
            while (mas[i].compareTo(opora) < 0)  {
                i++;
            }
            while (mas[j].compareTo(opora) > 0) {
                j--;
            }
            if (i <= j) {
                T temp = mas[i];
                mas[i] = mas[j];
                mas[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j) {
            sort(mas, low, j);
        }

        if (high > i)
        {
            sort(mas, i, high);
        }
        return mas;
    }
}

