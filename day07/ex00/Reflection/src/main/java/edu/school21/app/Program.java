package edu.school21.app;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.BiConsumer;


public class Program {
    private static final char PKG_SEPARATOR = '.';
    private static final char DIR_SEPARATOR = '/';
    private static final String CLASS_FILE_SUFFIX = ".class";
    private static final String CLASSES_PACKAGE = "edu.school21.classes";
    public static final Boolean LIGHT_SIDE = true;
    public static final Boolean DARK_SIDE = false;
    public static final BiConsumer<Integer, String> PRINT_DELIMITER_STRING = (count, delimiter) -> System.out.println(String.join("", Collections.nCopies(count, delimiter)));
    public static Class<?> classs;
    private static Object object;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        process();
    }

    public static void process() {
        printClasses();
        chooseClass(3);
        printClassInfo();
        createObject();
        changeField();
        callMethod();
    }

    public static void printClasses() {
        List<Class<?>> classes = findClasses();

        System.out.println("Classes:");
        for (Class<?> classss : classes) {
            System.out.println("\t" + classss.getSimpleName());
        }
        PRINT_DELIMITER_STRING.accept(20, "-");
    }

    private static List<Class<?>> findClasses() {
        File scannedDir = new File(
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResource(CLASSES_PACKAGE.replace(PKG_SEPARATOR, DIR_SEPARATOR))
                        .getFile()
        );
        List<Class<?>> classes = new ArrayList<>();

        for (File file : Objects.requireNonNull(scannedDir.listFiles())) {
            Class<?> classss = findClasses(file);
            if (classss != null) {
                classes.add(classss);
            }
        }
        return classes;
    }

    private static Class<?> findClasses(File file) {
        String resource = CLASSES_PACKAGE + PKG_SEPARATOR + file.getName();

        if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            String className = resource.substring(0, resource.lastIndexOf('.'));
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                errExit(e.getMessage());
            }
        }
        return null;
    }

    public static void chooseClass(int attempts) {
        if (attempts == 0) {
            errExit("You don't have any attempts");
        }
        System.out.println("Enter class name:");
        String className = scanner.nextLine();

        try {
            classs = Class.forName(CLASSES_PACKAGE + "." + className);
        } catch (ClassNotFoundException e) {
            --attempts;
            System.err.println("Class '" + className + "' not found try again you have " + attempts + " attempts");
            chooseClass(attempts);
        }
        PRINT_DELIMITER_STRING.accept(20, "-");
    }

    public static void printClassInfo() {
        Field[] fields = classs.getDeclaredFields();
        Method[] methods = classs.getDeclaredMethods();

        System.out.println("fields: ");
        for (Field field : fields) {
            System.out.println('\t' + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("methods: ");
        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            System.out.print('\t' + method.getReturnType().getSimpleName() + " " + method.getName());
            printParameters(parameters);
        }
        PRINT_DELIMITER_STRING.accept(20, "-");
    }

    private static void printParameters(Parameter[] parameters) {
        System.out.print('(');
        for (int i = 0; i < parameters.length; ++i) {
            System.out.print(parameters[i].getType().getSimpleName());
            if (i + 1 != parameters.length) {
                System.out.print(", ");
            }
        }
        System.out.println(");");
    }

    public static void createObject() {
        System.out.println("Let's create an object.");

        Constructor<?>[] constructors = classs.getConstructors();
        int constructorForCreate = chooseConstructorForCreate(constructors);
        scanner.nextLine();

        try {
            if (constructors[constructorForCreate].getParameterCount() == 0) {
                object = constructors[constructorForCreate].newInstance();
            } else {
                Parameter[] parameters = constructors[constructorForCreate].getParameters();
                Object[] initArgs = new Object[parameters.length];

                for (int i = 0; i < initArgs.length; i++) {
                    System.out.println(parameters[i].getName() + ":");
                    String line = scanner.nextLine();

                    switch (parameters[i].getType().getSimpleName()) {
                        case "String":
                            initArgs[i] = line;
                            break;
                        case "Integer":
                            initArgs[i] = Integer.parseInt(line);
                            break;
                        case "Double":
                            initArgs[i] = Double.parseDouble(line);
                            break;
                        case "Boolean":
                            initArgs[i] = Boolean.parseBoolean(line);
                            break;
                        case "Long":
                            initArgs[i] = Long.parseLong(line);
                            break;
                        default:
                            errExit("Illegal argument type in constructor");
                    }
                }
                object = constructors[constructorForCreate].newInstance(initArgs);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            errExit(e.getMessage());
        }
        System.out.println("Object created: " + object);
        PRINT_DELIMITER_STRING.accept(20, "-");
    }

    private static int chooseConstructorForCreate(Constructor<?>[] constructors) {
        if (constructors.length != 1) {
            System.out.println("Class had " + constructors.length + " constructors:");

            for (int i = 0; i < constructors.length; i++) {
                Parameter[] parameters = constructors[i].getParameters();

                System.out.print("\t" + (i + 1) + ". " + constructors[i].getDeclaringClass().getSimpleName());
                printParameters(parameters);
            }
            System.out.println("Choose constructor for create an object:");
            return scanner.nextInt() - 1;
        }
        return 0;
    }

    public static void changeField() {
        System.out.println("Enter name of the field for changing:");
        String fieldLine = scanner.nextLine();
        String changeFieldValue;

        try {
            Field field = object.getClass().getDeclaredField(fieldLine);

            field.setAccessible(true);
            System.out.println("Enter " + field.getType().getSimpleName() + " value:");
            changeFieldValue = scanner.nextLine();

            switch (field.getType().getSimpleName()) {
                case "String":
                    field.set(object, changeFieldValue);
                    break;
                case "Integer":
                    field.set(object, Integer.parseInt(changeFieldValue));
                    break;
                case "Double":
                    field.set(object, Double.parseDouble(changeFieldValue));
                    break;
                case "Boolean":
                    field.set(object, Boolean.parseBoolean(changeFieldValue));
                    break;
                case "Long":
                    field.set(object, Long.parseLong(changeFieldValue));
                    break;
                default:
                    errExit("Illegal field type");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            errExit("Field whit name " + fieldLine + " not exist");
        }
        System.out.println("Object updated: " + object);
        PRINT_DELIMITER_STRING.accept(20, "-");
    }

    public static void callMethod() {
        System.out.println("Enter name of the method for call:");
        String methodLine = scanner.nextLine();
        String methodName = methodLine.substring(0, methodLine.indexOf('('));
        Class<?>[] argsTypes = argsType(
                Arrays.stream(
                        methodLine.substring(methodLine.indexOf('(') + 1, methodLine.length() - 1)
                                .split("[, ]")
                )
                        .filter((s) -> !s.isEmpty())
                        .toArray(String[]::new)
        );

        try {
            Method method = classs.getMethod(methodName, argsTypes);
            Object[] args = new Object[argsTypes.length];

            for (int i = 0; i < args.length; i++) {
                System.out.println("Enter " + argsTypes[i].getSimpleName() + " value:");

                String argLine = scanner.nextLine();

                switch (argsTypes[i].getSimpleName()) {
                    case "String":
                        args[i] = argLine;
                        break;
                    case "Integer":
                        args[i] = Integer.parseInt(argLine);
                        break;
                    case "Double":
                        args[i] = Double.parseDouble(argLine);
                        break;
                    case "Boolean":
                        args[i] = Boolean.parseBoolean(argLine);
                        break;
                    case "Long":
                        args[i] = Long.parseLong(argLine);
                        break;
                    default:
                        errExit("Illegal argument type");
                }
            }
            Object ret = method.invoke(object, args);
            if (ret != null) {
                System.out.println("Method returned:\n" + ret);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            errExit(e.getMessage());
        }
    }

    private static Class<?>[] argsType(String[] types) {
        Class<?>[] argsTypes = new Class[types.length];

        for (int i = 0; i < types.length; i++) {
            switch (types[i]) {
                case "String":
                    argsTypes[i] = String.class;
                    break;
                case "Integer":
                    argsTypes[i] = Integer.class;
                    break;
                case "Double":
                    argsTypes[i] = Double.class;
                    break;
                case "Boolean":
                    argsTypes[i] = Boolean.class;
                    break;
                case "Long":
                    argsTypes[i] = Long.class;
                    break;
                default:
                    errExit("Illegal argument type");
            }
        }
        return argsTypes;
    }

    private static void errExit(String message) {
        System.err.println(message);
        System.exit(1);
    }
}
