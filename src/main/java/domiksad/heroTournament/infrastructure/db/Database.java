package domiksad.heroTournament.infrastructure.db;

import domiksad.heroTournament.domain.character.Player;
import domiksad.heroTournament.domain.items.Weapon;
import domiksad.heroTournament.domain.items.WeaponFactory;
import domiksad.heroTournament.domain.mechanics.Health;
import domiksad.heroTournament.domain.mechanics.Level;
import domiksad.heroTournament.domain.mechanics.Stats;
import domiksad.heroTournament.domain.ultimate.Ultimate;
import domiksad.heroTournament.domain.ultimate.UltimateFactory;

import java.sql.*;

public class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USER = "heros";
    private static final String PASSWORD = "heros";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void init() {
        String sql = """
            CREATE TABLE IF NOT EXISTS players (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                current_health INT NOT NULL,
                max_health INT NOT NULL,
                level INT NOT NULL,
                experience INT NOT NULL,
                damage INT NOT NULL,
                armor INT NOT NULL,
                weapon_type VARCHAR(50) NOT NULL,
                ultimate_type VARCHAR(50) NOT NULL,
                gold INT NOT NULL
            );
        """;

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getLatestSaveId() {
        String sql = "SELECT id FROM players ORDER BY id DESC LIMIT 1";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int save(Player player) {
        String sql = """
        INSERT INTO players
        (name, current_health, max_health, level,
         experience, damage, armor, weapon_type,
         ultimate_type, gold)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        RETURNING id;
        """;

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getHealth().getCurrent());
            stmt.setInt(3, player.getHealth().getMax());
            stmt.setInt(4, player.getLevel().getLevel());
            stmt.setInt(5, player.getLevel().getExperience());
            stmt.setInt(6, player.getStats().getDamage());
            stmt.setInt(7, player.getStats().getArmor());
            stmt.setString(8, player.getWeapon().getName());
            stmt.setString(9, player.getUltimate().getName());
            stmt.setInt(10, player.getGold());

            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Player load(int playerId) {
        String sqlPlayer = """
                    SELECT * FROM players WHERE id = ?;
                """;

        String sqlEffects = """
                    SELECT * FROM effects WHERE player_id = ?;
                """;

        try (Connection conn = Database.connect();
             PreparedStatement stmtPlayer = conn.prepareStatement(sqlPlayer)) {

            stmtPlayer.setInt(1, playerId);
            ResultSet rsPlayer = stmtPlayer.executeQuery();

            if (!rsPlayer.next()) {
                return null;
            }

            String name = rsPlayer.getString("name");
            int currentHealth = rsPlayer.getInt("current_health");
            int maxHealth = rsPlayer.getInt("max_health");
            int levelVal = rsPlayer.getInt("level");
            int experience = rsPlayer.getInt("experience");
            int damage = rsPlayer.getInt("damage");
            int armor = rsPlayer.getInt("armor");
            String weaponName = rsPlayer.getString("weapon_type");
            String ultimateName = rsPlayer.getString("ultimate_type");
            int gold = rsPlayer.getInt("gold");

            Health health = new Health(currentHealth, maxHealth);
            Level level = new Level(levelVal, experience);
            Stats stats = new Stats(damage, armor);

            Weapon weapon = null;
            for (var w : WeaponFactory.getWeaponList()) {
                if (w.getName().equals(weaponName)) {
                    weapon = w;
                    break;
                }
            }
            if (weapon == null) {
                throw new Exception("Weapon not recognised");
            }

            Ultimate ultimate = null;
            for (var u : UltimateFactory.getUltimateList()) {
                if (u.getName().equals(ultimateName)) {
                    ultimate = u;
                    break;
                }
            }
            if (ultimate == null) {
                throw new Exception("Ultimate not recognised");
            }

            Player player = new Player(name, health, level, stats, weapon, ultimate, gold);

            return player;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
