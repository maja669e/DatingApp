package com.example.demo.model;

import java.util.ArrayList;

public class CandidateList {

    private ArrayList<DatingUser> candidates = new ArrayList<>();

    public boolean removeCandidate(ArrayList<DatingUser> candidates, int id) {
        for (DatingUser datingUser : candidates) {
            if(datingUser.getID() == id){
                candidates.remove(datingUser);
                return true;
            }
        }
        return false;
    }

    //takes a list of alldatingusers and loop through it, to check if datingusers id equals the to id
    public boolean addCandidate(ArrayList<DatingUser> datingUsers, int id){
        for (DatingUser datingUser : datingUsers) {
            if(datingUser.getID() == id && !candidates.contains(datingUser)){
                System.out.println(candidates);
                candidates.add(datingUser);
                return true;
            }
        }
        return false;
    }

    //Loop through candidate list and check if id is equal
    public DatingUser getCandidate(ArrayList<DatingUser> datingUsers, int id){
        for (DatingUser datingUser : datingUsers) {
            if(datingUser.getID() == id){
                return datingUser;
            }
        }
        return null;
    }

    public ArrayList<DatingUser> getCandidates() {
        return candidates;
    }
/*
    public void addCandidate(int userid) {
        for (DatingUser datingUser : candidates) {
            if(datingUser.getID() == userid){
               candidates.add(datingUser);
            }
        }
    }

 */
}
