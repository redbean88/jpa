package steps;

import java.util.HashMap;
import java.util.Map;

public class StepMapper {
    private static Map<String, Step> steps = new HashMap<String, Step>();

    static {
        steps.put("step00" , new Step00());
        steps.put("step01" , new Step01());
        steps.put("step02" , new Step02());
        steps.put("step03" , new Step03());
        steps.put("step04" , new Step04());
    }

    public static Step getStep(String target){
        return steps.get(target);
    }
}
