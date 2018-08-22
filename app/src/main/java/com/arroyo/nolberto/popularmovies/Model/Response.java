package com.arroyo.nolberto.popularmovies.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

//generated using Gson plugin and http://www.parcelabler.com
public class Response {

    private int page;
    private int total_results;
    private int total_pages;
    private List<MoviesModel> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<MoviesModel> getResults() {
        return results;
    }

    public void setResults(List<MoviesModel> results) {
        this.results = results;
    }

    @Entity (tableName = "favorites")
    public static class MoviesModel implements Parcelable {
        /**
         * vote_count : 2935
         * id : 299536
         * video : false
         * vote_average : 8.6
         * title : Avengers: Infinity War
         * popularity : 560.647002
         * poster_path : /7WsyChQLEftFiDOVTGkv3hFpyyt.jpg
         * original_language : en
         * original_title : Avengers: Infinity War
         * genre_ids : [12,878,14,28]
         * backdrop_path : /bOGkgRGdhrBYJSLpXaxhXVstddV.jpg
         * adult : false
         * overview : As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.
         * release_date : 2018-04-25
         */
        @PrimaryKey(autoGenerate = true)
        private int db_id;
        private int id;
        private String title;
        private int vote_count;
        private boolean video;
        private double vote_average;
        private double popularity;
        private String poster_path;
        private String original_language;
        private String original_title;
        private String backdrop_path;
        private boolean adult;
        private String overview;
        private String release_date;
        @Ignore
        private List<Integer> genre_ids;


        public MoviesModel(int id, String title) {
            this.id = id;
            this.title = title;
        }


        public void setDb_id(int db_id) {
            this.db_id = db_id;
        }

        public int getDb_id() {
            return db_id;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }

        protected MoviesModel(Parcel in) {
            vote_count = in.readInt();
            id = in.readInt();
            video = in.readByte() != 0x00;
            vote_average = in.readDouble();
            title = in.readString();
            popularity = in.readDouble();
            poster_path = in.readString();
            original_language = in.readString();
            original_title = in.readString();
            backdrop_path = in.readString();
            adult = in.readByte() != 0x00;
            overview = in.readString();
            release_date = in.readString();
            if (in.readByte() == 0x01) {
                genre_ids = new ArrayList<Integer>();
                in.readList(genre_ids, Integer.class.getClassLoader());
            } else {
                genre_ids = null;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(vote_count);
            dest.writeInt(id);
            dest.writeByte((byte) (video ? 0x01 : 0x00));
            dest.writeDouble(vote_average);
            dest.writeString(title);
            dest.writeDouble(popularity);
            dest.writeString(poster_path);
            dest.writeString(original_language);
            dest.writeString(original_title);
            dest.writeString(backdrop_path);
            dest.writeByte((byte) (adult ? 0x01 : 0x00));
            dest.writeString(overview);
            dest.writeString(release_date);
            if (genre_ids == null) {
                dest.writeByte((byte) (0x00));
            } else {
                dest.writeByte((byte) (0x01));
                dest.writeList(genre_ids);
            }
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<MoviesModel> CREATOR = new Parcelable.Creator<MoviesModel>() {
            @Override
            public MoviesModel createFromParcel(Parcel in) {
                return new MoviesModel(in);
            }

            @Override
            public MoviesModel[] newArray(int size) {
                return new MoviesModel[size];
            }
        };
    }

}
