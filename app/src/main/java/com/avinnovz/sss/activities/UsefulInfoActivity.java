package com.avinnovz.sss.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.avinnovz.sss.R;
import com.avinnovz.sss.adapters.UsefulInfoPagerAdapter;
import com.avinnovz.sss.base.BaseActivity;
import com.avinnovz.sss.fragments.ContributionScheduleFragment;
import com.avinnovz.sss.fragments.FaqFragment;
import com.avinnovz.sss.fragments.GlossaryFragment;
import com.avinnovz.sss.models.Faq;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UsefulInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindString(R.string.faq1_question)
    String faq1_question;
    @BindString(R.string.faq1_answer)
    String faq1_answer;
    @BindString(R.string.faq2_question)
    String faq2_question;
    @BindString(R.string.faq2_answer)
    String faq2_answer;
    @BindString(R.string.faq3_question)
    String faq3_question;
    @BindString(R.string.faq3_answer)
    String faq3_answer;
    @BindString(R.string.faq4_question)
    String faq4_question;
    @BindString(R.string.faq4_answer)
    String faq4_answer;
    @BindString(R.string.faq5_question)
    String faq5_question;
    @BindString(R.string.faq5_answer)
    String faq5_answer;
    @BindString(R.string.faq6_question)
    String faq6_question;
    @BindString(R.string.faq6_answer)
    String faq6_answer;
    @BindString(R.string.faq7_question)
    String faq7_question;
    @BindString(R.string.faq7_answer)
    String faq7_answer;
    @BindString(R.string.faq8_question)
    String faq8_question;
    @BindString(R.string.faq8_answer)
    String faq8_answer;
    @BindString(R.string.faq9_question)
    String faq9_question;
    @BindString(R.string.faq9_answer)
    String faq9_answer;
    @BindString(R.string.faq10_question)
    String faq10_question;
    @BindString(R.string.faq10_answer)
    String faq10_answer;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_info);
        ButterKnife.bind(this);
        initToolbar();
        initTabs();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(R.string.title_usefulinfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                animateToRight(UsefulInfoActivity.this);
                break;
            default:
                break;
        }
        return false;
    }

    private void initTabs() {
        fragments.add(ContributionScheduleFragment.newInstance());
        fragments.add(FaqFragment.newInstance(getFaqs()));
        fragments.add(GlossaryFragment.newInstance());
        viewPager.setAdapter(new UsefulInfoPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
    }

    private ArrayList<Faq> getFaqs() {
        ArrayList<Faq> faqs = new ArrayList<>();
        faqs.add(new Faq(faq1_question, faq1_answer));
        faqs.add(new Faq(faq2_question, faq2_answer));
        faqs.add(new Faq(faq3_question, faq3_answer));
        faqs.add(new Faq(faq4_question, faq4_answer));
        faqs.add(new Faq(faq5_question, faq5_answer));
        faqs.add(new Faq(faq6_question, faq6_answer));
        faqs.add(new Faq(faq7_question, faq7_answer));
        faqs.add(new Faq(faq8_question, faq8_answer));
        faqs.add(new Faq(faq9_question, faq9_answer));
        faqs.add(new Faq(faq10_question, faq10_answer));
        return faqs;
    }
}
