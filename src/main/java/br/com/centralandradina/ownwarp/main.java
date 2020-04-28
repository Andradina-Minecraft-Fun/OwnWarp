package br.com.centralandradina.ownwarp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.api.IWarps;

public class main extends JavaPlugin implements Listener
{
	@Override
	public void onEnable()
	{
		// Registra o evento
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
    public void onDisable()
    {
		
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String commandName = cmd.getName().toLowerCase();
    	
		if (!(sender instanceof Player)) {
			sender.sendMessage("O comando deve ser executado por um player");
			return false;
		}
		
		Player player = (Player)sender;
		
    	// discord
    	if(commandName.equals("ownwarp")) {
    		
    		// Verifica se possui argumentos
    		if(args.length == 0) {
    			sender.sendMessage("Utilize /ownwarp create/delete dependendo da sua permissão");
    			return false;
    		}
    		
    		// Verifica se é create ou delete
    		if((!args[0].equalsIgnoreCase("create")) && (!args[0].equalsIgnoreCase("delete"))) {
    			sender.sendMessage("Utilize /ownwarp create/delete dependendo da sua permissão");
    			return false;
    		}
    		
    		// Verifica se tem permissão
    		if(!sender.hasPermission("ownwarp")) {
    			sender.sendMessage("Você não pode executar isso");
    			return false;
    		}
    		
    		// Recupera o nome do player
    		String playerName = sender.getName();
    		
    		Plugin plugin_es = Bukkit.getPluginManager().getPlugin("Essentials");
    		if(!(plugin_es instanceof Essentials)) {
    			sender.sendMessage("Problemas com Essentials");
    			return false;
    		}
    		Essentials ess = (Essentials) plugin_es;
    		final IWarps warps = ess.getWarps();
    		
    		// Verifica se é criação
    		if(args[0].equalsIgnoreCase("create")) {
    			Location location = player.getLocation();
    			try {
					warps.setWarp(playerName, location);
					sender.sendMessage("/warp " + playerName + " criado");
				} catch (Exception e) { }
    		}
    		
    		// Verifica se é delete
    		if(args[0].equalsIgnoreCase("delete")) {
    			try {
					warps.removeWarp(playerName);
					sender.sendMessage("/warp " + playerName + " deletado");
				} catch (Exception e) { }
    		}
    		
    	}
    	
		return false;
	}
}
