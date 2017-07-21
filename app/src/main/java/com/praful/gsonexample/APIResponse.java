package com.praful.gsonexample;

import java.util.List;

/**
 * Created by Praful Dhabekar on 7/20/2017.
 */

public class APIResponse {

    private List<Players> Players;

    public List<Players> getPlayers() {
        return Players;
    }

    public void setPlayers(List<Players> Players) {
        this.Players = Players;
    }

    public static class Players {
        /**
         * name : Sachin Ramesh Tendulkar
         * id : 1
         * photo : http://p.imgci.com/db/PICTURES/CMS/128400/128483.1.jpg
         * runs : 34347
         */

        private String name;
        private String id;
        private String photo;
        private String runs;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRuns() {
            return runs;
        }

        public void setRuns(String runs) {
            this.runs = runs;
        }
    }
}
