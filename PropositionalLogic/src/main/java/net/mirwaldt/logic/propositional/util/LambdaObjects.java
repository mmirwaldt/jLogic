package net.mirwaldt.logic.propositional.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class LambdaObjects {
    /**
     * Lambdas cannot be compared by equals as it would only compare identity.
     * However, lambdas can be serialized and the resulting bytes can be compared.
     * See https://stackoverflow.com/questions/24095875/is-there-a-way-to-compare-lambdas/36794336
     * 
     * @param leftLambda left lambda
     * @param rightLambda right lambda
     * @return whether the serialized bytes of the lambdas are equal
     * @throws RuntimeException if serialization does not work.
     */
    public static boolean equals(Serializable leftLambda, Serializable rightLambda) {
        try {
            final ByteArrayOutputStream leftByteStream = new ByteArrayOutputStream();
            final ObjectOutputStream leftObjectStream = new ObjectOutputStream(leftByteStream);
            leftObjectStream.writeObject(leftLambda);
            leftObjectStream.close();

            final ByteArrayOutputStream rightByteStream = new ByteArrayOutputStream();
            final ObjectOutputStream rightObjectStream = new ObjectOutputStream(rightByteStream);
            rightObjectStream.writeObject(rightLambda);
            rightObjectStream.close();

            return Arrays.equals(leftByteStream.toByteArray(), rightByteStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Cannot compare two lambdas.", e);
        }
    }

    public static int hash(Serializable lambda) {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(lambda);
            objectStream.close();

            return Arrays.hashCode(byteStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Cannot compare two lambdas.", e);
        }
    }
}
