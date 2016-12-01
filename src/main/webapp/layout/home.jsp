<%-- 
    Document   : home
    Created on : May 21, 2016, 12:17:17 AM
    Author     : Lakshitha
--%>
<style>
    #version{
        height: 20px;
        width: 100px;
        position:fixed;
        bottom:5px;
        right:5px;
    }
</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link  href="https://fonts.googleapis.com/css?family=Reenie+Beanie:regular" rel="stylesheet" type="text/css"> 
<link href="${pageContext.request.contextPath}/external/css/sticky.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/external/js/main/menu.js' />"></script>
<!DOCTYPE html>

<div class="component-bg container">
    <div class="row mar-b-30"> 
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
            <div class="faq-square">
                <div class="icon-wrap ico-bg round-five wow pulse" data-wow-duration="2s" data-wow-delay=".8s">
                    <i class="fa fa-tags"></i>
                </div>
                <div class="content">
                    <h3 class="title wow flipInX">${bugAmount} Bugs</h3>
                    <a href="bug/onlyMe">
                        <p>${bugDescription}</p> 
                    </a>
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
            <div class="faq-square">
                <div class="icon-wrap ico-bg round-five wow pulse" data-wow-duration="2s" data-wow-delay=".5s">
                    <i class="fa fa-code"></i>
                </div>
                <div class="content">
                    <h3 class="title wow flipInX">${taskAmount} Tasks</h3>
                    <a href="task/onlyMe">
                        <p>${taskDescription}</p>
                    </a>
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
            <div class="faq-square">
                <div class="icon-wrap ico-bg round-five wow pulse" data-wow-duration="2s" data-wow-delay=".2s">
                    <i class="fa fa-desktop"></i>
                </div>
                <div class="content">
                    <h3 class="title wow flipInX">Release</h3>
                    <p> ${description}</p>
                </div>
            </div>
        </div>
    </div>
    <section class="tab wow fadeInLeft">
        <header class="panel-heading tab-bg-dark-navy-blue">
            <ul class="nav nav-tabs nav-justified ">
                <li class="active">
                    <a data-toggle="tab" href="#news">
                        Progress
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#events">
                        History
                    </a>
                </li>
                <li class="">
                    <a data-toggle="tab" href="#notice-board">
                        Notice board
                    </a>
                </li>
            </ul>
        </header>
        <div class="panel-body">
            <div class="tab-content tasi-tab">
                <div id="news" class="tab-pane fade in active">
                    <article class="media">
                        <a class="pull-left thumb p-thumb">
                            <img src="external/img/bugTotal.png" alt="">
                        </a>
                        <div class="media-body b-btm">
                            <a href="#" class=" p-head">
                                Total <b>${totalReported}</b> Bugs Reported
                            </a>
                            <p>
                                ${individualReports}
                            </p>
                        </div>
                    </article>
                    <article class="media">
                        <a class="pull-left thumb p-thumb">
                            <img src="external/img/bugFixed.png" alt="">
                        </a>
                        <div class="media-body b-btm">
                            <a href="#" class=" p-head">
                                Total <b>${totalComplete}</b> Bugs Resolved/Released
                            </a>
                            <p>
                                ${individualFixes}
                            </p>
                        </div>
                    </article>
                    <article class="media">
                        <a class="pull-left thumb p-thumb">
                            <img src="external/img/bugPending.png" alt="">
                        </a>
                        <div class="media-body b-btm">
                            <a href="#" class=" p-head">
                                Total <b>${totalPending}</b> Bugs to Resolve
                            </a>
                            <p>
                                ${individualPending}
                            </p>
                        </div>
                    </article>
                </div>
                <div id="events" class="tab-pane fade">
                    <article class="media">
                        <a class="pull-left thumb p-thumb">
                            <!--image goes here-->
                        </a>
                        <div class="media-body b-btm">
                            <a href="#" class="cmt-head">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                            </a>
                            <p>

                                <i class="fa fa-time">
                                </i>
                                1 hours ago
                            </p>
                        </div>
                    </article>
                    <article class="media">
                        <a class="pull-left thumb p-thumb">
                            <!--image goes here-->
                        </a>
                        <div class="media-body b-btm">
                            <a href="#" class="cmt-head">
                                Nulla vel metus scelerisque ante sollicitudin commodo
                            </a>
                            <p>

                                <i class="fa fa-time">
                                </i>
                                23 mins ago
                            </p>
                        </div>
                    </article>
                </div>
                <div id="notice-board" class="tab-pane fade">
                    <p>
                        Notice board goes here Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ablic jiener.
                    </p>
                    <p>
                        Notice board goes here Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ablic jiener.
                    </p>
                </div>
            </div>
        </div>
    </section>
</div>
<br>
<div class="component-bg container">
    <div class="row mar-b-30"> 
        <textarea id="description" class="form-control" rows="3"></textarea>
        <button id="stickIt" class="btn btn-info btn-block">Stick!</button>
        <br>
        <ul class="specialUList">
            <div id="notes">
                <c:forEach items="${notes}" var="note" >
                    <li id="${note.id}" class="specialList">
                        <a class="specialAnchor" href="#">
                            <p class="specialPara"> 
                                <c:out value="${note.description}" />
                            </p>
                        </a>
                    </li>  
                </c:forEach>
            </div>
        </ul>
    </div>
    <div>
        <label id="version"> Build 16.11.28 </label>
    </div>
</div>

<!--container end-->
