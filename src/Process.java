import java.util.Deque;
import java.util.LinkedList;

public class Process {

    private int idProcess;
    private int time = 0;
    private int timeProcessInWork = 100;
    private int maxTimeWork;
    private int idStart = 1;
    private int idEnd = 2;
    private int idTerminate = 3;
    private boolean terminate;
    private Thread thread = new Thread();

    public static Deque<Process> procesOne = new LinkedList<>();
    public static Deque<Process> procesTwo = new LinkedList<>();
    public static Process process;
    private int maxTimeProcess = 100;

    public Process(int idProcess, int maxTimeWork, boolean terminate) {
        this.idProcess = idProcess;
        this.maxTimeWork = maxTimeWork;
        this.terminate = terminate;
    }

    public int getIdProcess() {
        return idProcess;
    }

    public int getTime() {
        return time;
    }

    public void addTime(int time) {
        this.time += time;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }

    public int startProcess() {
        boolean checkProcessEnd = false;
        if (!checkProcessEnd) {
            time = 0;
            System.out.println("\n"+"Запущен процесс № " + idProcess);
            time += timeProcessInWork;
            if (maxTimeWork < time) {
                System.out.println("Процессу №" + idProcess + " не хватило времени для завершения.");
                return idEnd;
            }
            try {
                thread.sleep(maxTimeWork);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkProcessEnd = true;
        }
        if (terminate) {
            System.out.print("Процесс № " + idProcess+ " разблокирован");
            terminate = false;
            return idTerminate;
        }
        if (checkProcessEnd) {
            time += timeProcessInWork;
            if (maxTimeWork > time) {
                System.out.println("Процессу № " + idProcess + " не хватило времени для завершения.");
                return idEnd;
            }
            try {
                thread.sleep(timeProcessInWork);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("Процесс № " + idProcess + " завершился");
        }
        return idStart;
    }
    public void createProcess(int timeInterval) {
        for (int i = 0; i < timeInterval; i++) {
            process = new Process(i, maxTimeProcess, false);
            if (i == 0) {
                process.setTerminate(true);
            }
            procesOne.add(process);
        }
        for (int i = 0; i < timeInterval; i++) {
            process = new Process(i, maxTimeProcess, false);
            if (i == 0) {
                process.setTerminate(true);
            }
            procesTwo.add(process);
        }
    }
}
