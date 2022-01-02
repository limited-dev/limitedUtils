package de.limited_dev.limited_utils_test.features.ClansCommand;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.MockPlugin;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import de.limited_dev.limited_utils.Main;
import org.bukkit.Bukkit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClansCommandTest {

    private ServerMock server;
    private Main plugin;
    private MockPlugin mockPlugin;
    private PlayerMock mockPlayer;

    @Before
    public void setUp()
    {
        System.out.println("Start setUp.\nSet server");
        server = MockBukkit.mock();
        System.out.println("Set plugin");
        plugin = (Main) MockBukkit.load(Main.class);
        System.out.println("Set mockPlugin");
        mockPlugin = MockBukkit.createMockPlugin();
        System.out.println("Set mockPlayer");
        mockPlayer = server.addPlayer("limited_dev");
        System.out.println("Done.");
    }

    @Test
    public void TestClanCommands(){
        System.out.println("Start Test Block\nTesting Clan info");
        System.out.println("End Test Block");
    }


    @After
    public void tearDown()
    {
        System.out.println("Start tearDown.\nRun cancelTasks");
        Bukkit.getScheduler().cancelTasks(plugin);
        System.out.println("Run unmock");
        MockBukkit.unmock();
        System.out.println("Done.\nEnd Test.");
    }
}
