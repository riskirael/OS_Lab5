import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class Breaking {
   public static Queue<Process> breaking= new LinkedList<>();
    public static Process memoryProcessOne;
    public static void breaking() {
        Timer timer;
        memoryProcessOne = Process.process;
        System.out.println("Произошло прерывание, планировщик не остановлен.");
        Process.process = Process.procesOne.pollFirst();
        timer = new Timer(Core.time, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Process.procesOne.addFirst(memoryProcessOne);
                System.out.println("Взаимодействие с устройством ввода-вывода завершено, процесс № " + memoryProcessOne.getIdProcess() + " разблокирован и продолжает работать.");
                Core.breakingCheck = true;
            }
        });
        timer.start();
    }
}
