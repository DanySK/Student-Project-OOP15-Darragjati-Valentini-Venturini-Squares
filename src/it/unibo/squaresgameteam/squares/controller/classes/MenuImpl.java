package controller.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.interfaces.Menu;
import controller.interfaces.ShowRanking;

public class MenuImpl implements Menu {

    private String rules;

    public MenuImpl() {
        this.rules = null;
    }

    public String rules() throws IOException {

        String txtDirectory = ClassLoader.class.getResource("/Rules.txt").getPath();
        //final FileReader in = new FileReader(txtDirectory);
        

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(txtDirectory), "UTF8"))) {
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                this.rules = this.rules + s;

            }
        } catch (IOException ex) {
            throw new IOException();
        }
        
        return this.rules;

    }

    public void exitApp() {
        System.exit(0);
    }

}
