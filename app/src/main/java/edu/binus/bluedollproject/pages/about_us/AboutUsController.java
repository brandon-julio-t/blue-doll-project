package edu.binus.bluedollproject.pages.about_us;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.binus.bluedollproject.R;
import edu.binus.bluedollproject.bases.BaseController;
import edu.binus.bluedollproject.facades.Auth;
import edu.binus.bluedollproject.pages.insert_doll.InsertDollActivity;
import edu.binus.bluedollproject.pages.login.LoginActivity;
import edu.binus.bluedollproject.pages.view_all_dolls.ViewAllDollsActivity;

public class AboutUsController extends BaseController<AboutUsActivity> implements OnMapReadyCallback {
    public AboutUsController(AboutUsActivity view) {
        super(view);
        view.getToolbar().setNavigationOnClickListener(this::onNavigation);
        view.getToolbar().setOnMenuItemClickListener(this::onToolbarMenuItemPressed);
        view.getNavigationDrawer().setNavigationItemSelectedListener(this::onNavigationItem);
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

    private boolean onNavigationItem(MenuItem menuItem) {
        Class<?> target = LoginActivity.class;
        int id = menuItem.getItemId();

        if (id == R.id.menu_about_us)
            target = AboutUsActivity.class;
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

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        RequestQueue queue = Volley.newRequestQueue(view);

        JsonObjectRequest stringRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/acad600/JSONRepository/master/ISYS6203/O212-ISYS6203-SO02-00-BlueDoll.json",
                null,
                this.onMarkersDataReceived(googleMap),
                error -> Toast.makeText(view, error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        queue.add(stringRequest);
    }

    private Response.Listener<JSONObject> onMarkersDataReceived(GoogleMap googleMap) {
        return response -> {
            try {
                JSONArray markers = response.getJSONArray("markers");
                LatLng lastCoordinate = null;

                for (int i = 0; i < markers.length(); i++) {
                    JSONObject marker = markers.getJSONObject(i);
                    JSONObject location = marker.getJSONObject("location");

                    String name = marker.getString("name");
                    double lat = location.getDouble("lat");
                    double lng = location.getDouble("lng");

                    googleMap.addMarker(
                            new MarkerOptions()
                                    .position(lastCoordinate = new LatLng(lat, lng))
                                    .title(name)
                    );
                }

                if (lastCoordinate != null)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(lastCoordinate));
            } catch (JSONException e) {
                Toast.makeText(view, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        };
    }
}
