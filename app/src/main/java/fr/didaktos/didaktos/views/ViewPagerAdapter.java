package fr.didaktos.didaktos.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fr.didaktos.didaktos.controllers.fragments.learn.MemorizeFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.QuizFragment;
import fr.didaktos.didaktos.controllers.fragments.learn.TestFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private int[]colors;

        public ViewPagerAdapter(FragmentManager fm, int[] colors) {
            super(fm);
            this.colors = colors;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Fragment getItem(int position) {
            return (MemorizeFragment.newInstance(position, this.colors[position]));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
}
