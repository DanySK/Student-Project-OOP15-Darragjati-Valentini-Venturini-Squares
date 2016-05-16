package controller.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.interfaces.Menu;
import controller.interfaces.ShowRanking;

public class MenuImpl implements Menu {

    private String rules;

    public MenuImpl() {

    }

    public String rules() throws IOException {

        String txtDirectory = ClassLoader.class.getResource("/Rules.txt").getPath();
        InputStream in = ClassLoader.class.getResourceAsStream(txtDirectory);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF8"))) {
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                this.rules = this.rules.concat(s);

            }
        } catch (IOException ex) {
            throw new IOException();
        }
        in.close();
        return this.rules;

    }

    public void exitApp() {
        System.exit(0);
    }

}
