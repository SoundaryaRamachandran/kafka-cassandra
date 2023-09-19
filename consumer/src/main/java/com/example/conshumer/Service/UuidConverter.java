package com.example.conshumer.Service;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UuidConverter {

    public static UUID generateUUIDFromInt(int orderId) {
        // Convert the integer to bytes
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(orderId);
        buffer.flip();
        byte[] bytes = buffer.array();

        // Create an MD5 hash of the bytes
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(bytes);

            // Set version and variant bits
            hash[6] &= 0x0F;  // clear version
            hash[6] |= 0x30;  // set to version 3 (MD5-based UUID)
            hash[8] &= 0x3F;  // clear variant
            hash[8] |= 0x80;  // set to IETF variant

            // Create the UUID from the hash bytes
            ByteBuffer hashBuffer = ByteBuffer.wrap(hash);
            long mostSigBits = hashBuffer.getLong();
            long leastSigBits = hashBuffer.getLong();
            return new UUID(mostSigBits, leastSigBits);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

