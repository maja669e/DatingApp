package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CandidateListTest {

    @Test
    void removeCandidateFromList() {

        //Arrange
        CandidateList candidateList = new CandidateList();
        DatingUser datingUser = new DatingUser("Nicolai", "blabla@gmail.com", "1", LocalDate.of(1996,02,01), "datinguser", "","",0);
        datingUser.setID(1);
        candidateList.getCandidates().add(datingUser);
        //Act
        candidateList.removeCandidate(candidateList.getCandidates(),1);
        //Assert
        assertTrue(candidateList.getCandidates().isEmpty());
    }

    @Test
    void addCandidate() {
        //Arrange
        CandidateList candidateList = new CandidateList();
        DatingUser datingUser = new DatingUser("Nicolai", "blabla@gmail.com", "1", LocalDate.of(1996,02,01), "datinguser", "","",0);
        datingUser.setID(1);
        //Act
        candidateList.addCandidate(datingUser);
        //Assert
        assertFalse(candidateList.getCandidates().isEmpty());
    }

    @Test
    void getCandidate() {
        //Arrange
        CandidateList candidateList = new CandidateList();
        DatingUser datingUser = new DatingUser("Nicolai", "blabla@gmail.com", "1", LocalDate.of(1996,02,01), "datinguser", "","",0);
        datingUser.setID(1);
        candidateList.addCandidate(datingUser);

        //Act
        DatingUser candidate = candidateList.getCandidate(1);
        //Assert
        assertEquals(candidate, candidateList.getCandidates().get(0));
    }
}