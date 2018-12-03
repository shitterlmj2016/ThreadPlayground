public class ThreadTest {

    public static boolean toolA = false;
    public static boolean toolB = false;

    public static int started = 0;
    public static int finished = 0;

    public static void main(String[] args) {
        new productB().start();
        new productA().start();
        new productA().start();
        new productB().start();
        new productB().start();


    }

}

class productA extends Thread {

    @Override
    public void run() {
        ThreadTest a = new ThreadTest();
        int index = a.started;
        a.started++;

        System.out.println("Product A" + index + " started!");
        while (a.toolA == true) {
            System.out.println("Product A" + index + " is waiting for tool A");
        }
        a.toolA = true;
        System.out.println("Product A" + index + " is using tool A");
        a.toolA = false;
        System.out.println("Product A" + index + " finished using tool A");


        while (a.toolB == true) {
            System.out.println("Product A" + index + " is waiting for tool B");
        }
        a.toolB = true;
        System.out.println("Product A" + index + " is using tool B");
        System.out.println("Product A" + index + " finished using tool B");
        //Finish should be here
        a.toolB = false;

        a.finished++;
        System.out.println("Finished product: " + a.finished);


    }
}

class productB extends Thread {

    @Override
    public void run() {
        ThreadTest a = new ThreadTest();

        int index = a.started;
        a.started++;
        System.out.println("Product B" + index + " started!");

        while (a.toolA == true) {
            System.out.println("Product B" + index + " is waiting for tool A");
        }
        a.toolA = true;
        System.out.println("Product B" + index + " is using tool A");

        a.toolA = false;
        System.out.println("Product B" + index + " finished using tool A");

        while (a.toolB == true) {
            System.out.println("Product B" + index + " is waiting for tool B");
        }
        a.toolB = true;
        System.out.println("Product B" + index + " is using tool B");


        a.toolB = false;
        System.out.println("Product B" + index + " finished using tool B");
        
        a.finished++;
        System.out.println("Finished product: " + a.finished);
    }
}

