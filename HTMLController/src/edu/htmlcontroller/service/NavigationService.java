package edu.htmlcontroller.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
public class NavigationService {
    public static final String FROM_PAGE_ATTRIBUTE = "from-page";
    private List<NavigationRule> rules;

    public NavigationService(List<NavigationRule> rules) {
        this.rules = rules;
    }

    public static NavigationService fromFile(String filePath, ServletContext context) throws IOException {
        try (InputStreamReader streamReader = new InputStreamReader(context.getResourceAsStream(filePath));
             BufferedReader reader = new BufferedReader(streamReader)) {
            return new NavigationService(readNavigationRules(reader));
        }
    }

    private static List<NavigationRule> readNavigationRules(BufferedReader reader) throws IOException {
        String line;
        List<NavigationRule> rules = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            List<String> navigationRules = Arrays.asList(line.split("[ \t]*,[ \t]*"));
            rules.add(new NavigationRule(navigationRules));
        }

        return rules;
    }

    public String getToPage(HttpServletRequest request) {
        String fromPage = (String) request.getSession().getAttribute(FROM_PAGE_ATTRIBUTE);
        String action = request.getPathInfo().replaceAll("[^a-zA-Z]*", "");

        if (fromPage == null) {
            return redirectToPage(request, rules.get(0));
        }
        return redirectToPage(request, getNavigationRule(fromPage, action));
    }

    private NavigationRule getNavigationRule(String fromPage, String action) {
        for (NavigationRule rule : rules) {
            if (rule.getAction().equals(action) && rule.getFrom().equals(fromPage)) {
                return rule;
            }
        }
        throw new IllegalArgumentException("No rule found");
    }

    private String redirectToPage(HttpServletRequest request, NavigationRule navigationRule) {
        request.getSession().setAttribute(FROM_PAGE_ATTRIBUTE, navigationRule.getTo());
        return navigationRule.getTo();
    }
}

class NavigationRule {
    private String from;
    private String to;
    private String action;

    NavigationRule(List<String> navigationRules) {
        if (navigationRules.size() < 3) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        from = navigationRules.get(0);
        action = navigationRules.get(1);
        to = navigationRules.get(2);
    }

    NavigationRule(String from, String to, String action) {
        this.from = from;
        this.to = to;
        this.action = action;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
