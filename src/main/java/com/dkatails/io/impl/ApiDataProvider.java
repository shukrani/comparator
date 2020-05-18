package com.dkatails.io.impl;

import com.dkatails.data.Pair;
import com.dkatails.io.ComparableDataProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

/**
 *
 */
public class ApiDataProvider implements ComparableDataProvider<String>, Closeable {

    private final File file1;
    private final File file2;

    private final Scanner scanner1;
    private final Scanner scanner2;
    private Pair<String, String> current;

    public ApiDataProvider(String filePath1, String filePath2) throws FileNotFoundException {
        file1 = new File(filePath1);
        file2 = new File(filePath2);
        scanner1 = new Scanner(file1);
        scanner2 = new Scanner(file2);
    }

    @Override
    public boolean hasData() {
        boolean flag = scanner1.hasNextLine() || scanner2.hasNextLine();
        return flag;
    }

    @Override
    public Pair<String, String> getData() {
        String path1 = scanner1.hasNextLine() ? scanner1.nextLine() : null;
        String path2 = scanner2.hasNextLine() ? scanner2.nextLine() : null;

        current = new Pair<>(path1, path2);
        return current;
    }

    @Override
    public Pair<String, String> getCurrentData() {
        return current;
    }


    @Override
    public void close() throws IOException {
        scanner1.close();
        scanner2.close();
    }

}
