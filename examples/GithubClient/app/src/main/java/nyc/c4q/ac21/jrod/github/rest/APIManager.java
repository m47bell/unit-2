package nyc.c4q.ac21.jrod.github.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class APIManager {
    public static final String API_URL = "https://api.github.com";

    static {
        setupRestClients();
    }

    private static GithubService githubService;

    private static void setupRestClients() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        // Create a very simple REST adapter which points the GitHub API endpoint.
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        // Create an instance of our GitHub API interface.
        githubService = retrofit.create(GithubService.class);
    }

    public static GithubService getGithubService() {
        return githubService;
    }
}
