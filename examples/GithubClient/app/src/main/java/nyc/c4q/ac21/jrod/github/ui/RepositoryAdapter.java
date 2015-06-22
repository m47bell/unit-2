package nyc.c4q.ac21.jrod.github.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nyc.c4q.ac21.jrod.github.model.Repository;

public class RepositoryAdapter extends ArrayAdapter<Repository> {
    private final Context context;

    public RepositoryAdapter(Context context) {
        super(context, 0, new ArrayList<Repository>());
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        Repository repository = getItem(position);

        TextView nameView = (TextView) view.findViewById(android.R.id.text1);
        TextView descView = (TextView) view.findViewById(android.R.id.text2);

        nameView.setText(repository.name);
        descView.setText(repository.description);

        return view;
    }
}
