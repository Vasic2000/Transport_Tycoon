import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Ship implements Runnable {

    private String name;
    public String getName() {
        return name;
    }

    private int speed;
    public int getSpeed() {
        return speed;
    }

    private int capasity;
    public int getCapasity() {
        return capasity;
    }

    private int load;
    public int getLoad() {
        return load;
    }
    public void setLoad(int load) {
        this.load = load;
    }

    private Product cargo;
    public void setCargo(Product cargo) {
        this.cargo = cargo;
    }
    public Product getCargo() {
        return cargo;
    }

    private CyclicBarrier cb;

    public Ship(String name, int speed, int capasity, CyclicBarrier cb) {
        this.name = name;
        this.speed = speed;
        this.capasity = capasity;
        this.cb = cb;
    }

    @Override
    public void run() {
        System.out.println(this.name + " спит в порту Istambul");
        while((TTD.p4.getPortCapacity()!=0) || (TTD.p5.getPortCapacity()!=0) || (TTD.p6.getPortCapacity()!=0)) {
            TTD.BlackSea.go(this);
            while((!TTD.p4.isFree()) && (!TTD.p5.isFree()) && (!TTD.p6.isFree())) {
            }
            if((TTD.p4.isFree()) && (TTD.p4.getPortCapacity()!=0)) {
                TTD.p4.go(this);
                Path mp = new Path(makePath(TTD.p4.load));
                mp.go(this);
            }
            else if((TTD.p5.isFree())  && (TTD.p5.getPortCapacity()!=0)) {
                TTD.p5.go(this);
                Path mp = new Path(makePath(TTD.p5.load));
                mp.go(this);
            }
            else if((TTD.p6.isFree()) && (TTD.p6.getPortCapacity()!=0)) {
                TTD.p6.go(this);
                Path mp = new Path(makePath(TTD.p6.load));
                mp.go(this);
            }
        }

        System.out.println(this.name + " закончил работу и вернулся в Istambul");

        try {
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Stage> makePath(Product cargo) {
        ArrayList<Stage> path = new ArrayList<>();
        path.add(TTD.BlackSea);
        path.add(TTD.Bosphorus);
        path.add(TTD.MediterraneanSea);
        for(Port p : TTD.unLoadPorts)
            if(p.unload == cargo)
                path.add(p);
        path.add(TTD.MediterraneanSea);
        path.add(TTD.BlackSea);
        return path;
    }

}
