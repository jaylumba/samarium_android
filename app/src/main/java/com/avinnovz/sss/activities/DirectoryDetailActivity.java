package com.avinnovz.sss.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avinnovz.sss.R;
import com.avinnovz.sss.base.BaseActivity;
import com.avinnovz.sss.models.Directory;
import com.avinnovz.sss.models.events.DirectoryItemClickEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DirectoryDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.lltOffice)
    LinearLayout lltOffice;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.tvOffice)
    TextView tvOffice;
    @BindView(R.id.tvDirectLine)
    TextView tvDirectLine;
    @BindView(R.id.tvTrunkline)
    TextView tvTrunkline;
    @BindView(R.id.tvLocal)
    TextView tvLocal;
    @BindView(R.id.tvEmailAddress)
    TextView tvEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(R.string.title_management_directory);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void receiveData(DirectoryItemClickEvent event) {
        Directory directory = event.getDirectory();
        if (directory == null) return;

        tvName.setText(directory.getName());
        tvPosition.setText(directory.getPosition());
        if (directory.getOffice() != null && !directory.getOffice().isEmpty()) {
            lltOffice.setVisibility(View.VISIBLE);
            tvOffice.setText(directory.getOffice());
        } else {
            lltOffice.setVisibility(View.GONE);
        }
        tvDirectLine.setText(directory.getDirectLine());
        tvTrunkline.setText(directory.getTrunkLine());
        tvLocal.setText(directory.getLocal());
        tvEmailAddress.setText(directory.getEmailAddress());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                animateToRight(DirectoryDetailActivity.this);
                break;
            default:
                break;
        }
        return false;
    }
}
