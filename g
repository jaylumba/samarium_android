[1mdiff --git a/app/build.gradle b/app/build.gradle[m
[1mindex 9bb18aa..7f8934e 100644[m
[1m--- a/app/build.gradle[m
[1m+++ b/app/build.gradle[m
[36m@@ -63,4 +63,5 @@[m [mdependencies {[m
     compile 'com.squareup:otto:1.3.8'[m
     compile 'com.github.rey5137:material:1.2.2.1-SNAPSHOT'[m
     compile 'org.ocpsoft.prettytime:prettytime:4.0.1.Final'[m
[32m+[m[32m    compile 'de.hdodenhof:circleimageview:2.0.0'[m
 }[m
[1mdiff --git a/app/src/main/AndroidManifest.xml b/app/src/main/AndroidManifest.xml[m
[1mindex 8169687..fb1435a 100644[m
[1m--- a/app/src/main/AndroidManifest.xml[m
[1m+++ b/app/src/main/AndroidManifest.xml[m
[36m@@ -9,7 +9,8 @@[m
         android:label="@string/app_name"[m
         android:supportsRtl="true"[m
         android:theme="@style/AppTheme">[m
[31m-        <activity android:name=".activities.LoginActivity"[m
[32m+[m[32m        <activity[m
[32m+[m[32m            android:name=".activities.LoginActivity"[m
             android:screenOrientation="portrait"[m
             android:theme="@style/Theme.AppCompat.NoActionBar">[m
             <intent-filter>[m
[36m@@ -18,6 +19,7 @@[m
                 <category android:name="android.intent.category.LAUNCHER" />[m
             </intent-filter>[m
         </activity>[m
[32m+[m[32m        <activity android:name=".activities.MainActivity"></activity>[m
     </application>[m
 [m
 </manifest>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/java/com/avinnovz/sss/activities/LoginActivity.java b/app/src/main/java/com/avinnovz/sss/activities/LoginActivity.java[m
[1mindex 1309edc..29ba5e9 100644[m
[1m--- a/app/src/main/java/com/avinnovz/sss/activities/LoginActivity.java[m
[1m+++ b/app/src/main/java/com/avinnovz/sss/activities/LoginActivity.java[m
[36m@@ -5,11 +5,20 @@[m [mimport android.os.Bundle;[m
 import com.avinnovz.sss.R;[m
 import com.avinnovz.sss.base.BaseActivity;[m
 [m
[32m+[m[32mimport butterknife.ButterKnife;[m
[32m+[m[32mimport butterknife.OnClick;[m
[32m+[m
 public class LoginActivity extends BaseActivity {[m
 [m
     @Override[m
     protected void onCreate(Bundle savedInstanceState) {[m
         super.onCreate(savedInstanceState);[m
         setContentView(R.layout.activity_login);[m
[32m+[m[32m        ButterKnife.bind(this);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @OnClick(R.id.btnSignIn)[m
[32m+[m[32m    public void signIN(){[m
[32m+[m[32m        moveToOtherActivity(MainActivity.class);[m
     }[m
 }[m
[1mdiff --git a/app/src/main/java/com/avinnovz/sss/activities/MainActivity.java b/app/src/main/java/com/avinnovz/sss/activities/MainActivity.java[m
[1mindex 80f400e..9bcd9a3 100644[m
[1m--- a/app/src/main/java/com/avinnovz/sss/activities/MainActivity.java[m
[1m+++ b/app/src/main/java/com/avinnovz/sss/activities/MainActivity.java[m
[36m@@ -1,15 +1,112 @@[m
 package com.avinnovz.sss.activities;[m
 [m
[32m+[m[32mimport android.content.Intent;[m
[32m+[m[32mimport android.graphics.PorterDuff;[m
[32m+[m[32mimport android.graphics.drawable.Drawable;[m
 import android.os.Bundle;[m
[31m-import android.support.v7.app.AppCompatActivity;[m
[32m+[m[32mimport android.support.design.widget.NavigationView;[m
[32m+[m[32mimport android.support.v4.widget.DrawerLayout;[m
[32m+[m[32mimport android.support.v7.app.ActionBarDrawerToggle;[m
[32m+[m[32mimport android.support.v7.widget.Toolbar;[m
[32m+[m[32mimport android.view.Menu;[m
[32m+[m[32mimport android.view.MenuInflater;[m
[32m+[m[32mimport android.view.MenuItem;[m
[32m+[m[32mimport android.view.View;[m
[32m+[m[32mimport android.widget.TextView;[m
 [m
 import com.avinnovz.sss.R;[m
[32m+[m[32mimport com.avinnovz.sss.base.BaseActivity;[m
[32m+[m[32mimport com.avinnovz.sss.interfaces.OnConfirmDialogListener;[m
 [m
[31m-public class MainActivity extends AppCompatActivity {[m
[32m+[m[32mimport butterknife.BindView;[m
[32m+[m[32mimport butterknife.ButterKnife;[m
[32m+[m
[32m+[m[32mpublic class MainActivity extends BaseActivity {[m
[32m+[m
[32m+[m[32m    @BindView(R.id.toolbar)Toolbar toolbar;[m
[32m+[m[32m    @BindView(R.id.navigationView) NavigationView navigationView;[m
[32m+[m[32m    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;[m
[32m+[m[32m    private ActionBarDrawerToggle actionBarDrawerToggle;[m
 [m
     @Override[m
     protected void onCreate(Bundle savedInstanceState) {[m
         super.onCreate(savedInstanceState);[m
         setContentView(R.layout.activity_main);[m
[32m+[m[32m        ButterKnife.bind(this);[m
[32m+[m[32m        setSupportActionBar(toolbar);[m
[32m+[m[32m        initNavigationDrawer();[m
[32m+[m[32m        toolbar.setTitle(R.string.app_name);[m
     }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public boolean onOptionsItemSelected(MenuItem item) {[m
[32m+[m[32m        switch (item.getItemId()) {[m
[32m+[m[32m            case R.id.menu_item_notification:[m
[32m+[m[32m                break;[m
[32m+[m[32m            default:[m
[32m+[m[32m                break;[m
[32m+[m[32m        }[m
[32m+[m[32m        return false;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public boolean onCreateOptionsMenu(Menu menu) {[m
[32m+[m[32m        super.onCreateOptionsMenu(menu);[m
[32m+[m[32m        MenuInflater inflater = getMenuInflater();[m
[32m+[m[32m        inflater.inflate(R.menu.menu_notification, menu);[m
[32m+[m[32m        for(int i = 0; i < menu.size(); i++){[m
[32m+[m[32m            Drawable drawable = menu.getItem(i).getIcon();[m
[32m+[m[32m            if(drawable != null) {[m
[32m+[m[32m                drawable.mutate();[m
[32m+[m[32m                drawable.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);[m
[32m+[m[32m            }[m
[32m+[m[32m        }[m
[32m+[m[32m        return true;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void initNavigationDrawer() {[m
[32m+[m[32m        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,[m
[32m+[m[32m                R.string.open_drawer,R.string.close_drawer) {[m
[32m+[m[32m            @Override[m
[32m+[m[32m            public void onDrawerOpened(View drawerView) {[m
[32m+[m[32m                super.onDrawerOpened(drawerView);[m
[32m+[m[32m            }[m
[32m+[m
[32m+[m[32m            @Override[m
[32m+[m[32m            public void onDrawerClosed(View drawerView) {[m
[32m+[m[32m                super.onDrawerClosed(drawerView);[m
[32m+[m[32m            }[m
[32m+[m[32m        };[m
[32m+[m[32m        drawerLayout.addDrawerListener(actionBarDrawerToggle);[m
[32m+[m[32m        actionBarDrawerToggle.syncState();[m
[32m+[m[32m        initSideDrawerMenu();[m
[32m+[m[32m    }[m
[32m+[m[32m    private void initSideDrawerMenu() {[m
[32m+[m[32m        final View view = navigationView.getHeaderView(0);[m
[32m+[m[32m        final TextView tvProfileName = (TextView)view.findViewById(R.id.tvProfileName);[m
[32m+[m[32m        navigationView.setNavigationItemSelectedListener(item -> {[m
[32m+[m[32m            switch (item.getItemId()) {[m
[32m+[m[32m                case R.id.menu_item_logout:[m
[32m+[m[32m                    showConfirmDialog("", "Logout", "Are you sure you want to logout from the app?",[m
[32m+[m[32m                            "Yes", "No", new OnConfirmDialogListener() {[m
[32m+[m[32m                                @Override[m
[32m+[m[32m                                public void onConfirmed(String action) {[m
[32m+[m[32m                                    startActivity(new Intent(MainActivity.this, LoginActivity.class));[m
[32m+[m[32m                                    finish();[m
[32m+[m[32m                                    animateToRight(MainActivity.this);[m
[32m+[m[32m                                }[m
[32m+[m
[32m+[m[32m                                @Override[m
[32m+[m[32m                                public void onCancelled(String action) {[m
[32m+[m
[32m+[m[32m                                }[m
[32m+[m[32m                            });[m
[32m+[m[32m                    break;[m
[32m+[m[32m            }[m
[32m+[m[32m            drawerLayout.closeDrawers();[m
[32m+[m[32m            return true;[m
[32m+[m[32m        });[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m
 }[m
[1mdiff --git a/app/src/main/res/layout/activity_main.xml b/app/src/main/res/layout/activity_main.xml[m
[1mindex 982c9da..7e14ddd 100644[m
[1m--- a/app/src/main/res/layout/activity_main.xml[m
[1m+++ b/app/src/main/res/layout/activity_main.xml[m
[36m@@ -1,12 +1,45 @@[m
 <?xml version="1.0" encoding="utf-8"?>[m
[31m-<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m    xmlns:app="http://schemas.android.com/apk/res-auto"[m
     xmlns:tools="http://schemas.android.com/tools"[m
[32m+[m[32m    android:id="@+id/drawerLayout"[m
     android:layout_width="match_parent"[m
[31m-    android:layout_height="match_parent"[m
[31m-    android:paddingBottom="@dimen/activity_vertical_margin"[m
[31m-    android:paddingLeft="@dimen/activity_horizontal_margin"[m
[31m-    android:paddingRight="@dimen/activity_horizontal_margin"[m
[31m-    android:paddingTop="@dimen/activity_vertical_margin"[m
[31m-    tools:context="com.avinnovz.sss.activities.MainActivity">[m
[32m+[m[32m    tools:openDrawer="start"[m
[32m+[m[32m    tools:menu="@menu/menu_notification"[m
[32m+[m[32m    android:layout_height="match_parent">[m
 [m
[31m-</RelativeLayout>[m
[32m+[m[32m    <android.support.design.widget.CoordinatorLayout[m
[32m+[m[32m        android:id="@+id/content_view"[m
[32m+[m[32m        android:layout_width="match_parent"[m
[32m+[m[32m        android:layout_height="match_parent">[m
[32m+[m
[32m+[m[32m        <android.support.v7.widget.Toolbar xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m            xmlns:app="http://schemas.android.com/apk/res-auto"[m
[32m+[m[32m            android:id="@+id/toolbar"[m
[32m+[m[32m            android:layout_width="match_parent"[m
[32m+[m[32m            android:layout_height="wrap_content"[m
[32m+[m[32m            android:background="@color/colorPrimary"[m
[32m+[m[32m            android:theme="@style/ThemeOverlay.AppCompat.Dark"[m
[32m+[m[32m            app:title="@string/app_name" />[m
[32m+[m
[32m+[m[32m        <LinearLayout[m
[32m+[m[32m            android:layout_width="match_parent"[m
[32m+[m[32m            android:layout_height="match_parent"[m
[32m+[m[32m            android:orientation="vertical">[m
[32m+[m
[32m+[m[32m            <FrameLayout[m
[32m+[m[32m                android:id="@+id/contentLayout"[m
[32m+[m[32m                android:layout_width="match_parent"[m
[32m+[m[32m                android:layout_height="match_parent"[m
[32m+[m[32m                app:layout_scrollFlags="scroll" />[m
[32m+[m[32m        </LinearLayout>[m
[32m+[m[32m    </android.support.design.widget.CoordinatorLayout>[m
[32m+[m
[32m+[m[32m    <android.support.design.widget.NavigationView[m
[32m+[m[32m        android:id="@+id/navigationView"[m
[32m+[m[32m        android:layout_width="@dimen/_250sdp"[m
[32m+[m[32m        android:layout_height="match_parent"[m
[32m+[m[32m        android:layout_gravity="start"[m
[32m+[m[32m        app:headerLayout="@layout/drawer_header"[m
[32m+[m[32m        app:menu="@menu/menu_side_drawer" />[m
[32m+[m[32m</android.support.v4.widget.DrawerLayout>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/layout/drawer_header.xml b/app/src/main/res/layout/drawer_header.xml[m
[1mindex 8e53a88..ed6e9ec 100644[m
[1m--- a/app/src/main/res/layout/drawer_header.xml[m
[1m+++ b/app/src/main/res/layout/drawer_header.xml[m
[36m@@ -1,13 +1,34 @@[m
 <?xml version="1.0" encoding="utf-8"?>[m
[31m-<RelativeLayout[m
[31m-    xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
     android:layout_width="match_parent"[m
[31m-    android:layout_height="150dp"[m
[32m+[m[32m    android:layout_height="wrap_content"[m
[32m+[m[32m    xmlns:tools="http://schemas.android.com/tools"[m
[32m+[m[32m    android:orientation="vertical"[m
[32m+[m[32m    android:padding="@dimen/_8sdp"[m
     android:background="@color/colorPrimary">[m
 [m
[31m-    <ImageView[m
[31m-        android:layout_width="match_parent"[m
[31m-        android:layout_height="match_parent"[m
[31m-        android:src="@drawable/img_p"/>[m
[32m+[m[32m    <de.hdodenhof.circleimageview.CircleImageView[m
[32m+[m[32m        android:id="@+id/imgProfilePic"[m
[32m+[m[32m        android:layout_width="@dimen/_80sdp"[m
[32m+[m[32m        android:layout_height="@dimen/_80sdp"[m
[32m+[m[32m        android:layout_gravity="center"[m
[32m+[m[32m        android:src="@drawable/img_profilepicsammple"/>[m
 [m
[31m-</RelativeLayout>[m
\ No newline at end of file[m
[32m+[m[32m    <TextView[m
[32m+[m[32m        android:layout_width="wrap_content"[m
[32m+[m[32m        android:layout_height="wrap_content"[m
[32m+[m[32m        android:textColor="@android:color/white"[m
[32m+[m[32m        android:textSize="@dimen/_16sdp"[m
[32m+[m[32m        android:text="Welcome!"/>[m
[32m+[m
[32m+[m[32m    <TextView[m
[32m+[m[32m        android:id="@+id/tvProfileName"[m
[32m+[m[32m        android:layout_width="wrap_content"[m
[32m+[m[32m        android:layout_height="wrap_content"[m
[32m+[m[32m        android:textColor="@android:color/white"[m
[32m+[m[32m        android:textSize="@dimen/_18sdp"[m
[32m+[m[32m        android:textStyle="bold"[m
[32m+[m[32m        android:singleLine="true"[m
[32m+[m[32m        android:text="Juan Dela Cruz"/>[m
[32m+[m
[32m+[m[32m</LinearLayout>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/layout/tool_bar.xml b/app/src/main/res/layout/tool_bar.xml[m
[1mindex 8aa9409..03d896e 100644[m
[1m--- a/app/src/main/res/layout/tool_bar.xml[m
[1m+++ b/app/src/main/res/layout/tool_bar.xml[m
[36m@@ -4,6 +4,5 @@[m
     android:layout_width="match_parent"[m
     android:layout_height="wrap_content"[m
     android:background="@color/colorPrimary"[m
[31m-    android:elevation="@dimen/material_elevation_toolbar"[m
     android:theme="@style/ThemeOverlay.AppCompat.Dark"[m
[31m-    app:title="Title"/>[m
\ No newline at end of file[m
[32m+[m[32m    app:title="@string/app_name"/>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/menu/menu_notification.xml b/app/src/main/res/menu/menu_notification.xml[m
[1mindex fe187c0..cd0a76d 100644[m
[1m--- a/app/src/main/res/menu/menu_notification.xml[m
[1m+++ b/app/src/main/res/menu/menu_notification.xml[m
[36m@@ -1,4 +1,9 @@[m
 <?xml version="1.0" encoding="utf-8"?>[m
[31m-<menu xmlns:android="http://schemas.android.com/apk/res/android">[m
[31m-[m
[32m+[m[32m<menu xmlns:android="http://schemas.android.com/apk/res/android"[m
[32m+[m[32m    xmlns:app="http://schemas.android.com/apk/res-auto">[m
[32m+[m[32m    <item[m
[32m+[m[32m        android:id="@+id/menu_item_notification"[m
[32m+[m[32m        android:icon="@drawable/ic_notifications_black_24dp"[m
[32m+[m[32m        android:title="Notification"[m
[32m+[m[32m        app:showAsAction="always" />[m
 </menu>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/menu/menu_side_drawer.xml b/app/src/main/res/menu/menu_side_drawer.xml[m
[1mindex e854897..c087fb9 100644[m
[1m--- a/app/src/main/res/menu/menu_side_drawer.xml[m
[1m+++ b/app/src/main/res/menu/menu_side_drawer.xml[m
[36m@@ -1,34 +1,67 @@[m
 <?xml version="1.0" encoding="utf-8"?>[m
 <menu xmlns:android="http://schemas.android.com/apk/res/android">[m
 [m
[31m-    <item android:title="Membership Profile">[m
[31m-        <menu>[m
[31m-            <item[m
[31m-                android:id="@+id/menu_item_contribution"[m
[31m-                android:icon="@drawable/ic_contribution_48dp"[m
[31m-                android:title="Contribution/ Other Records" />[m
[31m-            <item[m
[31m-                android:id="@+id/menu_item_usefulinfo"[m
[31m-                android:icon="@drawable/ic_contribution_48dp"[m
[31m-                android:title="Useful Info" />[m
[31m-        </menu>[m
[31m-    </item>[m
[32m+[m[32m    <group>[m
[32m+[m[32m        <item android:title="@string/membership_profile">[m
[32m+[m[32m            <menu>[m
[32m+[m[32m                <item[m
[32m+[m[32m                    android:id="@+id/menu_item_contribution"[m
[32m+[m[32m                    android:icon="@drawable/ic_assessment_black_24dp"[m
[32m+[m[32m                    android:title="@string/contribution_other_records" />[m
[32m+[m[32m                <item[m
[32m+[m[32m                    android:id="@+id/menu_item_usefulinfo"[m
[32m+[m[32m                    android:icon="@drawable/ic_info_black_24dp"[m
[32m+[m[32m                    android:title="@string/useful_info" />[m
[32m+[m[32m            </menu>[m
[32m+[m[32m        </item>[m
[32m+[m[32m    </group>[m
[32m+[m[32m    <group>[m
[32m+[m[32m        <item android:title="@string/sss_profile">[m
[32m+[m[32m            <menu>[m
[32m+[m[32m                <item[m
[32m+[m[32m                    android:id="@+id/menu_item_sssmandate"[m
[32m+[m[32m                    android:icon="@drawable/ic_flag_black_24dp"[m
[32m+[m[32m                    android:title="@string/the_sss_mandate" />[m
[32m+[m[32m                <item[m
[32m+[m[32m                    android:id="@+id/menu_item_managementdirectory"[m
[32m+[m[32m                    android:icon="@drawable/ic_contacts_black_24dp"[m
[32m+[m[32m                    android:title="@string/management_directory" />[m
[32m+[m[32m                <item[m
[32m+[m[32m                    android:id="@+id/menu_item_sssbranches"[m
[32m+[m[32m                    android:icon="@drawable/ic_place_black_24dp"[m
[32m+[m[32m                    android:title="@string/sss_branches" />[m
[32m+[m[32m            </menu>[m
[32m+[m[32m        </item>[m
[32m+[m[32m    </group>[m
[32m+[m[32m    <group>[m
[32m+[m[32m        <item android:title="@string/sss_social">[m
[32m+[m[32m            <menu>[m
[32m+[m[32m                <item[m
[32m+[m[32m                    android:id="@+id/menu_item_fbaccount"[m
[32m+[m[32m                    android:icon="@drawable/ic_fb_black_24dp"[m
[32m+[m[32m                    android:title="@string/facebook_account" />[m
[32m+[m[32m            </menu>[m
[32m+[m[32m        </item>[m
[32m+[m[32m    </group>[m
[32m+[m[32m    <group>[m
[32m+[m[32m        <item[m
[32m+[m[32m            android:id="@+id/menu_item_about"[m
[32m+[m[32m            android:icon="@drawable/ic_info_black_24dp"[m
[32m+[m[32m            android:title="@string/about_mysss_app" />[m
 [m
[31m-    <item android:title="SSS Profile">[m
[31m-        <menu>[m
[31m-            <item[m
[31m-                android:id="@+id/menu_item_sssmandate"[m
[31m-                android:icon="@drawable/ic_contribution_48dp"[m
[31m-                android:title="The SSS Mandate" />[m
[31m-            <item[m
[31m-                android:id="@+id/menu_item_managementdirectory"[m
[31m-                android:icon="@drawable/ic_contribution_48dp"[m
[31m-                android:title="Management Directory" />[m
[31m-            <item[m
[31m-                android:id="@+id/menu_item_sssbranches"[m
[31m-                android:icon="@drawable/ic_contribution_48dp"[m
[31m-                android:title="SSS Branches" />[m
[31m-        </menu>[m
[31m-    </item>[m
[32m+[m[32m        <item[m
[32m+[m[32m            android:id="@+id/menu_rate"[m
[32m+[m[32m            android:icon="@drawable/ic_rate_black_24dp"[m
[32m+[m[32m            android:title="@string/rate_this_app" />[m
 [m
[32m+[m[32m        <item[m
[32m+[m[32m            android:id="@+id/menu_item_changepassword"[m
[32m+[m[32m            android:icon="@drawable/ic_lock_black_24dp"[m
[32m+[m[32m            android:title="@string/change_password" />[m
[32m+[m
[32m+[m[32m        <item[m
[32m+[m[32m            android:id="@+id/menu_item_logout"[m
[32m+[m[32m            android:icon="@drawable/ic_power_settings_new_black_24dp"[m
[32m+[m[32m            android:title="@string/logout" />[m
[32m+[m[32m    </group>[m
 </menu>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/values/colors.xml b/app/src/main/res/values/colors.xml[m
[1mindex 05215ab..bfe409e 100644[m
[1m--- a/app/src/main/res/values/colors.xml[m
[1m+++ b/app/src/main/res/values/colors.xml[m
[36m@@ -4,7 +4,7 @@[m
     <color name="colorPrimary">#3F51B5</color>[m
     <color name="colorPrimaryDark">#303F9F</color>[m
     <color name="colorAccent">#03A9F4</color>[m
[31m-    <color name="colorPrimaryLight">#03a9f4</color>[m
[32m+[m[32m    <color name="colorPrimaryLight">#3f8ab5</color>[m
     <color name="colorPrimaryText">#212121</color>[m
     <color name="colorSecondaryText">#757575</color>[m
     <color name="colorIcons">#FFFFFF</color>[m
[1mdiff --git a/app/src/main/res/values/strings.xml b/app/src/main/res/values/strings.xml[m
[1mindex 15cc108..df5f91d 100644[m
[1m--- a/app/src/main/res/values/strings.xml[m
[1m+++ b/app/src/main/res/values/strings.xml[m
[36m@@ -1,4 +1,23 @@[m
 <resources>[m
     <string name="login">LOGIN</string>[m
     <string name="sign_up">SIGN UP</string>[m
[32m+[m
[32m+[m[32m    <!-- navigation drawer -->[m
[32m+[m[32m    <string name="open_drawer">Open</string>[m
[32m+[m[32m    <string name="close_drawer">Close</string>[m
[32m+[m[32m    <string name="welcome">Welcome!</string>[m
[32m+[m[32m    <string name="membership_profile">Membership Profile</string>[m
[32m+[m[32m    <string name="contribution_other_records">Contribution/ Other Records</string>[m
[32m+[m[32m    <string name="useful_info">Useful Info</string>[m
[32m+[m[32m    <string name="the_sss_mandate">The SSS Mandate</string>[m
[32m+[m[32m    <string name="management_directory">Management Directory</string>[m
[32m+[m[32m    <string name="sss_branches">SSS Branches</string>[m
[32m+[m[32m    <string name="sss_profile">SSS Profile</string>[m
[32m+[m[32m    <string name="sss_social">SSS Social</string>[m
[32m+[m[32m    <string name="facebook_account">Facebook Account</string>[m
[32m+[m[32m    <string name="about_mysss_app">About MySSS App</string>[m
[32m+[m[32m    <string name="rate_this_app">Rate This App</string>[m
[32m+[m[32m    <string name="change_password">Change Password</string>[m
[32m+[m[32m    <string name="logout">Logout</string>[m
[32m+[m
 </resources>[m
[1mdiff --git a/app/src/main/res/values/styles.xml b/app/src/main/res/values/styles.xml[m
[1mindex fc7a966..d8c5a59 100644[m
[1m--- a/app/src/main/res/values/styles.xml[m
[1m+++ b/app/src/main/res/values/styles.xml[m
[36m@@ -1,7 +1,7 @@[m
 <resources>[m
 [m
     <!-- Base application theme. -->[m
[31m-    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">[m
[32m+[m[32m    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">[m
         <!-- Customize your theme here. -->[m
         <item name="colorPrimary">@color/colorPrimary</item>[m
         <item name="colorPrimaryDark">@color/colorPrimaryDark</item>[m
[36m@@ -33,4 +33,7 @@[m
         <item name="android:textSize">@dimen/_10sdp</item>[m
     </style>[m
 [m
[32m+[m[32m    <style name="MenuItemTitleTextApearance" parent="@android:style/TextAppearance.Widget.IconMenu.Item">[m
[32m+[m[32m        <item name="android:textColor">@color/colorAccent</item>[m
[32m+[m[32m    </style>[m
 </resources>[m
