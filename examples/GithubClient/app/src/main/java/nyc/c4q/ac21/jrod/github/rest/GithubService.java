package nyc.c4q.ac21.jrod.github.rest;

import java.util.List;

import nyc.c4q.ac21.jrod.github.model.Contributor;
import nyc.c4q.ac21.jrod.github.model.Repository;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GithubService {
    @GET("/repos/{owner}/{repo}/contributors")
    void getContributors(@Path("owner") String owner,
                         @Path("repo") String repo,
                         Callback<List<Contributor>> callback);

    @GET("/users/{user}/repos")
    void getRepositories(@Path("user") String user,
                         Callback<List<Repository>> callback);
}
