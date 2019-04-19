public class Port extends Stage {

    private String name;
    public String getName() {
        return name;
    }

    private int portCapacity;
    public int getPortCapacity() {
        return portCapacity;
    }

    boolean isFree = true;
    int speed;
    Product load;
    Product unload;

    public String getLoad() {
        if(load!=null)
            return " tonns of " + this.load;
        else
            return " tonns of " + this.unload;
    }

    public Port(String name, int speed, int portCapacity, Product load, Product unload) {
        this.name = name;
        this.speed = speed;
        this.portCapacity = portCapacity;
        this.load = load;
        this.unload = unload;
    }

    public boolean isFree() {
        return isFree;
    }

    @Override
    public synchronized void go(Ship sh) {
        if (load != null) {
            try {
                this.isFree = false;
                System.out.println(sh.getName() + " начал погрузку в  " + name);
                if (portCapacity > sh.getCapasity()) {
                    sh.setLoad(sh.getCapasity());
                    sh.setCargo(this.load);
                    this.portCapacity -= sh.getCapasity();
                } else {
                    sh.setLoad(portCapacity);
                    sh.setCargo(this.load);
                    this.portCapacity = 0;
                }
                Thread.sleep(sh.getCapasity() / speed * 10);
                this.isFree = true;
                System.out.println(sh.getName() + " закончил загрузку в " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.isFree = false;
                System.out.println(sh.getName() + " начал разгрузку в  " + name);
                Thread.sleep(sh.getCapasity() / speed * 10);
                portCapacity += sh.getLoad();
                sh.setLoad(0);
                sh.setCargo(null);
                isFree = true;
                System.out.println(sh.getName() + " закончил разгружаться в " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}