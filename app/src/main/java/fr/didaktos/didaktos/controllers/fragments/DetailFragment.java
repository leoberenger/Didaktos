package fr.didaktos.didaktos.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.didaktos.didaktos.R;
import fr.didaktos.didaktos.models.DeckWithCards;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    String TAG = "DetailFragment";

    //FOR DATA
    private DeckWithCards deck;

    //FOR DESIGN
    @BindView(R.id.fragment_detail_description)
    TextView textViewDescription;
    @BindView(R.id.fragment_detail_surface) TextView textViewSurface;
    @BindView(R.id.fragment_detail_nbRooms) TextView textViewNbRooms;
    @BindView(R.id.fragment_detail_pois) TextView textViewPOIs;
    @BindView(R.id.fragment_detail_address_street) TextView textViewAddressStreet;
    @BindView(R.id.fragment_detail_address_appt) TextView textViewAddressAppt;
    @BindView(R.id.fragment_detail_address_city) TextView textViewAddressCity;
    @BindView(R.id.fragment_detail_address_country) TextView textViewAddressCountry;
    @BindView(R.id.fragment_detail_photo)
    ImageView photo;
    @BindView(R.id.fragment_detail_photo_description) TextView textViewPhotoDescription;


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        if(getArguments() != null) {
            deck = getArguments().getParcelable(DeckWithCards.DECK_KEY);
            updateShownDeck(deck);
        }

        return view;
    }

    private void updateShownDeck(DeckWithCards d){
/*
        //Img
        if(!p.getPhotoUrl().isEmpty()) {
            Glide.with(this).load(p.getPhotoUrl()).into(photo);
            textViewPhotoDescription.setText(p.getPhotoDescription());
        }else
            textViewPhotoDescription.setText("No photo");
*/
        //Description
            textViewDescription.setText(d.getName());

        textViewNbRooms.setText(d.getCards().get(0).getValue());
    }

}
