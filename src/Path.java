import java.util.ArrayList;

public class Path extends Stage {
    private ArrayList<Stage> stages;

    public Path(ArrayList<Stage> stages) {
        this.stages = stages;
    }

    @Override
    public void go(Ship sh) {
        for(Stage st : stages)
            st.go(sh);
    }
}
