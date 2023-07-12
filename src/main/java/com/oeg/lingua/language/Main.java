/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oeg.lingua.language;

import com.github.pemistahl.lingua.api.io.*;
import com.github.pemistahl.lingua.api.*;
import java.nio.file.Paths;
import shadow.kotlin.text.Charsets;
import com.github.pemistahl.lingua.api.*;
import static com.github.pemistahl.lingua.api.Language.*;
/**
 *
 * @author mnavas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
    String path = System.getProperty("user.dir");
    //System.out.println("Path: " + path +  "\\gallego");
    LanguageModelFilesWriter.createAndWriteLanguageModelFiles(Paths.get(path + "\\gallego\\Gallego.txt"), Charsets.UTF_8,Paths.get(path + "\\gallego"),Language.GALICIAN,"\\p{L}");
    }
    
}
