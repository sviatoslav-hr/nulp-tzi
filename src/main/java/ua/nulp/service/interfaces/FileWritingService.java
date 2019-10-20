package ua.nulp.service.interfaces;

public interface FileWritingService extends FileService {
    void write(String path, String text);
}
