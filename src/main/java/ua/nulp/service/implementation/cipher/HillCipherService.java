package ua.nulp.service.implementation.cipher;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import ua.nulp.service.interfaces.Alphabet;
import ua.nulp.service.interfaces.CipherService;

import java.math.BigInteger;

public class HillCipherService implements CipherService {
    private Alphabet alphabet;

    public HillCipherService(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    public String decode(String text, Object key) {
        return decode(text, (String) key, alphabet.get());
    }

    @Override
    public String encode(String text, Object key) {
        return encode(text, (String) key, alphabet.get());
    }

    @Override
    public String decode(String text, Object key, String alphabet) {
        return decode(text, (String) key, alphabet);
    }

    @Override
    public String encode(String text, Object key, String alphabet) {
        return encode(text, (String) key, alphabet);
    }

    private String encode(String text, String key, String alphabet) {
        text = text.toLowerCase().replaceAll("\n", "").trim();
        key = key.toLowerCase();
        alphabet = alphabet.toLowerCase();
        int dimension = (int) Math.sqrt(key.length());
        RealMatrix keyMatrix = createKeyMatrix(key, alphabet, dimension);
        return encodeByMatrix(text, dimension, keyMatrix, alphabet);
    }

    private String decode(String text, String key, String alphabet) {
        text = text.toLowerCase();
        key = key.toLowerCase();
        alphabet = alphabet.toLowerCase();
        int alphabetLength = alphabet.length();
        int dimension = (int) Math.sqrt(key.length());
        RealMatrix keyMatrix = createKeyMatrix(key, alphabet, dimension);
        long determinant = getDeterminant(keyMatrix);
        RealMatrix inversed;
        try {
            inversed = inverseMatrix(keyMatrix, determinant, alphabetLength);
        } catch (ArithmeticException e) {
            return "";
        }
        return encodeByMatrix(text, dimension, inversed, alphabet);
    }

    private String encodeByMatrix(String text, int dimension, RealMatrix keyMatrix, String alphabet) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i += dimension) {
            String substring;
            if (i + dimension > text.length()) {
                substring = text.substring(i);
            } else {
                substring = text.substring(i, i + dimension);
            }
            RealMatrix substringVector = createVector(substring, alphabet, dimension);
            RealMatrix multiplied = keyMatrix.multiply(substringVector);
            RealVector mappedVector = mapVector(multiplied.getColumn(0),
                    substringVector.getColumn(0), alphabet.length());
            String stringFromVector = getStringFromVector(mappedVector, alphabet);
            stringBuilder.append(stringFromVector);
        }
        return stringBuilder.toString().trim().toUpperCase();

    }

    private RealMatrix inverseMatrix(RealMatrix keyMatrix, long determinant, int alphabetLength) {
        double[][] matrix = MatrixUtils.inverse(keyMatrix).scalarMultiply(determinant).getData();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                long modInverse = modInverse(determinant, alphabetLength);
                matrix[i][j] = Math.floorMod(Math.round(matrix[i][j] * modInverse), alphabetLength);
            }
        }
        return MatrixUtils.createRealMatrix(matrix);
    }

    private long modInverse(long determinant, long alphabetLength) {
        return BigInteger.valueOf(determinant)
                .modInverse(BigInteger.valueOf(alphabetLength))
                .longValue();
    }

    private long getDeterminant(RealMatrix matrix) {
        return Math.round(new LUDecomposition(matrix)
                .getDeterminant());
    }

    private RealVector mapVector(double[] columnVector, double[] substringVector, int alphabetLength) {
        for (int j = 0; j < columnVector.length; j++) {
            double value = columnVector[j];
            value = value >= 0
                    ? Math.floorMod((int) value, alphabetLength)
                    : substringVector[j];
            columnVector[j] = value;
        }
        return MatrixUtils.createRealVector(columnVector);
    }

    private RealMatrix createVector(String text, String alphabet, int dimension) {
        double[][] matrix = new double[dimension][1];
        for (int i = 0; i < matrix.length; i++) {
            char textChar = i < text.length() ? text.charAt(i) : ' ';
            int index = alphabet.indexOf(textChar);
            if (index < 0) {
                index = -((int) textChar);
            }
            matrix[i][0] = index;
        }
        return MatrixUtils.createRealMatrix(matrix);
    }

    private RealMatrix createKeyMatrix(String key, String alphabet, int dimension) {
        double[][] matrix = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int index = i * dimension + j;
                char keyChar = key.charAt(index);
                matrix[i][j] = alphabet.indexOf(keyChar);
            }
        }
        return MatrixUtils.createRealMatrix(matrix);
    }

    private String getStringFromVector(RealVector vector, String alphabet) {
        double[] data = vector.toArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (double datum : data) {
            int index = (int) datum;
            char vectorChar;
            if (index >= 0) {
                vectorChar = alphabet.charAt(index);
            } else {
                vectorChar = (char) -index;
            }
            stringBuilder.append(vectorChar);
        }
        return stringBuilder.toString();
    }
}
