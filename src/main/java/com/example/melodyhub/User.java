package com.example.melodyhub;

import com.example.melodyhub.Server.MelodyHub.MelodyHub;

import java.util.ArrayList;
import java.util.UUID;

public class User extends Account{
    private ArrayList<UUID> queue;
    private String imageStory;
    private String gender;
    private int age;
    private Song currentPlay;
    private boolean premium;
    private  ArrayList<String> oldNotification;
    private  ArrayList<String> notification;

    public User(String uuid, String username, String password, String email, String phoneNumber, String image, String imageStory, String gender, int age, boolean premium) {
        super(uuid, username, password, email, phoneNumber, image);
        this.imageStory = imageStory;
        this.gender = gender;
        this.age = age;
        this.premium = premium;
        oldNotification=new ArrayList<>();
        notification = new ArrayList<>();
        queue=new ArrayList<>();
    }

    public User(String uuid, String username, String password, String email, String phoneNumber, String image, ArrayList<UUID> queue, String imageStory, String gender, int age, ArrayList<String> oldNotification, ArrayList<String> notification, boolean premium) {
        super(uuid, username, password, email, phoneNumber, image);
        this.queue = queue;
        this.imageStory = imageStory;
        this.gender = gender;
        this.age = age;
        this.premium = premium;
        this.oldNotification = oldNotification;
        this.notification = notification;
    }


    public ArrayList<UUID> getQueue() {
        return queue;
    }

    public void addQueue(UUID song)
    {
        queue.add(song);
    }

    public void setQueue(ArrayList<UUID> queue) {
        this.queue = queue;
    }

    public void removeQueue(UUID song) {
        queue.remove(song);
    }
    public void setAge(int age)
    {
        this.age=age;
        MelodyHub.sendQuery("update person set age = "+age+" where id = '"+getId()+"';");
    }

    public int getAge()
    {
        return age;
    }

    public String getImageStory() {
        return imageStory;
    }

    public void setImageStory(String imageStory) {
        this.imageStory = imageStory;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Song getCurrentPlay() {
        return currentPlay;
    }

    public void setCurrentPlay(Song currentPlay) {
        this.currentPlay = currentPlay;
    }

    public void addNotification(String notification)
    {
        this.notification.add(notification);
    }

    public ArrayList<String> getOldNotification() {
        return oldNotification;
    }

    public ArrayList<String> getNotification() {
        return notification;
    }
    public String seeNotification()
    {
        if(notification.size()==0)
            return null;
        String notif = notification.get(0);
        oldNotification.add(notif);
        notification.remove(0);
        return notif;

    }
    @Override
    public String toString() {
        return "User{" +
                "currentPlay=" + currentPlay +
                "} " + super.toString();
    }
}
