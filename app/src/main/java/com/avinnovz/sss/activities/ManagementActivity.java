package com.avinnovz.sss.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.avinnovz.sss.R;
import com.avinnovz.sss.adapters.DirectoryPagerAdapter;
import com.avinnovz.sss.adapters.UsefulInfoPagerAdapter;
import com.avinnovz.sss.base.BaseActivity;
import com.avinnovz.sss.fragments.ContributionScheduleFragment;
import com.avinnovz.sss.fragments.DirectoryFragment;
import com.avinnovz.sss.fragments.FaqFragment;
import com.avinnovz.sss.fragments.GlossaryFragment;
import com.avinnovz.sss.models.Directory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManagementActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        ButterKnife.bind(this);
        initToolbar();
        initTabs();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                animateToRight(ManagementActivity.this);
                break;
            default:
                break;
        }
        return false;
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(R.string.title_management_directory);
    }

    private void initTabs() {
        fragments.add(DirectoryFragment.newInstance(getSscManagementDirectory()));
        fragments.add(DirectoryFragment.newInstance(getSssManagementDirectory()));
        viewPager.setAdapter(new DirectoryPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
    }

    private ArrayList<Directory> getSscManagementDirectory(){
        ArrayList<Directory> directories = new ArrayList<>();

        Directory d1 = new Directory("JUAN B. SANTOS", "Chairman", "(02) 813-4312 MAKATI\n(02) 924-7342 QC", "(02) 920-6401" ,"5002","santosjb@sss.gov.ph");
        Directory d2 = new Directory("EMILIO S. DE QUIROS JR.","Vice-Chairman","(02) 813-4286 MAKATI\n(02) 927-2089 QC","(02) 920-6401","5040","dequiroses@sss.gov.ph");
        Directory d3 = new Directory("DIANA B. PARDO-AGUILAR","Commissioner","(02) 813-4288 MAKATI\n(02) 924-7344 QC","(02) 920-6401","5010","aguilardp@sss.gov.ph\naguilardp@gmail.com");
        Directory d4 = new Directory("MICHAEL VICTOR N. ALIMURUNG","Commissioner","(02) 813-4291 MAKATI","(02) 920-6401","5014","alimurungmn@sss.gov.ph");

        directories.add(d1);
        directories.add(d2);
        directories.add(d3);
        directories.add(d4);

        return directories;
    }

    private ArrayList<Directory> getSssManagementDirectory(){
        ArrayList<Directory> directories = new ArrayList<>();

        Directory d1 = new Directory("EMILIO S. DE QUIROS, JR.","President and CEO","OFFICE OF THE PRESIDENT","(02) 927-2089","(02) 920-6401","5040","dequiroses@sss.gov.ph");
        Directory d2 = new Directory("RIZALDY T. CAPULONG","Executive Vice President","INVESTMENTS SECTOR","(02) 924-7878","(02) 920-6401","5105","capulongrt@sss.gov.ph");
        Directory d3 = new Directory("VOLTAIRE P. AGAS","Senior Vice President","LEGAL SERVICES DIVISION","(02) 352-4964","(02) 920-6401","5377","agasvp@sss.gov.ph");
        Directory d4 = new Directory("SANTIAGO DIONISIO R. AGDEPPA","Senior Vice President","OFFICE OF THE COMMISSION SECRETARY","(02) 813-4313","(02)","920-6401","agdeppasr@sss.gov.ph");
        Directory d5 = new Directory("ELVIRA G. ALCANTARA-RESARE ","Senior Vice President","CONTROLLERSHIP GROUP","(02) 435-9842","(02) 920-6401","5232","resareea@sss.gov.ph");
        Directory d6 = new Directory("JOSE B. BAUTISTA","Senior Vice President","NCR OPERATIONS GROUP","(02) 435-9813","(02) 920-6401","5932","bautistajb@sss.gov.ph");
        Directory d7 = new Directory("MAY CATHERINE C. CIRIACO","Senior Vice President/ Concurrent Officer-in-Charge","ADMINISTRATION GROUP/ LENDING AND ASSET MANAGEMENT DIVISION","(02) 435-9869","(02) 920-6401","5352","ciriacomc@sss.gov.ph");
        Directory d8 = new Directory("EDDIE A. JARA","Senior Vice President","VISAYAS AND MINDANAO OPERATIONS GROUP","(082)-222-3023","(082) 224-1014","","jaraea@sss.gov.ph");
        Directory d9 = new Directory("JOEL A. LAYSON","Senior Vice President","INFORMATION TECHNOLOGY MANAGEMENT GROUP","(02) 924-7291","(02) 920-6401","5556","laysonja@sss.gov.ph");
        Directory d10 = new Directory("JOSIE G. MAGANA","Senior Vice President","LUZON OPERATIONS GROUP","(02) 922-1064","(02) 920-6401","5812","maganajg@sss.gov.ph");
        Directory d11 = new Directory("JUDY FRANCES A. SEE","Senior Vice President/ Concurrent Officer-in-Charge","ACCOUNT MANAGEMENT GROUP/ INTERNATIONAL OPERATIONS DIVISION","(02) 435-9822","(02) 920-6401","5734","seeja@sss.gov.ph");
        Directory d12 = new Directory("ANTONIO S. ARGABIOSO","Vice President","LARGE ACCOUNTS DIVISION","(02) 435-9823","(02) 920-6401","5750","argabiosoas@sss.gov.ph");
        Directory d13 = new Directory("NICHOLAS C. BALBUENA","Vice President","COMPUTER OPERATIONS DIVISION","(02) 924-7811\n02) 435-9797","(02) 920-6401","5628","balbuenanc@sss.gov.ph");
        Directory d14 = new Directory("MARISSU G. BUGANTE","Vice President","PUBLIC AFFAIRS AND SPECIAL EVENTS DIVISION","(02) 924-7328","(02) 920-6401","5319","bugantemg@sss.gov.ph");
        Directory d15 = new Directory("JESSE J. CABEROY","Vice President","HUMAN RESOURCE MANAGEMENT DIVISION","(02) 922-2961\n(02) 924-7870","(02) 920-6401","5461","caberoyjj@sss.gov.ph");
        Directory d16 = new Directory("MARIA LOURDES N. MENDOZA","Special Assistant to the Corporate Head II","OFFICE OF THE PRESIDENT","(02) 927-2089","(02) 920-6401","6050","mendozamn@sss.gov.ph");
        
        directories.add(d1);
        directories.add(d2);
        directories.add(d3);
        directories.add(d4);
        directories.add(d5);
        directories.add(d6);
        directories.add(d7);
        directories.add(d8);
        directories.add(d9);
        directories.add(d10);
        directories.add(d11);
        directories.add(d12);
        directories.add(d13);
        directories.add(d14);
        directories.add(d15);
        directories.add(d16);

        return directories;
    }
}
