package ru.netology.graphics.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class TextColorSchemaNew implements TextColorSchema {

    private final ArrayList<Character> symbols = new ArrayList<>();
    private final TreeSet<Integer> numbersOfEachSymbol = new TreeSet<>();
    private final Map<Integer, Character> symbolsMap = new HashMap<>();

    @Override
    public char convert (int color) {
        Integer cellingValue = numbersOfEachSymbol.ceiling(color);
        Integer floorValue = numbersOfEachSymbol.floor(color);
        if (floorValue == null) {
            return symbolsMap.get(cellingValue);
        } else if (cellingValue == null) {
            return symbolsMap.get(floorValue);
        }
        return Math.abs(cellingValue - color) < Math.abs(floorValue - color) ? symbolsMap.get(cellingValue) : symbolsMap.get(floorValue);
    }

    public void fillColorMap () {
        fillSymbolsList();
        int intOfOneSymbol = 255 / symbols.size();
        int numberOfSymbol;
        for (int i = 0; i < symbols.size(); i++) {
            numberOfSymbol = intOfOneSymbol * i;
            numbersOfEachSymbol.add(numberOfSymbol);
            symbolsMap.put(numberOfSymbol, symbols.get(i));
        }
    }

    public void fillSymbolsList () {
        symbols.add('▇');
        symbols.add('●');
        symbols.add('◉');
        symbols.add('◍');
        symbols.add('◎');
        symbols.add('○');
        symbols.add('☉');
        symbols.add('◌');
        symbols.add('-');
    }
}