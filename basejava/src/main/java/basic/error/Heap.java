package basic.error;

import java.util.ArrayList;

public class Heap
{
    public static void main(String[] args)
    {
        System.out.println("Memory leak" + new Heap());
    }
    public String toString() {
        System.out.println("Memory leak" + this);
        return "";
    }
}