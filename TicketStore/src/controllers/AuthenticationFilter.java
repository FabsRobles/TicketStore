package controllers;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {
    private String[] protectedPaths = {"/home", "/cart", "/checkout", "/order-history", "/logout"};
    private String[] publicPaths = {"/login.jsp", "/register.jsp", "/"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();
        String referer = httpRequest.getHeader("Referer");

        System.out.println("FILTER - Request URI: " + requestURI);
        System.out.println("FILTER - Referer: " + referer);

        boolean isProtected = isPathProtected(requestURI);
        boolean isPublic = isPathPublic(requestURI);
        System.out.println("FILTER - isProtected: " + isProtected);
        System.out.println("FILTER - isPublic: " + isPublic);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        
        System.out.println(httpRequest.getContextPath());
        if (isProtected && !isLoggedIn) {
            // El usuario no está autenticado y trata de acceder a una ruta protegida
            System.out.println("FILTER - Usuario no autenticado");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        } else if (isLoggedIn && isPublic) {
            System.out.println("FILTER - Usuario autenticado");
            // El usuario ya está autenticado y está tratando de acceder a una página pública (como login)
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
        } else {
            System.out.println("FILTER - Acceso permitido");
            if(!isPublic && requestURI.contains(".jsp")) {
                String servlet = requestURI.replace(".jsp", "");
                System.out.println("FILTER - Redirigiendo a: " + servlet);
                httpResponse.sendRedirect(servlet);
                return;
            }

            // El usuario está autenticado o está accediendo a una página pública
            chain.doFilter(request, response);
        }
    }

    private boolean isPathProtected(String requestURI) {
        for (String path : protectedPaths) {
            if (requestURI.startsWith("/Carrito" + path)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPathPublic(String requestURI) {
        for (String path : publicPaths) {
            if(path.equals("/")) {
                return requestURI.equals("/Carrito/");
            }

            if (requestURI.startsWith("/Carrito" + path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {}
}
