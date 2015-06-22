package nyc.c4q.ac21.jrod.github;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.concurrent.Executor;

import nyc.c4q.ac21.jrod.github.model.Contributor;
import nyc.c4q.ac21.jrod.github.rest.APIManager;
import nyc.c4q.ac21.jrod.github.rest.GithubService;
import nyc.c4q.ac21.jrod.github.rest.MockGithubService;
import retrofit.Callback;
import retrofit.MockRestAdapter;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class TestFoo {
    private GithubService githubService;

    @Before
    public void setUp() {
        // this serializes execution for unit tests
        Executor syncExecutor = new SynchronousExecutor();

        RestAdapter restAdapter = new RestAdapter.Builder()
                                    .setEndpoint(APIManager.API_URL)
                                    .setExecutors(syncExecutor, syncExecutor)
                                    .build();

        MockRestAdapter mockRestAdapter = MockRestAdapter.from(restAdapter);
        MockGithubService mockGithubService = new MockGithubService();

        githubService = mockRestAdapter.create(GithubService.class, mockGithubService);
    }

    @Test
    public void shouldReturnSomeContributors() {
        githubService.getContributors("owner1", "project1", new Callback<List<Contributor>>() {
            public void success(List<Contributor> contributors, Response response) {
                assertThat(contributors).isNotEmpty();
            }

            public void failure(RetrofitError error) {
                fail(error.toString());
            }
        });
    }

    @Test
    public void shouldReturnNoContributors() {
        githubService.getContributors("owner", "project2", new Callback<List<Contributor>>() {
            public void success(List<Contributor> contributors, Response response) {
                assertThat(contributors).isEmpty();
            }

            public void failure(RetrofitError error) {
                fail(error.toString());
            }
        });
    }

    private class SynchronousExecutor implements Executor {
        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
