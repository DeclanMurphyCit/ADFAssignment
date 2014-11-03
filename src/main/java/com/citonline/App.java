package com.citonline;

import com.citonline.interfaces.impl.LecturerImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        LecturerImpl li = new LecturerImpl("firstName", "lastName", "email@mail.com", "0123456789", "C123");
    }
}
