package com.seriabov.fintecharch.presentation.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.seriabov.fintecharch.R;
import com.seriabov.fintecharch.data.model.CoinInfo;
import com.seriabov.fintecharch.presentation.viewmodel.ListCoinsViewModel;

import java.util.List;

import timber.log.Timber;

/*
 * TODO:
 * 1) Подключить ViewModel и LiveData из Android Architecture components
 * 2) Разделить классы по пакетам
 * 3) Внедрить в проект архитектуру MVVM, вынести бизнес-логику во вьюмодель.
 * В идеале вьюмодель не должна содержать в себе андроид-компонентов (таких как Context)
 * 4) Сделать так, чтобы при повороте экрана данные не перезапрашивались заново,
 * а использовались полученные ранее
 * 5) Don't repeat yourself - если найдете в коде одинаковые куски кода, выносите в утилитные классы
 */

public class MainActivity extends AppCompatActivity {

    private ListCoinsViewModel viewModel;

    private CoinsAdapter adapter;
    private View errorView;
    private View contentView;
    private View loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(ListCoinsViewModel.class);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> getData());
        errorView = findViewById(R.id.error_layout);
        contentView = findViewById(R.id.main_recycler_view);
        loadingView = findViewById(R.id.loading_layout);
        initRecyclerView();

        viewModel.getApiResponse().observe(this, apiResponse -> {
            if (apiResponse.getError() != null) {
                showError(apiResponse.getError());
            } else {
                setData(apiResponse.getCoinInfos());
            }
        });

        if (viewModel.getApiResponse().getValue() == null) {
            getData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            getData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        loadingView.setVisibility(View.VISIBLE);
        viewModel.loadCoinsInfo();
    }

    private void setData(List<CoinInfo> data) {
        errorView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
        adapter.setData(data);
    }

    private void showError(Throwable error) {
        Timber.d(error);
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CoinsAdapter(coinInfo -> DetailsActivity.start(MainActivity.this, coinInfo));
        recyclerView.setAdapter(adapter);
    }
}
