package com.chemicalsafety.infosafecsi_onmoveandroid.Entities;

import java.util.ArrayList;

public class loginVarMulti {

        public String mclientid;
        public String mclientname;
        public static ArrayList<loginVarMulti> clientList = new ArrayList<>();

        public loginVarMulti(String clientnamev, String clientidv) {
            mclientid = clientidv;
            mclientname = clientnamev;
        }


    public String getClientid() {
            return mclientid;
        }

        public String getClientname() {
            return mclientname;
        }
}
