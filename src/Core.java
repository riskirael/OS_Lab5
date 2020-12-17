import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;

public class Core {
    private Thread thread = new Thread();
    private Process memoryProcessTwo;
    public static boolean breakingCheck;
    private int timeoutLock = 0;
    private int timeLock = 0;
    public static int time = 1000;

    public int blockingProcess() {
        System.out.println("\n" + "Процессы с блокировкой: ");
        Process.process = Process.procesTwo.pollFirst();
        while (Process.process != null) {
            int check = Process.process.startProcess();
            if (check == 1) {
                Process.process = Process.procesTwo.pollFirst();
            }
            if (check == 2) {
                timeLock += Process.process.getTime();
                Process.procesOne.addLast(Process.process);
            }
            if (check == 3) {
                System.out.println("Произошло прерывание для взаимодействия с устройством ввода-вывода, планировщик остановлен.");
                memoryProcessTwo = Process.process;
                try {
                    thread.sleep(time);
                    Process.procesOne.addFirst(memoryProcessTwo);
                    System.out.println("Взаимодействое с устройством ввода-вывода завершено, процесс номер " + memoryProcessTwo.getIdProcess() + " разблокирован и продолжает работать.");
                    timeLock += time;
                    memoryProcessTwo.addTime(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("\nНа выполнение процессов с блокировкой ушло: " + timeLock + '\n');
        return memoryProcessTwo.getTime();
    }

    public int non_blockingProcess() {
        System.out.println("Процессы без блокировки: ");
        Process.process = Process.procesOne.pollFirst();
        while (Process.process != null) {
            int check = Process.process.startProcess();
            if (check == 1) {
                timeoutLock += Process.process.getTime();
                Process.process = Process.procesOne.pollFirst();
            }
            if (check == 2) {
                Process.procesOne.addLast(Process.process);
            }
            if (check == 3) {
                if (breakingCheck) {
                    breakingCheck = false;
                    Breaking.breaking();
                } else {
                    Breaking.breaking.add(Process.process);
                    Process.process = Process.procesOne.pollFirst();
                }
            }
            if (Process.process == null && Breaking.breaking.peek() != null) {
                Process.process =Breaking.breaking.poll();
                breakingCheck = false;
                Breaking.breaking();
            }
            if (Process.process == null && Breaking.breaking.peek() == null) {
                Process.process = Process.procesOne.poll();
            }
        }
        System.out.println("На выполнение процессов без блокировки ушло: " + timeoutLock + '\n');
        return Breaking.memoryProcessOne.getTime();
    }
}