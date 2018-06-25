package fr.didaktos.didaktos.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fr.didaktos.didaktos.controllers.fragments.learn.MemorizeFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.QuizFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.TestFragment;
import fr.didaktos.didaktos.models.DeckWithCards;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private DeckWithCards deck;

        public ViewPagerAdapter(FragmentManager fm, DeckWithCards deck) {
            super(fm);
            this.deck = deck;
        }

        @Override
        public int getCount() {
            return deck.getCards().size();
        }

        @Override
        public Fragment getItem(int position) {
            return (MemorizeFragment.newInstance(position,
                    deck.getCards().get(position).getKey(),
                    deck.getCards().get(position).getValue()));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
}
