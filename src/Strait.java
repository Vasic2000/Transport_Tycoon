import java.util.concurrent.Semaphore;

public class Strait extends Stage {

    private int length;
    private int maxSpeed;
    private int maxShip;
    private Semaphore smp;

    public Strait(int length, int maxSpeed, int maxShip) {
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.maxShip = maxShip;
        this.smp = new Semaphore(2);
    }

    @Override
    public void go(Ship sh) {
        try {
            System.out.println(sh.getName() + " подошёл к проливу Bosphorus");
            smp.acquire();
            System.out.println(sh.getName() + " вошёл в пролив");
            Thread.sleep(length / maxSpeed * 100);
            smp.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(sh.getName() + " вышел из пролива Bosphorus");
        }
    }
}
