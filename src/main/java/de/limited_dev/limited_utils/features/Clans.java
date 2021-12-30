package de.limited_dev.limited_utils.features;

import de.limited_dev.limited_utils.utils.ClanFiles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.*;

public class Clans {

    private static List<String> clans = new ArrayList<String>();
    private static HashMap<UUID, String> clan = new HashMap<UUID, String>();
    private static HashMap<String, UUID> owner = new HashMap<String, UUID>();
    private static HashMap<String, List<UUID>> members = new HashMap<String, List<UUID>>();
    private static HashMap<String, List<UUID>> recruits = new HashMap<String, List<UUID>>();

    private static HashMap<String, Long> created = new HashMap<String, Long>();
    private static HashMap<String, List<String>> joined = new HashMap<String, List<String>>();

    public static HashMap<Player, String> invites = new HashMap<Player, String>();

    public static void loadClans() {
        ConfigurationSection conf = ClanFiles.config.getConfigurationSection("users");
        if (conf != null) {
            for (String s : conf.getKeys(false)) {
                clan.put(UUID.fromString(s), conf.getString(s));
            }
        }
        conf = ClanFiles.config.getConfigurationSection("clans");
        if (conf != null) {
            for (String c : conf.getKeys(false)) {
                clans.add(c);
                owner.put(c, UUID.fromString(conf.getString(c + ".owner")));
                created.put(c, conf.getLong(c + ".created"));
                List<UUID> cMembers = new ArrayList<UUID>();
                if (conf.getStringList(c + ".members").size() > 0) {
                    for (String s : conf.getStringList(c + ".members")) {
                        cMembers.add(UUID.fromString(s));
                    }
                }
                members.put(c, cMembers);
                List<UUID> cRecruits = new ArrayList<UUID>();
                if (conf.getStringList(c + ".recruits").size() > 0) {
                    for (String s : conf.getStringList(c + ".recruits")) {
                        cRecruits.add(UUID.fromString(s));
                    }
                }
                recruits.put(c, cRecruits);
                joined.put(c, conf.getStringList(c + ".joined"));
            }
        }
    }

