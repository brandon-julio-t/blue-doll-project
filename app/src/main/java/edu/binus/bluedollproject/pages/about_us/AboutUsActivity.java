package edu.binus.bluedollproject.pages.about_us;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import edu.binus.bluedollproject.R;

public class AboutUsActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private DrawerLayout layout;
    private NavigationView navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar = findViewById(R.id.ab_toolbar);
        layout = findViewById(R.id.ab_drawer_layout);
        navigationDrawer = findViewById(R.id.ab_navigation_drawer);

        AboutUsController controller = new AboutUsController(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(controller);
    }

    public MaterialToolbar getToolbar() {
        return toolbar;
    }

    public DrawerLayout getLayout() {
        return layout;
    }

    public NavigationView getNavigationDrawer() {
        return navigationDrawer;
    }
}