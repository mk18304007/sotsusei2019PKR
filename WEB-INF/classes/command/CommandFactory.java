package command;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;

import context.RequestContext;

public class CommandFactory{
	private CommandFactory(){}
	public static AbstractCommand getCommand(RequestContext rc){
		AbstractCommand command=null;
		
		try{
			Properties prop=new Properties();
			
			prop.load(new FileInputStream("c:/webapps/impaku/WEB-INF/classes/commands.properties"));
			System.out.println("CommandFactory.getCommand()_rc.getCommandPath():"+rc.getCommandPath());
			String name=prop.getProperty(rc.getCommandPath());
			System.out.println("CommandFactory.getCommand()"+name+"が呼ばれたよ");
			
			Class c=Class.forName(name);
			
			command=(AbstractCommand)c.newInstance();
		}catch(FileNotFoundException e){
			throw new RuntimeException(e.getMessage(),e);
		}catch(IOException e){
			throw new RuntimeException(e.getMessage(),e);
		}catch(InstantiationException e){
			throw new RuntimeException(e.getMessage(),e);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e.getMessage(),e);
		}catch(IllegalAccessException e){
			throw new RuntimeException(e.getMessage(),e);
		}
		return command;
	}
}