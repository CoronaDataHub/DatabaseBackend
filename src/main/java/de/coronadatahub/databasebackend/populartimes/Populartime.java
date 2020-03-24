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

package de.coronadatahub.databasebackend.populartimes;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.coronadatahub.databasebackend.populartimes.xpath.XPathManager;
import de.coronadatahub.databasebackend.populartimes.xpath.enums.Day;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

public class Populartime {

    private XPathManager xPathManager;
    private WebClient client;

    public Populartime() {
        this.xPathManager = new XPathManager();
        init();
    }

    private void init() {
        client = new WebClient(new BrowserVersion.BrowserVersionBuilder(BrowserVersion.BEST_SUPPORTED).setUserAgent("Google").build());
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        client.getOptions().setJavaScriptEnabled(true);
        client.getOptions().setRedirectEnabled(true);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setCssEnabled(true);
        client.getOptions().setUseInsecureSSL(true);
        client.getCookieManager().setCookiesEnabled(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.setIncorrectnessListener((arg0, arg1) -> { });
    }

    private void getTime() {

    }

    public void getFromGoogle(String url) {
        try {
            Page page = client.getPage(new URL(url));
            if (page.isHtmlPage()) {
                HtmlPage htmlPage = ((HtmlPage) page);
                HtmlDivision div = htmlPage.getDocumentElement().getFirstByXPath(xPathManager.getXPathFromDay(Day.MONDAY));
                div.getChildNodes().forEach(domNode -> {
                    if (domNode.getAttributes().getNamedItem("jsinstance") != null){
                        System.out.println(domNode.getAttributes().getNamedItem("aria-label").getTextContent());
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
