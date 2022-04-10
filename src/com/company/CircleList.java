package com.company;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class CircleList<T extends Comparable<T>> implements Iterable<T> {
    Node<T> First = null;
    Node<T> Last = null;
    int Count = 0;

    public void AddAfter(T value) {
        Node<T> newNode = new Node<T>(value);
        if (First == null)
            First = newNode;
        else
            Last.next = newNode;
        Last = newNode;
        Last.next = First;
        Count++;
    }

    public void AddBefore(T value) {
        Node<T> cur = Last;
        Node<T> prevLast = First;
        while (prevLast.next!= Last) {
            prevLast =prevLast.next;
        }
        Node<T> newNode = new Node<T>(value);
        if (First == null){
            First = newNode;
            Last = newNode;
            newNode.next = First;
        }
        else {
            prevLast.next = newNode;
            newNode.next = cur;
        }
        Count++;
    }
    public void AddFirst(T value) {
        Node<T> newNode = new Node<T>(value);
        Node<T> F = First;
        First = newNode;
        newNode.next = F;
        Count++;
    }
    public void AddLast(T value) {
        Node<T> newNode = new Node<T>(value);
        Last.next = newNode;
        Last = newNode;
        Last.next = First;
        Count++;
    }

    public void Contains(T val) {
        int n = 0;
        Node<T> newNode = First;
        while (newNode != null && n != Count) {
            if (newNode.value == val || newNode.value.equals(val))
         //   if((newNode.value).Equals(val))
            {
                System.out.println("True");
                break;
            }
            newNode = newNode.next;
            n++;
        }
        if (n == Count) {
            System.out.println("False");
        }
    }

    public Node<T> Find(T c) {
        int n = 0;
        Node<T> find = null;
        Node<T> newNode = First;
        while (newNode != null && n != Count) {
            if (c == newNode.value) {
                find = newNode;
                break;
            }
            newNode = newNode.next;
            n++;
        }
        return find;
    }
    public Node<T> FindLast(T c) {
        int n = 0;
        Node<T> find = null;
        Node<T> newNode = First;
        while (newNode != null && n != Count) {
            if (c == newNode.value) {
                find = newNode;
            }
            newNode = newNode.next;
            n++;
        }
        return find;
    }

    public void Clear() {// остальное будет мусором
        First = null;
    }

    public void Remove(T c) {
        int n = 0;
        Node<T> newNode = First;
        Node<T> prev;
        if(First.value.equals(c) || Last.value.equals(c)) {
            if (First.value.equals(c)) {
                RemoveFirst();
            }
            if (Last.value.equals(c)) {
                RemoveLast();
            }
        }
        else {
            while (n != Count) {
                prev = newNode;
                newNode = newNode.next;
                if(newNode.value.equals(c))
                {
                    prev.next = newNode.next;
                    Count--;
                    break;
                }
                n++;
            }
        }
    }

    public void RemoveFirst(){
        Count--;
        if(First.next != null) {
             First = First.next;
        }
        else {
            First = null;
        }
    }

    public void RemoveLast(){
        Node<T> prevLast = First;
        Count--;
        if(Last.next != null) {
            while (prevLast.next != Last) {
                prevLast =prevLast.next;
            }
            Last = prevLast;
        }
        else {
            First = null;
        }
    }


    public void Print() {
        int n = 0;
        Node<T> newNode = First;
        while (newNode != null && n != Count) {
            System.out.println(newNode.value);
            newNode = newNode.next;
            n++;
        }
    }
    public boolean Equals(Object obj)
    {
        return this.equals(obj);
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> f = First;
            Node<T> l = Last;
            int C = Count, e = 0;
            @Override
            public boolean hasNext() {
                if(e != C) {
                    e++;
                    return true;
                }
                else
                    return false;
            }

            @Override
            public T next() throws IndexOutOfBoundsException {
                try {
                    T res = f.value;
                    if(!hasNext()) throw new IndexOutOfBoundsException("Last");
                    f = f.next;
                    return res;
                }
                catch (Exception e){
                    return null;
                }

            }
        };
    }

    public boolean Any(Iterator n){
        if(n != null)
            return true;
        else
            return false;
    }

    public int Count (Iterator n){
        int count = 0;
        while (n.next() != null){
            count++;
        }
        return count;
    }

    public  Object ElementAt(Iterator n,int index){
        int count = 0;
        Object t = n.next();
        while (t != null && count != index){
            count++;
            t = n.next();
        }
        return t;
    }
    public Object ElementAtOrDefault(Iterator n,int index){
        int count = 0;
        Object t = n.next();
        while (t != null && count != index){
            count++;
            t = n.next();
        }
        return t;
    }
    public Object First(Iterator n){
        return n.next();
    }
    public Object Last(Iterator n){
        Object t = n.next();
        Object c = null;
        while (t != null){
            c = t;
            t = n.next();
        }
        return c;
    }
    

}

