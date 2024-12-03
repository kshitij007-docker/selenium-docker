package org.kshitijSelenium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private  static final Logger log= LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {


        log.debug("debug"); //we can use it if we define it in xml at root level
        log.info("info");
        log.error("error");
        log.warn("warn");
        add(2,3);
        }

        public static void add(int a,int b)
        {
            int result=a+b;
            log.info("{} + {} = {}",a,b,result); //curly braces are just placeholder
        }
    }
