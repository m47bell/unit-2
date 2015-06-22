package nyc.c4q.ac21.jrod.github.ui;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import nyc.c4q.ac21.jrod.github.R;
import nyc.c4q.ac21.jrod.github.model.Contributor;

public class ContributorAdapter extends ArrayAdapter<Contributor> {
    private Context context;

    public ContributorAdapter(Context context) {
        super(context, 0, new ArrayList<Contributor>());
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_contributor, parent, false);
        }

        Contributor contributor = getItem(position);

        ImageView photoView = (ImageView) view.findViewById(R.id.photo);
        TextView loginView = (TextView) view.findViewById(R.id.login);
        TextView contributionCountView = (TextView) view.findViewById(R.id.contribution_count);

        Picasso.with(context)
                .load(contributor.avatarUrl)
                .placeholder(R.mipmap.ic_launcher)
                .resizeDimen(R.dimen.list_detail_image_size, R.dimen.list_detail_image_size)
                .centerInside()
                .into(photoView);

        loginView.setText(contributor.login);

        Resources resources = context.getResources();
        int c = contributor.contributions;
        String contributionSummaryText = resources.getQuantityString(R.plurals.num_contributions, c, c);
        contributionCountView.setText(contributionSummaryText);

        return view;
    }
}
