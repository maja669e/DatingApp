package com.example.demo.model;

import java.util.*;

public class CandidateList {

    private List<DatingUser> candidates = new ArrayList<>();
    //private Set<DatingUser> candidates = new HashSet<>();

    public boolean removeCandidate(List<DatingUser> candidates, int id) {
        for (DatingUser datingUser : candidates) {
            if (datingUser.getID() == id) {
                candidates.remove(datingUser);
                return true;
            }
        }
        return false;
    }

    //takes a list of alldatingusers and loop through it, to check if datingusers id equals the to id
    public boolean addCandidate(DatingUser candidate) {
        if (candidates.isEmpty()) {
            candidates.add(candidate);
            return true;
        } else {
            for (int i = 0; i < candidates.size(); i++) {
                if (candidate.getID() == candidates.get(i).getID()) {
                    return false;
                }
            }
            candidates.add(candidate);
        }
        return false;
    }

    //Loop through candidate list and check if id is equal
    public DatingUser getCandidate(int id) {
        for (DatingUser datingUser : candidates) {
            if (datingUser.getID() == id) {
                return datingUser;
            }
        }
        return null;
    }

    public List<DatingUser> getCandidates() {
        return candidates;
    }
}
