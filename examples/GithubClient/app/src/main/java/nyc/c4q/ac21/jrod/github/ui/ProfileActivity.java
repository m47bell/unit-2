package nyc.c4q.ac21.jrod.github.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import nyc.c4q.ac21.jrod.github.R;
import nyc.c4q.ac21.jrod.github.model.Repository;
import nyc.c4q.ac21.jrod.github.rest.APIManager;
import nyc.c4q.ac21.jrod.github.rest.GithubService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProfileActivity extends AppCompatActivity {
    public static final String EXTRA_USER_LOGIN = "LOGIN";
    public static final String EXTRA_USER_AVATAR_URL = "AVATAR_URL";

    private ImageView photoView;
    private TextView loginView;
    private ListView listView;

    private GithubService githubService;
    private RepositoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String login = intent.getStringExtra(EXTRA_USER_LOGIN);
        String avatarUrl = intent.getStringExtra(EXTRA_USER_AVATAR_URL);

        photoView = (ImageView) findViewById(R.id.photo);
        loginView = (TextView) findViewById(R.id.login);
        listView = (ListView) findViewById(android.R.id.list);

        Picasso.with(this)
                .load(avatarUrl)
                .placeholder(R.mipmap.ic_launcher)
                .resizeDimen(R.dimen.list_detail_image_size, R.dimen.list_detail_image_size)
                .centerInside()
                .into(photoView);

        loginView.setText(login);

        adapter = new RepositoryAdapter(this);
        listView.setAdapter(adapter);

        githubService = APIManager.getGithubService();
        githubService.getRepositories(login, new Callback<List<Repository>>() {
            @Override
            public void success(List<Repository> repositories, Response response) {
                adapter.clear();
                adapter.addAll(repositories);
            }

            @Override
            public void failure(RetrofitError error) {
                String errorMessage = ProfileActivity.this.getString(R.string.error_loading_repositories, error.toString());
                Toast.makeText(ProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
