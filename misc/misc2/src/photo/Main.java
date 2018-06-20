/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package photo;

import static photo.Style.Filter.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Example program using photo album types.
 */
public class Main {
    
    /**
     * Create and save an album of vacation photos.
     * 
     * @param args unused
     * @throws MalformedURLException if a photo URL is invalid
     */
    public static void main(String[] args) throws MalformedURLException {
        
        ///////////////
        // Problem 1 //
        ///////////////
        
        final Style original = Style.original();
        
        // we can refer to style filters directly because of line 6:
        //     import static photo.Style.Filter.*;
        final Style sunshine = original.with(BRIGHTNESS, 25).with(SATURATE, 50);
        final Style raincloud = original.with(CONTRAST, -25).with(SATURATE, -25);
        
        System.out.println("sunshine = " + sunshine.toCSS());
        System.out.println("raincloud = " + raincloud.toCSS());
        
        ///////////////
        // Problem 2 //
        ///////////////
        
        final String mountSharp = "http://mars.nasa.gov/images/PIA16099u_Grotzinger2unannotated-br2.jpg";
        final Photo mars = Photo.create(new URL(mountSharp));
        final Photo sunnyMars = mars.withStyle(sunshine);
        final Photo rainyMars = mars.withStyle(raincloud);
        
        System.out.println("sunnyMars = " + sunnyMars.toHTML());
        System.out.println("rainyMars = " + rainyMars.toHTML());
        
        ///////////////
        // Problem 3 //
        ///////////////
        
        final PhotoAlbum vacation = PhotoAlbum.empty();
        final Page landscapes = vacation.addPage("Martian landscapes");
        landscapes.insert(mars, 0, 0);      // original photo on top row
        landscapes.insert(sunnyMars, 1, 0); // sunny photo on bottom left
        landscapes.insert(rainyMars, 1, 1); // rainy photo on bottom right
        
        System.out.println("Album");
        for (Page page : vacation) {
            System.out.println("  Page \"" + page.getTitle() + "\"");
            for (Photo photo : page) {
                System.out.println("    Photo " + photo);
            }
        }
        
        ///////////////
        // Problem 4 //
        ///////////////
        
        final File outputMars1 = new File("output/mars-1");
        
        if ( ! outputMars1.exists()) {
            System.err.println("Must create directory " + outputMars1.getAbsolutePath());
            return;
        }
        
        try {
            vacation.toHTML(outputMars1);
        } catch (IOException ioe) {
            System.err.println("Unable to save photo album HTML");
            ioe.printStackTrace();
            return;
        }
        System.out.println("Saved album in " + outputMars1);
        
        ///////////////
        // Problem 5 //
        ///////////////
        
        vacation.undo(); // remove rainyMars photo
        vacation.undo(); // remove sunnyMars photo
        landscapes.setTitle("The Martian landscape");
        
        final File outputMars2 = new File("output/mars-2");
        
        if ( ! outputMars2.exists()) {
            System.err.println("Must create directory " + outputMars2.getAbsolutePath());
            return;
        }
        
        try {
            vacation.toHTML(outputMars2);
        } catch (IOException ioe) {
            System.err.println("Unable to save photo album HTML");
            ioe.printStackTrace();
            return;
        }
        System.out.println("Saved updated album in " + outputMars2);
    }
}
