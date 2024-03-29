package sg.edu.nus.imovin.Retrofit.Object;

public class LibraryData {
    private String title;
    private String publish;
    private String year;
    private String pic_url;
    private String link_url;

    public LibraryData(String title, String publish, String year, String pic_url, String link_url) {
        this.title = title;
        this.publish = publish;
        this.year = year;
        this.pic_url = pic_url;
        this.link_url = link_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }
}
