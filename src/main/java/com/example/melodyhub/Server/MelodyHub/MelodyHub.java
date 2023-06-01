package com.example.melodyhub.Server.MelodyHub;

import com.example.melodyhub.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MelodyHub {
    private User user;
    public static Connection connection;
    public static Gson gson;

    public MelodyHub(User user) {
        this.user = user;
    }

    public static synchronized ResultSet sendQuery(String query) {
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs;
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }
    public static String hashPassword(String password)
    {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }
    public static boolean checkPassword(String plain,String hashed)
    {
        return BCrypt.checkpw(plain,hashed);
    }
    public static User login(String username, String password) {
        ResultSet res = MelodyHub.sendQuery("select * from person where username ='"+username+"';");
        if(res==null)
        {
            return null;
        }
        try {
            if(!checkPassword(password,res.getString("pass")))
                return null;
            return new User(res.getString("id"),res.getString("username"),res.getString("pass"),res.getString("email"),res.getString("phone"),res.getString("image"),gson.fromJson(res.getString("queue"),new TypeToken<ArrayList<UUID>>(){}.getType()),res.getString("imageStory"),res.getString("gender"),res.getInt("age") ,gson.fromJson(res.getString("notifications"), new TypeToken<ArrayList<String>>(){}.getType()),gson.fromJson(res.getString("old_notification"), new TypeToken<ArrayList<String>>(){}.getType()),res.getBoolean("premium"));
        } catch (SQLException e) {
            return null;
        }
    }
    public static User findUser(UUID user)
    {
        ResultSet res = MelodyHub.sendQuery("select * from person where id ='"+user+"';");
        if(res==null)
        {
            return null;
        }
        try {
            return new User(res.getString("id"),res.getString("username"),res.getString("pass"),res.getString("email"),res.getString("phone"),res.getString("image"),gson.fromJson(res.getString("queue"),new TypeToken<ArrayList<UUID>>(){}.getType()),res.getString("imageStory"),res.getString("gender"),res.getInt("age") ,gson.fromJson(res.getString("notifications"), new TypeToken<ArrayList<String>>(){}.getType()),gson.fromJson(res.getString("old_notification"), new TypeToken<ArrayList<String>>(){}.getType()),res.getBoolean("premium"));
        } catch (SQLException e) {
            return null;
        }
    }

    public static Artist findArtist(UUID artist) {
        ResultSet res = MelodyHub.sendQuery("select * from artist where id = '" + artist + "';");
        if(res==null)
            return null;
        try {
            return new Artist(res.getString("id"),res.getString("username"),res.getString("pass"),res.getString("email"),res.getString("phone"),res.getString("image"),res.getBoolean("verify"),res.getString("bio"),res.getInt("listeners"),res.getDouble("rate"));
        } catch (SQLException e)
        {
            return null;
        }
    }

    public static Podcaster findPodcaster(UUID podcaster)
    {
        ResultSet res = MelodyHub.sendQuery("select * from podcaster where id = '" + podcaster + "';");
        if(res==null)
            return null;
        try {
            return new Podcaster(res.getString("id"),res.getString("username"),res.getString("pass"),res.getString("email"),res.getString("phone"),res.getString("image"),res.getBoolean("verify"),res.getString("bio"),res.getDouble("rate"));
        } catch (SQLException e)
        {
            return null;
        }
    }
    public static Song findSong(UUID song)
    {
        ResultSet res = MelodyHub.sendQuery("select * from song where id = '"+song+"'");
        if(res==null)
            return null;
        try {
            return new Song(res.getString("id"),res.getString("name"),res.getString("genre"),res.getDouble("duration"),res.getInt("year"),res.getDouble("rate"),res.getString("lyrics"));
        } catch (SQLException e) {
            return null;
        }
    }

    public static PlayList findPlaylist(UUID playlist)
    {
        ResultSet res = MelodyHub.sendQuery("select * from playlist where id ='"+playlist+"'");
        if(res==null)
            return null;
        try {
            return new PlayList(res.getString("id"),res.getBoolean("is_public static"),res.getDouble("rate"),res.getDouble("duration"),res.getString("artist"),res.getString("first_owner"));
        } catch (SQLException e) {
            return null;
        }
    }

    public static Podcast findPodcast(UUID podcast)
    {
        ResultSet res = MelodyHub.sendQuery("select * from song where id ='"+podcast+"'");
        if(res==null)
            return null;
        try {
            return new Podcast(res.getString("id"),res.getString("name"),res.getString("genre"),res.getDouble("duration"),res.getInt("year"),res.getDouble("rate"),res.getString("lyrics"),res.getString("description"));
        } catch (SQLException e) {
            return null;
        }
    }

    public void createPlaylist(PlayList playList)
    {
        MelodyHub.sendQuery(String.format("insert into playlists (id, duration, is_public, rate, artist, first_owner) VALUES (id = '%s', duration = %d , is_public = %b , rate = %.2f ,artist = '%s' , first_owner = '%s')",UUID.randomUUID(),playList.getDuration(),playList.isPersonal(),playList.getRate(),playList.getArtist(),playList.getFirstOwner()));
    }

    public void removePlaylist(UUID playlist)
    {

    }

    public static void createSong(Song song)
    {
        MelodyHub.sendQuery(String.format("insert into song (id, name, genre, duration, year, rate, lyrics) VALUES (id = '%s' , name= '%s', genre= '%s' , duration = %.2f , year = %d , rate = %.3f , lyrics = '%s');",UUID.randomUUID(),song.getName(),song.getGenre(),song.getDuration(),song.getYear(),song.getRate(),song.getLyrics()));
    }

    public static void removeSong(UUID song)
    {

    }

    public static void updateSong(UUID song , HashMap<String,String> command)
    {
        for(String column : command.keySet())
        {
            MelodyHub.sendQuery(String.format("update song set %s = %s where id = '%s';",column ,command.get(column),song));
        }
    }

    public static void createUser(User user)
    {
        MelodyHub.sendQuery(String.format("insert into person (id, username, pass, email, phone, gender, age) VALUES (id ='%s',username='%s',pass='%s',email='%s',phone='%s',gender='%s',age=%d);",UUID.randomUUID(),user.getUsername(),hashPassword(user.getPassword()),user.getEmail(),user.getPhoneNumber(),user.getGender(),user.getAge()));
    }

    public static void removeUser(UUID user)
    {

    }

    public static void updateUser(UUID user,HashMap<String,String> command)
    {
        for(String column : command.keySet())
        {
            MelodyHub.sendQuery(String.format("update person set %s = %s where id = '%s';",column ,command.get(column),user));
        }
    }

    public static void updatePass(String table,UUID account,String password)
    {
        MelodyHub.sendQuery(String.format("update %s set pass = %s where id = '%s';",table,hashPassword(password),account));
    }

    public static void createArtist(Artist artist)
    {
        MelodyHub.sendQuery(String.format("insert into artist (id, username, pass, email, phone, image, verify, bio, listeners, rate) VALUES (id='%s',username='%s',pass='%s',email='%s',phone='%s',image='%s',verify=%b,bio='%s',listeners=%d,rate=%.2f);",UUID.randomUUID(),artist.getUsername(),hashPassword(artist.getPassword()),artist.getEmail(),artist.getPhoneNumber(),artist.getImage(),artist.isVerify(),artist.getBio(),artist.getListeners(),artist.getRate()));
    }

    public static void removeArtist(UUID artist)
    {

    }

    public static void updateArtist(UUID Artist,HashMap<String,String> command)
    {
        for(String column : command.keySet())
        {
            MelodyHub.sendQuery(String.format("update artist set %s = %s where id = '%s';",column ,command.get(column),Artist));
        }
    }

    public static void createPodcaster(Podcaster podcaster)
    {
        MelodyHub.sendQuery(String.format("insert into podcaster (id, username, pass, email, phone, image, verify, bio, rate) values (id='%s', username='%s',pass='%s',email='%s',phone='%s',image='%s',verify=%b ,bio='%s',rate=%.2f);",UUID.randomUUID(),podcaster.getUsername(),hashPassword(podcaster.getPassword()),podcaster.getEmail(),podcaster.getPhoneNumber(),podcaster.getImage(),podcaster.isVerify(),podcaster.getBio(),podcaster.getRate()));
    }

    public static void removePodcaster(UUID podcaster)
    {

    }


    public static void updatePodcaster(UUID podcaster,HashMap<String,String> command)
    {
        for(String column : command.keySet())
        {
            MelodyHub.sendQuery(String.format("update podcaster set %s = %s where id = '%s';",column ,command.get(column),podcaster));
        }
    }

    public static void downloadSong(UUID song)
    {

    }
    public static ArrayList<ArrayList<UUID>> search(String searched)
    {
        ArrayList<ArrayList<UUID>> ret= new ArrayList<>();
        ArrayList<UUID> song = new ArrayList<>();
        ResultSet res=MelodyHub.sendQuery(String.format("SELECT id\n" +
                "FROM Song\n" +
                "WHERE name LIKE '%s'\n" +
                "   OR lyrics LIKE '%s'\n" +
                "    OR genre LIKE '%s'\n" +
                "    OR description LIKE '%s'",searched));
        while (true) {
            try {
                if (!res.next()) break;
                song.add(UUID.fromString(res.getString("id")));
            } catch (SQLException e) {
                break;
            }
        }
        ret.add(song);
        ArrayList<UUID> artists = new ArrayList<>();
        res = MelodyHub.sendQuery(String.format("SELECT id\n" +
                "FROM artist\n" +
                "WHERE username like '%s'\n" +
                "OR genre like '%s';\n",searched));
        while (true) {
            try {
                if (!res.next()) break;
                artists.add(UUID.fromString(res.getString("id")));
            } catch (SQLException e) {
                break;
            }
        }
        ret.add(artists);
        return ret;
    }
    public static ArrayList<UUID> getGenreArtist(String genre)
    {
        ArrayList<UUID> ret = new ArrayList<>();
        ResultSet res=MelodyHub.sendQuery(String.format("select id from artist where genre='%s';",genre));
        while (true) {
            try {
                if (!res.next()) break;
                ret.add(UUID.fromString(res.getString("id")));
            } catch (SQLException e) {
                break;
            }
        }
        return ret;
    }
    public static void logout()
    {

    }
}
