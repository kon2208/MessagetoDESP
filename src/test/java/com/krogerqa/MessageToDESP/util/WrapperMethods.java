package com.krogerqa.MessageToDESP.util;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class WrapperMethods {

    public static void main(String[] args) {

    }
    public static long generateRandomNumber(int digits) {
        Random random = new Random();
        long min = (long) Math.pow(10, digits - 1);
        long max = (long) Math.pow(10, digits) - 1;
        return min + Math.abs(random.nextLong()) % (max - min + 1);
    }

    public static long generateCurrentEpochTime() {
        Instant now = Instant.now();
        long currentMilliseconds = now.toEpochMilli();
        return currentMilliseconds;
    }

    public static String generateRandomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }



}
