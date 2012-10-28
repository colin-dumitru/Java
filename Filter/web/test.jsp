<%--
  Catalin Dumitru
  Universitatea Alexandru Ioan Cuza
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Filter Example</title>
</head>
<body>
<h3>First Sample</h3>
<pre>

@WebServlet(urlPatterns = "/*")
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uriParams[] = request.getRequestURI().split("\\s");
        if(uriParams.length > 1) {
            writeFile(uriParams[1], response.getWriter());
        } else {
            response.getWriter().write("No file found");
        }
    }

    private void writeFile(String fileName, PrintWriter writer) {
        try {
            try(InputStream inputStream = getClass().getResourceAsStream(fileName)){
                byte[] inputBuffer = new byte[inputStream.available()];
                inputStream.read(inputBuffer);
                String content = new String(inputBuffer, "UTF-8");
                writer.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
</pre>

<h3>Second Sample</h3>

<pre>@WebFilter(urlPatterns = "/*")
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
        originalResponse.getWriter().write(formattedFile);
    }

    private String formatFile(String initialFile) {
        Pattern prePattern = Pattern.compile("");
        Matcher preMather = prePattern.matcher(initialFile);
        StringWriter output = new StringWriter();
        Integer lastEnd = 0;

        while(preMather.find()) {
            String formattedSnippet = formatSnippet(preMather.group());
            output.write(initialFile.substring(lastEnd, preMather.start()));
            output.write(formattedSnippet);
            lastEnd = preMather.end();
        }

        return output.toString();
    }

    private String formatSnippet(String group) {
        JavaSource source = new JavaSourceParser().parse(group);
        StringWriter destination = new StringWriter();

        JavaSource2HTMLConverter converter = new JavaSource2HTMLConverter();
        try {
            converter.convert(source, JavaSourceConversionOptions.getDefault(), destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination.toString();
    }

    @Override
    public void destroy() {

    }
}</pre>

</body>
</html>