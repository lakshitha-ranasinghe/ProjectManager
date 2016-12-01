<%-- 
    Document   : login
    Created on : May 28, 2016, 9:10:17 PM
    Author     : Lakshitha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    #java{
        height: 64px;
        width: 64px;
        position:fixed;
        bottom:10px;
        right:188px;
    }
    #springLogo{
        height: 64px;
        width: 64px;
        position:fixed;
        bottom:10px;
        right:104px;
    }
    #hibernateLogo{
        height: 64px;
        width: 64px;
        position:fixed;
        bottom:10px;
        right:20px;
    }
</style>
<!DOCTYPE html>
<header class="head-section">
    <div class="navbar navbar-default navbar-static-top container">
        <div class="navbar-header">
            <button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse" type="button">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">Bug<span>Manager</span></a>
        </div>       
    </div>
</header>
<form class="form-signin wow fadeInUp" action="login" method="POST">
    <h2 class="form-signin-heading">Login</h2>
    <div class="login-wrap">
        <input type="text" class="form-control" placeholder="Username" autofocus name="username" id="username">
        <input type="password" class="form-control" placeholder="Password" name="password" id="password">
        <label class="checkbox">
            <input type="checkbox" value="remember-me" name="remember-me" id="remember-me"> Remember me          
        </label>
        <button class="btn btn-lg btn-login btn-block" type="submit">Sign in</button>
    </div>
</form>
<div>
    <img class="imageModal-content" src="external/img/springLogo.png" id="springLogo">
    <img class="imageModal-content" src="external/img/hibernateLogo.png" id="hibernateLogo">
    <img class="imageModal-content" src="external/img/java8.png" id="java">
</div>