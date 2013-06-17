package com.untoldadventures.improvedfakeop;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ImprovedFakeOp extends JavaPlugin
{
	private File pluginFolder;
	private File configFile;
	public static FileConfiguration config;

	public void onEnable()
	{
		getCommand("fakeop").setExecutor(new FakeOpCommandExecutor(this));
		getServer().getPluginManager().registerEvents(new EventListener(this), this);
		
		pluginFolder = getDataFolder();
		configFile = new File(pluginFolder, "config.yml");
		config = new YamlConfiguration();
		if (!pluginFolder.exists())
		{
			try
			{
				pluginFolder.mkdir();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		if (!configFile.exists())
		{
			try
			{
				configFile.createNewFile();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			config.load(configFile);
			config.save(configFile);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
