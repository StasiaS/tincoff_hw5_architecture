package com.seriabov.fintecharch.presentation.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seriabov.fintecharch.R;
import com.seriabov.fintecharch.data.model.CoinInfo;
import com.seriabov.fintecharch.utils.ViewUtils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    private static final String EXTRA_INFO = "extra_info";

    public static void start(Activity activity, CoinInfo coinInfo) {
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putExtra(EXTRA_INFO, coinInfo);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CoinInfo info = (CoinInfo) getIntent().getSerializableExtra(EXTRA_INFO);
        getSupportActionBar().setTitle(info.getSymbol());
        ImageView logo = findViewById(R.id.coin_logo);
        String logoUrl = getString(R.string.coin_logo_url, info.getSymbol().toLowerCase());
        Glide.with(this)
                .load(logoUrl)
                .into(logo);

        TextView title = findViewById(R.id.coin_title);
        title.setText(info.getName());
        TextView price = findViewById(R.id.price_value);
        price.setText(getString(R.string.price_format, info.getPriceUsd()));
        TextView change7d = findViewById(R.id.change_value_7d);
        change7d.setText(getString(R.string.percent_format, info.getPercentChange7d()));
        ViewUtils.setColorPercentChange(info.getPercentChange7d(), change7d, this);
        TextView change24h = findViewById(R.id.change_value_24h);
        change24h.setText(getString(R.string.percent_format, info.getPercentChange24h()));
        ViewUtils.setColorPercentChange(info.getPercentChange24h(), change24h, this);
        TextView change1h = findViewById(R.id.change_value_1h);
        change1h.setText(getString(R.string.percent_format, info.getPercentChange1h()));
        ViewUtils.setColorPercentChange(info.getPercentChange1h(), change1h, this);
        TextView marketCap = findViewById(R.id.market_cap_value);
        marketCap.setText(getString(R.string.price_format, info.getMarketCapUsd()));
        TextView lastUpdate = findViewById(R.id.last_update_value);
        lastUpdate.setText(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.getDefault())
                .format(new Date(info.getLastUpdated() * 1000)));
    }
}
