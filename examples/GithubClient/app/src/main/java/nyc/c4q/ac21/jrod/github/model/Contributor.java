package nyc.c4q.ac21.jrod.github.model;

public class Contributor {
    public final String login;
    public final String avatarUrl;
    public final String url;
    public final int contributions;

    public Contributor(String login, String avatarUrl, String url, int contributions) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.url = url;
        this.contributions = contributions;
    }
}