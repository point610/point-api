package com.point;

import com.point.apisdk.client.PointApiClient;

public class Main {
    public static void main(String[] args) {
        PointApiClient pointApiClient = new PointApiClient("11a752a94adeb492a040854dad54902c", "bb31046ce681ec922e9f9ecf0a46b21b");
        System.out.println(pointApiClient.getRandomBoringTalk());
    }
}