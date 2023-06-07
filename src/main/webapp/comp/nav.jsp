<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div id="nav">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <style>
        header {
            background-color: appworkspace;
        }
    </style>

    <div class="header">
        <header class="p-3  border-bottom">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 link-body-emphasis text-decoration-none">
                        <img class="bi me-2" width="120" height="80" role="img" aria-label="Bootstrap"
                             src="https://upload.wikimedia.org/wikipedia/commons/3/3e/Logo_Rasmi_UMT.png">
                    </a>

                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="../dashboard.jsp" class="nav-link px-2 link-body-emphasis">Dashboard</a></li>
                        <li><a href="../reserve.jsp" class="nav-link px-2 link-body-emphasis">Reserve</a></li>
                        <li><a href="aboutus.jsp" class="nav-link px-2 link-body-emphasis">About-us</a></li>
                        <li><a href="help-support" class="nav-link px-2 link-body-emphasis">Help</a></li>
                    </ul>


                    <div class="dropdown text-end">
                        <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <%="Omar Alomory"%>
                        </a>
                        <ul class="dropdown-menu text-small">
                            <li><a class="dropdown-item" href="#">Edit Profile</a></li>

                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Sign out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
        // this script will change the nave label to the current page diplayed
        $(document).ready(function () {
            var currentFile = window.location.href.split('/').pop();

            $(".nav-link").each(function () {
                var linkHref = $(this).attr('href');

                if (linkHref === currentFile) {
                    $(this).addClass("link-secondary");
                    $(this).removeClass("link-body-emphasis");
                }
            });
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
</div>
