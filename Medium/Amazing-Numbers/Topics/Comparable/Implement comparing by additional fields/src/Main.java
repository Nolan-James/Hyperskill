class Article implements Comparable<Article> {
    private String title;
    private int size;

    public Article(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public int compareTo(Article otherArticle) {
        if (Integer.valueOf(this.getSize()).compareTo(otherArticle.getSize()) == 0) {
            return this.getTitle().compareTo(otherArticle.getTitle());
        } else {
            return Integer.valueOf(this.getSize()).compareTo(otherArticle.getSize());
        }
    }
}