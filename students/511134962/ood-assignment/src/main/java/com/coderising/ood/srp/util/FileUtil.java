/**********************************************************************************************************************
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package com.coderising.ood.srp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil
{
    public FileUtil() { }

    public String[] readFile( File file ) throws IOException // @02C
    {
        BufferedReader br = null;
        try
        {
            br = new BufferedReader( new FileReader( file ) );
            String   temp = br.readLine();
            String[] data = temp.split( " " );
            br.close();
            return data;
        }
        catch ( IOException e )
        {
            throw new IOException( e.getMessage() );
        }
        finally
        {
            if ( null != br )
            {
                br.close();
            }
        }
    }
}