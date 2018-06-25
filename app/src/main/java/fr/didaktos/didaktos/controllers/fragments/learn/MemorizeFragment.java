package fr.didaktos.didaktos.controllers.fragments.learn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemorizeFragment extends Fragment {

    private String TAG = "Memorize Fragment";

    @BindView(R.id.memorize_question) TextView keyTextView;
    @BindView(R.id.memorize_answer) TextView valueTextView;

    public static final String KEY_POSITION = "position";


    public MemorizeFragment() {
        // Required empty public constructor
    }

    public static MemorizeFragment newInstance(int position, String key, String value){
        MemorizeFragment fragment = new MemorizeFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putString(DeckWithCards.KEY_KEY, key);
        args.putString(DeckWithCards.VALUE_KEY, value);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_memorize, container, false);
        ButterKnife.bind(this, v);

        int position = getArguments().getInt(KEY_POSITION);
        String key = getArguments().getString(DeckWithCards.KEY_KEY);
        String value = getArguments().getString(DeckWithCards.VALUE_KEY);

        keyTextView.setText(key);
        valueTextView.setText(value);

        return v;
    }

}
