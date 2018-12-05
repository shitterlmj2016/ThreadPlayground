public class Main {


    public static void main(String[] args) {
        System.out.println("==Begin Production==");
        Factory f = new Factory();
        productA a1 = new productA(f);
        productA a2 = new productA(f);
        productA a3 = new productA(f);
        productA a4 = new productA(f);
        //
        productB b1 = new productB(f);
        productB b2 = new productB(f);
        productA a5 = new productA(f);

        a1.start();
        a2.start();
        b1.start();
        b2.start();
        a3.start();
        a4.start();
        a5.start();

    }
}

class productA extends Thread {
    Factory factory;
    int id;

    productA(Factory f) {
        factory = f;
    }

    public void run() {

        //Acquire Semaphore
        try {
            factory.toolA.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Critical section


        id = factory.started++;

        System.out.println("Product " + id + " A Started!");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        //Critical Section Ends
        //Release Semaphore




        factory.toolA.release();

        try {
            factory.toolB.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Set result and print
        //synchronized (factory) {

        factory.finished++;
        factory.productA++;
        System.out.println("Product " + id + " A Finished");
        System.out.println("Total finished products: " + factory.finished + ": A: "+ factory.productA+"; B: "+factory.productB);

        factory.toolB.release();
        //  }

    }

}


class productB extends Thread {
    Factory factory;
    int id;

    productB(Factory f) {
        factory = f;
    }

    public void run() {


        try {
            factory.toolA.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        id = factory.started++;
        System.out.println("Product " + id + " B Started!");
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        factory.toolA.release();


        try {
            factory.toolB.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Set result and print
        // synchronized (factory) {

        factory.finished++;
        factory.productB++;
        System.out.println("Product " + id + " B Finished");




        System.out.println("Total finished products: " + factory.finished + ": A: "+ factory.productA+"; B: "+factory.productB);
        factory.toolB.release();
        // }

    }

}

