# BTM (Better Than Manual) Room Reservation System

A generic room reservation system.

## Conventions (~~Why Lab 8 is rubbish in my opinion~~)

### 1. Use `@WebServlet` annotations instead of `web.xml`

`web.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app ...>
  <servlet>
    <servlet-name>SomeServlet</servlet-name>
    <servlet-class>com.example.SomeServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>SomeServlet</servlet-name>
    <url-pattern>/login</url-pattern>
  </servlet-mapping>
</web-app>
```

is equivalent in `SomeServlet.java`

```java
@WebServlet("/login")
```

As such, if there are no specific reasons to use `web.xml`, don't. (Also, it overrides the annotations).

#### Bonus: Setting session timeout

```java
request.getSession().setMaxInactiveInterval(SECONDS);
```

## Addendum

- [How many Controllers do I need?](https://softwareengineering.stackexchange.com/questions/208114)
