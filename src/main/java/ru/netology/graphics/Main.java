package ru.netology.graphics;

import ru.netology.graphics.image.TextGraphicsConverterNew;
import ru.netology.graphics.server.GServer;

public class Main {
    public static void main (String[] args) throws Exception {
        TextGraphicsConverterNew converter = new TextGraphicsConverterNew();
        GServer server = new GServer(converter);
        server.start();
    }
}
