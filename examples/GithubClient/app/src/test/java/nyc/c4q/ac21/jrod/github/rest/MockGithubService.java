package nyc.c4q.ac21.jrod.github.rest;

import android.net.wifi.WifiConfiguration;

import org.apache.http.HttpStatus;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nyc.c4q.ac21.jrod.github.model.Contributor;
import nyc.c4q.ac21.jrod.github.model.Repository;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Path;
import retrofit.mime.TypedByteArray;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.Collections.EMPTY_LIST;

public class MockGithubService implements GithubService {
    private Map<String, Map<String, List<Contributor>>> ownerRepoContributors;
    private Map<String, List<Repository>> userRepositories;

    public MockGithubService() {
        ownerRepoContributors = new LinkedHashMap<>();

        addContributor("owner1", "project1", "Alice Doe", 12);
        addContributor("owner1", "project1", "Bob Doe", 2);

        userRepositories = new LinkedHashMap<>();

        addRepository("jrod", "project1");
        addRepository("jrod", "project2");
        addRepository("bob", "project3");
        addRepository("bob", "project4");
    }

    @Override
    public void getContributors(String owner, String repo, Callback<List<Contributor>> callback) {
        TypedByteArray body = new TypedByteArray("application/json", "".getBytes());

        Map<String, List<Contributor>> repoContributors = ownerRepoContributors.get(owner);
        if(repoContributors == null) {
            Response response = new Response("", HTTP_OK, "nothing", EMPTY_LIST, body);
            callback.success(EMPTY_LIST, response);
            return;
        }

        List<Contributor> contributors = repoContributors.get(repo);
        if(contributors == null) {
            Response response = new Response("", HTTP_OK, "nothing", EMPTY_LIST, body);
            callback.success(EMPTY_LIST, response);
            return;
        }

        Response response = new Response("", HTTP_OK, "nothing", EMPTY_LIST, body);
        callback.success(contributors, response);
    }

    @Override
    public void getRepositories(String user, Callback<List<Repository>> callback) {
        TypedByteArray body = new TypedByteArray("application/json", "".getBytes());

        List<Repository> repositories = userRepositories.get(user);
        if(repositories == null) {
            Response response = new Response("", HTTP_OK, "nothing", EMPTY_LIST, body);
            callback.success(EMPTY_LIST, response);
            return;
        }

        Response response = new Response("", HTTP_OK, "nothing", EMPTY_LIST, body);
        callback.success(repositories, response);
    }

    private void addContributor(String owner, String repo, String name, int contributions) {
        Map<String, List<Contributor>> repoContributors = ownerRepoContributors.get(owner);
        if(repoContributors == null) {
            repoContributors = new LinkedHashMap<>();
            ownerRepoContributors.put(owner, repoContributors);
        }
        List<Contributor> contributors = repoContributors.get(repo);
        if(contributors == null) {
            contributors = new ArrayList<>();
            repoContributors.put(repo, contributors);
        }
        contributors.add(new Contributor(name, "", "", contributions));
    }

    private void addRepository(String user, String repo) {
        List<Repository> repositories = userRepositories.get(user);
        if(repositories == null) {
            repositories = new ArrayList<>();
            userRepositories.put(user, repositories);
        }
        repositories.add(new Repository(repo, "some repo"));
    }
}
