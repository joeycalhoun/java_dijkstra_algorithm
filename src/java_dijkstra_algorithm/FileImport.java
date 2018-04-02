/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_dijkstra_algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Joey
 */

public class FileImport {
    String file_path;
    
    public FileImport(String file_path)
    {
        this.file_path = file_path;
    }
    
    public String[] openFile() throws FileNotFoundException, IOException
    {
        FileReader fr = new FileReader(file_path);
        BufferedReader textReader = new BufferedReader(fr);
        int numberOfLines = readLines();
        String[] textData = new String[numberOfLines];
        for(int i = 0; i < numberOfLines; i++)
        {
            textData[i] = textReader.readLine();
        }
        textReader.close();
        return textData;
    }
    
    public int readLines() throws IOException
    {
        FileReader file_to_read = new FileReader(file_path);
        BufferedReader bf = new BufferedReader(file_to_read);
        String a_line;
        int numberOfLines = 0;
        while((a_line = bf.readLine()) != null)
        {
            numberOfLines++;
        }
        bf.close();
        return numberOfLines;
    }
}
