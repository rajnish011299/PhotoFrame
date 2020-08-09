package com.rajumia.photoframe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;

import com.rajumia.photoframe.Adapter.RecyclerViewAdapter;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerVIew;

    // Array list for recycler view data source
    int[] source={R.drawable.minion,R.drawable.sunflower,R.drawable.scene,R.drawable.stone,R.drawable.lake,R.drawable.new1};

    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    RecyclerViewAdapter adapter;
    LinearLayoutManager HorizontalLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_main);
        mRecyclerVIew = findViewById(R.id.homeView);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerVIew.setLayoutManager(RecyclerViewLayoutManager);

        adapter = new RecyclerViewAdapter(source,mRecyclerVIew);

        HorizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        mRecyclerVIew.setLayoutManager(HorizontalLayout);

        // new improvement
        final SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerVIew);

        mRecyclerVIew.setAdapter(adapter);
    }
}