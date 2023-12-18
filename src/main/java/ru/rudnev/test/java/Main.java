package ru.rudnev.test.java;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        firstOption();
        secondOption();
    }

    public static void firstOption(){
        double[] array = new double[100_000_000];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < array.length ; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения: " + ((endTime - startTime) / 1000.0) + " с");
    }

    public static void secondOption() throws InterruptedException {
        double[] array = new double[100_000_000];
        int quarterSize = array.length / 4;
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            int start = i * quarterSize;
            int end = (i + 1) * quarterSize;
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    array[j] = 1.14 * Math.cos(j) * Math.sin(j * 0.2) * Math.cos(j / 1.2);
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads){
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        double timeInSeconds = (endTime - startTime) / 1000.0;
        System.out.println("Время выполнения: " + timeInSeconds + " с");
    }
}