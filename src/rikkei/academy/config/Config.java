package rikkei.academy.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config<T> {
    public static Scanner scanner;

    public static Scanner scanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
            return scanner;
        }
        return scanner;
    }

    public T readFile(String pathFile) {
        try (FileInputStream fileInputStream = new FileInputStream(pathFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            return (T) objectInputStream.readObject();

        } catch (Exception e) {
            System.out.println("Error reading");
            return null;
        }
    }


    public void writeFile(String pathFile, T data) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(pathFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(data);

        } catch (Exception e) {
            System.out.println("Error writing");

        }
    }
}