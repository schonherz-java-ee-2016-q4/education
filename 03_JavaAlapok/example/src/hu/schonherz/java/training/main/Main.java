package hu.schonherz.java.training.main;

import hu.schonherz.java.training.ember.Ember;
import hu.schonherz.java.training.ember.Ember.SzemSzin;

import java.util.Arrays;

public class Main {

    private static Integer a;

    public static void main(String[] args) {
        Ember kati = new Ember("Kati", SzemSzin.BARNA);
        Ember margit = new Ember("Margi", SzemSzin.KEK);
        System.out.println(margit.getSzam());

        Ember rozi = new Ember();
        System.out.println(rozi.getNev());
    }

    private static void test() {
        System.out.println("kacsa");
    }

    private static void valtozo() {
        System.out.println(a);
        int b = 3;
        int c = 4;
        int e = 5;
        int d = 3 + b;
        System.out.println(d);
    }

    private static void blocks() {
        int a = getOne();
        {
            int b = 2;
            System.out.println(a + "," + b);
            {

            }
        }
        System.out.println(a);
    }

    private static int getOne() {
        return 1;
    }

    private static void elagazas() {
        int a = 3;
        if (a < 4) {
            System.out.println("kisebb mint 4");
        }
        if (a > 1) {
            System.out.println("nagyobb mint 1");
        }
    }

    private static void ciklus() {
        int a = 0;
        do {
            System.out.println(a);
            a++;
        } while (a < 0);
        System.out.println("ciklus utan " + a);

        int b = 0;
        while (b < 5) {
            System.out.println(b);
            b++;
        }

        for (int c = 0; c < 5; c++) {
            System.out.println(c);
        }

        Iterable<Ember> emberek = Arrays.asList(new Ember("Bela", SzemSzin.SZURKE), new Ember());
        for (Ember ember : emberek) {
            System.out.println(ember);
        }
    }

    private static int ciklusMegszakitas() {
        int[] lista = {3, 4, 23, 6, 56, 8, 9, 10, 12, 95};
        int index = 0;
        for (; index < lista.length; index++) {
            if (lista[index] == 23) {
                return index;
            }
            System.out.println(lista[index]);
        }
        System.out.println(index);
        return -1;
    }

}
