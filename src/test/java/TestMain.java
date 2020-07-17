import ru.zont.gamelogic.logic.objs.EquipSetup;
import ru.zont.gamelogic.logic.objs.Plane;

import java.lang.reflect.InvocationTargetException;

public class TestMain {
    public static class Pizdauskas extends Plane {
        @Override
        public Property<Integer> size() {
            return new Property<>(22);
        }

        @Override
        public Property<Integer> speed() {
            return new Property<>(14);
        }

        @Override
        public Property<Integer> man() {
            return new Property<>(88);
        }

        @Override
        public String manuf() {
            return "Latvian TURBO-KB";
        }

        @Override
        public String label() {
            return "PZDUSKS-14-88";
        }

        @Override
        public String alias() {
            return null;
        }

        @Override
        public String suffix() {
            return "DNIWE";
        }

        @Override
        public EquipSetup getEquip() {
            return null;
        }

        @Override
        public int getMaxHP() {
            return 150;
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        // TODO Creation, usage model for <? extends Unit> classes
        // TODO Learn about unitTests for task above

        System.out.println("OK");
    }
}
