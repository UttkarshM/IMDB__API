package com.example.demo.WebRequests;

import com.example.demo.Database.Entries;

public interface Rest {
    Entries entry = new Entries();
    void getImdbData() throws Exception;
}
