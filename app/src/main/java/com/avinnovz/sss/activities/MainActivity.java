package com.avinnovz.sss.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avinnovz.sss.R;
import com.avinnovz.sss.base.BaseActivity;
import com.avinnovz.sss.fragments.ChangePasswordDialogFragment;
import com.avinnovz.sss.helpers.DateHelper;
import com.avinnovz.sss.helpers.ShapeHelper;
import com.avinnovz.sss.interfaces.OnConfirmDialogListener;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.imgNewsPic)
    ImageView imgNewsPic;
    @BindView(R.id.tvNewsDate)
    TextView tvNewsDate;
    @BindView(R.id.tvNewsHeadline)
    TextView tvNewsHeadline;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initNavigationDrawer();
        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_notification:
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_notification, menu);
        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
            }
        }
        return true;
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(R.string.app_name);
    }

    public void initNavigationDrawer() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        initSideDrawerMenu();
    }

    private void initSideDrawerMenu() {
        final View view = navigationView.getHeaderView(0);
        final TextView tvProfileName = (TextView) view.findViewById(R.id.tvProfileName);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_item_usefulinfo:
                    moveToOtherActivity(UsefulInfoActivity.class);
                    break;
                case R.id.menu_item_sssmandate:
                    moveToOtherActivity(SssMandateActivity.class);
                    break;
                case R.id.menu_item_about:
                    moveToOtherActivity(AboutActivity.class);
                    break;
                case R.id.menu_item_changepassword:
                    changePassword();
                    break;
                case R.id.menu_item_logout:
                    showConfirmDialog("", "Logout", "Are you sure you want to logout from the app?",
                            "Yes", "No", new OnConfirmDialogListener() {
                                @Override
                                public void onConfirmed(String action) {
                                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                    finish();
                                    animateToRight(MainActivity.this);
                                }

                                @Override
                                public void onCancelled(String action) {

                                }
                            });
                    break;
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void initData() {
        tvNewsDate.setText(DateHelper.formatDate(new Date(), "dd MMM yyyy"));
        tvNewsDate.setBackground(ShapeHelper.getPointedRectangleBg());
        tvNewsHeadline.setText("SSS issues over 300,000 membership numbers to online applicants");
    }

    private void changePassword() {
        ChangePasswordDialogFragment fragment = ChangePasswordDialogFragment.newInstance();
        fragment.setOnChangePasswordListener((oldPassword, newPassword) -> {showSnackbarSuccess("Change password successful.");});
        fragment.show(getFragmentManager(), "chane password");
    }
}
