package makchamp.workoutlog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WorkoutLog extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected static ArrayList<LogBox> logBoxesALL = new ArrayList<>();;
    protected static ArrayList<String> addedExercises =  new ArrayList<>();
    private static final String FILE_NAME = "Data.txt";

  //  public String path = Environment.getExternalStorageDirectory().toString() + "/FitnessLog";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        /*View headerView = navigationView.getHeaderView(0);
        TextView navTitleView = (TextView) headerView.findViewById(R.id.nav_header_title);
        navTitleView.setText(navTitle);*/

       /* File fileDir = new File(path);

        if(!fileDir.exists())
            fileDir.mkdirs();*/

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(6);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.createFragment(new FragmentAll());
        pagerAdapter.createFragment(new FragmentChest());
        pagerAdapter.createFragment(new FragmentArms());
        pagerAdapter.createFragment(new FragmentShoulders());
        pagerAdapter.createFragment(new FragmentBack());
        pagerAdapter.createFragment(new FragmentLegs());

        mViewPager.setAdapter(pagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        for(int i =0; i < tabLayout.getTabCount(); i++){
        TabLayout.Tab tab = tabLayout.getTabAt(i);

        tab.setCustomView(pagerAdapter.tabView(i));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);





    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i0 = new Intent(WorkoutLog.this, MainMenu.class);
        startActivity(i0);
    }


   /* public void read(){

        File file = new File(path + "/Data.txt");

        ObjectInputStream objectinputstream = null;
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            objectinputstream = new ObjectInputStream(in);
            if(logBoxesALL.isEmpty())
            logBoxesALL = (ArrayList<LogBox>) objectinputstream.readObject();

            if(addedExercises.isEmpty())
                addedExercises = (ArrayList<String>) objectinputstream.readObject();


        } catch (Exception e) {

        } finally {


            try {
                if (objectinputstream != null)
                    objectinputstream.close();

            } catch (IOException e) {
            }
        }
    }

    public void write(){

        File file = new File(path + "/Data.txt");

        ObjectOutputStream oOut = null;
        FileOutputStream fOut = null;
        try{
            fOut = new FileOutputStream(file);
            oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(logBoxesALL);
            oOut.writeObject(addedExercises);

        } catch (Exception e) {

        }finally {

            try{

                if(oOut  != null)
                    oOut.close();
            }catch(IOException e){

            }

        }

    }*/

    public void save(){

      FileOutputStream fOut = null;
      ObjectOutputStream oOUt = null;

      try{
          fOut = openFileOutput(FILE_NAME, MODE_PRIVATE);
          oOUt = new ObjectOutputStream(fOut);
          oOUt.writeObject(logBoxesALL);
          oOUt.writeObject(addedExercises);



      }catch(FileNotFoundException e){

      }catch (IOException e){


      }finally {

          try{

              if(oOUt  != null)
                  oOUt.close();
          }catch(IOException e){

          }

      }





    }

    public void load(){

        FileInputStream fIN = null;
        ObjectInputStream oIN = null;

        try{
            fIN = openFileInput(FILE_NAME);
            oIN = new ObjectInputStream(fIN);
            if(logBoxesALL.isEmpty())
                logBoxesALL = (ArrayList<LogBox>) oIN.readObject();

            if(addedExercises.isEmpty())
                addedExercises = (ArrayList<String>) oIN.readObject();


        }catch(ClassNotFoundException e){

        }catch (IOException e){


        }finally {

            try{

                if(oIN  != null)
                    oIN.close();
            }catch(IOException e){

            }

        }



    }



    @Override
    protected void onStart() {
        super.onStart();
       // read();
        load();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // write();
         save();
    }

    public void onResume() {
        super.onResume();
        invalidateOptionsMenu();

    }

    @Override
    protected void onPause() {
        super.onPause();

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.workout_log, menu);
        MenuItem searchField = menu.findItem(R.id.action_settings_search_wl);
        SearchView searchView = (SearchView) searchField.getActionView();

        searchView.setIconified(true);


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                FragmentAll.adapter2.getFilter().filter(newText);

                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        if (id == R.id.action_settings_add) {
            Intent toAddLog = new Intent(WorkoutLog.this, addToLog.class);
            startActivity(toAddLog);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menuLog) {


        } else if (id == R.id.menuAddExercise) {

            Intent i3= new Intent(WorkoutLog.this, AddExercise.class);
            startActivity(i3);

        } else if (id == R.id.menuSettings) {

            Intent i4= new Intent(WorkoutLog.this, Settings.class);
            startActivity(i4);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class PagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragments = new ArrayList<>();
        String[] tabs = {"ALL", "Chest", "Arms", "Shoulders", "Back", "Legs" };

        public PagerAdapter(FragmentManager fm) {
            super(fm);

        }

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);

        }

        public View tabView(int position){
            View tab = LayoutInflater.from(WorkoutLog.this).inflate(R.layout.tabs, null);
            TextView title = (TextView)tab.findViewById(R.id.tabTitle);
            title.setText(tabs[position]);
            return tab;
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            if(position == 0) {

                fragment = new FragmentAll();

            }
            else if (position == 1)
                fragment = new FragmentChest();
            else if (position == 2)
                fragment = new FragmentArms();
            else if(position == 3)
                fragment = new FragmentShoulders();
            else if(position == 4)
                fragment = new FragmentBack();
            else if(position == 5)
                fragment =  new FragmentLegs();

            return fragment;
        }



        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

        public void createFragment(Fragment fragment){

            fragments.add(fragment);
        }



        @Override
        public int getCount() {

            return 6;
        }
    }






}
