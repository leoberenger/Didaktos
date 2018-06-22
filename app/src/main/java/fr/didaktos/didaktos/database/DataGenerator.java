package fr.didaktos.didaktos.database;

import java.util.ArrayList;
import java.util.List;

import fr.didaktos.didaktos.models.Card;
import fr.didaktos.didaktos.models.Deck;

public class DataGenerator {


    public static List<Deck> generateDecks(){
        List<Deck> decks = new ArrayList<>();

        Deck deck0 = new Deck("English", "Basic Verbs",
                "Apprends les verbes les plus importants en anglais",
                "https://picsum.photos/200/200/?image=820");
        Deck deck1 = new Deck("Physique", "Atomes",
                "Apprends les atomes et leurs numéros atomiques du tableau périodique",
                "https://picsum.photos/200/200/?image=20");

        decks.add(deck0);
        decks.add(deck1);

        return decks;
    }

    public static List<Card> generateCards(){
        List<Card> cards = new ArrayList<>();

        Card card0 = new Card(1, "eat", "manger", 0);
        Card card1 = new Card(1, "drink", "boire", 0);
        Card card2 = new Card(1, "sleep", "dormir", 0);
        Card card3 = new Card(2, "1", "Hydrogène", 0);
        Card card4 = new Card(2, "2", "Hélium", 0);

        cards.add(card0);
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        return cards;
/*
        String [] deck0Keys = {"eat", "drink", "sleep", "study", "work",
                "travel", "think", "ask", "leave", "speak"};
        String [] deck0Values = {"manger", "boire", "dormir", "étudier", "travailler",
                "voyager", "penser", "demander", "partir", "parler"};
        String [] deck1Keys = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
        String [] deck1Values = {"Hydrogène", "Hélium", "Lithium", "Béryllium", "Bore",
                "Carbone", "Azote", "Oxygène", "Fluor", "Néon"};
*/

    }

}
