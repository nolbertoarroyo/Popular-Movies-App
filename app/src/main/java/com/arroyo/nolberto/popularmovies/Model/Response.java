package com.arroyo.nolberto.popularmovies.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Response {

    /**
     * page : 1
     * total_results : 19933
     * total_pages : 997
     * results : [{"vote_count":2935,"id":299536,"video":false,"vote_average":8.6,"title":"Avengers: Infinity War","popularity":560.647002,"poster_path":"/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg","original_language":"en","original_title":"Avengers: Infinity War","genre_ids":[12,878,14,28],"backdrop_path":"/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg","adult":false,"overview":"As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.","release_date":"2018-04-25"},{"vote_count":1719,"id":337167,"video":false,"vote_average":6,"title":"Fifty Shades Freed","popularity":531.278675,"poster_path":"/jjPJ4s3DWZZvI4vw8Xfi4Vqa1Q8.jpg","original_language":"en","original_title":"Fifty Shades Freed","genre_ids":[18,10749],"backdrop_path":"/9ywA15OAiwjSTvg3cBs9B7kOCBF.jpg","adult":false,"overview":"Believing they have left behind shadowy figures from their past, newlyweds Christian and Ana fully embrace an inextricable connection and shared life of luxury. But just as she steps into her role as Mrs. Grey and he relaxes into an unfamiliar stability, new threats could jeopardize their happy ending before it even begins.","release_date":"2018-02-07"},{"vote_count":4945,"id":284054,"video":false,"vote_average":7.3,"title":"Black Panther","popularity":278.8261,"poster_path":"/uxzzxijgPIY7slzFvMotPv8wjKA.jpg","original_language":"en","original_title":"Black Panther","genre_ids":[28,12,14,878],"backdrop_path":"/AlFqBwJnokrp9zWTXOUv7uhkaeq.jpg","adult":false,"overview":"King T'Challa returns home from America to the reclusive, technologically advanced African nation of Wakanda to serve as his country's new leader. However, T'Challa soon finds that he is challenged for the throne by factions within his own country as well as without. Using powers reserved to Wakandan kings, T'Challa assumes the Black Panther mantel to join with girlfriend Nakia, the queen-mother, his princess-kid sister, members of the Dora Milaje (the Wakandan 'special forces') and an American secret agent, to prevent Wakanda from being dragged into a world war.","release_date":"2018-02-13"},{"vote_count":6066,"id":284053,"video":false,"vote_average":7.4,"title":"Thor: Ragnarok","popularity":176.328774,"poster_path":"/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg","original_language":"en","original_title":"Thor: Ragnarok","genre_ids":[28,12,14],"backdrop_path":"/kaIfm5ryEOwYg8mLbq8HkPuM1Fo.jpg","adult":false,"overview":"Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the prophecy of destruction to his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.","release_date":"2017-10-25"},{"vote_count":7130,"id":269149,"video":false,"vote_average":7.7,"title":"Zootopia","popularity":170.40617,"poster_path":"/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg","original_language":"en","original_title":"Zootopia","genre_ids":[16,12,10751,35],"backdrop_path":"/mhdeE1yShHTaDbJVdWyTlzFvNkr.jpg","adult":false,"overview":"Determined to prove herself, Officer Judy Hopps, the first bunny on Zootopia's police force, jumps at the chance to crack her first case - even if it means partnering with scam-artist fox Nick Wilde to solve the mystery.","release_date":"2016-02-11"},{"vote_count":12909,"id":118340,"video":false,"vote_average":7.9,"title":"Guardians of the Galaxy","popularity":164.025115,"poster_path":"/y31QB9kn3XSudA15tV7UWQ9XLuW.jpg","original_language":"en","original_title":"Guardians of the Galaxy","genre_ids":[28,878,12],"backdrop_path":"/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg","adult":false,"overview":"Light years from Earth, 26 years after being abducted, Peter Quill finds himself the prime target of a manhunt after discovering an orb wanted by Ronan the Accuser.","release_date":"2014-07-30"},{"vote_count":470,"id":427641,"video":false,"vote_average":5.8,"title":"Rampage","popularity":150.895989,"poster_path":"/30oXQKwibh0uANGMs0Sytw3uN22.jpg","original_language":"en","original_title":"Rampage","genre_ids":[28,12,878],"backdrop_path":"/wrqUiMXttHE4UBFMhLHlN601MZh.jpg","adult":false,"overview":"Primatologist Davis Okoye shares an unshakable bond with George, the extraordinarily intelligent, silverback gorilla who has been in his care since birth.  But a rogue genetic experiment gone awry mutates this gentle ape into a raging creature of enormous size.  To make matters worse, it\u2019s soon discovered there are other similarly altered animals.  As these newly created alpha predators tear across North America, destroying everything in their path, Okoye teams with a discredited genetic engineer to secure an antidote, fighting his way through an ever-changing battlefield, not only to halt a global catastrophe but to save the fearsome creature that was once his friend.","release_date":"2018-04-12"},{"vote_count":14626,"id":24428,"video":false,"vote_average":7.5,"title":"The Avengers","popularity":123.972672,"poster_path":"/cezWGskPY5x7GaglTTRN4Fugfb8.jpg","original_language":"en","original_title":"The Avengers","genre_ids":[878,28,12],"backdrop_path":"/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg","adult":false,"overview":"When an unexpected enemy emerges and threatens global safety and security, Nick Fury, director of the international peacekeeping agency known as S.H.I.E.L.D., finds himself in need of a team to pull the world back from the brink of disaster. Spanning the globe, a daring recruitment effort begins!","release_date":"2012-04-25"},{"vote_count":7760,"id":198663,"video":false,"vote_average":7,"title":"The Maze Runner","popularity":123.693754,"poster_path":"/coss7RgL0NH6g4fC2s5atvf3dFO.jpg","original_language":"en","original_title":"The Maze Runner","genre_ids":[28,9648,878,53],"backdrop_path":"/lkOZcsXcOLZYeJ2YxJd3vSldvU4.jpg","adult":false,"overview":"Set in a post-apocalyptic world, young Thomas is deposited in a community of boys after his memory is erased, soon learning they're all trapped in a maze that will require him to join forces with fellow \u201crunners\u201d for a shot at escape.","release_date":"2014-09-10"},{"vote_count":8136,"id":321612,"video":false,"vote_average":6.8,"title":"Beauty and the Beast","popularity":110.104916,"poster_path":"/tWqifoYuwLETmmasnGHO7xBjEtt.jpg","original_language":"en","original_title":"Beauty and the Beast","genre_ids":[10751,14,10749],"backdrop_path":"/6aUWe0GSl69wMTSWWexsorMIvwU.jpg","adult":false,"overview":"A live-action adaptation of Disney's version of the classic tale of a cursed prince and a beautiful young woman who helps him break the spell.","release_date":"2017-03-16"},{"vote_count":4265,"id":354912,"video":false,"vote_average":7.8,"title":"Coco","popularity":108.873086,"poster_path":"/eKi8dIrr8voobbaGzDpe8w0PVbC.jpg","original_language":"en","original_title":"Coco","genre_ids":[12,35,10751,16],"backdrop_path":"/askg3SMvhqEl4OL52YuvdtY40Yb.jpg","adult":false,"overview":"Despite his family\u2019s baffling generations-old ban on music, Miguel dreams of becoming an accomplished musician like his idol, Ernesto de la Cruz. Desperate to prove his talent, Miguel finds himself in the stunning and colorful Land of the Dead following a mysterious chain of events. Along the way, he meets charming trickster Hector, and together, they set off on an extraordinary journey to unlock the real story behind Miguel's family history.","release_date":"2017-10-27"},{"vote_count":9107,"id":99861,"video":false,"vote_average":7.3,"title":"Avengers: Age of Ultron","popularity":105.198298,"poster_path":"/t90Y3G8UGQp0f0DrP60wRu9gfrH.jpg","original_language":"en","original_title":"Avengers: Age of Ultron","genre_ids":[28,12,878],"backdrop_path":"/rFtsE7Lhlc2jRWF7SRAU0fvrveQ.jpg","adult":false,"overview":"When Tony Stark tries to jumpstart a dormant peacekeeping program, things go awry and Earth\u2019s Mightiest Heroes are put to the ultimate test as the fate of the planet hangs in the balance. As the villainous Ultron emerges, it is up to The Avengers to stop him from enacting his terrible plans, and soon uneasy alliances and unexpected action pave the way for an epic and unique global adventure.","release_date":"2015-04-22"},{"vote_count":5304,"id":181808,"video":false,"vote_average":7.1,"title":"Star Wars: The Last Jedi","popularity":101.783875,"poster_path":"/kOVEVeg59E0wsnXmF9nrh6OmWII.jpg","original_language":"en","original_title":"Star Wars: The Last Jedi","genre_ids":[14,12,878],"backdrop_path":"/bIUaCtWaRgd78SnoHJDI8TNf7Sd.jpg","adult":false,"overview":"Rey develops her newly discovered abilities with the guidance of Luke Skywalker, who is unsettled by the strength of her powers. Meanwhile, the Resistance prepares to do battle with the First Order.","release_date":"2017-12-13"},{"vote_count":6827,"id":76338,"video":false,"vote_average":6.7,"title":"Thor: The Dark World","popularity":99.663402,"poster_path":"/bnX5PqAdQZRXSw3aX3DutDcdso5.jpg","original_language":"en","original_title":"Thor: The Dark World","genre_ids":[28,12,14],"backdrop_path":"/3FweBee0xZoY77uO1bhUOlQorNH.jpg","adult":false,"overview":"Thor fights to restore order across the cosmos\u2026 but an ancient race led by the vengeful Malekith returns to plunge the universe back into darkness. Faced with an enemy that even Odin and Asgard cannot withstand, Thor must embark on his most perilous and personal journey yet, one that will reunite him with Jane Foster and force him to sacrifice everything to save us all.","release_date":"2013-10-29"},{"vote_count":1335,"id":274855,"video":false,"vote_average":5.7,"title":"Geostorm","popularity":95.939422,"poster_path":"/nrsx0jEaBgXq4PWo7SooSnYJTv.jpg","original_language":"en","original_title":"Geostorm","genre_ids":[28,878,53],"backdrop_path":"/79X8JkGxzc1tJMi0KxUSaPLooVg.jpg","adult":false,"overview":"After an unprecedented series of natural disasters threatened the planet, the world's leaders came together to create an intricate network of satellites to control the global climate and keep everyone safe. But now, something has gone wrong: the system built to protect Earth is attacking it, and it becomes a race against the clock to uncover the real threat before a worldwide geostorm wipes out everything and everyone along with it.","release_date":"2017-10-13"},{"vote_count":8826,"id":10195,"video":false,"vote_average":6.6,"title":"Thor","popularity":94.378718,"poster_path":"/bIuOWTtyFPjsFDevqvF3QrD1aun.jpg","original_language":"en","original_title":"Thor","genre_ids":[12,14,28],"backdrop_path":"/LvmmDZxkTDqp0DX7mUo621ahdX.jpg","adult":false,"overview":"Against his father Odin's will, The Mighty Thor - a powerful but arrogant warrior god - recklessly reignites an ancient war. Thor is cast down to Earth and forced to live among humans as punishment. Once here, Thor learns what it takes to be a true hero when the most dangerous villain of his world sends the darkest forces of Asgard to invade Earth.","release_date":"2011-04-21"},{"vote_count":931,"id":447332,"video":false,"vote_average":7.3,"title":"A Quiet Place","popularity":92.31,"poster_path":"/nAU74GmpUk7t5iklEp3bufwDq4n.jpg","original_language":"en","original_title":"A Quiet Place","genre_ids":[18,27,53,878],"backdrop_path":"/roYyPiQDQKmIKUEhO912693tSja.jpg","adult":false,"overview":"A family is forced to live in silence while hiding from creatures that hunt by sound.","release_date":"2018-04-05"},{"vote_count":15,"id":462593,"video":false,"vote_average":5.4,"title":"What the Waters Left Behind","popularity":91.277576,"poster_path":"/4lJS2NUtcgyJPjOPfFlXcHzHu0D.jpg","original_language":"es","original_title":"Los olvidados","genre_ids":[27],"backdrop_path":"/ufyPovbgRKlKTWrlzDFgULfgfyi.jpg","adult":false,"overview":"Epecuén was one of the most important touristic villages of Argentina. Thousands of people concurred, attracted by the healing properties of its thermal waters. On November 10th 1985, a huge volume of water broke the protecting embankment and the village was submerged under ten meters of salt water. Epecuén disappeared. Thirty years later, the waters receded and the ruins of Epecuén emerged exposing a bleak and deserted landscape. The residents never returned.  The plot revolves around a group of young people that take a trip to the ruins in order to film a documentary about Epecuén. Ignoring the warnings, and after a brief tour, they get stranded in the abandoned village. Contrary to what they thought, they begin to realize that they are really not alone\u2026","release_date":"2017-10-11"},{"vote_count":8582,"id":297762,"video":false,"vote_average":7.2,"title":"Wonder Woman","popularity":90.156006,"poster_path":"/imekS7f1OuHyUP2LAiTEM0zBzUz.jpg","original_language":"en","original_title":"Wonder Woman","genre_ids":[28,12,14],"backdrop_path":"/6iUNJZymJBMXXriQyFZfLAKnjO6.jpg","adult":false,"overview":"An Amazon princess comes to the world of Man in the grips of the First World War to confront the forces of evil and bring an end to human conflict.","release_date":"2017-05-30"},{"vote_count":1807,"id":333339,"video":false,"vote_average":7.8,"title":"Ready Player One","popularity":81.564422,"poster_path":"/pU1ULUq8D3iRxl1fdX2lZIzdHuI.jpg","original_language":"en","original_title":"Ready Player One","genre_ids":[12,878,28],"backdrop_path":"/q7fXcrDPJcf6t3rzutaNwTzuKP1.jpg","adult":false,"overview":"When the creator of a popular video game system dies, a virtual contest is created to compete for his fortune.","release_date":"2018-03-28"}]
     */

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

        private int vote_count;
        private int id;
        private boolean video;
        private double vote_average;
        private String title;
        private double popularity;
        private String poster_path;
        private String original_language;
        private String original_title;
        private String backdrop_path;
        private boolean adult;
        private String overview;
        private String release_date;
        private List<Integer> genre_ids;

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
