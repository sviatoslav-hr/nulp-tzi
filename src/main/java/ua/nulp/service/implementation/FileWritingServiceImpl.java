package ua.nulp.service.implementation;

import ua.nulp.service.interfaces.FileWritingService;

import java.io.*;

public class FileWritingServiceImpl implements FileWritingService {
    @Override
    public void write(String path, String text) {
        File file = new File(path);
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file, true))) {
            printWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
