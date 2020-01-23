package com.ds.mdb.service;

public interface SequenceService {
    Long getNextSequence(String inKey);
    Long getLastSequence(String inKey);
}
