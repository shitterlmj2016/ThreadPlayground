public class Main {


    public static void main(String[] args) {
        System.out.println("==Begin Production==");
        Factory f = new Factory();
        productA a1 = new productA(f);
        productA a2 = new productA(f);
        productA a3 = new productA(f);
        productA a4 = new productA(f);

        productB b1 = new productB(f);
        productB b2 = new productB(f);

        a1.start();
        a2.start();
        b1.start();
        b2.start();
        a3.start();
        a4.start();

    }

}

class productA extends Thread {
    Factory fRef;
    int no;

    productA(Factory f) {
        fRef = f;
    }

    public void run() {



        while (fRef.toolA == true) {
            //wait for tool A
            //System.out.println("Product A waiting for tool A");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //Critical section
        fRef.toolA = true;

        no = fRef.start++;

        System.out.println("Product A" + no + " Started!");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fRef.toolA = false;


        while (fRef.toolB == true) {
            //wait for tool B
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        fRef.toolB = true;

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Set result and print
        //synchronized (fRef) {

        fRef.finished++;
        System.out.println("Product A" + no + " Finished");
        System.out.println("Total finished products: "+fRef.finished);
        fRef.toolB = false;
        //  }

    }

}


class productB extends Thread {
    Factory fRef;
    int no;

    productB(Factory f) {
        fRef = f;
    }

    public void run() {


        while (fRef.toolA == true) {
            //wait for tool A
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        fRef.toolA = true;
        no = fRef.start++;
        System.out.println("Product B" + no + " Started!");
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fRef.toolA = false;

        while (fRef.toolB == true) {
            //wait for tool B
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//

        fRef.toolB = true;

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Set result and print
        // synchronized (fRef) {

        fRef.finished++;
        System.out.println("Product B" + no + " Finished");
        System.out.println("Total finished products: "+fRef.finished);
        fRef.toolB = false;
        // }

    }

}

