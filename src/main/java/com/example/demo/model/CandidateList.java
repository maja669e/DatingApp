package com.example.demo.model;

import java.util.ArrayList;

public class CandidateList {

    ArrayList<DatingUser> candidates = new ArrayList<>();

    public void removeCandidate(int candidateNumber) {
        candidates.remove(candidateNumber);
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

    public ArrayList<DatingUser> getCandidates() {
        return candidates;
    }

}
