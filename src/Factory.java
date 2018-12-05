import java.util.concurrent.Semaphore;


public class Factory {

    Semaphore toolA;
    Semaphore toolB;

    int started;
    int finished;

    int productA;
    int productB;


    public Factory() {
        toolA = new Semaphore(1);
        toolB = new Semaphore(1);
        started = 0;
        finished = 0;
        productA = 0;
        productB = 0;
    }

}
