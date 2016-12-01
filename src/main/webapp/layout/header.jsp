<%-- 
    Document   : header
    Created on : May 21, 2016, 12:36:55 AM
    Author     : Lakshitha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<header class="head-section">
    <div class="navbar navbar-default navbar-static-top container">
        <div class="navbar-header">
            <button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse" type="button">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <c:choose>
                <c:when test="${name == null}">
                    <a class="navbar-brand" href="/">BUG<span>Manager</span></a>
                </c:when>
                <c:otherwise>
                    <a class="navbar-brand" href="/">Hello <span>${name}</span></a>
                </c:otherwise>
            </c:choose>

        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">               
                <li class="dropdown">
                    <a class="dropdown-toggle" data-close-others="false" data-delay="0" data-hover=
                       "dropdown" data-toggle="dropdown" href="#">Tasks <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<c:url value="/task/add" />">New Task</a>
                        </li>
                        <li>
                            <a href="<c:url value="/task/viewAll" />">View Tasks</a>
                        </li> 
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-close-others="false" data-delay="0" data-hover=
                       "dropdown" data-toggle="dropdown" href="#">Bugs <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<c:url value="/bug/add" />">New Bug</a>
                        </li>
                        <li>
                            <a href="<c:url value="/bug/viewFirst" />">View Bugs</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-close-others="false" data-delay="0" data-hover=
                       "dropdown" data-toggle="dropdown" href="index.html">Employee <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<c:url value="/employee/registration" />">New Employee</a>
                        </li>
                        <li>
                            <a  href="<c:url value="/employee/manage" />">Manage Employee</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a class="dropdown-toggle" data-close-others="false" data-delay="0" data-hover=
                       "dropdown" data-toggle="dropdown" href="index.html">Misc <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<c:url value="/menu/add" />">Menus</a>
                        </li>
                        <li>
                            <a  href="<c:url value="/page/add" />">Pages</a>
                        </li>
                    </ul>
                </li> 
<!--                <li>
                    <a class="dropdown-toggle" data-close-others="false" data-delay="0" data-hover=
                       "dropdown" data-toggle="dropdown" href="index.html">Memos <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<c:url value="/menu/add" />">Add Memos</a>
                        </li>
                        <li>
                            <a  href="<c:url value="/employee/manage" />">View Memos</a>
                        </li>
                        <li>
                            <a  href="<c:url value="/employee/manage" />">Memos Report</a>
                        </li>
                    </ul>
                </li>-->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-close-others="false" data-delay="0" data-hover=
                       "dropdown" data-toggle="dropdown" href="index.html">Release <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<c:url value="/release/newRelease" />">Test Release</a>
                        </li>
                        <li>
                            <a  href="<c:url value="/release/liveRelease" />">Live Release</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-close-others="false" data-delay="0" data-hover=
                       "dropdown" data-toggle="dropdown" href="index.html">Reports <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a  href="<c:url value="/report/releaseReport" />">Release Report</a>
                        </li>
                        <li>
                            <a  href="<c:url value="/report/customReport" />">Customized Report</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-close-others="false" data-delay="0" data-hover=
                       "dropdown" data-toggle="dropdown" href="index.html"><i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="<c:url value="" />">Change Password</a>
                        </li>
                        <li>
                            <a href="<c:url value="/logout" />">Logout</a>
                        </li>
                    </ul>
                </li>

                <li><input class="form-control search" placeholder=" Search" type="text"></li>
            </ul>
        </div>
    </div>
</header>
