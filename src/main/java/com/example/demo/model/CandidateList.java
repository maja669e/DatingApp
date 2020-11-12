package com.example.demo.model;

import java.util.ArrayList;

public class CandidateList {

    ArrayList<DatingUser> candidates = new ArrayList<>();

    public boolean removeCandidate(ArrayList<DatingUser> candidates, int candidateNumber) {
        for (DatingUser datingUser : candidates) {
            if(datingUser.getID() == candidateNumber){
                candidates.remove(datingUser);
                return true;
            }
        }
        return false;
    }

    public boolean addCandidate(ArrayList<DatingUser> datingUsers, int id){
        for (DatingUser datingUser : datingUsers) {
            if(datingUser.getID() == id){
                candidates.add(datingUser);
                return true;
            }
        }
        return false;
    }

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
