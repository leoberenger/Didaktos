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

/**
 * A simple {@link Fragment} subclass.
 */
public class MemorizeFragment extends Fragment {

    private String TAG = "Memorize Fragment";

    @BindView(R.id.fragment_memorize_title) TextView titleTextView;
    @BindView(R.id.fragment_memorize_rootview) FrameLayout rootView;

    public static final String KEY_POSITION = "position";
    public static final String KEY_COLOR = "color";


    public MemorizeFragment() {
        // Required empty public constructor
    }

    public static MemorizeFragment newInstance(int position, int color){
        MemorizeFragment fragment = new MemorizeFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
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
        int color = getArguments().getInt(KEY_COLOR);

        rootView.setBackgroundColor(color);
        titleTextView.setText("Page number " + position);

        return v;
    }

}
