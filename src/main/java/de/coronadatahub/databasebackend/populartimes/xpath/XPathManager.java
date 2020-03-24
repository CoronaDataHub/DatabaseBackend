/*
 * *
 *    ____                                          ____            _             _   _           _
 *   / ___|   ___    _ __    ___    _ __     __ _  |  _ \    __ _  | |_    __ _  | | | |  _   _  | |__
 *  | |      / _ \  | '__|  / _ \  | '_ \   / _` | | | | |  / _` | | __|  / _` | | |_| | | | | | | '_ \
 *  | |___  | (_) | | |    | (_) | | | | | | (_| | | |_| | | (_| | | |_  | (_| | |  _  | | |_| | | |_) |
 *   \____|  \___/  |_|     \___/  |_| |_|  \__,_| |____/   \__,_|  \__|  \__,_| |_| |_|  \__,_| |_.__/
 *
 *  	CoronaDataHub ist ein Projekt welches im Rahmen von der Initiative #WirVSVirus-Hackathon vom 20-22 März 2020 ins Leben gerufen wurde.
 *
 *
 */

package de.coronadatahub.databasebackend.populartimes.xpath;

import de.coronadatahub.databasebackend.populartimes.xpath.enums.Day;

public class XPathManager {

    public static String SUN_0 = "//*[@id=\"pane\"]/div/div[1]/div/div/div[18]/div[3]/div[1]";
    //*[@id="pane"]/div/div[1]/div/div/div[18]/div[3]/div[3]/div[2]


    public String getXPathFromDay(Day day) {
        return "//*[@id=\"pane\"]/div/div[1]/div/div/div[18]/div[3]/div[" + day.getId() + "]";
        //return "#pane > div > div.widget-pane-content.scrollable-y > div > div > div.section-popular-times > div.section-popular-times-container > div:nth-child(" + day.getId() + ")";
    }

}
