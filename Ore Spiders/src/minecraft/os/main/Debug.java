package os.main;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class Debug
{

	private static boolean debugEnabled = false;

	public static void out(Object text)
	{
		if(debugEnabled) System.out.println("[" + (FMLCommonHandler.instance().getSide() == Side.CLIENT ? "CLIENT" : "SERVER") + "] " + text);
	}

}
