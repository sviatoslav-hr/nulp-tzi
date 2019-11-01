package ua.nulp.service.implementation;

import ua.nulp.service.interfaces.Alphabet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesAlphabet implements Alphabet {
    private  static  final  String  PROPERTIES_NAME = "application.properties";
    private  static  final  String  PROPERTIES_KEY = "app.alphabet";

    @Override
    public String get() {
        try (InputStream input = PropertiesAlphabet
                .class.getClassLoader()
                .getResourceAsStream(PROPERTIES_NAME)) {
            Properties prop = new Properties();
            if (input == null) {
                return null;
            }
            prop.load(input);
            return prop.getProperty(PROPERTIES_KEY);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
