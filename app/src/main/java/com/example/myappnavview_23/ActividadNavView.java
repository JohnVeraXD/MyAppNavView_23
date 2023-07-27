package com.example.myappnavview_23;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class ActividadNavView extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_nav_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.iconmenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView = (NavigationView)findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1 , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;
        if (menuItem.getItemId() == R.id.menu_seccion_1){
            fragment = new FragmentClientes();
            fragmentTransaction = true;
        }else if(menuItem.getItemId() == R.id.menu_seccion_2){
            fragment = new FragmentProductos();
            fragmentTransaction = true;
        }else if(menuItem.getItemId() ==R.id.menu_seccion_3){
                fragment = new FragmentVentas();
                fragmentTransaction = true;
        }
        if(fragment !=null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
            menuItem.setChecked(true);
            getSupportActionBar().setTitle(menuItem.getTitle());
        }
        drawerLayout.closeDrawers();
        return true;
    }
}