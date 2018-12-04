public class App {

    public static void main(String[] args) {//main threads
        System.out.println("==App Started==");


        MyTask task = new MyTask();
        task.start();


        System.out.println("==App Finished==");
    }
}

class MyTask extends Thread {
    @Override
    public void run() {
        for (int doc = 1; doc <= 10; doc++) {
            System.out.println("@@ Printing Document #" + doc + " - Printer2");
        }
    }
}