package ru.zont.gamelogic.logic.objs;

import ru.zont.gamelogic.logic.DynamicName;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Equipment extends StatedObject {
    /**
     * Instances created from this class will be an empty equipment slots
     */
    public Equipment() { }

    public boolean isEmpty() {
        for (Class<?> i: getClass().getInterfaces())
            if (i.isAnnotationPresent(EquipLogic.class))
                return false;
        return true;
    }

    @Override
    public DynamicName getName() {
        return new DynamicName("");
    }

    /**
     * Do not forget to mark interfaces for equipment with this annotation
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface EquipLogic { }
}
