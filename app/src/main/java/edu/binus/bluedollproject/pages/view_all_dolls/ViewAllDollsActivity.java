package edu.binus.bluedollproject.pages.view_all_dolls;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import edu.binus.bluedollproject.R;

public class ViewAllDollsActivity extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private DrawerLayout layout;
    private NavigationView navigationDrawer;
    private ListView dollsList;

    public ListView getDollsList() {
        return dollsList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_dolls);

        toolbar = findViewById(R.id.vad_toolbar);
        layout = findViewById(R.id.vad_drawer_layout);
        navigationDrawer = findViewById(R.id.vad_navigation_drawer);
        dollsList = findViewById(R.id.vad_dolls_list);

        new ViewAllDollsController(this);
    }
}