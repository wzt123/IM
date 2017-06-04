package com.example.lsy.myapp;

/**
 * Created by lsy on 2017/5/27.
 */
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;


public class FragmentAttention1 extends Fragment {
    public FragmentAttention1(){};
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.attention_content1,container,false);
        return view;
    }

}