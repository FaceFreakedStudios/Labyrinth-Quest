package com.facefreakedstudios.app.lq;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gavin17
 */
abstract class Art
{
    static void title() // created with http://www.figlet.org/
    {
        String ascii_title = " _          _                _       _   _        ___                  _\n" +
"| |    __ _| |__  _   _ _ __(_)_ __ | |_| |__    / _ \\ _   _  ___  ___| |_\n" +
"| |   / _` | '_ \\| | | | '__| | '_ \\| __| '_ \\  | | | | | | |/ _ \\/ __| __|\n" +
"| |__| (_| | |_) | |_| | |  | | | | | |_| | | | | |_| | |_| |  __/\\__ \\ |_\n" +
"|_____\\__,_|_.__/ \\__, |_|  |_|_| |_|\\__|_| |_|  \\__\\_\\\\__,_|\\___||___/\\__|\n" +
"                  |___/"; 
        System.out.print(ascii_title);
    }
}