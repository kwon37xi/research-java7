package ch09.extendedsslsession;

import java.security.AlgorithmConstraints;
import java.security.AlgorithmParameters;
import java.security.CryptoPrimitive;
import java.security.Key;
import java.security.interfaces.RSAKey;
import java.util.Set;

public class SimpleConstraints implements AlgorithmConstraints {

    @Override
    public boolean permits(Set<CryptoPrimitive> primitives, String algorithm, AlgorithmParameters parameters) {
        return permits(primitives, algorithm, null, parameters);
    }

    @Override
    public boolean permits(Set<CryptoPrimitive> primitives, Key key) {
        return permits(primitives, null, key, null);
    }

    @Override
    public boolean permits(Set<CryptoPrimitive> primitives, String algorithm, Key key, AlgorithmParameters parameters) {
        if (algorithm == null) {
            algorithm = key.getAlgorithm();
        }

        // RSA가 아니면 허용안함.
        if (algorithm.indexOf("RSA") == -1) {
            return false;
        }

        if (key != null) {
            RSAKey rsaKey = (RSAKey) key;
            int size = rsaKey.getModulus().bitLength();
            if (size < 4096) { // 2048bit 미만은 허용안함.
                return false;
            }
        }
        return true;
    }
}
