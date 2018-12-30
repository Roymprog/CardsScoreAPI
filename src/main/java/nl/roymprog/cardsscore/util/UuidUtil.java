package nl.roymprog.cardsscore.util;

import java.util.UUID;

public class UuidUtil {

    public static String generateRandomId() {
        return UUID.randomUUID().toString();
    }
}
