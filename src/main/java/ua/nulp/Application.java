package ua.nulp;


import ua.nulp.controller.MainController;
import ua.nulp.service.implementation.PropertiesAlphabet;
import ua.nulp.service.implementation.TextAnalysingServiceImpl;
import ua.nulp.view.MainFrame;

public class Application {
    /**
     * Main method that runs when the program is started.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new MainController(new MainFrame(), new TextAnalysingServiceImpl(), new PropertiesAlphabet());
    }
}
