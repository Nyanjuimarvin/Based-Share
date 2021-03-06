package com.nyanjuimarvin.basedshare.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nyanjuimarvin.basedshare.R;
import com.nyanjuimarvin.basedshare.adapters.FragmentViewAdapter;
import com.nyanjuimarvin.basedshare.databinding.ActivityDetailBinding;
import com.nyanjuimarvin.basedshare.firebase.authentication.Authentication;
import com.nyanjuimarvin.basedshare.fragments.GameFragment;
import com.nyanjuimarvin.basedshare.fragments.MovieFragment;
import com.nyanjuimarvin.basedshare.fragments.MusicFragment;

//Fragment class : FragmentActivity ()
public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding detailBinding;

    //Fragments Array
    private final Fragment[] fragments = {new GameFragment(), new MovieFragment(), new MusicFragment()};;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        detailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        FragmentViewAdapter viewAdapter = new FragmentViewAdapter(fm, getLifecycle(), fragments);
        detailBinding.detailPager.setAdapter(viewAdapter);
        View view = detailBinding.getRoot();
        setContentView(view);

    }
}