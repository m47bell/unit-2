package nyc.c4q.ac21.jrod.github.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nyc.c4q.ac21.jrod.github.R;
import nyc.c4q.ac21.jrod.github.model.Contributor;
import nyc.c4q.ac21.jrod.github.rest.APIManager;
import nyc.c4q.ac21.jrod.github.rest.GithubService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private EditText ownerView;
    private EditText repositoryView;
    private ListView listView;

    private GithubService githubService;
    private ContributorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ownerView = (EditText) findViewById(R.id.owner);
        repositoryView = (EditText) findViewById(R.id.repository);
        listView = (ListView) findViewById(android.R.id.list);

        adapter = new ContributorAdapter(this);
        listView.setAdapter(adapter);

        // hold onto this for later
        githubService = APIManager.getGithubService();

        repositoryView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    loadRepositoryContributors(null);
                    return true;
                }
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contributor item = adapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_USER_LOGIN, item.login);
                intent.putExtra(ProfileActivity.EXTRA_USER_AVATAR_URL, item.avatarUrl);
                startActivity(intent);
            }
        });
    }

    public void loadRepositoryContributors(View view) {
        String owner = ownerView.getText().toString();
        String repository = repositoryView.getText().toString();
        if (!owner.isEmpty() && !repository.isEmpty()) {
            githubService.getContributors(owner, repository, new Callback<List<Contributor>>() {
                @Override
                public void success(List<Contributor> contributors, Response response) {
                    adapter.clear();
                    adapter.addAll(contributors);
                }

                @Override
                public void failure(RetrofitError error) {
                    String errorMessage = MainActivity.this.getString(R.string.error_loading_contributors, error.toString());
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
