package ogya.workshop.performance_appraisal.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDUtil {

    public static byte[] uuidToBinary(UUID uuid) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        return buffer.array();
    }

    public static UUID binaryToUUID(byte[] binary) {
        if (binary.length != 16) {
            throw new IllegalArgumentException("Binary data must be 16 bytes long to be a valid UUID.");
        }
        ByteBuffer buffer = ByteBuffer.wrap(binary);
        long mostSigBits = buffer.getLong();
        long leastSigBits = buffer.getLong();
        return new UUID(mostSigBits, leastSigBits);
    }

}
