package com.untoldadventures.improvedfakeop;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FakeOpCommandExecutor implements CommandExecutor
{
	Plugin plugin;

	public FakeOpCommandExecutor(Plugin plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (args.length == 1)
		{
			if (sender.hasPermission("fakeop.use"))
			{
				String pName = args[0];
				Player player = Bukkit.getPlayer(pName);
				if (player.isOnline())
				{

					List<String> players = ImprovedFakeOp.config.getStringList("players.list");
					if (!players.contains(pName))
					{
						players.add(pName);
						ImprovedFakeOp.config.set("players.list", players);
						if (sender instanceof Player)
						{
							sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "FakeOp" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + " Player FakeOPed!");
						} else
						{
							sender.sendMessage("[" + "FakeOp" + "]" + " Player FakeOPed!");
						}
						player.sendMessage(ChatColor.YELLOW + "You are now op!");
						return true;
					}
					if (players.contains(pName))
					{
						players.remove(pName);
						ImprovedFakeOp.config.set("players.list", players);
						if (sender instanceof Player)
						{
							sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "FakeOp" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + " Player no longer FakeOPed!");
						} else
						{
							sender.sendMessage("[" + "FakeOp" + "]" + " Player no longer FakeOPed!");
						}
						player.sendMessage(ChatColor.YELLOW + "You are no longer op!");
						return true;
					}
				}
				if (sender instanceof Player)
				{
					sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "FakeOp" + ChatColor.DARK_GRAY + "]" + ChatColor.YELLOW + "That player isn't online!");
				} else
				{
					sender.sendMessage("[" + "FakeOp" + "]" + "That player isn't online!");
				}
				return true;
			}
			return true;
		}
		return false;
	}

}
