package com.kusofan.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.kusofan.demo.base.MyBaseActivity;
import com.kusofan.demo.home.actions.ActionsCreator;
import com.kusofan.demo.home.model.GirlMode;
import com.kusofan.demo.home.stores.GankStore;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MyBaseActivity {

    @Bind(R.id.text)
    TextView text;
    private GankStore gankStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new SelectListener());


        registerBus(this);
        gankStore = GankStore.getInstance();
        gankStore.register();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBus(this);
        gankStore.unregister();
    }

    private class SelectListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tab_home:
                    return true;
                case R.id.tab_find:
                    return true;
                case R.id.tab_take:
                    return true;
                case R.id.tab_mine:
                    return true;
            }
            return false;
        }
    }

    @OnClick(R.id.click)
    void onclick() {
        ActionsCreator.getInstance().getRandomGirl(this, 1);
    }

    @Subscribe
    public void onSuccessEvent(GankStore.GirlSuccessEvent event) {
        List<GirlMode.ResultsBean> results = event.getMode().results;
        text.setText(results.get(0).toString());
    }
}
