package mc.server.survival.managers;

import mc.server.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class FileManager 
{
	private FileManager() {}

	static FileManager instance = new FileManager();
		
	public static FileManager getInstance() 
	{
		return instance;
	}
	
	Plugin plugin;
	
	FileConfiguration configuration;
	File data;
	
	public void start(Plugin var4) 
	{
		data = new File(var4.getDataFolder(), "data.yml");
		configuration = var4.getConfig();

		if (!var4.getDataFolder().exists()) 
		{
			var4.getDataFolder().mkdir();
			Logger.log("&aPomyslnie utworzono katalog plikow plug-inu!");
		}	
		try 
		{
			configuration.load(data);
			Logger.log("&aPomyslnie zaladowano plik &edata.yml!");
		} 
		catch (IOException | InvalidConfigurationException e1) 
		{
			Logger.log("&cNie mozna odnalezc pliku &edata.yml!");
			Logger.log("&fNie przejmuj sie, plug-in sprobuje stworzyc go samemu!");
		}
		
		if (!data.exists()) 
		{
			try 
			{
				data.createNewFile();
				Logger.log("&aPomyslnie stworzono plik &edata.yml!");
				
				try
				{
					configuration.load(data);
					Logger.log("&aPomyslnie zaladowano plik &edata.yml!");
				}
				catch (InvalidConfigurationException e) 
				{
					Logger.log("&cNie mozna zaladowac pliku &edata.yml. &cProsimy o zrestartowanie serwera!");
				}
				
				this.save();
			}
			catch (IOException e) 
			{
				Logger.log("&cNie mozna stworzyc pliku &edata.yml!");
			}
		}
	}
		
	public FileConfiguration configuration() 
	{
		return configuration;
	}
		
	public void save() 
	{
		try 
		{
			configuration.save(data);
		}
		catch (IOException e) 
		{
			Logger.log("&cNie mozna zapisac pliku &edata.yml!");
		}
	}
		
	public void restartKonfiguracji() { configuration = YamlConfiguration.loadConfiguration(data); }
}