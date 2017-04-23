package com.example.administrator.open_source_in_china_liwenxi.model.fragment.bean;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class Detail_BlogJavaBean {


    private BlogBean blog;

    @Override
    public String toString() {
        return "Detail_BlogJavaBean{" +
                "blog=" + blog +
                '}';
    }

    public BlogBean getBlog() {
        return blog;
    }

    public void setBlog(BlogBean blog) {
        this.blog = blog;
    }

    public static class BlogBean {
        private String id;
        private String title;
        private String url;
        private String where;
        private String commentCount;
        private String body;
        private String author;
        private String authorid;
        private String documentType;
        private String pubDate;
        private String favorite;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWhere() {
            return where;
        }

        @Override
        public String toString() {
            return "BlogBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", where='" + where + '\'' +
                    ", commentCount='" + commentCount + '\'' +
                    ", body='" + body + '\'' +
                    ", author='" + author + '\'' +
                    ", authorid='" + authorid + '\'' +
                    ", documentType='" + documentType + '\'' +
                    ", pubDate='" + pubDate + '\'' +
                    ", favorite='" + favorite + '\'' +
                    '}';
        }

        public void setWhere(String where) {
            this.where = where;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }
    }
}
