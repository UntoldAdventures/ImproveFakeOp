package com.untoldadventures.improvedfakeop;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class EventListener implements Listener
{
	Plugin plugin;

	public EventListener(Plugin plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		List<String> players = ImprovedFakeOp.config.getStringList("players.list");
		if (players.contains(event.getPlayer().getName()))
		{
			event.getPlayer().setFoodLevel(0);
			event.getPlayer().getLocation().getWorld().strikeLightning(event.getPlayer().getLocation());
		}
	}
}
