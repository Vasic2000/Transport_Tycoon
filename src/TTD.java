import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TTD {
    public static ArrayList<Port> loadPorts = new ArrayList<>();
    public static ArrayList<Port> unLoadPorts = new ArrayList<>();

    public static Port p1 = new Port("Tripoli", 100, 0, null, Product.Clothing);
    public static Port p2 = new Port("Benghazy", 80, 0, null, Product.Fuel);
    public static Port p3 = new Port("Alexandria", 150, 0 , null, Product.Food);
    public static Port p4 = new Port("Sevastopol", 120, 1500, Product.Food, null);
    public static Port p5 = new Port("Novorossiysk", 200, 1400, Product.Clothing, null);
    public static Port p6 = new Port("Sochi", 90, 1100 , Product.Fuel, null);

    public static Strait Bosphorus = new Strait(27, 15, 2);

    public static Sea BlackSea = new Sea("BlackSea", 800);
    public static Sea MediterraneanSea = new Sea("MediterrianSea", 1400);

    public static final CyclicBarrier cb = new CyclicBarrier(6);

    public static Ship ship1 = new Ship("Ship 1", 50, 220, cb);
    public static Ship ship2 = new Ship("Ship 2", 50, 210, cb);
    public static Ship ship3 = new Ship("Very Fast Ship", 100, 50, cb);
    public static Ship ship4 = new Ship("Ship 3", 50, 200, cb);
    public static Ship ship5 = new Ship("Big Load Ship ", 25, 900, cb);

    public static void main(String[] args) {
        TTD tt = new TTD();

        loadPorts.add(p4);
        loadPorts.add(p5);
        loadPorts.add(p6);

        unLoadPorts.add(p1);
        unLoadPorts.add(p2);
        unLoadPorts.add(p3);

        for(Port p : loadPorts)
            System.out.println(p.getName() + " "  + p.getPortCapacity() + p.getLoad());
        for(Port p : unLoadPorts)
            System.out.println(p.getName() + " "  + p.getPortCapacity() + p.getLoad());


        new Thread(ship1).start();
        new Thread(ship2).start();
        new Thread(ship3).start();
        new Thread(ship4).start();
        new Thread(ship5).start();

        try {
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        for(Port p : loadPorts)
            System.out.println(p.getName() + " "  + p.getPortCapacity() + p.getLoad());
        for(Port p : unLoadPorts)
            System.out.println(p.getName() + " "  + p.getPortCapacity() + p.getLoad());
    }
}
