package com.kalangirajeev.ebooks.apgovtebooks;

import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnDataPass {

    SearchView searchView;
    ClipboardManager clipboard;
    String string;

    private InterstitialAd interstitial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        interstitial = new InterstitialAd(MainActivity.this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (string.equals(null)) {
                        Toast.makeText(MainActivity.this, "Long Press to Copy and Share text....", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        sharingIntent.setType("text/html");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, string);
                        startActivity(Intent.createChooser(sharingIntent, "Share using..."));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Select Text before sharing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.day_mode) {
            return true;
        } else if(id == R.id.night_mode){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.ebook_apleaverules) {
            try {
                FragmentAPLeaveRules fragmentAPLeaveRules = new FragmentAPLeaveRules();
                fragmentAPLeaveRules.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragmentAPLeaveRules);
                transaction.addToBackStack(null);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }

            interstitial = new InterstitialAd(getApplicationContext());
            interstitial.setAdUnitId(getString(R.string.admob_interstitial_ad));
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitial.loadAd(adRequest);
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    if (interstitial.isLoaded()) {
                        interstitial.show();
                    }
                }
            });

        } else if (id == R.id.ebook_apdss) {
            try {
                FragmentAPDSS fragmentAPDSS = new FragmentAPDSS();
                fragmentAPDSS.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragmentAPDSS);
                transaction.addToBackStack(null);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }

            interstitial = new InterstitialAd(getApplicationContext());
            interstitial.setAdUnitId(getString(R.string.admob_interstitial_ad));
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitial.loadAd(adRequest);
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    if (interstitial.isLoaded()) {
                        interstitial.show();
                    }
                }
            });

        } else if (id == R.id.ebook_apdcode) {
            try {
                FragmentAPDCode fragmentAPDCode = new FragmentAPDCode();
                fragmentAPDCode.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragmentAPDCode);
                transaction.addToBackStack(null);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }

            interstitial = new InterstitialAd(getApplicationContext());
            interstitial.setAdUnitId(getString(R.string.admob_interstitial_ad));
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitial.loadAd(adRequest);
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    if (interstitial.isLoaded()) {
                        interstitial.show();
                    }
                }
            });

        } else if (id == R.id.ebook_apservicerules) {
            try {
                FragmentAPServiceRules fragmentAPServiceRules = new FragmentAPServiceRules();
                fragmentAPServiceRules.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragmentAPServiceRules);
                transaction.addToBackStack(null);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }

            interstitial = new InterstitialAd(getApplicationContext());
            interstitial.setAdUnitId(getString(R.string.admob_interstitial_ad));
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitial.loadAd(adRequest);
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    if (interstitial.isLoaded()) {
                        interstitial.show();
                    }
                }
            });

        }else if (id == R.id.ebook_apconductrules) {
            try {
                FragmentAPConductRules fragmentAPConductRules = new FragmentAPConductRules();
                fragmentAPConductRules.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_main, fragmentAPConductRules);
                transaction.addToBackStack(null);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
            }

            interstitial = new InterstitialAd(getApplicationContext());
            interstitial.setAdUnitId(getString(R.string.admob_interstitial_ad));
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitial.loadAd(adRequest);
            interstitial.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    if (interstitial.isLoaded()) {
                        interstitial.show();
                    }
                }
            });

        } else if(id == R.id.sms_send) {
            try {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("sms_body", string);
                startActivity(sendIntent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Select Text before sharing", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_send) {

            PackageManager pm=getPackageManager();
            try {
                Intent waIntent = new Intent(Intent.ACTION_SEND);
                waIntent.setType("text/plain");
                PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                waIntent.setPackage("com.whatsapp");
                waIntent.putExtra(Intent.EXTRA_TEXT, string);
                Toast.makeText(this,string,Toast.LENGTH_LONG).show();
                startActivity(Intent.createChooser(waIntent, "Share with"));

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Text Not Selected (or) WhatsApp not Installed", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
/*
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search_view).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
*/

        return true;
    }


    @Override
    public void onDataPass(String data) {
        string = data;
    }
}
