package com.example.guessinggame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {
    List<QuestionModel> list;
    Random random;
    int index;
    int wrongGuesses = 0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ImageView imageView;
    MediaPlayer mPlayer;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        imageView = view.findViewById(R.id.animal);
        list = new ArrayList<>();
        ArrayList<QuestionModel> tempList = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < DataBase.images.length; i++) {
            tempList.add(new QuestionModel(DataBase.images[i], DataBase.answers[i]));
            //list.add(new QuestionModel(DataBase.images[i], DataBase.answers[i]));
        }
        Collections.shuffle(tempList);
        list = tempList.subList(0, 10);
        mPlayer = MediaPlayer.create(view.getContext(), R.raw.scream);
        index = list.size();
        nextQuestion();
    }

    private void nextQuestion() {
        refresh();
        imageView.setImageResource(list.get(index - 1).image);
        int correct = random.nextInt(3);
        correct ++;
        int f1 = index - 1;
        int f2;
        int f3;
        int f4;
        switch (correct) {
            case 1:
                button1.setText(list.get(f1).name);
                setButton(button1);
                do {
                    f2 = random.nextInt(list.size());
                } while (f2 == f1);
                do {
                    f3 = random.nextInt(list.size());
                } while (f3 == f2 || f3 == f1);
                do {
                    f4 = random.nextInt(list.size());
                } while (f4 == f3 || f4 == f2 || f4 == f1);
                button2.setText(list.get(f2).name);
                button3.setText(list.get(f3).name);
                button4.setText(list.get(f4).name);
                break;
            case 2:
                button2.setText(list.get(f1).name);
                setButton(button2);
                do {
                    f2 = random.nextInt(list.size());
                } while (f2 == f1);
                do {
                    f3 = random.nextInt(list.size());
                } while (f3 == f2 || f3 == f1);
                do {
                    f4 = random.nextInt(list.size());
                } while (f4 == f3 || f4 == f2 || f4 == f1);
                button1.setText(list.get(f2).name);
                button3.setText(list.get(f3).name);
                button4.setText(list.get(f4).name);
                break;
            case 3:
                button3.setText(list.get(f1).name);
                setButton(button3);
                do {
                    f2 = random.nextInt(list.size());
                } while (f2 == f1);
                do {
                    f3 = random.nextInt(list.size());
                } while (f3 == f2 || f3 == f1);
                do {
                    f4 = random.nextInt(list.size());
                } while (f4 == f3 || f4 == f2 || f4 == f1);
                button1.setText(list.get(f2).name);
                button2.setText(list.get(f3).name);
                button4.setText(list.get(f4).name);
                break;
            case 4:
                button4.setText(list.get(f1).name);
                setButton(button4);
                do {
                    f2 = random.nextInt(list.size());
                } while (f2 == f1);
                do {
                    f3 = random.nextInt(list.size());
                } while (f3 == f2 || f3 == f1);
                do {
                    f4 = random.nextInt(list.size());
                } while (f4 == f3 || f4 == f2 || f4 == f1);
                button1.setText(list.get(f2).name);
                button2.setText(list.get(f3).name);
                button3.setText(list.get(f4).name);
                break;
        }
        index--;
    }
    private void wrong(){
        Toast.makeText(getActivity(), "WRONG!",
                Toast.LENGTH_SHORT).show();
        wrongGuesses++;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        if(sharedPref.getBoolean("sound", false))
            mPlayer.start();
    }
    private void refresh(){
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        button1.setOnClickListener(v-> {
            button1.setVisibility(View.INVISIBLE); wrong();
        });
        button2.setOnClickListener(v-> {button2.setVisibility(View.INVISIBLE); wrong();});
        button3.setOnClickListener(v-> {button3.setVisibility(View.INVISIBLE); wrong();});
        button4.setOnClickListener(v-> {button4.setVisibility(View.INVISIBLE); wrong();});
    }
    private void setButton(Button button){
        if(index != 1) {
            button.setOnClickListener(v-> {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                Animation.AnimationListener listener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        nextQuestion();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };
                animation.setAnimationListener(listener);
                button.startAnimation(animation);
            });
        } else {
            button.setOnClickListener(v-> {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(((10.0/(wrongGuesses+10)) * 100 )+ "%");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_FirstFragment);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                }
                );
        }
    }
}