public class Sea extends Stage {
    public String name;
    public int distance;

    public Sea(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    @Override
    public void go(Ship sh) {
        try {
            if(sh.getCargo()!=null)
                System.out.println(sh.getName() + " вышел в море " + name + " в трюме " + sh.getLoad() + " тонн " + sh.getCargo());
            else
                System.out.println(sh.getName() + " вышел в море " + name + " пустой");
            Thread.sleep(distance / sh.getSpeed() * 100);
            if(sh.getCargo()!=null)
                System.out.println(sh.getName() + " прошёл море " + name + " в трюме " + sh.getLoad() + " тонн " + sh.getCargo());
            else
                System.out.println(sh.getName() + " прошёл море " + name +" пустой");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
