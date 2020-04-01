package com.avatarduel.util;

import com.avatarduel.Paths;
import com.avatarduel.model.card.*;
import com.avatarduel.model.card.Character;
import com.avatarduel.model.element.Element;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CardLoader {
    public static void loadAllCardsToCardsDAO() throws IOException, URISyntaxException {
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
    }

    private static List<String[]> loadCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(CardLoader.class.getResource("../" + path).toURI());
        CSVReader landReader = new CSVReader(csvFile, "\t");
        landReader.setSkipHeader(true);
        return landReader.read();
    }
}