    public static void saveClans() {
        ClanFiles.config.set("users", null);
        ClanFiles.config.set("clans", null);
        if (clans.size() > 0) {
            for (UUID u : clan.keySet()) {
                ClanFiles.config.set("users." + u.toString(), clan.get(u));
            }
            for (String c : clans) {
                ClanFiles.config.set("clans." + c + ".owner", owner.get(c).toString());
                ClanFiles.config.set("clans." + c + ".created", created.get(c));
                List<String> cMembers = new ArrayList<String>();
                if (members.get(c).size() > 0) {
                    for (UUID u : members.get(c)) {
                        cMembers.add(u.toString());
                    }
                }
                ClanFiles.config.set("clans." + c + ".members", cMembers);
                List<String> cRecruits = new ArrayList<String>();
                if (recruits.get(c).size() > 0) {
                    for (UUID u : recruits.get(c)) {
                        cRecruits.add(u.toString());
                    }
                }
                ClanFiles.config.set("clans." + c + ".recruits", cRecruits);
                ClanFiles.config.set("clans." + c + ".joined", joined.get(c));
            }
        }
        try {
            ClanFiles.config.save(ClanFiles.file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void createClan(Player p, String c){
        if(!hasClan(p)){
            if(!isNameTaken(c)){
                if(c.length() <= 20){
                        UUID u = p.getUniqueId();
                        clans.add(c);
                        clan.put(u, c);
                        owner.put(c, u);
                        members.put(c, new ArrayList<UUID>());
                        recruits.put(c, new ArrayList<UUID>());
                        joined.put(c, Arrays.asList(p.getUniqueId().toString()+";"+System.currentTimeMillis()));
                        created.put(c, System.currentTimeMillis());
                        p.sendMessage(ChatColor.GREEN + "The Clan " + c + " was created by " + p.getName() + ".");
                }else{
                    p.sendMessage(ChatColor.RED + "Sorry, but the name of the clan cannot be longer the 20 characters.");
                }
            }else{
                p.sendMessage(ChatColor.RED + "Sorry, this name has already been taken. Try another one.");
            }
        } else{
            p.sendMessage(ChatColor.RED + "Sorry, you are already in a Clan. Leave and try again.");
        }
    }

    public static void deleteClan(Player p, String c){
        if(p.getUniqueId().equals(getOwner(c))){
            clans.remove(c);
            clan.remove(owner.get(c));
            List<UUID> Cmembers = getMembers(c);
            if(Cmembers.size() > 0){
                for (UUID u : Cmembers){
                    clan.remove(u);
                }
            }
            List<UUID> Crecruits = getRecruits(c);
            if (Crecruits.size() > 0) {
                for(UUID u : Crecruits){
                    clan.remove(u);
                }
            }
            owner.remove(c);
            members.remove(c);
            recruits.remove(c);
            Bukkit.broadcastMessage(ChatColor.RED + ChatColor.BOLD.toString() + "The clan " + c + " was deleted by " + p.getName() + ".");
            return;
        }
        p.sendMessage(ChatColor.RED + "You cannnot delete your Clan, because you are not the Owner. Try leaving.");
    }

    public static void joinClan(Player p, String c){
        if(hasClan(p)){
            p.sendMessage(ChatColor.RED + "Sorry, but you are already in a clan");
            return;
        }
        List<UUID> cRecruits = getRecruits(c);
        UUID u = p.getUniqueId();
        clan.put(u, c);
        cRecruits.add(u);
        recruits.put(c, cRecruits);
        List<String> cJoined = new ArrayList<String>();
        cJoined.addAll(joined.get(c));
        cJoined.add(u.toString() + ";" + System.currentTimeMillis());
        joined.put(c, cJoined);
        p.sendMessage(p.getName() + " joined " + c);
    }

    public static void leaveClan(Player p, String c){
        if(!hasClan(p)){
            p.sendMessage(ChatColor.RED + "Sorry, but you are not in a clan.");
            return;
        }
        UUID u = p.getUniqueId();
        String us = u.toString();
        clan.remove(u);
        if(isMember(p, c)){
            List<UUID> cMembers = getMembers(c);
            cMembers.remove(u);
            members.put(c, cMembers);
        }else{
            List<UUID> cRecruits = getRecruits(c);
            cRecruits.remove(u);
            recruits.put(c, cRecruits);
        }
        List<String> cJoined = new ArrayList<String>();
        cJoined.addAll(joined.get(c));
        for(String s : cJoined){
            String[] str = s.split(";");
            if(str[0].equals(us)){
                cJoined.remove(s);
                break;
            }
        }
        joined.put(c, cJoined);
        p.sendMessage(p.getName() + " left " + c);
    }

    public static void kick(Player kicker, Player kicked, String c){
        if(!isOwner(kicker, getClan(kicker))){
            kicker.sendMessage(ChatColor.RED + "You do not have the permission to kick " + kicked.getName());
            return;
        }
        UUID u = kicked.getUniqueId();
        String us = u.toString();
        clan.remove(u);
        if(isMember(kicked, c)){
                List<UUID> cMembers = getMembers(c);
                cMembers.remove(u);
                members.put(c, cMembers);
        }else if (isRecruit(kicked, c)){
            List<UUID> cRerecruits = getRecruits(c);
            cRerecruits.remove(u);
            recruits.put(c, cRerecruits);
        } else{
            kicker.sendMessage(ChatColor.RED + "This player is not a member of your clan.");
            return;
        }
        List<String> cJoined = new ArrayList<String>();
        cJoined.addAll(joined.get(c));
        for(String s : cJoined){
            String[] str = s.split(";");
            if(str[0].equals(us)){
                cJoined.remove(s);
                kicked.sendMessage(ChatColor.RED + "You have been kicked from " + c);
                break;
            }
        }
        joined.put(c, cJoined);
        kicker.sendMessage(ChatColor.RED + kicked.getName() + " was kicked from " + c + " by " + kicker.getName());
    }

    public static void promote(Player p, String c){
        if(isRecruit(p, c)){
            UUID u = p.getUniqueId();
            List<UUID> cRecruits = getRecruits(c);
            cRecruits.remove(u);
            recruits.put(c, cRecruits);
            List<UUID> cMembers = getMembers(c);
            cMembers.add(u);
            members.put(c, cMembers);
            p.sendMessage(ChatColor.GOLD + "^ You have been promoted!");
        }
    }

    public static void demote(Player p, String c){
        if(isMember(p, c)){
          UUID u = p.getUniqueId();
          List<UUID> cMembers = getMembers(c);
          cMembers.remove(u);
          members.put(c, cMembers);
          List<UUID> cRecruits = getRecruits(c);
          cRecruits.add(u);
          recruits.put(c, cRecruits);
          p.sendMessage(ChatColor.RED + "v You have been demoted!");
        }
    }

    public static boolean hasClan(Player p){
        if(clan.containsKey(p.getUniqueId())){
            return true;
        }return false;
    }

    public static String getClan(Player p){
        if(hasClan(p)){
            return clan.get(p.getUniqueId());
        }
        return null;
    }

    public static UUID getOwner(String c){
        return owner.get(c);
    }

    public static long getCreated(String c){
        return created.get(c);
    }

    public static List<UUID> getMembers(String c){
        List<UUID> l = new ArrayList<UUID>();
        if(members.get(c).size()>0){
            l.addAll(members.get(c));
        }
        return l;
    }

    public static List<UUID> getRecruits(String c){
        List<UUID> l = new ArrayList<UUID>();
        if(recruits.get(c).size()>0){
            l.addAll(recruits.get(c));
        }
        return l;
    }

    public static boolean isMember(Player p, String c) {
        List<UUID> l = getMembers(c);
        if(l.size() > 0 && l.contains(p.getUniqueId())){
            return true;
        }
        return false;
    }

    public static boolean isRecruit(Player p, String c) {
        List<UUID> l = getRecruits(c);
        if (l.size() > 0 && l.contains(p.getUniqueId())) {
            return true;
        }
        return false;
    }

    public static long getJoinTime(Player p, String c){
        List<String> l = joined.get(c);
        String u = p.getUniqueId().toString();
        for(String s: l){
            String[] str = s.split(";");
            if(str[0].equals(u)){
                return Long.valueOf(str[1]);
            }
        }
        return -1;
    }

    public static boolean isOwner(Player p, String c){
        if(getOwner(c).equals(p.getUniqueId())){
            return true;
        }
        return false;
    }

    public static List<String> getMembersNames(String c){
        List<String> l = new ArrayList<String>();
        List<UUID> uuids = getMembers(c);
        if(uuids.size() > 0){
            for( UUID u : uuids){
                l.add(Bukkit.getPlayer(u).getName());
            }
        }
        return l;
    }
    public static List<String> getRecruitsNames(String c){
        List<String> l = new ArrayList<String>();
        List<UUID> uuids = getRecruits(c);
        if(uuids.size() > 0){
            for( UUID u : uuids){
                l.add(Bukkit.getPlayer(u).getName());
            }
        }
        return l;
    }

    public static boolean isNameTaken(String c){
        if(clans.size() > 0) {
            for (String s : clans) {
                if(s.equalsIgnoreCase(c)){
                    return true;
                }
            }
        }
        return false;
    }
}
