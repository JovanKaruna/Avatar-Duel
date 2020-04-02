package com.avatarduel.model.card;

import com.avatarduel.Paths;
import com.avatarduel.model.element.Element;
import com.avatarduel.util.CSVReader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

// Repository Pattern
public class CardDAO {
    private static ArrayList<Card> cards = new ArrayList<>();

    private CardDAO() {
        throw new AssertionError("This is a utility class.");
    }

    public static ArrayList<Card> getCards() {
        return CardDAO.cards;
    }

    public static void add(Card c) {
        CardDAO.cards.add(c);
    }

    public static Card get(Integer i) {
        return CardDAO.cards.get(i);
    }

    // Factory Pattern
    public static void init() throws IOException, URISyntaxException {
        for (String[] row : loadCards(Paths.landCSV)) {
            CardDAO.add(new Land(row[1], row[3], Element.valueOf(row[2]), row[4]));
            CardDAO.add(new Land(row[1], row[3], Element.valueOf(row[2]), row[4]));
        }
        for (String[] row : loadCards(Paths.characterCSV))
            CardDAO.add(new Character(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5]), Integer.valueOf(row[6]), Integer.valueOf(row[7])));
        for (String[] row : loadCards(Paths.auraCSV))
            CardDAO.add(new Aura(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5]), Integer.valueOf(row[6]), Integer.valueOf(row[7])));
        for (String[] row : loadCards(Paths.destroyCSV))
            CardDAO.add(new Destroy(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5])));
        for (String[] row : loadCards(Paths.powerupCSV))
            CardDAO.add(new PowerUp(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5])));
    }

    private static List<String[]> loadCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(Paths.resourceFolder + path);
        CSVReader landReader = new CSVReader(csvFile, "\t");
        landReader.setSkipHeader(true);
        return landReader.read();
    }
}
