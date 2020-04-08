package com.avatarduel.util;


import com.avatarduel.Paths;
import com.avatarduel.model.card.Card;
import com.avatarduel.model.card.land.Land;
import com.avatarduel.model.card.summonable.character.Character;
import com.avatarduel.model.card.summonable.skill.Aura;
import com.avatarduel.model.card.summonable.skill.Destroy;
import com.avatarduel.model.card.summonable.skill.PowerUp;
import com.avatarduel.model.element.Element;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

// Repository Pattern
public class CardDAO {
    public enum Type{
        LAND, AURA, CHARACTER, DESTROY, POWERUP
    }
    private static HashMap<Type, ArrayList<Card>> cards = new HashMap<Type, ArrayList<Card>>(){
        {
            put(Type.LAND, new ArrayList<Card>());
            put(Type.AURA, new ArrayList<Card>());
            put(Type.CHARACTER, new ArrayList<Card>());
            put(Type.DESTROY, new ArrayList<Card>());
            put(Type.POWERUP, new ArrayList<Card>());
        }
    };
    private CardDAO() {
        throw new AssertionError("This is a utility class.");
    }

    public static ArrayList<Card> getCards(Type type) {
        return CardDAO.cards.get(type);
    }

    public static List<Card> get(Integer i, Type type) {
        Collections.shuffle(CardDAO.getCards(type));
        return CardDAO.cards.get(type).subList(0,i);
    }

    // Factory Pattern
    public static void init() throws IOException, URISyntaxException {
        for (String[] row : loadCards(Paths.landCSV))
            CardDAO.getCards(Type.LAND).add(new Land(row[1], row[3], Element.valueOf(row[2]), row[4]));
        for (String[] row : loadCards(Paths.characterCSV))
            CardDAO.getCards(Type.CHARACTER).add(new Character(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5]), Integer.valueOf(row[6]), Integer.valueOf(row[7])));
        for (String[] row : loadCards(Paths.auraCSV))
            CardDAO.getCards(Type.AURA).add(new Aura(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5]), Integer.valueOf(row[6]), Integer.valueOf(row[7])));
        for (String[] row : loadCards(Paths.destroyCSV))
            CardDAO.getCards(Type.DESTROY).add(new Destroy(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5])));
        for (String[] row : loadCards(Paths.powerupCSV))
            CardDAO.getCards(Type.POWERUP).add(new PowerUp(row[1], row[3], Element.valueOf(row[2]), row[4], Integer.valueOf(row[5])));
    }

    private static List<String[]> loadCards(String path) throws IOException, URISyntaxException {
        File csvFile = new File(Paths.resourceFolder + path);
        CSVReader landReader = new CSVReader(csvFile, "\t");
        landReader.setSkipHeader(true);
        return landReader.read();
    }
}
