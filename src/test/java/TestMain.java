import org.junit.Test;
import ru.zont.gamelogic.logic.objs.*;
import ru.zont.gamelogic.logic.objs.StatedObject.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class TestMain {

    @Equipment.EquipLogic
    public interface TopGun {
        int getPowah();
    }

    public static class WunderwaffleGun extends Equipment
                                        implements TopGun {
        @Override
        public int getPowah() {
            return 1488*228/1337;
        }
    }

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
            return new EquipPatterns(new EquipSetup.Template(eqSlots().get(), WunderwaffleGun.class, WunderwaffleGun.class));
        }

        @Override
        public Property<Integer> eqSlots() {
            return new Property<>(3);
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
//        assertEquals(map.size(), PLANE_PROPCNT);
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
        for (int i = 0; i < 10000; i++) list.push(new Pizdauskas());
        assertEquals(list.size(), 10000);
    }

    @Test
    public void basicEquality() {
        Equipment e2 = new WunderwaffleGun();
        Basic e1 = new WunderwaffleGun();
        Basic u1 = new Pizdauskas();
        Basic u2 = new Lolkek();
        Plane u3 = new Pizdauskas();
        assertEquals(e1, e2);
        assertEquals(u1, u3);
        assertNotEquals(u1, u2);
    }

    @Test
    public void planeEquipment() {
        Plane p = new Pizdauskas();
        EquipSetup setup = p.getEquip();
        assertEquals(3, setup.size());
        assertEquals(Equipment.class, setup.get(2).getClass());
        assertEquals(WunderwaffleGun.class, setup.get(0).getClass());
        assertEquals(WunderwaffleGun.class, setup.get(1).getClass());
        assertFalse(setup.isEmpty());
        assertTrue(setup.get(2).isEmpty());
    }

    @Test
    public void equipmentPatterns() {
        ArrayList<EquipSetup> list = new ArrayList<>();
        EquipSetup.Template t1 = new EquipSetup.Template(3, WunderwaffleGun.class);
        EquipSetup.Template t2 = new EquipSetup.Template(3);
        for (int i = 0; i < 1000; i++)
            list.add(new EquipPatterns(t1, t2).get());
        int freq1 = Collections.frequency(list, t1.buildSetup());
        int freq2 = Collections.frequency(list, t2.buildSetup());
//        System.out.printf("F1:%d|F2:%d", freq1, freq2);
        assertTrue( freq1 > 400 && freq2 < 600);
        assertTrue(freq2 > 400);
    }
}
