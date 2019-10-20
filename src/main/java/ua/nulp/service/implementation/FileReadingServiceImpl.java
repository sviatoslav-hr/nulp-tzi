package ua.nulp.service.implementation;

import ua.nulp.service.interfaces.FileReadingService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReadingServiceImpl implements FileReadingService {
    @Override
    public String read(String path) {
        File file = new File(path);
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
