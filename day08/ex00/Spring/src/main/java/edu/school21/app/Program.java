package edu.school21.app;

import edu.school21.implementations.PreProcessorToUpperImpl;
import edu.school21.implementations.PrinterWithPrefixImpl;
import edu.school21.implementations.RendererErrImpl;
import edu.school21.interfaces.PreProcessor;
import edu.school21.interfaces.Printer;
import edu.school21.interfaces.Renderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

    public static void main(String[] args) {
        standardWay();
        springWay();
    }

    private static void standardWay() {
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);

        printer.setPrefix("Prefix ");
        printer.print("Hello!");
    }

    private static void springWay() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefix", Printer.class);

        printer.print("Hello!");
    }
}
