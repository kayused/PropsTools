package com.kevinserver.test;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try {
			InitProp.load();
			
			System.out.println(InitProp.DB_TYPE);
			System.out.println(InitProp.CFG_DEV_MODE);
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
    }
}
