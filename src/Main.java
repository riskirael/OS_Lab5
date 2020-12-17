public class Main {
    public static void main(String[] args) {
        int timeInter = 3;
        int timeNonBlock;
        int timeBlock;
        Core core = new Core();
        Process process=new Process(1,2,true);
        process.createProcess(timeInter);
        timeNonBlock = core.non_blockingProcess();
        timeBlock = core.blockingProcess();
        System.out.println("Без блокировки: " + timeBlock);
        System.out.println("С блокировкой: " + timeNonBlock);
    }
}