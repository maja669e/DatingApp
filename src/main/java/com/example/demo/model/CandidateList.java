package com.example.demo.model;

import java.util.ArrayList;

public class CandidateList {

    static ArrayList<DatingUser> candidates = new ArrayList<>();

    public static boolean removeCandidate(ArrayList<DatingUser> candidates, int candidateNumber) {
        for (DatingUser datingUser : candidates) {
            if(datingUser.getID() == candidateNumber){
                candidates.remove(datingUser);
                return true;
            }
        }
        return false;
    }

    public static boolean addCandidate(ArrayList<DatingUser> datingUsers, int id){
        for (DatingUser datingUser : datingUsers) {
            if(datingUser.getID() == id){
                candidates.add(datingUser);
                return true;
            }
        }
        return false;
    }

    public static DatingUser getCandidate(ArrayList<DatingUser> datingUsers, int id){
        for (DatingUser datingUser : datingUsers) {
            if(datingUser.getID() == id){
                return datingUser;
            }
        }
        return null;
    }

    public static ArrayList<DatingUser> getCandidates() {
        return candidates;
    }

}
