package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        String regex = createRegexString(delimiters);
        List<String> result = new ArrayList<>(Arrays.asList(source.split(regex)));
        result.removeIf(s -> s.equals(""));
        return result;
    }

    private String createRegexString(Collection<String> delimiters) {
        StringBuilder regex = new StringBuilder();
        for (String str : delimiters) {
            regex.append(str);
            regex.append("|");
        }
        regex.delete(regex.length() - 1, regex.length());
        return regex.toString();
    }
}
