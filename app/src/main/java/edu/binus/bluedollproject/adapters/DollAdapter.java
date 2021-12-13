package edu.binus.bluedollproject.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.binus.bluedollproject.R;
import edu.binus.bluedollproject.facades.Auth;
import edu.binus.bluedollproject.facades.ImageResolver;
import edu.binus.bluedollproject.models.Doll;
import edu.binus.bluedollproject.models.User;
import edu.binus.bluedollproject.pages.insert_doll.InsertDollActivity;
import edu.binus.bluedollproject.pages.view_doll_detail.ViewDollDetailActivity;
import edu.binus.bluedollproject.repositories.DollRepository;

public class DollAdapter extends ArrayAdapter<Doll> {
    private final int resource;
    private List<Doll> dolls;

    public DollAdapter(AppCompatActivity parent, int resource, List<Doll> dolls) {
        super(parent, resource, dolls);
        this.resource = resource;
        this.dolls = dolls;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position >= dolls.size()) return convertView;

        Doll doll = dolls.get(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(resource, parent, false);

        ImageView image = convertView.findViewById(R.id.li_doll_image);
        TextView name = convertView.findViewById(R.id.li_doll_name);
        TextView creator = convertView.findViewById(R.id.li_doll_creator);
        TextView description = convertView.findViewById(R.id.li_doll_description);
        Button view = convertView.findViewById(R.id.li_doll_view);
        Button edit = convertView.findViewById(R.id.li_doll_edit);
        Button delete = convertView.findViewById(R.id.li_doll_delete);

        image.setImageResource(ImageResolver.get(doll.getImage()));
        name.setText(doll.getName());
        creator.setText(doll.getUser().getName());
        description.setText(doll.getDescription());

        view.setOnClickListener(this.onViewClicked(doll));
        edit.setOnClickListener(this.onEditClicked(doll));
        delete.setOnClickListener(this.onDeleteClicked(doll));

        return convertView;
    }

    private View.OnClickListener onViewClicked(Doll doll) {
        return view -> {
            Intent intent = new Intent(view.getContext(), ViewDollDetailActivity.class);
            intent.putExtra("id", doll.getId());
            view.getContext().startActivity(intent);
        };
    }

    private View.OnClickListener onEditClicked(Doll doll) {
        return view -> {
            Intent intent = new Intent(view.getContext(), InsertDollActivity.class);
            intent.putExtra("id", doll.getId());
            view.getContext().startActivity(intent);
        };
    }

    private View.OnClickListener onDeleteClicked(Doll doll) {
        return view -> {
            Auth svc = new Auth(getContext());
            User user = svc.getUser();
            boolean isAdmin = user.getEmail().equals("admin@email.com");
            boolean isCreator = user.getId() == doll.getUserId();
            if (!isAdmin && !isCreator) {
                Toast.makeText(getContext(), "You are not eligible to remove this doll!", Toast.LENGTH_SHORT).show();
                return;
            }

            DollRepository repo = new DollRepository(getContext());
            boolean isDeleted = repo.delete(doll);
            if (isDeleted) {
                dolls = repo.getAll();
                DollAdapter.this.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "An error occurred while deleting doll.", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
