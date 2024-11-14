package ru.volga.launcher.fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import ru.volga.utils.DLog;

public class IniParser {

    public static Map<String, Map<String, String>> parseIniFile(File file) {
        Map<String, Map<String, String>> iniData = new HashMap<>();
        Map<String, String> currentSection = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Пропускаем пустые строки и комментарии
                if (line.isEmpty() || line.startsWith(";") || line.startsWith("#")) {
                    continue;
                }

                // Если это секция [section]
                if (line.startsWith("[") && line.endsWith("]")) {
                    String sectionName = line.substring(1, line.length() - 1);
                    currentSection = new HashMap<>();
                    iniData.put(sectionName, currentSection);
                }
                // Если это параметр key=value
                else if (currentSection != null && line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    currentSection.put(key, value);
                }
            }
        } catch (Exception e) {
            DLog.handleException(e);
        }

        return iniData;
    }
}