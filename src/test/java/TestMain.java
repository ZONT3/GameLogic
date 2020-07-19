import org.junit.Test;
import ru.zont.gamelogic.logic.objs.EquipPatterns;
import ru.zont.gamelogic.logic.objs.Plane;
import ru.zont.gamelogic.logic.objs.Unit.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.*;

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
        public EquipPatterns defaultEquip() {
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
        assertEquals(Integer.valueOf(22), p1.size().get());
        assertEquals(Integer.valueOf(44), p2.size().get());
        assertNotSame(p1.size(), p2.size());
        assertNotEquals(p1.size(), p2.size());
        assertEquals(p1.speed(), p2.speed());
        assertNotSame(p1.speed(), p2.speed());
    }

    @Test
    public void planeBasic() {
        Plane pizdauskas = new Pizdauskas();
        HashMap<String, Property<?>> map = pizdauskas.getStats();
        assertEquals(map.size(), PLANE_PROPCNT);
        assertEquals(pizdauskas.getMaxHP(), 150);
        assertEquals(14, (int) pizdauskas.speed().get());
    }

    @Test
    public void planeBatchCreation() {
        ArrayList<Plane> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) list.add(new Pizdauskas());
        assertEquals(list.size(), 10000);
        assertEquals(Integer.valueOf(14), list.get(228).speed().get());
    }

    @Test
    public void planeBatchCreationOpt() {
        LinkedList<Plane> list = new LinkedList<>();
        for (int i = 0; i < 10000; i++) list.add(new Pizdauskas());
        assertEquals(list.size(), 10000);
    }

    @Test
    public void planeEquipment() {
        Plane p = new Pizdauskas();
        // TODO
    }
}
