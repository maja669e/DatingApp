package com.example.demo.model;

import java.util.ArrayList;

public class CandidateList {

    ArrayList<DatingUser> candidates = new ArrayList<>();


    public void removeCandidate(int candidateNumber) {
        candidates.remove(candidateNumber);
    }

    public void addCandidate(DatingUser user) {
        candidates.add(user);
    }


    public ArrayList<DatingUser> getCandidates() {
        return candidates;
    }

}
