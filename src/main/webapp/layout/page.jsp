<%-- 
    Document   : page
    Created on : May 20, 2016, 11:39:03 PM
    Author     : Lakshitha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bug Manager </title>
        <link rel="icon" href="${pageContext.request.contextPath}/external/img/Bug.ico">
        <link href="${pageContext.request.contextPath}/external/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/external/css/theme.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/external/css/bootstrap-reset.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/external/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/external/css/flexslider.css"/>
        <link href="${pageContext.request.contextPath}/external/assets/bxslider/jquery.bxslider.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/external/css/animate.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/external/assets/owlcarousel/owl.carousel.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/external/assets/owlcarousel/owl.theme.css">
        <link href="${pageContext.request.contextPath}/external/css/superfish.css" rel="stylesheet" media="screen">
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/external/css/component.css">
        <link href="${pageContext.request.contextPath}/external/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/external/css/build.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/external/css/style-responsive.css" rel="stylesheet" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/external/css/parallax-slider/parallax-slider.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/external/js/parallax-slider/modernizr.custom.28468.js">
        </script>

        <script src="${pageContext.request.contextPath}/external/js/jquery.js">
        </script>
        <script src="${pageContext.request.contextPath}/external/js/bootstrap.min.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/external/js/hover-dropdown.js">
        </script>
        <script defer src="${pageContext.request.contextPath}/external/js/jquery.flexslider.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/external/assets/bxslider/jquery.bxslider.js">
        </script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/external/js/jquery.parallax-1.1.3.js">
        </script>
        <script src="${pageContext.request.contextPath}/external/js/wow.min.js">
        </script>
        <script src="${pageContext.request.contextPath}/external/assets/owlcarousel/owl.carousel.js">
        </script>

        <script src="${pageContext.request.contextPath}/external/js/jquery.easing.min.js">
        </script>
        <script src="${pageContext.request.contextPath}/external/js/link-hover.js">
        </script>
        <script src="${pageContext.request.contextPath}/external/js/superfish.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/external/js/parallax-slider/jquery.cslider.js">
        </script>
        <link href="${pageContext.request.contextPath}/external/css/bootstrap-social.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/external/css/component.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/external/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/external/css/style-responsive.css" rel="stylesheet" />
        <script type="text/javascript">
            $(function () {

                $('#da-slider').cslider({
                    autoplay: true,
                    bgincrement: 100
                });

            });
        </script>
        <script src="${pageContext.request.contextPath}/external/js/common-scripts.js">
        </script>

        <script type="text/javascript">
            $(document).ready(function () {


                $('.bxslider1').bxSlider({
                    minSlides: 5,
                    maxSlides: 6,
                    slideWidth: 360,
                    slideMargin: 2,
                    moveSlides: 1,
                    responsive: true,
                    nextSelector: '#slider-next',
                    prevSelector: '#slider-prev',
                    nextText: 'Onward →',
                    prevText: '← Go back'
                });

            });
        </script>


        <script>
            $('a.info').tooltip();

            $(window).load(function () {
                $('.flexslider').flexslider({
                    animation: "slide",
                    start: function (slider) {
                        $('body').removeClass('loading');
                    }
                });
            });

            $(document).ready(function () {

                $("#owl-demo").owlCarousel({
                    items: 4

                });

            });

            $(document).ready(function () {
                $('ul.superfish').superfish();
            });

            new WOW().init();


        </script>
    </head>
    <body>
        <div id="header">
            <t:insertAttribute name="header" />
        </div>
        <div id="content">
            <t:insertAttribute name="body" />
        </div>
        <!--        <div id="footer">
        <%--<t:insertAttribute name="footer"/>--%>
    </div>-->
    </body>
</html>
