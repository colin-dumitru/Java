package edu.filter.filter;

import de.java2html.converter.JavaSource2HTMLConverter;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import de.java2html.options.JavaSourceConversionOptions;
import de.java2html.options.JavaSourceStyleTable;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Catalin Dumitru
 * Universitatea Alexandru Ioan Cuza
 */
@WebFilter(urlPatterns = "/*")
public class PreFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        final ServletResponse originalResponse = servletResponse;
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);

        Object proxyResponse = Proxy.newProxyInstance(HttpServletResponse.class.getClassLoader(),
                new Class[]{HttpServletResponse.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("getWriter")) {
                            return printWriter;
                        } else {
                            return method.invoke(originalResponse, args);
                        }
                    }
                });
        filterChain.doFilter(servletRequest, (HttpServletResponse) proxyResponse);

        String initialFile = stringWriter.toString();
        String formattedFile = formatFile(initialFile);
        originalResponse.setContentType("text/html");
        originalResponse.getWriter().write(formattedFile);
    }

    private String formatFile(String initialFile) {
        Pattern prePattern = Pattern.compile("<pre[^>]*>(.+?)</pre\\s*>", Pattern.DOTALL | Pattern.MULTILINE);
        Matcher preMather = prePattern.matcher(initialFile);
        StringWriter output = new StringWriter();
        Integer lastEnd = 0;

        while (preMather.find()) {
            String originalSnippet = initialFile.substring(preMather.start(), preMather.end());
            originalSnippet = originalSnippet.replaceAll("<(/)?pre>", "");
            String formattedSnippet = formatSnippet(originalSnippet);
            output.write(initialFile.substring(lastEnd, preMather.start()));
            output.write(formattedSnippet);
            lastEnd = preMather.end();
        }

        if (lastEnd < initialFile.length()) {
            output.write(initialFile.substring(lastEnd));
        }

        return output.toString();
    }

    private String formatSnippet(String group) {
        JavaSource source = new JavaSourceParser().parse(group);
        StringWriter destination = new StringWriter();

        JavaSource2HTMLConverter converter = new JavaSource2HTMLConverter();
        try {
            JavaSourceConversionOptions options = JavaSourceConversionOptions.getDefault();
            options.setStyleTable(JavaSourceStyleTable.getDefaultEclipseStyleTable());
            converter.convert(source, options, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination.toString();
    }

    @Override
    public void destroy() {

    }
}
