package com.novare.inventoryManager.utils;

import java.util.List;

public class Table {
    // Properties
    private final List<Integer> columnWidth;
    private final List<String> header;
    private final List<List<String>> body;
    private final String format;
    private final String border;
    private final String title;

    // Constructor
    public Table(List<Integer> columnWidth, List<String> header, List<List<String>> body,String title) {
        this.columnWidth = columnWidth;
        this.header = header;
        this.body = body;
        this.format = createFormat();
        this.border = createBorder();
        this.title = title;
    }

    // Public
    public void showData()  {
        generateBorder();
        generateTitle();
        generateBorder();
        generateHeader();
        generateBorder();
        generateBody();
        generateBorder();
    }

    private void generateTitle() {
        StringBuilder result = new StringBuilder();
        String prefix = "+";
        String endingCharacter = "+";

        result.append(prefix);
        int totalWidthWithoutEndCharacter = 0;
        for (int item: columnWidth) {
            totalWidthWithoutEndCharacter += ( prefix + createBorderCell(item) ).length();
        }

        //subtracting title length and prefix from totalWidthWithoutEndCharacter to get spaces needed
        int totalSpacedRequired = totalWidthWithoutEndCharacter - title.length() - 1;

        int halfWidthSpaces = totalSpacedRequired / 2 ;
        int halfWidthRemainder = totalSpacedRequired %2;

        String repeatSpaces = " ".repeat(halfWidthSpaces);

        result.append(repeatSpaces);
        result.append(title);
        result.append(repeatSpaces);
        //adding the extra remainder space after the title
        result.append(" ".repeat(halfWidthRemainder));
        result.append(endingCharacter);

        System.out.println(result);
    }

    // Private
    private String createFormat() {
        StringBuilder result = new StringBuilder();
        String prefix = "| %-";
        String postfix = "s ";
        String endingCharacter = "|%n";

        for (int item: columnWidth) {
            result.append(prefix).append(item).append(postfix);
        }
        result.append(endingCharacter);

        return result.toString();
    }

    private String createBorder() {
        StringBuilder result = new StringBuilder();
        String prefix = "+";
        String endingCharacter = "+";

        for (int item: columnWidth) {
            result.append(prefix).append(createBorderCell(item));
        }
        result.append(endingCharacter);

        return result.toString();
    }

    private String createBorderCell(int width) {
        int blankSpacesBetweenCells = 2;

        return "-".repeat(Math.max(0, width + blankSpacesBetweenCells));
    }

    private void generateHeader() {
        System.out.format(format, header.toArray());
    }

    private void generateBody() {
        for (List<String> item : body) {
            System.out.format(format, item.toArray());
        }
    }

    private void generateBorder() {
        System.out.println(border);
    }
}
