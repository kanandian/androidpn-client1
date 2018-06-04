package org.androidpn.demoapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidpn.test.Adapter;
import org.androidpn.test.ItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestActivity extends Activity {

    private RecyclerView recyclerView;
    private List<ItemModel> itemModels = new ArrayList<>();
    private Random random = new Random();
    private int[] circleColors = {Color.RED, Color.parseColor("#98F5FF"), Color.parseColor("#87CEFF"),
            Color.parseColor("#8B658B"), Color.parseColor("#B22222")};
    private int[] textColors = {Color.BLACK, Color.WHITE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        for (int i = 0; i < 50; i++) {
            ItemModel itemModel = new ItemModel();
            itemModel.setMsgCount(String.valueOf(i));
            itemModel.setCircleColor(circleColors[random.nextInt(circleColors.length)]);
            itemModel.setTextColor(textColors[random.nextInt(textColors.length)]);
            itemModels.add(itemModel);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(TestActivity.this, itemModels));
    }
}
