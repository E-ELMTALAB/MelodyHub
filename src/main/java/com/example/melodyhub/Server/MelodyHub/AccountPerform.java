package com.example.melodyhub.Server.MelodyHub;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class AccountPerform {
   public static ArrayList<UUID> getFollowings(UUID Id)
    {
        ArrayList<UUID> ret = new ArrayList<>();
        ResultSet res = MelodyHub.sendQuery("select user1id from follow where user2id='"+Id+"';");
        while (true) {
            try {
                if (!res.next()) break;
                ret.add(UUID.fromString(res.getString("user1id")));
            } catch (SQLException e) {
                break;
            }
        }
        return ret;
    }

   public static ArrayList<UUID> getFollowers(UUID Id)
    {
        ArrayList<UUID> ret = new ArrayList<>();
        ResultSet res = MelodyHub.sendQuery("select user2id from follow where user1id='"+Id+"';");
        while (true) {
            try {
                if (!res.next()) break;
                ret.add(UUID.fromString(res.getString("user2id")));
            } catch (SQLException e) {
                break;
            }
        }
        return ret;
    }

   public static void follow(UUID Id,UUID user)
    {
        MelodyHub.sendQuery("insert into follow (user1id, user2id) VALUES (user1id = '"+Id+"' , user2id ='"+user+"');");
    }

   public static void unfollow(UUID Id,UUID user) {
        MelodyHub.sendQuery("delete from follow where user1id ='"+Id+"' and user2id='"+user+"';");
    }
   public static void addPlaylist(UUID Id,UUID playlist)
    {
        MelodyHub.sendQuery("insert into playlist_owning (playlistid, ownerid) values (playlistid = '"+playlist+"' , ownerid = '"+Id+"');");
    }

   public static void removePlaylist(UUID Id,UUID playlist) {
        MelodyHub.sendQuery("delete from playlist_owning where ownerid='"+Id+"' and playlistid ='"+playlist+"';");
    }

   public static ArrayList<UUID> getPlaylists(UUID Id)
    {
        ArrayList<UUID> ret = new ArrayList<>();
        ResultSet res = MelodyHub.sendQuery("select playlistid from playlist_owning where ownerid ='"+Id+"';");
        while (true) {
            try {
                if (!res.next()) break;
                ret.add(UUID.fromString(res.getString("playlistid")));
            } catch (SQLException e) {
                break;
            }
        }
        return ret;
    }
}
