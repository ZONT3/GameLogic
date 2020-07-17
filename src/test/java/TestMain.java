import org.junit.Test;
import ru.zont.gamelogic.logic.objs.EquipSetup;
import ru.zont.gamelogic.logic.objs.Plane;
import ru.zont.gamelogic.logic.objs.Unit.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class TestMain {
    private static final int PLANE_PROPCNT = 3;

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
            return new Property<>(88, 13, 37);
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

    public static class Lolkek extends Pizdauskas {
        @Override
        public Property<Integer> size() {
            return new Property<>(44);
        }
    }

    @Test
    public void propertiesEquals() {
        Plane p1 = new Pizdauskas();
        Plane p2 = new Lolkek();
        assert p1.size().equals(22);
        assert p2.size().equals(44);
        assert p1.size() != p2.size();
        assert !p1.size().equals(p2.size());
        assert p1.speed().equals(p2.speed());
        assert p1.speed() != p2.speed();
    }

    @Test
    public void planeBasic() {
        Plane pizdauskas = new Pizdauskas();
        HashMap<String, Property<?>> map = pizdauskas.getStats();
        assert map.size() == PLANE_PROPCNT;
        assert pizdauskas.getMaxHP() == 150;
        assert pizdauskas.speed().get().equals(14);
    }

    @Test
    public void planeBatchCreation() {
        ArrayList<Plane> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) list.add(new Pizdauskas());
        assert list.size() == 10000;
        assert list.get(228).speed().equals(14);
    }

    @Test
    public void planeBatchCreationOpt() {
        LinkedList<Plane> list = new LinkedList<>();
        for (int i = 0; i < 10000; i++) list.add(new Pizdauskas());
        assert list.size() == 10000;
    }


}
