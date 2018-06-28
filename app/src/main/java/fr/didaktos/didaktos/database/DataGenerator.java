package fr.didaktos.didaktos.database;

import java.util.ArrayList;
import java.util.List;

import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.models.Deck;

public class DataGenerator {


    public static List<Deck> generateDecks(){
        List<Deck> decks = new ArrayList<>();

        Deck deck0 = new Deck(0, "English", "Basic Verbs",
                "Apprends les verbes les plus importants en anglais",
                "https://picsum.photos/200/200/?image=820");
        Deck deck1 = new Deck(1, "Physique", "Atomes",
                "Apprends les atomes et leurs numéros atomiques du tableau périodique",
                "https://picsum.photos/200/200/?image=20");
        Deck deck2 = new Deck(2, "Maths", "Théorèmes",
                "Révise les théorèmes de mathématiques!",
                "https://picsum.photos/200/200/?image=28");

        decks.add(deck0);
        decks.add(deck1);
        decks.add(deck2);

        return decks;
    }

    public static List<Card> generateCards(){
        List<Card> cards = new ArrayList<>();

        Card card0 = new Card(0, "eat", "manger", 0);
        Card card1 = new Card(0, "drink", "boire", 0);
        Card card2 = new Card(0, "sleep", "dormir", 0);
        Card card3 = new Card(0, "play", "jouer", 0);
        Card card4 = new Card(1, "1", "Hydrogene", 0);
        Card card5 = new Card(1, "2", "Helium", 0);
        Card card6 = new Card(1, "3", "Lithium", 0);
        Card card7 = new Card(1, "4", "Bore", 0);
        Card card8 = new Card(2, "Triangle rectangle", "Pythagore", 0);
        Card card9 = new Card(2, "Triangles imbriqués", "Thalès", 0);
        Card card10 = new Card(2, "Droites parallèles", "Réciproque Thalès", 0);
        Card card11 = new Card(2, "Angle droit", "Réciproque Pythagore", 0);

        cards.add(card0);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);
        cards.add(card8);
        cards.add(card9);
        cards.add(card10);
        cards.add(card11);

        return cards;
    }

}
