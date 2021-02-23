package com.example.guessinggame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MediaPlayer mPlayer = MediaPlayer.create(view.getContext(), R.raw.rickroll);
        FloatingActionButton fab = view.findViewById(R.id.play);
        ImageView imageView = view.findViewById(R.id.imageView);
        fab.setOnClickListener(v -> {
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());

                    if (sharedPref.getBoolean("sound", true)) {
                        imageView.setImageResource(R.drawable.rick);
                        mPlayer.start();
                        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoomin);
                        Animation.AnimationListener listener = new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                NavHostFragment.findNavController(FirstFragment.this)
                                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        };
                        animation.setAnimationListener(listener);
                        imageView.startAnimation(animation);

                    } else {
                        NavHostFragment.findNavController(FirstFragment.this)
                                .navigate(R.id.action_FirstFragment_to_SecondFragment);
                    }

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                }

        );
    }


//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });


}