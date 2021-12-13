package edu.binus.bluedollproject.pages.view_all_dolls;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.core.view.GravityCompat;

import java.util.List;

import edu.binus.bluedollproject.R;
import edu.binus.bluedollproject.adapters.DollAdapter;
import edu.binus.bluedollproject.bases.BaseController;
import edu.binus.bluedollproject.facades.Auth;
import edu.binus.bluedollproject.pages.about_us.AboutUsActivity;
import edu.binus.bluedollproject.pages.insert_doll.InsertDollActivity;
import edu.binus.bluedollproject.pages.login.LoginActivity;
import edu.binus.bluedollproject.repositories.DollRepository;

public class ViewAllDollsController extends BaseController<ViewAllDollsActivity> {
    public ViewAllDollsController(ViewAllDollsActivity view) {
        super(view);

        ListView lv = view.getDollsList();
        DollAdapter adapter = new DollAdapter(
                view,
                R.layout.list_item_doll,
                new DollRepository(view).getAll()
        );
        lv.setAdapter(adapter);

        view.getToolbar().setNavigationOnClickListener(this::onNavigation);
        view.getToolbar().setOnMenuItemClickListener(this::onToolbarMenuItemPressed);
        view.getNavigationDrawer().setNavigationItemSelectedListener(this::onNavigationItemPressed);
    }

    private void onNavigation(View v) {
        view.getLayout().openDrawer(GravityCompat.START);
    }

    private boolean onToolbarMenuItemPressed(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.menu_about_us) {
            view.startActivity(new Intent(view, AboutUsActivity.class));
            return true;
        }
        return false;
    }

    private boolean onNavigationItemPressed(MenuItem menuItem) {
        Class<?> target = LoginActivity.class;
        int id = menuItem.getItemId();

        if (id == R.id.menu_insert_doll)
            target = InsertDollActivity.class;
        if (id == R.id.menu_view_all_dolls)
            target = ViewAllDollsActivity.class;

        view.startActivity(new Intent(view, target));

        if (id == R.id.menu_logout) {
            new Auth(view).logout();
            view.finish();
        }

        return true;
    }
}
